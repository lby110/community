package com.community.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.community.community.model.Comment;

@Mapper
public interface CommentDao {
    int insert(@Param("pojo") Comment pojo);

    int insertSelective(@Param("pojo") Comment pojo);

    int insertList(@Param("pojos") List<Comment> pojo);

    int update(@Param("pojo") Comment pojo);

    Comment selectByParentId(Long parentId);
}
