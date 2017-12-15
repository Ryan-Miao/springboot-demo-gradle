package com.test.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryan Miao on 12/2/17.
 */
@Entity(name = "room")
public class RoomTable implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column
    private String comment;

    @Column(updatable = false)
    private Date createDate;

    @Column
    private Date updateDate;

//    @Version
//    private Long version;

    public RoomTable() {
    }

    public RoomTable(String name, String comment, Date createDate, Date updateDate) {
        this.name = name;
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


    public RoomTable(Integer id, String name, String comment, Date updateDate) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.updateDate = updateDate;
    }

    public RoomTable(Integer id, String name, String comment, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public String toString() {
        return "RoomTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
