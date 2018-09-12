package com.ys.serviceribbon.controller;

import com.ys.serviceribbon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTwo {

    @Autowired
    TestService testService;

    @RequestMapping("/hi")
    public String sayHi(String name) {
        return testService.sayHi(name);
    }
}
