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

    List<Transaction> getTranByYear(int year, int type, int sort);

    List<Transaction> getTranByMonth(int year, int month, int type, int sort);

    List<Transaction> getTranByDay(int year, int month, int day, int type, int sort);

    List<Transaction> getTranByType(int type, int sort);
}
