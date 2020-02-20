package com.community.community.service.impl;

import com.community.community.dao.CommentDao;
import com.community.community.dao.QuestionDao;
import com.community.community.dao.UserDao;
import com.community.community.dto.ReCommentDTO;
import com.community.community.enums.CommentTypeEnum;
import com.community.community.exception.CustomizeErrorCode;
import com.community.community.exception.CustomizeException;
import com.community.community.model.Comment;
import com.community.community.model.Question;
import com.community.community.model.User;
import com.community.community.service.ICommentService;
import com.community.community.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private QuestionDao questionDao;

    @Autowired
    private IQuestionService iQuestionService;

    @Resource
    private UserDao userDao;

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

    /**
     * 新增评论
     *
     * @param comment
     */
    @Override
    @Transactional
    public void insertComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FIND);
        }
        if ("".equals(comment.getType()) || CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment comment1 = commentDao.selectByParentId(comment.getParentId());
            if (comment1 == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FIND);
            }
            commentDao.insert(comment);
        } else {
            //回复问题
            Question question = questionDao.selectById(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTIONS_NOT_FIND);
            }
            commentDao.insert(comment);
            long commentCount = question.getCommentCount() == null ? 0L : question.getCommentCount() + 1L;
            question.setCommentCount(commentCount);
            iQuestionService.insertCommentCount(question);
        }
    }

    /**
     * 获取问题评论列表
     *
     * @param id
     * @return
     */
    @Override
    public List<ReCommentDTO> getCommentList(Long id) {
        List<Comment> comments = commentDao.selectByQId(id, CommentTypeEnum.QUESTION.getType());
        Set<Long> collect = comments.stream().map(comment -> comment.getUserId()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(collect);
        List<User> users = new ArrayList<>();
        for (Long userId : userIds) {
            User user = userDao.selectByUserIds(userId);
            users.add(user);
        }

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<ReCommentDTO> reCommentDTOS = comments.stream().map(comment -> {
            ReCommentDTO reCommentDTO = new ReCommentDTO();
            BeanUtils.copyProperties(comment, reCommentDTO);
            reCommentDTO.setUser(userMap.get(comment.getUserId()));
            return reCommentDTO;
        }).collect(Collectors.toList());

        return reCommentDTOS;
    }
}
