package com.qingtian.controller;

import com.qingtian.entity.FileInfo;
import com.qingtian.service.FileMapper;
import com.qingtian.util.FileStringUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/7/31
 */
@Controller
public class FileController {

    /**
     * 文件存储路径
     */
    @Value("${file.storePath}")
    private String UPLOADED_FOLDER;

    @Autowired
    private FileMapper fileMapper;

    /**
     * 跳转到文件列表页面
     *
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("fileList", fileMapper.getAll());
        return "list";
    }

    @GetMapping("/toUpload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "上传文件不能为空");
            return "redirect:uploadStatus";
        }

        try {
            // 判断文件路径是否存在，不存在则创建
            UPLOADED_FOLDER = FileStringUtil.mkdirs(UPLOADED_FOLDER);

            FileInfo fileInfo = initData(file);

            // 保存文件到本地
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileInfo.getFilePath());
            Files.write(path, bytes);

            // 保存信息到数据库
            fileMapper.saveFileInfo(fileInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("/download/{id}")
    public void downLoad(@PathVariable("id") Long id, HttpServletResponse response) {

        FileInfo fileInfo = fileMapper.selectById(id);
        String filePath = fileInfo.getFilePath();
        String fileName = fileInfo.getFileOriginalFileName() + "." + fileInfo.getFileType();
        try {
            response.reset();
            // 获取文件
            File file = new File(filePath);
            // 转码,防止输出到浏览器中文乱码
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            response.setCharacterEncoding("utf-8");
            //根据文件设置文件类型
            setContentTypeHeader(response, file);
            FileInputStream is = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            try {
                IOUtils.copy(is, os);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != os) {
                    os.close();
                }
                if (null != is) {
                    is.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


    private FileInfo initData(MultipartFile file) {

        FileInfo fileInfo = new FileInfo();
        // 文件重命名，防止名字冲突
        String fileName = FileStringUtil.genUUID();
        // 文件原名-带后缀
        String originalFilename = file.getOriginalFilename();
        // 文件原名-不带后缀
        String fileNameWithOutSuffix = FileStringUtil.getFileNameWithOutSuffix(originalFilename);
        // 文件类型
        String suffix = FileStringUtil.getSuffixByFileName(originalFilename);
        // 文件大小
        long size = file.getSize();
        // 文件存储路径
        StringBuilder sb = new StringBuilder();
        sb.append(UPLOADED_FOLDER).append(File.separator)
                .append(fileName).append(".")
                .append(suffix);
        String filePath = sb.toString();

        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(filePath);
        fileInfo.setFileOriginalFileName(fileNameWithOutSuffix);
        fileInfo.setFileSize(size);
        fileInfo.setFileType(suffix);
        fileInfo.setCreateTime(new Date());
        fileInfo.setCreateUser("mcrwayfun");

        return fileInfo;
    }

    /**
     * 根据文件mime设置响应的contentType
     */
    private void setContentTypeHeader(HttpServletResponse response, File file) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        response.setContentType(mimeTypesMap.getContentType(file.getPath()));
    }
}
