package com.ccs.dogcat.controller;

import com.ccs.dogcat.controller.BaseController;
import com.ccs.dogcat.service.DogcatService;
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
@RequestMapping("/dogcat/")
public class DogcatController extends BaseController {

    @Autowired
    private DogcatService dogcatService;

    @ResponseBody
    @RequestMapping("/list")
    public String findStar(Integer num) {

        return success(dogcatService.findStar(num));
    }
  @ResponseBody
    @RequestMapping("/init")
    public String init(Integer num) {
        dogcatService.init();
        return success(null);
    }


}
