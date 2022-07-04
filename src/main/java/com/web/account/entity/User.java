package com.web.account.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/07/01 02:15
 */
public class User {
    int id;
    String username;
    double monthlimit;
    double yearlimit;

    public double getMonthlimit() {
        return monthlimit;
    }

    public void setMonthlimit(double monthlimit) {
        this.monthlimit = monthlimit;
    }

    public double getYearlimit() {
        return yearlimit;
    }

    public void setYearlimit(double yearlimit) {
        this.yearlimit = yearlimit;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", monthlimit=" + monthlimit +
                ", yearlimit=" + yearlimit +
                ", balance=" + balance +
                ", password='" + password + '\'' +
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
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(balance, user.balance) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, balance, password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    BigDecimal balance;
    String password;

}
