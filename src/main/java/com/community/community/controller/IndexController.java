package com.community.community.controller;

import com.community.community.Mapper.QuestionMapper;
import com.community.community.Mapper.UserMapper;
import com.community.community.dto.PaginationDTO;
import com.community.community.model.User;
import com.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    User user = userMapper.selectByToken(cookie.getValue());
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PaginationDTO paginationDTO = questionService.getQuestionList(page, pageSize);
        model.addAttribute("pageList",paginationDTO);
        return "index";
    }
}
