package com.qingtian;

import com.qingtian.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author mcrwayfun
 * @Description
 * @Date Created in 2018/05/16
 */
@Mapper
public interface UserMapper {

    @Select("SELECT id,firstName,lastName FROM user WHERE lastName = #{lastName}")
    List<User> findByLastName(String lastName);
}
