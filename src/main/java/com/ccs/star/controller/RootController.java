package com.ccs.star.controller;

import com.ccs.star.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ccs on 2016/11/8.
 */
@RestController
public class RootController {

    public static final String PATH_ROOT = "/";

    @ResponseBody
    @RequestMapping("/home/*")
    public String welcome() {
        return "Welcome!";
    }
    @Autowired
    StarService starService;

    @ResponseBody
    @RequestMapping("/test")
    public String welcome2() {
        return "Welcome!";
    }

}