package com.community.community.controller;

import com.community.community.Mapper.QuestionMapper;
import com.community.community.Mapper.UserMapper;
import com.community.community.model.Question;
import com.community.community.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/insertQuestion")
    public String insertQuestion(@Param("title") String title,
                                 @Param("description") String description,
                                 @Param("tag") String tag,
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

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                user = userMapper.selectByToken(cookie.getValue());
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }

        }
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
        questionMapper.insertQuestion(question);
        return "redirect:/";
    }
}
