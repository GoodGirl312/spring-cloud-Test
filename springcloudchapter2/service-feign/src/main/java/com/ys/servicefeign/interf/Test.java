package com.ys.servicefeign.interf;

import com.ys.servicefeign.controller.impl.HyTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",fallback = HyTest.class)
public interface Test {
    @RequestMapping(value = "/hi")
    String sayHi(@RequestParam(value = "name") String name);
}
