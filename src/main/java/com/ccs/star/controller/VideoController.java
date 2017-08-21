package com.ccs.star.controller;

import com.ccs.dogcat.controller.BaseController;
import com.ccs.star.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by chaichuanshi on 2016/11/11.
 */
@RestController
@RequestMapping("/video/")
public class VideoController extends BaseController {

    @Autowired
    private MovieService movieService;

    @ResponseBody
    @RequestMapping("/list")
    public String findStar(String videoName, ModelAndView vm, HttpServletResponse response) {


        List<String> list = movieService.getDirName(null);

        return success(list);
    }

}
