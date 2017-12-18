package com.test.demo.domain.dao;

import com.test.demo.domain.entity.UserTable;

/**
 * Created by Ryan Miao(http://www.cnblogs.com/woshimrf/)
 */
public interface IUserDao {

    /**
     * 创建一个用户。
     * @param userTable user信息，id将被忽略
     * @return id。
     */
    Integer insert(UserTable userTable);

    /**
     * 获取用户by id。
     */
    UserTable getById(Integer id);

}
