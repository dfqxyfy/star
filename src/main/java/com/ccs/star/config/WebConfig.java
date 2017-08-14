package com.ccs.star.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ccs on 2017/2/23.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/movie/**")
                //.addResourceLocations("file:F:/dd/");
                .addResourceLocations("file:F:/电影/国务卿女士/");
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:e:/ppMM/");
        super.addResourceHandlers(registry);
    }
}
