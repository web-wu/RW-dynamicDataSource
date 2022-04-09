package com.tabwu.service;

import com.tabwu.entity.User;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 14:48
 * @Description:
 */
public interface UserService {

    void add(User user);

    User getUserById(int id);
}
