package cn.leekoko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringMvcController {

    @RequestMapping("/testPage")
    @ResponseBody
    public String testPage(){
        return "testPage";
    }
}
