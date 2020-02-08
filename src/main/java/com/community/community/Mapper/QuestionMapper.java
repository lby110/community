package com.community.community.Mapper;

import com.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,tag,create_user,view_count,like_count,gmt_create,gmt_modified" +
            ") values(#{title},#{description},#{tag},#{createUser},#{viewCount},#{likeCount},#{gmtCreate},#{gmtModified})")
    void insertQuestion(Question question);

}
