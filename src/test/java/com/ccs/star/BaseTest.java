package com.ccs.star;

import com.ccs.star.constant.Stars;
import com.ccs.star.entity.Person;
import com.ccs.star.entity.PersonD;
import com.ccs.star.entity.PersonDetail;
import com.ccs.star.util.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.List;

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
    public void initPerson(){
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
                        person.setName(p.replace("。","").replace("等",""));
                        mongoTemplate.insert(person);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertPersonDetail(){


        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"star"));

        int curPage = 1;
        int pageSize = 10;
        query.skip((curPage-1)*pageSize).limit(pageSize);
        List<Person> list = mongoTemplate.find(query,Person.class);
        while(list.size()>0){
            for(Person p:list){

                PersonD pp = new PersonD();
                PersonDetail detail = new PersonDetail();

                String str = HttpClient.get("https://wapbaike.baidu.com/item/"+p.getName(),null);
                //System.out.println(str);
                detail.setId(System.currentTimeMillis());
                detail.setDesc(str);
                pp.setPersonDetail(detail);

                mongoTemplate.insert(pp);
                mongoTemplate.insert(detail);
            }
            curPage ++;
            query.skip((curPage-1)*pageSize).limit(pageSize);
            list = mongoTemplate.find(query,Person.class);
        }
    }
}
