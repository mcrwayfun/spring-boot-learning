package com.qingtian;

import com.qingtian.entity.User;
import com.qingtian.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author mcrwayfun
 * @Description
 * @Date Created in 2018/05/12
 */
@Component
public class Demo implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // 插入5个用户
        repository.save(new User("Jack", "Bauer"));
        repository.save(new User("Chloe", "O'Brian"));
        repository.save(new User("Kim", "Bauer"));
        repository.save(new User("David", "Palmer"));
        repository.save(new User("Michelle", "Dessler"));

        // 查询用户列表
        log.info("Users found with findAll():");
        log.info("-------------------------------");
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }
        log.info("");

        // 通过id查询某个用户
        repository.findById(1L)
                .ifPresent(user -> {
                    log.info("User found with findById(1L):");
                    log.info("--------------------------------");
                    log.info(user.toString());
                    log.info("");
                });

        // 通过lastName查询用户
        log.info("User found with findByLastName('Bauer'):");
        log.info("--------------------------------------------");
        repository.findByLastName("Bauer").forEach(bauer -> {
            log.info(bauer.toString());
        });
        log.info("");
    }
}
