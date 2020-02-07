package com.community.community.Mapper;

import com.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    User selectAllUser();
    @Insert("insert into user(name,token,account_id,gmt_create,gmt_modifted) values(#{name},#{token},#{accountId},#{gmtCreate},#{gmtModifted})")
    void insertUser(User user);
}
