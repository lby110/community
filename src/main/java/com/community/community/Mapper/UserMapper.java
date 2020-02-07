package com.community.community.Mapper;

import com.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    User selectAllUser();
    @Insert("insert into user(name,token,account_id,gmt_create,gmt_modifted) values(#{name},#{token},#{accountId},#{gmtCreate},#{gmtModifted})")
    void insertUser(User user);
    @Select("select account_id from user where account_id=${accountId} limit 1")
    boolean selectByAccountId(@Param("accountId") Long accountId);
    @Update("update user set token=#{token} where account_id=#{accountId}")
    void updateUserByAccountId(User user);
    @Select("select * from user where token=#{token}")
    User selectByToken(@Param("token") String token);
}
