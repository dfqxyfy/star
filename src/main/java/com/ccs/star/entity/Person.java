package com.ccs.star.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by ccs on 2016/11/21.
 */
@Document(collection = "person")
public class Person {

    //姓名
    private String name;
    //出生日期
    private Date birthday;
    //逝世日期
    private Date dieDay;
    //星座
    private Integer star;
    //国家
    private String nation;
    //人物简介
    private String personDesc;

    @DBRef(db="personDetail")
    private PersonDetail personDetail;

    public PersonDetail getPersonDetail() {
        return personDetail;
    }

    public void setPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getPersonDesc() {
        return personDesc;
    }

    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }

    public Date getDieDay() {
        return dieDay;
    }

    public void setDieDay(Date dieDay) {
        this.dieDay = dieDay;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
