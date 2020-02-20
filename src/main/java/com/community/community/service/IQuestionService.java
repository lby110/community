package com.community.community.service;

import com.community.community.dto.PaginationDTO;
import com.community.community.dto.QuestionDTO;
import com.community.community.model.Question;

public interface IQuestionService {
    PaginationDTO getQuestionList(Integer page, Integer pageSize);

    PaginationDTO selectByUserId(Long id,Integer page,Integer pageSize);

    QuestionDTO selectById(String id);

    void insertQuestion(Question question);

    void updateQuestion(Question question);

    void addView(String id);

    void insertCommentCount(Question question);
}



