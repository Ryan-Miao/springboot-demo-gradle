package com.test.demo.domain.service.impl;

import com.test.demo.domain.dao.IUserDao;
import com.test.demo.domain.entity.UserTable;
import com.test.demo.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserDao userDao;

    @Transactional
    @Override
    public Integer insert(UserTable userTable) {
        userTable.setId(null);
        userTable.setCreateDate(new Date());
        userTable.setUpdateDate(new Date());

        return userDao.insert(userTable);
    }

    @Override
    public UserTable getById(Integer id) {
        return userDao.getById(id);
    }
}
