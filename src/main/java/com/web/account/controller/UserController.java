package com.web.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.web.account.dao.UserMapper;
import com.web.account.entity.Result;
import com.web.account.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.jar.JarEntry;

/**
 * @author aptx
 * @date 2022/07/01 22:39
 */
@Controller
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String login(@RequestBody String body) {
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if (userMapper.getUserByName(username).getPassword().equals(password)) {
            return Result.newSuccessfulResult("登录成功");
        } else {
            return Result.newFailedResult("登录失败");
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public String register(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.addUser(user);
        return Result.newSuccessfulResult("注册成功");
    }

}
