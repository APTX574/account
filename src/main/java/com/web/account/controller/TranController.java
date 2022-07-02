package com.web.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.web.account.entity.Result;
import com.web.account.entity.Transaction;
import com.web.account.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author aptx
 * @date 2022/07/01 11:32
 */
@Controller
public class TranController {

    @Autowired
    TranService tranService;

    @RequestMapping(value = "/insert/outcome", method = RequestMethod.POST)
    @ResponseBody
    public String insertoutcome(@RequestBody String body){

        JSONObject jsonObject = JSONObject.parseObject(body);
        double account = jsonObject.getDouble("account");
        String type = jsonObject.getString("type"); ;
        String sort = jsonObject.getString("subtype");
        String beizhu = jsonObject.getString("beizhu");
        String location = jsonObject.getString("location");
        String way = jsonObject.getString("way");
        Date createTime = new Date();

        Date date = jsonObject.getDate("time");
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        String format = bf.format(date);
        String[] split = format.split("-");
        int year = Integer.parseInt(split[0]);
        int month= Integer.parseInt(split[1]);
        int day= Integer.parseInt(split[2]);


        Transaction newtrans = new Transaction();
        newtrans.setAccount(account);
        newtrans.setType(type);
        newtrans.setLocation(location);
        newtrans.setWay(way);
        newtrans.setBeizhu(beizhu);
        newtrans.setCreateTime(createTime);
        newtrans.setSort(sort);
        newtrans.setYear(year);
        newtrans.setMonth(month);
        newtrans.setDay(day);
        newtrans.setUserId(0);
        int result = tranService.addTran(newtrans);
        if(result >= 1){
            return "添加成功！";
        }else{
            return "添加失败！";
        }
    }

    @RequestMapping(value = "/insert/income", method = RequestMethod.POST)
    @ResponseBody
    public String insertincome(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);

        double account = jsonObject.getDouble("account");
        String type = jsonObject.getString("type"); ;
        String beizhu = jsonObject.getString("beizhu");
        String location = jsonObject.getString("location");
        String way = jsonObject.getString("way");
        Date createTime = new Date();

        Date date = jsonObject.getDate("time");
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        String format = bf.format(date);
        String[] split = format.split("-");
        int year = Integer.parseInt(split[0]);
        int month= Integer.parseInt(split[1]);
        int day= Integer.parseInt(split[2]);


        Transaction newtrans = new Transaction();
        newtrans.setAccount(account);
        newtrans.setType(type);
        newtrans.setLocation(location);
        newtrans.setWay(way);
        newtrans.setBeizhu(beizhu);
        newtrans.setCreateTime(createTime);
        newtrans.setYear(year);
        newtrans.setMonth(month);
        newtrans.setDay(day);
        newtrans.setUserId(1);

        int result = tranService.addTran(newtrans);
        if(result >= 1){
            return "添加成功！";
        }else{
            return "添加失败！";
        }
    }

    @RequestMapping(value = "/get/output", method = RequestMethod.POST)
    @ResponseBody
    public List<Transaction>getoutput( Transaction transaction ){

        return tranService.getTran(transaction,0);

    }

    @RequestMapping(value = "/get/income", method = RequestMethod.POST)
    @ResponseBody
    public List<Transaction> getincome( Transaction transaction ){

        return tranService.getTran(transaction,1);

    }
}
