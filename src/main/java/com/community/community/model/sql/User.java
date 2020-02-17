package com.community.community.model.sql;

import lombok.Data;

import javax.persistence.Table;

@Table(name = "user")
@Data
public class User {
    private Long id;
    private String name;
    private String token;
    private String gmtCreate;
    private String gmtModifted;
    private String accountId;
    private String avatarUrl;
}
