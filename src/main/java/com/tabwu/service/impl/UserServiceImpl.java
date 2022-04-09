package com.tabwu.service.impl;

import com.tabwu.entity.User;
import com.tabwu.mapper.UserMapper;
import com.tabwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 14:52
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }


    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
