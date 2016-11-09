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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void googleSearch(String url) {
        driver.get(url);
        List<WebElement> list = driver.findElement(By.className("info_box")).findElement(By.className("info"))
                .findElement(By.tagName("dl")).findElement(By.tagName("dt")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for(WebElement ele:list){
            //System.out.println(ele.findElement(By.tagName("li")).getText());
            System.out.println(ele.getText());
        }

    }

    public static void main(String[] args) {

        //Logger logger = Logger.getLogger(RemoteWebDriver.class.getName());
        Logger.getGlobal().setLevel(Level.OFF);
        String url ="http://www.xzw.com/astro/aries/";
        ChromeSpider spider = new ChromeSpider();
        spider.googleSearch(url);
    }
}
