package com.community.community.dto;

import com.community.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
     * 作者
     */
    private String userId;

    /**
     * 浏览人数
     */
    private int viewCount;

    /**
     * 点赞人数
     */
    private int likeCount;

    /**
     * 新增时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 评论数
     */
    private Long commentCount;
}
