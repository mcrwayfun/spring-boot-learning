package com.qingtian.controller;

import com.qingtian.entity.User;
import com.qingtian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author mcrwayfun
 * @create 2018-04-19 0:09
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        List<User> users = userService.getUser();
        return users;
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if (null == user) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/create",headers ="Accept=application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        User reUser = userService.findUserById(user.getId());
        return new ResponseEntity<>(reUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update",headers = "Accept=application/json")
    public ResponseEntity<String> update(@RequestBody User currentUser){
        User user = userService.findUserById(currentUser.getId());
        if(null == user){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setName(currentUser.getName());
        user.setCountry(currentUser.getCountry());
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        User user = userService.findUserById(id);
        if(null == user){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
