package com.zh.communicate.domain.model;

import com.zh.communicate.domain.BaseObject;

/**
 * 用户实体
 *
 * @author zh
 */
public class User extends BaseObject {


    private static final long serialVersionUID = 983600452143036649L;
    private String username;
    private String password;


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
