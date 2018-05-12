package com.qingtian.service;

import com.qingtian.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mcrwayfun
 * @Description
 * @Date Created in 2018/4/18
 */
public interface UserService {

    List<User> getUser();

    User findUserById(Long id);

    void createUser(User user);

    void update(User user);

    void deleteUserById(Long id);


}
