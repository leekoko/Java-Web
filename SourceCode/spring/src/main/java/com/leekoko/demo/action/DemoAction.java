package com.leekoko.demo.action;

import com.leekoko.demo.service.IDemoService;
import com.leekoko.mvcframework.annotation.GPAutowired;
import com.leekoko.mvcframework.annotation.GPController;
import com.leekoko.mvcframework.annotation.GPRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("query.json")
    public void query(HttpServletRequest req, HttpServletResponse resp, String name){
        String result = demoService.get(name);
    }


}
