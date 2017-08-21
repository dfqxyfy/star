package com.ccs.dogcat.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ccs on 2017/8/19.
 */
@Document(collection = "basicDogCatItem")
public class BasicDogCatItem {
    private String title;
    private String detailUrl;
    private String desc;
    private String answerUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAnswerUrl() {
        return answerUrl;
    }

    public void setAnswerUrl(String answerUrl) {
        this.answerUrl = answerUrl;
    }
}
