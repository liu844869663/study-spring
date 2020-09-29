package com.fullmoon.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.fullmoon.study.dao.UserMapper;
import com.fullmoon.study.entity.Persion;
import com.fullmoon.study.entity.User;
import com.fullmoon.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserInfo(Integer id) {
        try {
            Thread.sleep(99999999);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return userMapper.queryUserInfo(id);
    }

    @Override
    public List<User> queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public Integer insertUser(User user) {
        int result = userMapper.insertUser(user);
        return result > 0 ? user.getId() : -1;
    }

    @Override
    public String testGeneric(Persion persion) {
        persion = persion == null ? new Persion() : persion;
        return JSON.toJSONString(persion);
    }
}
