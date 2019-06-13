package com.leekoko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String LoginPage(){
        return "LoginPage";
    }

    @RequestMapping("/page/index")
    public String Index(Model model, String data, String account){
        model.addAttribute("data",data);
        model.addAttribute("account",account);
        return "Index";
    }

    @RequestMapping("/page/problemPage")
    public String problemPage(Model model, String id){
        model.addAttribute("chapterId",id);
        return "ProblemPage";
    }

}
