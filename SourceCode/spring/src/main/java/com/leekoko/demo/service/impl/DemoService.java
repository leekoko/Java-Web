package com.leekoko.demo.service.impl;

import com.leekoko.demo.service.IDemoService;
import com.leekoko.mvcframework.annotation.GPRequestParam;
import com.leekoko.mvcframework.annotation.GPService;

@GPService
public class DemoService implements IDemoService{

    public String get(@GPRequestParam("name") String name) {
        return "This is result";
    }
}
