package com.ccs.star.util;

/**
 * Created by ccs on 2016/11/9.
 */

import com.alibaba.fastjson.JSON;
import com.ccs.star.constant.Stars;
import com.ccs.star.entity.Star;
import com.ccs.star.entity.StarDetail;
import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseInitDogcatMongoDb<T> {
    @Autowired
    protected MongoProperties properties ;
    @Autowired(required = false)
    protected MongoClientOptions options;
    protected Mongo mongo;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }

    @Bean
    public Mongo mongo() throws UnknownHostException {
        this.mongo = this.properties.createMongoClient(this.options, null);
        return this.mongo;
    }

    @PostConstruct
    public void getMongoProperties() {
        properties.setDatabase("stardb");
        properties.setHost("localhost");
        //properties.setUsername("star");
        //properties.setPassword("star".toCharArray());
        properties.setPort(27017);
    }


    private ChromeDriverService service;
    private WebDriver driver;

    protected WebDriver getDriver() {
        return driver;
    }

    public BaseInitDogcatMongoDb() {
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("D:/program/chromedriver.exe"))
                .usingAnyFreePort().build();
        try {
            service.start();
            driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAndStopService() {
        driver.quit();
        service.stop();
    }

    public abstract List<T> spideDetail(String url);

    public void sprideAndInsertData(String url) {
        final List<T> ts = spideDetail(url);
        for (T t : ts) {
            mongoTemplate.insert(t);
        }
    }




}
