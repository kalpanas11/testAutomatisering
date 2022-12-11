package com.selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    public  WebDriver driver;
    public  String sURL = "https://opensource-demo.orangehrmlive.com";


    public BaseClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(sURL);
    }
}
