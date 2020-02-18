package com.community.community.model;

import lombok.Data;

import javax.persistence.Table;

@Table(name = "user")
@Data
public class User {
    /**
     * 用户主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 修改时间
     */
    private String gmtModifted;

    /**
     *
     */
    private String accountId;

    /**
     * 头像路径
     */
    private String avatarUrl;
}
