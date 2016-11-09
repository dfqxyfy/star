package com.ccs.star.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ccs on 2016/11/6.
 */

public enum Stars {

    //白羊座，金牛座，双子座，巨蟹座，狮子座，处女座，天秤座，天蝎座，射手座，摩羯座，水瓶座，双鱼座
    BAIYANG_STAR(1,"3.21","4.19",4,"白羊座"),
    JINNIU_STAR(2,"4.20","5.20",5,"金牛座"),
    SHUANGZI_STAR(3,"5.21","6.21",6,"双子座"),
    JUXIE_STAR(4,"6.22","7.22",7,"巨蟹座"),
    SHIZI_STAR(5,"7.23","8.22",8,"狮子座"),
    CHUNV_STAR(6,"8.23","9.22",9,"处女座"),
    TIANPING_STAR(7,"9.23","10.23",10,"天秤座"),
    TIANXIE_STAR(8,"10.24","11.22",11,"天蝎座"),
    SHESHOU_STAR(9,"11.23","12.21",12,"射手座"),
    MOXIE_STAR(10,"12.21","1.19",1,"摩羯座"),
    SHUIPING_STAR(11,"1.20","2.18",2,"水瓶座"),
    SHUANGYU_STAR(12,"2.19","3.20",3,"双鱼座"),
    ;

    private Integer num;
    private Integer pic;
    private String starDate;
    private String endDate;
    private String starName;

    private Stars(Integer num,String starDate,String endDate,Integer pic,String starName){
        this.num = num;
        this.pic = pic;
        this.starDate = starDate;
        this.endDate = endDate;
        this.starName = starName;
    }

    public String getStarName() {
        return starName;
    }

    public Integer getNum(Integer num){
        return num;
    }

    public Integer getNum() {
        return num;
    }

    public Integer getPic() {
        return pic;
    }

    public String getStarDate() {
        return starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public static Stars getStarByDate(Date d){

        Integer month = d.getMonth();
        Integer day = d.getDay();

        Stars star = getStarByNum(month);
        if(Long.valueOf(star.getStarDate().split("\\.")[1]) > day){
            return getStarByNum(month);
        }else{
            if(month-1<0)
                return getStarByNum(12);
            return getStarByNum(month-1);
        }

    }

    public static Stars getStarByNum(Integer num){
        if(num>12||num<1)
            return Stars.BAIYANG_STAR;
        Stars[] starses = Stars.values();
        for(Stars s:starses){
            if(s.getNum().equals(num)){
                return s;
            }
        }
        return Stars.BAIYANG_STAR;
    }

}
