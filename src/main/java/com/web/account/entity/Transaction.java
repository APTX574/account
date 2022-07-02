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
    BigDecimal account;
    int type;
    int sort;
    int year;
    int month;
    int day;
    Date createTime;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", account=" + account +
                ", type=" + type +
                ", sort='" + sort + '\'' +
                ", year=" + year +
                ", mount=" + month +
                ", day=" + day +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return id == that.id && userId == that.userId && type == that.type && year == that.year && month == that.month && day == that.day && Objects.equals(account, that.account) && Objects.equals(sort, that.sort) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, account, type, sort, year, month, day, createTime);
    }

    public int getId() {
        return id;
    }

    public Transaction setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Transaction setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public Transaction setAccount(BigDecimal account) {
        this.account = account;
        return this;
    }

    public int getType() {
        return type;
    }

    public Transaction setType(int type) {
        this.type = type;
        return this;
    }

    public int getSort() {
        return sort;
    }

    public Transaction setSort(int sort) {
        this.sort = sort;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Transaction setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Transaction setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public Transaction setDay(int day) {
        this.day = day;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Transaction setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
