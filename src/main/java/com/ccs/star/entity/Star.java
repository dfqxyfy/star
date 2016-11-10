package com.ccs.star.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccs on 2016/11/10.
 */
@Document(collection = "star")
public class Star {
    private Integer star;
    private String starName;
    private List<StarDetail> starDetail = new ArrayList<>();

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public List<StarDetail> getStarDetail() {
        return starDetail;
    }

    public void setStarDetail(List<StarDetail> starDetail) {
        this.starDetail = starDetail;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }
}
