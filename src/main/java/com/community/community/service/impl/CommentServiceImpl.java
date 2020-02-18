package com.community.community.service.impl;

import com.community.community.service.CommentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.community.community.model.Comment;
import com.community.community.dao.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    public int insert(Comment pojo){
        return commentDao.insert(pojo);
    }

    public int insertSelective(Comment pojo){
        return commentDao.insertSelective(pojo);
    }

    public int insertList(List<Comment> pojos){
        return commentDao.insertList(pojos);
    }

    public int update(Comment pojo){
        return commentDao.update(pojo);
    }
}
