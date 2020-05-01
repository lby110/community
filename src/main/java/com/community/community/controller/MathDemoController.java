package com.community.community.controller;

import com.community.community.model.Naizui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MathDemoController {
    @Autowired
    private NaizuiMapper naizuiMapper;

    @GetMapping("/index")
    public String getList(Model model){
        return "mix-line-bar";
    }
    @GetMapping("/getList")
    @ResponseBody
    public List<String> getList2(Model model){
        List<Naizui> naizuis = naizuiMapper.selectList2();
        List<String> arrayList=new ArrayList<>();
        for (Naizui item:naizuis
        ) {
            arrayList.add(item.getProductId());
        }

        return arrayList;
    }

}
