package com.community.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.community.community.model.User;

@Mapper
public interface UserDao {
    int insert(@Param("pojo") User pojo);

    int insertSelective(@Param("pojo") User pojo);

    int insertList(@Param("pojos") List<User> pojo);

    int update(@Param("pojo") User pojo);

    User selectAllUser();

    String selectByAccountId(@Param("accountId") Long accountId);

    void updateUserByAccountId(@Param("user") User user);

    User selectByToken(@Param("token") String token);

    User selectByCreateUserId(@Param("createUser") String createUser);

    User selectByUserIds(@Param("userId") Long userId);
}
