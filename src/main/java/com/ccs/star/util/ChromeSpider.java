package com.ccs.star.util;

/**
 * Created by ccs on 2016/11/9.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeSpider {

    private ChromeDriverService service;
    private WebDriver driver;

    public ChromeSpider() {
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("D:/program/chromedriver.exe"))
                .usingAnyFreePort().build();
        try {
            service.start();
            driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAndStopService() {
        driver.quit();
        service.stop();
    }


    // {@literal }
    public void googleSearch(String url) {
        driver.get(url);

        List<WebElement> list = driver.findElement(By.id("sm-maindata")).findElement(By.tagName("div"))
                .findElement(By.tagName("ul")).findElements(By.tagName("li"));
        List<Map<String, String>> baseList = new ArrayList<>();
        //for(int k = 0;k<list.size();k++){
        for (int k = 0; k < 11; k++) {
            Map<String, String> map = new HashMap<>();
            System.out.println("dealing with " + (k + 1) + " ��");
            WebElement element = list.get(k);
            //System.out.println(element.getText());
            WebElement lielement = element.findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("a"));
            String hrefUrl = lielement.getAttribute("href");
            System.out.println("���ӵ�url:" + hrefUrl);
            String imageUrl = lielement.findElement(By.tagName("img")).getAttribute("src");
            System.out.println("ͼƬurl��" + imageUrl);
            map.put("hrefUrl", hrefUrl);
            map.put("imageUrl", imageUrl);
            baseList.add(map);
        }
        for (Map<String, String> m : baseList) {
            driver.get(m.get("hrefUrl"));
            WebElement myEle = driver.findElement(By.id("mod-detail-title"));
            String energyName = myEle.findElement(By.tagName("h1")).getText();
            System.out.println("������֣�" + energyName);
            List<WebElement> trsElements = driver.findElement(By.id("mod-detail-attributes")).findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));

        }
    }

}
