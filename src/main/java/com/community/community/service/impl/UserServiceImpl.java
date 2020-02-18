package com.community.community.service.impl;

import com.community.community.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.community.community.model.User;
import com.community.community.dao.UserDao;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    public int insert(User pojo) {
        return userDao.insert(pojo);
    }

    public int insertSelective(User pojo) {
        return userDao.insertSelective(pojo);
    }

    public int insertList(List<User> pojos) {
        return userDao.insertList(pojos);
    }

    public int update(User pojo) {
        return userDao.update(pojo);
    }

    @Override
    public String selectByAccountId(Long id) {
        return userDao.selectByAccountId(id);
    }

    @Override
    public void updateUserByAccountId(User user) {
        userDao.updateUserByAccountId(user);
    }

    @Override
    public void insertUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User selectByToken(String value) {
        return userDao.selectByToken(value);
    }
}
