package com.qingtian.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/7/31
 */
public class FileInfo implements Serializable {

    /**
     * 主键，自增
     */
    private Long id;

    /**
     * 文件名（存储名）
     */
    private String fileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名（原名）
     */
    private String fileOriginalFileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    private Date createTime;

    private String createUser;

    /**
     * 无参构造函数，防止反序列化异常
     */
    public FileInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileOriginalFileName() {
        return fileOriginalFileName;
    }

    public void setFileOriginalFileName(String fileOriginalFileName) {
        this.fileOriginalFileName = fileOriginalFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
