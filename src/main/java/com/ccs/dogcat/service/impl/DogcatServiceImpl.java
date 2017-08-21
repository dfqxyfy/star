package com.ccs.dogcat.service.impl;

import com.ccs.dogcat.service.DogcatService;
import com.ccs.dogcat.entity.BasicDogCatItem;
import com.ccs.star.util.SprideDogCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ccs on 2016/11/9.
 */
@Service
public class DogcatServiceImpl implements DogcatService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BasicDogCatItem> findStar(Integer num) {
        Query query = new Query();
        //query.addCriteria(Criteria.where("star").is(num));
        //return mongoTemplate.find(query,BasicDogCatItem.class);

        List<BasicDogCatItem> basicDogCatItem = null;
        basicDogCatItem = mongoTemplate.findAll(BasicDogCatItem.class);
        return basicDogCatItem;
    }

    @Override
    public void init() {
        SprideDogCat sprideDogCat = new SprideDogCat();
        sprideDogCat.sprideAndInsertData("https://www.zhihu.com/search?type=content&q=%E7%8B%97+%E5%8F%AF%E7%88%B1");
    }
}
