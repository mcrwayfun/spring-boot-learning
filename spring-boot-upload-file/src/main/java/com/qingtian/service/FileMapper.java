package com.qingtian.service;

import com.qingtian.entity.FileInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/7/31
 */
@Mapper
public interface FileMapper {

    @Insert("INSERT INTO fileInfo(fileName,filePath,fileType,fileOriginalFileName,fileSize,createTime,createUser) " +
            "VALUES(#{fileName},#{filePath},#{fileType},#{fileOriginalFileName},#{fileSize},#{createTime},#{createUser})")
    void saveFileInfo(FileInfo fileInfo);

    @Select("SELECT * FROM fileInfo WHERE id = #{id}")
    FileInfo selectById(Long id);

    @Select("SELECT * FROM fileInfo")
    List<FileInfo> getAll();
}
