package com.qingtian.util;

import java.io.File;
import java.util.UUID;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 文件信息处理工具
 * @date Created in 2018/7/31
 */
public class FileStringUtil {

    /**
     * 获取文件名（不包含后缀）
     *
     * @param fileOriginalName
     * @return
     */
    public static String getFileNameWithOutSuffix(String fileOriginalName) {

        return fileOriginalName.substring(0, fileOriginalName.lastIndexOf("."));
    }


    /**
     * 获取文件后缀
     *
     * @param fileOriginalName
     * @return
     */
    public static String getSuffixByFileName(String fileOriginalName) {

        return fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
    }

    /**
     * 文件路径不存在则创建
     *
     * @param dir
     * @return
     */
    public static String mkdirs(String dir) {

        StringBuilder sb = new StringBuilder();
        sb.append(dir);
        File f = new File(sb.toString());
        if (!f.exists()) {
            f.mkdirs();
        }
        return sb.toString();
    }

    /**
     * 返回UUID串
     *
     * @return
     */
    public static String genUUID() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
