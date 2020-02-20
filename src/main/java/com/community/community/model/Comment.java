package com.community.community.model;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "comment")
public class Comment {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 父类id
     */
    private Long parentId;

    /**
     *父类类型
     */
    private int type;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;

    /**
     * 评论内容
     */
    private String context;

}
