package com.community.community.controller;

import com.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeController {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model){
        if (e instanceof CustomizeException){
            model.addAttribute("meaage",e.getMessage());
        }else {
            model.addAttribute("message","服务器繁忙，请稍后尝试");
        }
        return new ModelAndView("error");
    }
}
