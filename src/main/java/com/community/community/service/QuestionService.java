package com.community.community.service;

import com.community.community.dto.PaginationDTO;

public interface QuestionService {
    PaginationDTO getQuestionList(Integer page, Integer pageSize);
}
