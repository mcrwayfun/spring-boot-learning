package com.qingtian.service;

import com.qingtian.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author mcrwayfun
 * @Description
 * @Date Created in 2018/4/18
 */
@Service
public class UserServiceImpl implements UserService{

    private static List<User> users;

    static {
        users = initUser();
    }

    private static List<User> initUser() {
        List<User> users = new ArrayList<>(10);
        users.add(new User(1L,"Mac","USA"));
        users.add(new User(2L,"HuaWei","CHINA"));
        users.add(new User(3L,"3Idiots","INDIA"));
        users.add(new User(4L,"Cartoon","JAPAN"));
        users.add(new User(5L,"TUHao","DUBAI"));
        return users;
    }

    @Override
    public List<User> getUser() {
        return users;
    }

    @Override
    public User findUserById(Long id) {
       for(User user:users){
           if(user.getId() == id){
               return user;
           }
       }
       return null;
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        int index = users.indexOf(user);
        users.add(index,user);
    }

    @Override
    public void deleteUserById(Long id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
            }
        }

    }
}
