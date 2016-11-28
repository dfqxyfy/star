package com.ccs.star.util;

/**
 * Created by ccs on 2016/11/9.
 */

import com.alibaba.fastjson.JSON;
import com.ccs.star.constant.Stars;
import com.ccs.star.entity.Person;
import com.ccs.star.entity.PersonDetail;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class InitMongoDbPerson {


    @Autowired
    private MongoProperties properties;
    @Autowired(required = false)
    private MongoClientOptions options;
    private Mongo mongo;
    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }
    @Bean
    public Mongo mongo() throws UnknownHostException {
        this.mongo = this.properties.createMongoClient(this.options,null);
        return this.mongo;
    }

    @PostConstruct
    public void getMongoProperties(){
        properties.setDatabase("stardb");
        properties.setHost("localhost");
        properties.setUsername("star");
        properties.setPassword("star".toCharArray());
        properties.setPort(27017);
    }



    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) throws Exception{

        InitMongoDbPerson spider = new InitMongoDbPerson();

        spider.properties = new MongoProperties();
        spider.getMongoProperties();
        spider.options = MongoClientOptions.builder().build();

        spider.mongo = spider.mongo();

        spider.mongoTemplate = new MongoTemplate(spider.mongo,"stardb");

        Person person = new Person();
        person.setStar(1);
        PersonDetail pd = new PersonDetail();
        pd.setId(1l);
        pd.setDesc("aaa");
        person.setPersonDetail(pd);
        spider.mongoTemplate.insert(pd);
        spider.mongoTemplate.insert(person);

        Query query = new Query();
        query.addCriteria(Criteria.where("star").is(1));
        List<Person> list = spider.mongoTemplate.find(query,Person.class);
        System.out.println("................................");
        System.out.println(list.size());
        String desc = list.get(0).getPersonDetail().getDesc();
        System.out.println(desc);
//        spider.initStar();
    }
}
