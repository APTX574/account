package com.web.account.dao;

import com.web.account.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.function.DoubleUnaryOperator;

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

    List<Transaction> getAll();

    Double getDaySum(int year,int  month, int day,int userId );
    Double getNowDaySum(int year,int  month, int day,int userId);
    Double getNowMonthSum(int year,int  month,int userId);
    Double getNowYearSum(int year,int userId);

    Double getPie(String type,int year,int month,int day,int userId);
    Double getPieSon(String type,int year,int month,int day,int userId,String sort);

    Integer deleteTran(int id);
    int updateTran(Transaction transaction);
    double sumin(double in);
    double  sumout(double out);
}
