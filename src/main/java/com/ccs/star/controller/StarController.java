package com.ccs.star.controller;

import com.ccs.star.entity.Star;
import com.ccs.star.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chaichuanshi on 2016/11/11.
 */
@RestController
@RequestMapping("/star/")
public class StarController extends BaseController{

    @Autowired
    private StarService starService;

    @ResponseBody
    @RequestMapping("/findStar")
    public String findStar(Integer num) {
        Star star = starService.findStar(num);
        return success(star);
    }
}
