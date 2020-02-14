package com.community.community.Mapper;

import com.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,tag,user_id,view_count,like_count,gmt_create,gmt_modified" +
            ") values(#{title},#{description},#{tag},#{userId},#{viewCount},#{likeCount},#{gmtCreate},#{gmtModified})")
    void insertQuestion(Question question);

    @Select("select id,title,description,tag,user_id,view_count,like_count,gmt_create,gmt_modified from question limit #{offSet},#{pageSize}")
    List<Question> selectAllQuestion(@Param("offSet") Integer offSet,@Param("pageSize") Integer pageSize);

    @Select("select count(1) from question")
    int count();

    @Select("select count(1) from question where user_id=#{userId}")
    int countByUserId(@Param("userId") Long userId);

    @Select("select * from question where user_id=#{id} limit #{offSet},#{pageSize}")
    List<Question> selectQuestionList(@Param("id") Long id,@Param("offSet") Integer offSet,@Param("pageSize") Integer pageSize);

    @Select("select * from  question where id=#{id}")
    Question selectById(@Param("id") String id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag} where id=#{id}")
    void updateQuestion(Question question);
}
