package com.community.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.community.community.model.Question;

@Mapper
public interface QuestionDao {
    int insert(@Param("pojo") Question pojo);

    int insertSelective(@Param("pojo") Question pojo);

    int insertList(@Param("pojos") List<Question> pojo);

    int update(@Param("pojo") Question pojo);
}
