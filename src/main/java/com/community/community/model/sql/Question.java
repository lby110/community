package com.community.community.model.sql;

import lombok.Data;

import javax.persistence.Table;

@Table(name = "question")
@Data
public class Question {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 标签
     */
    private String tag;

    /**
     * 作者id
     */
    private String userId;

    /**
     * 浏览人数
     */
    private String viewCount;

    /**
     * 点赞人数
     */
    private String likeCount;

    /**
     * 新增时间
     */
    private String gmtCreate;

    /**
     * 修改时间
     */
    private String gmtModified;

}
