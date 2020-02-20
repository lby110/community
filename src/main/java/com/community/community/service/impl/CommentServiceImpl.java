package com.community.community.service.impl;

import com.community.community.dao.CommentDao;
import com.community.community.dao.QuestionDao;
import com.community.community.enums.CommentTypeEnum;
import com.community.community.exception.CustomizeErrorCode;
import com.community.community.exception.CustomizeException;
import com.community.community.model.Comment;
import com.community.community.model.Question;
import com.community.community.service.ICommentService;
import com.community.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private QuestionDao questionDao;

    @Autowired
    private IQuestionService iQuestionService;

    public int insert(Comment pojo) {
        return commentDao.insert(pojo);
    }

    public int insertSelective(Comment pojo) {
        return commentDao.insertSelective(pojo);
    }

    public int insertList(List<Comment> pojos) {
        return commentDao.insertList(pojos);
    }

    public int update(Comment pojo) {
        return commentDao.update(pojo);
    }

    @Override
    @Transactional
    public void insertComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FIND);
        }
        if ("".equals(comment.getType()) || CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment comment1 = commentDao.selectByParentId(comment.getParentId());
            if (comment1==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FIND);
            }
            commentDao.insert(comment);
        }else {
            //回复问题
            Question question = questionDao.selectById(comment.getParentId());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTIONS_NOT_FIND);
            }
            commentDao.insert(comment);
            long commentCount = question.getCommentCount()==null? 0L:question.getCommentCount() + 1L;
            question.setCommentCount(commentCount);
            iQuestionService.insertCommentCount(question);
        }
    }
}
