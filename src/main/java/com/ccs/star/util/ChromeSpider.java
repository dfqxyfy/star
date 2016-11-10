package com.ccs.star.util;

/**
 * Created by ccs on 2016/11/9.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.ccs.star.config.MongoDBConfiguration;
import com.ccs.star.constant.Stars;
import com.ccs.star.entity.Star;
import com.ccs.star.entity.StarDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ComponentScan("com.ccs.star")
public class ChromeSpider {

    private ChromeDriverService service;
    private WebDriver driver;

    public ChromeSpider() {
//        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("D:/program/chromedriver.exe"))
//                .usingAnyFreePort().build();
//        try {
//            service.start();
//            driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void createAndStopService() {
        driver.quit();
        service.stop();
    }

    public List<StarDetail> spideDetail(String url) {
        driver.get(url);
        List<WebElement> list = driver.findElement(By.className("info_box")).findElement(By.className("info"))
                .findElement(By.tagName("dl")).findElement(By.tagName("dt")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        List<StarDetail> listDetail = new ArrayList<>();
        for(int i = 0 ;i<list.size();i++){
            WebElement ele = list.get(i);
            //System.out.println(ele.findElement(By.tagName("li")).getText());
            System.out.println(ele.getText().split("：")[0]+ele.getText().split("：")[1]);
            StarDetail starDetail = new StarDetail();
            starDetail.setOrder(i+1);
            starDetail.setAttr(ele.getText().split("：")[0]);
            starDetail.setDesc(ele.getText().split("：")[1]);
            listDetail.add(starDetail);
        }
        return listDetail;
    }

    public void spiderStarUrl(String url,List<String> starsListUrl){
        driver.get(url);
        List<WebElement> pageList = driver.findElement(By.id("menu")).findElement(By.className("astronav")).findElements(By.tagName("a"));
        for(WebElement ele:pageList){
            //System.out.println(ele.getAttribute("href"));
            starsListUrl.add(ele.getAttribute("href"));
        }
        //driver.close();
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public void initStar(){
        String basUrl = "http://www.xzw.com/astro/aries/";
        List<String> starsListUrl = new ArrayList<>();
        spiderStarUrl(basUrl,starsListUrl);
        System.out.println("..........."+ JSON.toJSONString(starsListUrl));
        for(int i = 0;i<starsListUrl.size();i++){
            Star star = new Star();
            star.setStar(Stars.getStarByNum(i+1).getNum());
            star.setStarName(Stars.getStarByNum(i+1).getStarName());
            List<StarDetail> listDetail = spideDetail(starsListUrl.get(i));
            star.setStarDetail(listDetail);
            mongoTemplate.insert(star);
        }

    }

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(ChromeSpider.class);

        //Logger logger = Logger.getLogger(RemoteWebDriver.class.getName());
        Logger.getGlobal().setLevel(Level.OFF);
        String url ="http://www.xzw.com/astro/aries/";
        ChromeSpider spider = new ChromeSpider();
        spider.mongoTemplate = ctx.getBean(MongoTemplate.class);

        Star star = new Star();
        star.setStar(1);
        spider.mongoTemplate.insert(star);
        //spider.googleSearch(url);
        //spider.initStar();
    }
}
