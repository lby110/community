package com.community.community.service;

import com.community.community.dto.ReCommentDTO;
import com.community.community.enums.CommentTypeEnum;
import com.community.community.model.Comment;

import java.util.List;

public interface ICommentService {


    public int insert(Comment pojo);

    public int insertSelective(Comment pojo);

    public int insertList(List<Comment> pojos);

    public int update(Comment pojo);

    /**
     * 新增评论
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 获取问题评论列表
     * @param id
     * @param typeEnum
     * @return
     */
    List<ReCommentDTO> getCommentList(Long id, CommentTypeEnum typeEnum);
}
