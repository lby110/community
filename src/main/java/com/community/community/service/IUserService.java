package com.community.community.service;

import com.community.community.model.User;

import java.util.List;

public interface IUserService {


    public int insert(User pojo);

    public int insertSelective(User pojo);

    public int insertList(List<User> pojos);

    public int update(User pojo);

    String selectByAccountId(Long id);

    void updateUserByAccountId(User user);

    void insertUser(User user);

    User selectByToken(String value);
}
