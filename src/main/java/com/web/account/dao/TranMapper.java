package com.web.account.dao;

import com.web.account.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author aptx
 * @date 2022/07/01 02:19
 */
@Mapper
public interface TranMapper {
    void insertTransaction(Transaction transaction);

    List<Transaction> getTranByYear(int year, String type, String sort);

    List<Transaction> getTranByMonth(int year, int month, String type, String sort);

    List<Transaction> getTranByDay(int year, int month, int day, String type, String sort);

    List<Transaction> getTranByType(int type, int sort);

    List<Transaction> getTran(Transaction transaction,int tp);

    double sumin(double in);
    double  sumout(double out);
}
