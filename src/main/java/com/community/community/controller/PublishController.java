package com.community.community.controller;

import com.community.community.dto.QuestionDTO;
import com.community.community.model.sql.Question;
import com.community.community.model.sql.User;
import com.community.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/insertQuestion")
    public String insertQuestion(@Param("title") String title,
                                 @Param("description") String description,
                                 @Param("tag") String tag,
                                 @Param("id")String id,
                                 HttpServletRequest request,
                                 Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "描述不能为空");
            return "publish";

        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("error", "用户未登录，请先登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setUserId(user.getId().toString());
        question.setGmtCreate(String.valueOf(System.currentTimeMillis()));
        question.setGmtModified(String.valueOf(System.currentTimeMillis()));
        if ("".equals(id)) {
            questionService.insertQuestion(question);
        }else {
            question.setId(Long.valueOf(id));
            questionService.updateQuestion(question);
        }
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") String id,
                       Model model) {
        QuestionDTO questionDTO = questionService.selectById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        return "publish";
    }

}
