package com.community.community.dto;


import com.community.community.model.User;
import lombok.Data;

@Data
public class ReCommentDTO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 评论内容
     */
    private String context;

    /**
     * 用户信息
     */
    private User user;

}
