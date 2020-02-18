package com.community.community.service.impl;

import com.community.community.dao.QuestionDao;
import com.community.community.dao.UserDao;
import com.community.community.dto.PaginationDTO;
import com.community.community.dto.QuestionDTO;
import com.community.community.exception.CustomizeErrorCode;
import com.community.community.exception.CustomizeException;
import com.community.community.model.Question;
import com.community.community.model.User;
import com.community.community.service.IQuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Resource
    private QuestionDao questionDao;

    @Resource
    private UserDao userDao;



    /**
     * 查询首页所有问题
     *
     * @return
     */
    public PaginationDTO getQuestionList(Integer page, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = questionDao.count();
        paginationDTO.setPaginationDTO(totalCount, page, pageSize);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        int offSet = pageSize * (page - 1);
        PageHelper.startPage(offSet,pageSize);
        List<Question> questions = questionDao.selectAllQuestion();
        QuestionDTO questionDTO = null;
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTO = new QuestionDTO();
            User user = userDao.selectByCreateUserId(question.getUserId());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTO.setGmtCreate(Long.valueOf(question.getGmtCreate()));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    /**
     * 用户通过id查询自己提出的问题
     *
     * @param id
     * @return
     */
    @Override
    public PaginationDTO selectByUserId(Long id, Integer page, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = questionDao.countByUserId(id);
        paginationDTO.setPaginationDTO(totalCount, page, pageSize);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        int offSet = pageSize * (page - 1);
        PageHelper.startPage(offSet,pageSize);
        List<Question> questions = questionDao.selectQuestionList(id);
        QuestionDTO questionDTO = null;
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTO = new QuestionDTO();
            User user = userDao.selectByCreateUserId(question.getUserId());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTO.setGmtCreate(Long.valueOf(question.getGmtCreate()));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    /**
     * 问题详情
     *
     * @param id
     * @return
     */
    @Override
    public QuestionDTO selectById(String id) {
        QuestionDTO questionDTO=new QuestionDTO();
        Question question = questionDao.selectById(id);
        if (question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FIND);
        }
        User user = userDao.selectByCreateUserId(question.getUserId());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        questionDTO.setGmtCreate(Long.valueOf(question.getGmtCreate()));
        return questionDTO;
    }

    @Override
    public void insertQuestion(Question question) {
        questionDao.insert(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    @Override
    public void addView(String id) {
        Question question = questionDao.selectById(id);
        question.setViewCount(question.getViewCount()+1);
        questionDao.addView(question);
    }
}
