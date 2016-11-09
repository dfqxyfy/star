package com.ccs.star.service.impl;

import com.ccs.star.entity.StarDetail;
import com.ccs.star.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ccs on 2016/11/9.
 */
@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void find(){
        StarDetail sd = new StarDetail();

        mongoTemplate.insert(sd);
    }
}
