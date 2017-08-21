package com.ccs.dogcat.service;

import com.ccs.dogcat.entity.BasicDogCatItem;

import java.util.List;

/**
 * Created by ccs on 2016/11/9.
 */
public interface DogcatService {
    List<BasicDogCatItem> findStar(Integer num) ;

    void init();

}
