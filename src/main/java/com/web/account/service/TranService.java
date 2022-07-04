package com.web.account.service;

import com.web.account.dao.TranMapper;
import com.web.account.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author aptx
 * @date 2022/07/01 02:53
 */
@Service
public class TranService {

    @Autowired
    TranMapper tranMapper;

    List<Transaction> getTranByMonth(int year, int month, int day, String type, String sort) {
        return tranMapper.getTranByDay(year, month, day, type, sort);
    }

    List<Transaction> getTranByMonth(int year, int month, String type, String sort) {
        return tranMapper.getTranByMonth(year, month, type, sort);
    }

    List<Transaction> getTranByDay(int year, String type, String sort) {
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
    public double nowDaySum(int year,int  month, int day,int userId) {
        return tranMapper.getNowDaySum(year,month, day,userId);
    }
    public double nowMonthSum(int year,int  month, int userId) {
        return tranMapper.getNowMonthSum(year,month,userId);
    }
    public double nowYearSum(int year, int userId) {
        return tranMapper.getNowYearSum(year,userId);
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

    public List<Map<String, Object>> getPie(Date start, Date end, int userId) {
        List<Map<String, Object>> ans = new ArrayList<>();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        assert start!=null&&end!=null;
        String format = bf.format(start);
        String[] split = format.split("-");
        int s_y = Integer.parseInt(split[0]);
        int s_m = Integer.parseInt(split[1]);
        int s_d = Integer.parseInt(split[2]);
        format = bf.format(end);
        String[] split2 = format.split("-");
        int e_y = Integer.parseInt(split2[0]);
        int e_m = Integer.parseInt(split2[1]);
        int e_d = Integer.parseInt(split2[2]);
        List<Map<String, Object>> pie;
        List<Map<String, Object>> pie2;
        if (e_y == s_y && e_m == s_m) {
            return getPie(s_y, s_m, -1, userId);
        } else if (e_y == s_y) {
            pie = getPie(e_y, s_m, -1, userId);
            for (int i = s_m + 1; i <= e_m; i++) {
                pie2 = getPie(e_y, i, -1, userId);
                List<Map<String, Object>> add = add(pie, pie2);
                pie.clear();
                pie.addAll(add);
            }
            return pie;
        }
        return null;

    }

    public List<Map<String, Object>> getPieSon(int year, int month, int day, int userId, String type) {
        String[] strs1 = {"个人就餐", "外卖", "零食", "饮料", "请客", "其他"};
        String[] strs2 = {"服饰装扮", "日用百货", "家居用品", "数码电器", "母婴用品", "宠物用品", "其他"};
        String[] strs3 = {"运动健身", "美容美发", "住房物业", "汽车保养", "酒店旅游", "书籍教育", "音乐影视", "医疗健康", "文娱休闲", "充值消费", "其他"};
        String[] strs4 = {"公共交通", "汽车加油", "机票", "船票", "景区门票", "其他"};
        String[] strs5 = {"购房", "购车", "其他"};
        String[] strs6 = {"转账", "红包", "公益", "保险", "信用借还", "理财", "银行存储", "其他"};
        String[] strs = null;
        switch (type) {
            case "餐饮":
                strs = strs1;
                break;
            case "购物":
                strs = strs2;
                break;
            case "生活":
                strs = strs3;
                break;
            case "出行":
                strs = strs4;
                break;
            case "大件":
                strs = strs5;
                break;
            case "其他":
                strs = strs6;
                break;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        if (strs == null) {
            return list;
        }
        for (String str : strs) {
            Map<String, Object> map1 = new HashMap<>();
            Double daySum;

            if (day == -1) {
                daySum = getDoubSon(type, year, month, userId, str);
            } else {
                daySum = tranMapper.getPieSon(type, year, month, day, userId, str);
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

    public List<Map<String, Object>> getPieSon(Date start, Date end, int userId, String type) {
        List<Map<String, Object>> ans = new ArrayList<>();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        String format = bf.format(start);
        String[] split = format.split("-");
        int s_y = Integer.parseInt(split[0]);
        int s_m = Integer.parseInt(split[1]);
        int s_d = Integer.parseInt(split[2]);
        format = bf.format(end);
        String[] split2 = format.split("-");
        int e_y = Integer.parseInt(split2[0]);
        int e_m = Integer.parseInt(split2[1]);
        int e_d = Integer.parseInt(split2[2]);
        List<Map<String, Object>> pie;
        List<Map<String, Object>> pie2;
        if (e_y == s_y && e_m == s_m) {
            return getPieSon(e_y, e_m, -1, userId, type);
        } else if (e_y == s_y) {
            pie = getPie(e_y, s_m, -1, userId);
            for (int i = s_m + 1; i <= e_m; i++) {
                pie2 = getPieSon(e_y, i, -1, userId, type);
                List<Map<String, Object>> add = add(pie, pie2);
                pie.clear();
                pie.addAll(add);
            }
            return pie;
        }
        return null;

    }

    public Integer deleteTran(int id) {
        return tranMapper.deleteTran(id);
    }

    public int updateTran(Transaction transaction) {
        return tranMapper.updateTran(transaction);
    }

    public double getDoub(int year, int month, int userId, String type) {
        Double d = tranMapper.getPie(type, year, month, -1, userId);
        return d == null ? 0 : d;

    }

    public double getDoubSon(String type, int year, int month, int userId, String sort) {
        Double d = tranMapper.getPieSon(type, year, month, -1, userId, sort);
        return d == null ? 0 : d;


    }

    public List<Map<String, Object>> add(List<Map<String, Object>> list1, List<Map<String, Object>> list2) {

        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        Map<String, Object> tmp = new HashMap<>();
        List<Map<String, Object>> ans = new ArrayList<>();
        for (Map<String, Object> map : list2) {
            Object name = map.get("name");
            Object value = map.get("value");
            tmp.put((String) name, value);
        }
        System.out.println("tmp1" + tmp);
        for (Map<String, Object> map : list1) {
            Object name = map.get("name");
            Object value = map.get("value");
            Object o = tmp.get(name);
            if (value == null && o == null) {
                tmp.put(String.valueOf(name), 0);
            } else if (value == null) {
                tmp.put((String) name, o);
            } else if (o == null) {
                tmp.put((String) name, value);
            } else {
                tmp.put((String) name, (double) o + (double) value);
            }
        }
        System.out.println("tmp" + tmp);
        for (String key : tmp.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", tmp.get(key));
            map.put("name", key);
            ans.add(map);
        }
        System.out.println("ans" + ans);
        return ans;
    }
}