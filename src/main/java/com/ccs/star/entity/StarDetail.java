package com.ccs.star.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ccs on 2016/11/9.
 */
@Document(collection="starDetail")
public class StarDetail {
    private Integer order;
    private String attr;
    private String desc;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
