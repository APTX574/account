package com.web.account.service;

import com.web.account.dao.TranMapper;
import com.web.account.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aptx
 * @date 2022/07/01 02:53
 */
@Service
public class TranService {
    @Autowired
    TranMapper tranMapper;

    List<Transaction> getTranByDate(int year, int month, int day, int type, int sort) {
        return tranMapper.getTranByDay(year, month, day, type, sort);
    }

    List<Transaction> getTranByDate(int year, int month, int type, int sort) {
        return tranMapper.getTranByMonth(year, month, type, sort);
    }

    List<Transaction> getTranByDate(int year, int type, int sort) {
        return tranMapper.getTranByYear(year, type, sort);
    }

    int addTran(Transaction transaction) {
        tranMapper.insertTransaction(transaction);
        return transaction.getId();
    }

}