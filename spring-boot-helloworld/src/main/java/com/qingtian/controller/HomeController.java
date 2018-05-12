package com.qingtian.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mcrwayfun
 * @create 2018-04-01 18:08
 **/
@RestController
public class HomeController {

    @RequestMapping("/helloworld")
    public String sayHelloWorld(){
        return "hello world!";
    }
}
