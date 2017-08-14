package com.ccs.star.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ccs on 2017/6/3.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/movie/**").addResourceLocations("E:\\电影\\生活大爆炸\\10");
        registry.addResourceHandler(".js").addResourceLocations("classpath:/qrcode/");
        super.addResourceHandlers(registry);
    }

}