package com.test.demo.domain.entity;

import java.util.Date;

/**
 * Created by Ryan Miao(http://www.cnblogs.com/woshimrf/)
 */
public class UserTable {
    private Integer id;
    private String username;
    private String name;
    private Date createDate;
    private Date updateDate;

    public UserTable() {
    }

    public UserTable(Integer id, String username, String name, Date createDate, Date updateDate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public UserTable(String username, String name, Date createDate, Date updateDate) {
        this.username = username;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
