package com.sample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/10/15
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String sayHello() {
        return "Hello IBoot Framework";
    }
}
