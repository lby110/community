package com.community.community.service.impl;

import com.community.community.Mapper.QuestionMapper;
import com.community.community.Mapper.UserMapper;
import com.community.community.dto.PaginationDTO;
import com.community.community.dto.QuestionDTO;
import com.community.community.model.Question;
import com.community.community.model.User;
import com.community.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询首页所有问题
     *
     * @return
     */
    public PaginationDTO getQuestionList(Integer page, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = questionMapper.count();
        paginationDTO.setPaginationDTO(totalCount, page, pageSize);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        int offSet = pageSize * (page - 1);
        List<Question> questions = questionMapper.selectAllQuestion(offSet, pageSize);
        QuestionDTO questionDTO = null;
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTO = new QuestionDTO();
            User user = userMapper.selectByCreateUserId(question.getUserId());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTO.setGmtCreate(Long.valueOf(question.getGmtCreate()));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }
}
