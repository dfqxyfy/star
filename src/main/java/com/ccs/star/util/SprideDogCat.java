package com.ccs.star.util;

import com.ccs.dogcat.entity.BasicDogCatItem;
import com.mongodb.MongoClientOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccs on 2017/8/19.
 */

public class SprideDogCat extends BaseInitDogcatMongoDb<BasicDogCatItem> {
    @Override
    public List<BasicDogCatItem> spideDetail(String url) {
        getDriver().get(url);

        By item = By.tagName("name");
        final List<WebElement> elements = getDriver().findElement(By.className("zu-main-content-inner")).findElement(By.className("contents")).findElements(By.tagName("li"));
        List<BasicDogCatItem> resultList = new ArrayList<>();
        for(WebElement element:elements){

            System.out.println(element.getText());
//            if(true)
//                break;
            final WebElement title = element.findElement(By.className("title"));
            BasicDogCatItem dogCatItem = new BasicDogCatItem();
            dogCatItem.setDetailUrl(title.findElement(By.tagName("a")).getAttribute("href"));
            dogCatItem.setTitle(title.findElement(By.tagName("a")).getText().replaceAll("<em>","").replaceAll("</em>",""));
            final WebElement content = element.findElement(By.className("content")).findElement(By.className("entry-body")).findElement(By.className("entry-content"));
            dogCatItem.setDesc(content.findElement(By.className("summary")).getText().replaceAll("<em>","").replaceAll("</em>",""));
            dogCatItem.setAnswerUrl(content.findElement(By.className("visible-expanded")).findElement(By.tagName("a")).getAttribute("href"));
            resultList.add(dogCatItem);
        }
        return resultList;
    }

    public static void main(String[] args) throws UnknownHostException {
        SprideDogCat sprideDogCat = new SprideDogCat();
        sprideDogCat.properties = new MongoProperties();
        sprideDogCat.getMongoProperties();
        sprideDogCat.mongo();

        sprideDogCat.options = MongoClientOptions.builder().build();
        sprideDogCat.mongo = sprideDogCat.mongo();
        sprideDogCat.mongoTemplate = new MongoTemplate(sprideDogCat.mongo,"stardb");

        sprideDogCat.sprideAndInsertData("https://www.zhihu.com/search?type=content&q=%E7%8B%97+%E5%8F%AF%E7%88%B1");

    }


}
