package com.ccs.star.controller;

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
    @RequestMapping("/*")
    public String welcome() {
        return "Welcome!";
    }

}