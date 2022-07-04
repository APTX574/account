package com.web.account.service;

import com.web.account.dao.TranMapper;
import com.web.account.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aptx
 * @date 2022/07/01 02:53
 */
@Service
public class TranService {

    @Autowired
    TranMapper tranMapper;

    List<Transaction> getTranByDate(int year, int month, int day, String type, String sort) {
        return tranMapper.getTranByDay(year, month, day, type, sort);
    }

    List<Transaction> getTranByDate(int year, int month, String type, String sort) {
        return tranMapper.getTranByMonth(year, month, type, sort);
    }

    List<Transaction> getTranByDate(int year, String type, String sort) {
        return tranMapper.getTranByYear(year, type, sort);
    }


    public List<Transaction> getTran(Transaction transaction, int tp) {
        return tranMapper.getTran(transaction, tp);
    }

    public int addTran(Transaction transaction) {
        tranMapper.insertTransaction(transaction);
        return transaction.getId();
    }

    public double sumin(double in) {
        return tranMapper.sumin(in);
    }

    public double sumout(double out) {
        return tranMapper.sumout(out);
    }

    public Map<String, Object> getDaySum(int year, int month, int userId) {
        Map<String, Object> map = new HashMap<>();
        List<Double> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            Double daySum = tranMapper.getDaySum(year, month, i, userId);
            if (daySum == null) {
                daySum = 0.0;
            }
            list.add(daySum);
            list1.add(String.format("%d日", i));
        }
        map.put("day", list1);
        map.put("sum", list);
        return map;
    }
    public Map<String, Object> getPie(int year,int month,int day,int userId){
        if(userId == 0) {
            String[]  strs = {"餐饮消费", "购物消费", "生活消费", "出行消费", "大件消费", "其他消费"};

            Map<String, Object> map = new HashMap<>();
            List<Double> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            for (String str : strs) {
                Double daySum = tranMapper.getPie(str, year, month, day, userId);
                if (daySum == null) {
                    daySum = 0.0;
                }
                list.add(daySum);
                list1.add(str);
            }
            map.put("type", list1);
            map.put("sum", list);
            return map;
        }
        else {
            String[]  strs = {"工资","红包","借贷","退款","转账","提现","理财","其他"};

            Map<String, Object> map = new HashMap<>();
            List<Double> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            for (String str : strs) {
                Double daySum = tranMapper.getPie(str, year, month, day, userId);
                if (daySum == null) {
                    daySum = 0.0;
                }
                list.add(daySum);
                list1.add(str);
            }
            map.put("type", list1);
            map.put("sum", list);
            return map;
        }
    }
    public Integer deleteTran(int id) {
        return tranMapper.deleteTran(id);
    }

    public int updateTran(Transaction transaction) {
        return tranMapper.updateTran(transaction);
    }
}