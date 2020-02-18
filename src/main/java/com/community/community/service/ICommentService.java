package com.community.community.service;

import com.community.community.model.Comment;

import java.util.List;

public interface ICommentService {


    public int insert(Comment pojo);

    public int insertSelective(Comment pojo);

    public int insertList(List<Comment> pojos);

    public int update(Comment pojo);
}
