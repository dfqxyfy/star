package com.ccs.star.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.UnknownHostException;

/**
 * Created by ccs on 2016/11/9.
 */
@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoDBConfiguration {
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
        //properties.setUsername("star");
        //properties.setPassword("star".toCharArray());
        properties.setPort(27017);

        System.out.println("aaaaaaaaaaaaaa");
    }
}