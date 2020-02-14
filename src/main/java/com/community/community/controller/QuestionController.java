package com.community.community.controller;

import com.community.community.dto.QuestionDTO;
import com.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")String id,
                          Model model){
        QuestionDTO questionDTO = questionService.selectById(id);
        model.addAttribute("details",questionDTO);
        return "details";
    }
}
