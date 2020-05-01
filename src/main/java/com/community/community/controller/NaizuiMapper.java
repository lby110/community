package com.community.community.controller;

import com.community.community.model.Naizui;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NaizuiMapper {
    @Select("SELECT * FROM naizui  WHERE star_rating=5 and sum_total_votes >=100")
    public List<Naizui> selectList();
    @Select("SELECT * FROM naizui  WHERE star_rating=5 and sum_total_votes <=100 and sum_total_votes>=50  ORDER BY sum_total_votes+0 ASC")
    public List<Naizui> selectList2();
}
