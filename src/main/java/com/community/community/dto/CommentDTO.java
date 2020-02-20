package com.community.community.dto;

import lombok.Data;

@Data
public class CommentDTO {

    /**
     * 父类id
     */
    private Long parentId;

    /**
     *父类类型
     */
    private int type;

    /**
     * 评论内容
     */
    private String context;
}
