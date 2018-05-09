package com.qingtian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qingtian.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootRestCurdApplicationTests {

    /** 获取随机端口 */
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/users/");
    }

    @Test
    public void testGetUsers() {

        // 获取json字符
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        ArrayList<User> users =
                JSONObject.parseObject(response.getBody(), new TypeReference<ArrayList<User>>() {});
        assertEquals(5,users.size());
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserById(){

        ResponseEntity<String> response = restTemplate.getForEntity(base + "1", String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        User user = JSON.parseObject(response.getBody(), User.class);
        assertEquals("1",user.getId());
        System.out.println(user);
    }

    @Test
    public void testCreateUser(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT,"application/json");
        User user = new User(1000L,"mcrwayfun","China");
        HttpEntity httpEntity = new HttpEntity(user,headers);

        ResponseEntity<String> response =
                restTemplate.exchange(base + "create", HttpMethod.POST, httpEntity, String.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        User reUser = JSON.parseObject(response.getBody(), User.class);
        assertEquals(reUser.getName(),"mcrwayfun");
    }

    @Test
    public void testUpdateUser(){

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT,"application/json");
        User user = new User(1L,"mcrwayfun","China");

        HttpEntity httpEntity = new HttpEntity(user,headers);
        // 执行修改操作
        ResponseEntity<String> response =
                restTemplate.exchange(base + "update", HttpMethod.PUT, httpEntity, String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        // 查询刚才修改的user
        // 结果为：user已修改
        response = restTemplate.getForEntity(base + "1", String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        user = JSON.parseObject(response.getBody(), User.class);
        assertEquals("mcrwayfun",user.getName());
    }

    @Test
    public void testDeleteUserById(){

        // 删除操作
        restTemplate.delete(base + "1",String.class);

        // 查询刚才修改的user
        // 结果为：不存在
        ResponseEntity<String> response = restTemplate.getForEntity(base + "1", String.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

}
