package com.community.community.controller;

import com.community.community.dto.PaginationDTO;
import com.community.community.model.sql.User;
import com.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class profileController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{url}")
    public String profile(@PathVariable("url")String url,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                          Model model){
        User user =(User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(url)){
            //通过用户id查询用户自己的问题列表
            PaginationDTO paginationDTO = questionService.selectByUserId(user.getId(),page, pageSize);
            model.addAttribute("headName","我的提问");
            model.addAttribute("section","questions");
            model.addAttribute("pageList",paginationDTO);
        }
        if ("repies".equals(url)){
            model.addAttribute("section","repies");
            model.addAttribute("headName","最新回复");
            PaginationDTO paginationDTO = questionService.selectByUserId(user.getId(),page, pageSize);
            model.addAttribute("pageList",paginationDTO);
        }
        return "profile";
    }
}
