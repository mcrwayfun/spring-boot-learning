package com.qingtian.service;

import com.qingtian.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author mcrwayfun
 * @Description
 * @Date Created in 2018/05/11
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
}
