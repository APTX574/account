package com.web.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.web.account.dao.TranMapper;
import com.web.account.dao.UserMapper;
import com.web.account.entity.Result;
import com.web.account.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    TranMapper tranMapper;

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

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/setmonthlimit")
    public String monthlimit(@RequestBody double monthlimit) {
        userMapper.monthlimit(monthlimit);
        return Result.newSuccessfulResult("1");
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/setyearlimit")
    public String yearlimit(@RequestBody double yearlimit) {
        userMapper.yearlimit(yearlimit);
        return Result.newSuccessfulResult("2");
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/leftmonthlimit")
    public String leftmonthlimit() {

        double monthlimit = userMapper.getmonthlimit();
        Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        String format = bf.format(date);
        String[] split = format.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        return Result.newSuccessfulResult(monthlimit-tranMapper.getNowMonthSum(year,month,0));
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/leftyearlimit")
    public String leftyearlimit() {
        double yearlimit = userMapper.getyearlimit();
        Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        String format = bf.format(date);
        String[] split = format.split("-");
        int year = Integer.parseInt(split[0]);
        return Result.newSuccessfulResult(yearlimit-tranMapper.getNowYearSum(year,0));
    }

}
