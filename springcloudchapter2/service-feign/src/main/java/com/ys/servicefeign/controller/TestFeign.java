package com.ys.servicefeign.controller;

import com.ys.servicefeign.interf.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeign {

    @Autowired
    Test test;

    @RequestMapping("/hi")
    public String sayHi(String name){
        return test.sayHi(name);
    }
}
