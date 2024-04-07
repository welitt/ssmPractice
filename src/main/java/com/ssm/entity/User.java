package com.ssm.entity;

import com.ssm.constant.UserInfoConstant;

public class User {
    private Integer id;
    private String name;
    private UserInfoConstant.ConstellationType constellationType;

    public User() {
    }

    public User(Integer id, String name, UserInfoConstant.ConstellationType constellationType) {
        this.id = id;
        this.name = name;
        this.constellationType = constellationType;
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

    public UserInfoConstant.ConstellationType getConstellationType() {
        return constellationType;
    }

    public void setConstellationType(UserInfoConstant.ConstellationType constellationType) {
        this.constellationType = constellationType;
    }
}
