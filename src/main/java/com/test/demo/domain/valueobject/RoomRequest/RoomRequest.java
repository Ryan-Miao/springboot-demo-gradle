package com.test.demo.domain.valueobject.RoomRequest;

import java.util.Date;

/**
 * Created by Ryan Miao on 12/14/17.
 */
public class RoomRequest {
    private Integer id;
    private String name;
    private String comment;

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

    @Override
    public String toString() {
        return "RoomRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
