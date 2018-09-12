package com.ys.servicefeign.controller.impl;

import com.ys.servicefeign.interf.Test;
import org.springframework.stereotype.Component;

/**
 * 断路由
 */
@Component
public class HyTest implements Test{
    @Override
    public String sayHi(String name) {
        return "sorry "+name;
    }
}
