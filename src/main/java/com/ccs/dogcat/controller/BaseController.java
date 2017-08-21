package com.ccs.dogcat.controller;


import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public abstract class BaseController {

    private Logger logger;

    public BaseController() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @ExceptionHandler
    public ModelAndView handleValidateException(Exception exception, HttpServletResponse response,
                                                HttpServletRequest request, ModelMap modelMap) {
        logger.error("handler exception ...", exception);
        response.setHeader("Access-Control-Allow-Origin", "*");
        String callback = request.getParameter("callback");
        ModelAndView mv = new ModelAndView(exception.getMessage(), modelMap);
        return mv;
    }

    /**
     * 成功返回
     *
     * @param object
     * @return
     */
    protected String success(Object object) {
        final SerializerFeature[] features = {
                SerializerFeature.WriteMapNullValue, // 输出空值字段
                SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
                SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
                SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
                SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
        };
        Map<String,Object> map = new HashedMap();
        map.put("code",0);
        map.put("data",object);
        return JSON.toJSONString(map, features);
    }

}
