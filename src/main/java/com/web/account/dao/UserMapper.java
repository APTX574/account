package com.web.account.dao;

import com.web.account.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aptx
 * @date 2022/07/01 02:43
 */
@Mapper
public interface UserMapper {
    int addUser(User user);
    User getUserById(int id);
    User getUserByName(String username);
    int monthlimit(double ml);
    int yearlimit(double ml);

    Double getyearlimit();
    Double getmonthlimit();
}
