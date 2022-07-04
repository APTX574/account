package com.web.account.service;

import com.web.account.dao.TranMapper;
import com.web.account.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Transaction> getAll() {
        return tranMapper.getAll();
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

    public List<Map<String, Object>> getPie(int year, int month, int day, int userId) {
        String[] strs1 = {"餐饮", "购物", "生活", "出行", "大件", "其他"};
        String[] strs2 = {"工资", "红包", "借贷", "退款", "转账", "提现", "理财", "其他"};
        String[] strs;
        if (userId == 0) {
            strs = strs1;
        } else {
            strs = strs2;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (String str : strs) {
            Map<String, Object> map1 = new HashMap<>();
            Double daySum;

            if (day == -1) {
                daySum = getDoub(year, month, userId, str);
            } else {
                daySum = tranMapper.getPie(str, year, month, day, userId);
            }
            if (daySum == null) {
                daySum = 0.0;
            }
            map1.put("name", str);
            map1.put("value", daySum);
            list.add(map1);
        }
        return list;
    }
    public List<Map<String, Object>> getPieSon(int year, int month, int day, int userId,String type) {
        String[] strs1 = {"个人就餐","外卖","零食","饮料","请客","其他"};
        String[] strs2 = {"服饰装扮","日用百货","家居用品","数码电器","母婴用品","宠物用品","其他"};
        String[] strs3 ={"运动健身","美容美发","住房物业","汽车保养","酒店旅游","书籍教育","音乐影视","医疗健康","文娱休闲","充值消费","其他"};
        String[] strs4= {"公共交通","汽车加油","机票","船票","景区门票","其他"};
        String[] strs5 ={"购房","购车","其他"};
        String[] strs6 ={"转账","红包","公益","保险","信用借还","理财","银行存储","其他"};
        String[] strs = null;
        switch (type) {
            case"餐饮":strs = strs1; break;
            case"购物":strs = strs2; break;
            case"生活":strs = strs3; break;
            case"出行":strs = strs4; break;
            case"大件":strs = strs5; break;
            case"其他":strs = strs6; break;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        for (String str : strs) {
            Map<String, Object> map1 = new HashMap<>();
            Double daySum;

            if (day == -1) {
                daySum = getDoubSon(type,year, month, userId, str);
            } else {
                daySum = tranMapper.getPieSon(type, year, month, day, userId,str);
            }
            if (daySum == null) {
                daySum = 0.0;
            }
            map1.put("name", str);
            map1.put("value", daySum);
            list.add(map1);
        }
        return list;
    }
    public Integer deleteTran(int id) {
        return tranMapper.deleteTran(id);
    }

    public int updateTran(Transaction transaction) {
        return tranMapper.updateTran(transaction);
    }

    public double getDoub(int year, int month, int userId, String type) {
        Double sum = 0.0;
        for (int i = 1; i < 31; i++) {
            Double pie = tranMapper.getPie(type, year, month, i, userId);
            if (pie == null) {
                pie = 0.0;
            }
            sum += pie;
        }
        return sum;
    }

    public double getDoubSon(String type,int year, int month, int userId, String sort) {
        Double sum = 0.0;
        for (int i = 1; i < 31; i++) {
            Double pie = tranMapper.getPieSon(type, year, month, i, userId,sort);
            if (pie == null) {
                pie = 0.0;
            }
            sum += pie;
        }
        return sum;
    }
}