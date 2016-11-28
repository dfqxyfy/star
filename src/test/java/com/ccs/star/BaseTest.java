package com.ccs.star;

import com.ccs.star.constant.Stars;
import com.ccs.star.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

/**
 * Created by ccs on 2016/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles(profiles = {"test"})
public class BaseTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\workspace\\star\\src\\test\\starperson"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str = null;
        try {
            while((str = br.readLine()) != null){
                if(str.contains("：")){
                    String strName = str.split("：")[0];
                    Stars star = null;
                    for(Stars s : Stars.values()){
                        if(strName.contains(s.getStarName())){
                            star = s;
                            break;
                        }
                    }
                    String[] persons = str.split("：")[1].split("、");
                    for(String p:persons){
                        Person person = new Person();
                        person.setStar(star.getNum());
                        person.setName(p);
                        mongoTemplate.insert(person);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
