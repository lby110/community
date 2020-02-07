package com.community.community.model;

import javax.persistence.Table;

@Table(name = "user")
public class User {
    private Long id;
    private String name;
    private String token;
    private String gmtCreate;
    private String gmtModifted;
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModifted() {
        return gmtModifted;
    }

    public void setGmtModifted(String gmtModifted) {
        this.gmtModifted = gmtModifted;
    }
}
