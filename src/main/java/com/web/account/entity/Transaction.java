package com.web.account.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/07/01 02:11
 */
public class Transaction {
    int id;
    int userId;
    double account;
    String type;
    String sort;
    int year;
    int month;
    int day;
    String beizhu;
    String location;
    String way;
    Date createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && userId == that.userId && Double.compare(that.account, account) == 0 && type == that.type && sort == that.sort && year == that.year && month == that.month && day == that.day && Objects.equals(beizhu, that.beizhu) && Objects.equals(location, that.location) && Objects.equals(way, that.way) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, account, type, sort, year, month, day, beizhu, location, way, createTime);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", account=" + account +
                ", type=" + type +
                ", sort=" + sort +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", beizhu='" + beizhu + '\'' +
                ", location='" + location + '\'' +
                ", way='" + way + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

