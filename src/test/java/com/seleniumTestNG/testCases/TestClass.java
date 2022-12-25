package com.seleniumTestNG.testCases;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestClass {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
       // WebDriverManager.chromedriver().setup();
         driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @Test
    public void testTitle() {
        String actualTitle=driver.getTitle();
        String expectedTitle="OrangeHRM";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testCopyRightsLink(){
        try {

            String expectedTitle="OrangeHRM HR Software | Free & Open Source HR Software | HRMS | HRIS | OrangeHRM";
            Thread.sleep(500);
            driver.findElement(By.cssSelector("a[href='http://www.orangehrm.com']")).click();
            Thread.sleep(500);
            Set<String> handles = driver.getWindowHandles();
            List<String> ls = new ArrayList<String>(handles);
            String  parent = ls.get(0);
            String child = ls.get(1);
            driver.switchTo().window(child);
            Thread.sleep(500);
            String actualTitle= driver.getTitle();
            System.out.println("actualTitle--> "+actualTitle);
            Thread.sleep(500);
            Assert.assertEquals(actualTitle, expectedTitle);
            //  Thread.sleep(1000);
        }catch(Exception e){e.getMessage();}
    }

    @Test
    public void testLogin() {

            String usr="Admin"; String pwd = "admin123";
         //   String actualText;
            try {
                Thread.sleep(1000);
                driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(usr);
                Thread.sleep(500);
                driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
                Thread.sleep(500);
                String loginBtn = "//button[contains(@class,\"orangehrm-login-button\")]";

                driver.findElement(By.xpath(loginBtn)).click();
                Thread.sleep(1000);
                String actualText  = driver.getCurrentUrl();
            System.out.println("actualtext :" + actualText);
            Thread.sleep(1000);
            String expectedText="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

            Assert.assertEquals(actualText, expectedText);
            Thread.sleep(1000);
        }catch(Exception e){e.getMessage();}

    }

    @Test
    public void testForgotPasswordLink(){
        try {
            Thread.sleep(1000);
            WebElement searchBtn = driver.findElement(By.cssSelector(".orangehrm-login-forgot"));
            wait(2000);
            System.out.println(searchBtn.getText());
            String expectedText="Reset Password";
            String actualText=driver.findElement(By.xpath("//h6[contains(@class,'orangehrm-forgot-password-title']")).getText();
            Thread.sleep(1000);
            Assert.assertEquals(actualText, expectedText);

            Thread.sleep(1000);
        }catch(Exception e){e.getMessage();}
    }

    @Test
    public void openCloseSideMenuButton(){

        String usr="Admin"; String pwd = "admin123";
        String actualText;
        try {
            /******** login ***************/
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(usr);
            Thread.sleep(500);
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
            Thread.sleep(500);
            String loginBtn = "//button[contains(@class,\"orangehrm-login-button\")]";
            driver.findElement(By.xpath(loginBtn)).click();

            /******** Side Menu - COLLAPSE ***************/
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".oxd-icon.bi-chevron-left")).click();
            Thread.sleep(1000);
            String sClassName= driver.findElement(By.xpath("//div[@class='oxd-layout-container --collapse']")).getAttribute("class");
            /******** Assert - COLLAPSE ********/
            Assert.assertTrue(sClassName.contains("--collapse"));


            /******** Side Menu - OPEN ***************/
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".oxd-icon.bi-chevron-right")).click();

            Thread.sleep(1000);
             sClassName= driver.findElement(By.xpath("//div[@class='oxd-layout-container']")).getAttribute("class");

             /******** Assert - NO COLLAPSE ********/
            Assert.assertTrue((!sClassName.contains("--collapse")));
        }catch(Exception e){e.getMessage();}
    }

    @Test
    public void verifySearchFilter() {
        /******** login ***************/
        String usr="Admin"; String pwd = "admin123";
        String actualText;
         try {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(usr);
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
        Thread.sleep(500);
        String loginBtn = "//button[contains(@class,\"orangehrm-login-button\")]";
        driver.findElement(By.xpath(loginBtn)).click();

        /******** search menuItems containing letter "a" ***************/
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("a");
        Thread.sleep(1000);
        List<WebElement> myElements = driver.findElements(By.xpath("//ul[contains(@class,'oxd-main-menu')]"));
             Thread.sleep(1000);
        String actualTxt = myElements.get(0).getText();
             Thread.sleep(1000);
        System.out.println(actualTxt+ "\n");
        int listLength = myElements.iterator().next().getText().split(System.getProperty("line.separator")).length;

        /******** hover over the filtered menuItems containing letter "a" ***************/
        System.out.println(listLength);
        for (int i=1; i<=listLength; i++) {
            WebElement searchBtn = driver.findElement(By.xpath("(//li[@class='oxd-main-menu-item-wrapper'])["+ i +"]/a"));
            Thread.sleep(100);
            Actions action = new Actions(driver);
            Thread.sleep(100);
            action.moveToElement(searchBtn).perform();
        }
        /******** assert ***************/
        Thread.sleep(1000);
         String expectedTxt ="Admin\n" + "Leave\n" + "Performance\n" + "Dashboard\n" + "Maintenance";
        // String expectedTxt ="PIM\n" + "Performance";
        System.out.println(expectedTxt);
        Assert.assertEquals(actualTxt,expectedTxt);
        }catch(Exception e){e.getMessage();}

    }

    @Test
    public void testBrandBLogo() {

        String usr="Admin"; String pwd = "admin123";
        //   String actualText;
        try {
            /******** login ***************/
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(usr);
            Thread.sleep(500);
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
            Thread.sleep(500);
            String loginBtn = "//button[contains(@class,\"orangehrm-login-button\")]";

            driver.findElement(By.xpath(loginBtn)).click();
            Thread.sleep(1000);
            String actualText  = driver.getCurrentUrl();
           // System.out.println("actualtext :" + actualText);
            Thread.sleep(1000);
            String expectedText="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

            Assert.assertEquals(actualText, expectedText);
            Thread.sleep(1000);

            /******** click on OrangeHRM brand logo ***************/
            driver.findElement(By.xpath("//img[@alt='client brand banner']")).click();
            Thread.sleep(1000);
             actualText  = driver.getCurrentUrl();
          //  System.out.println("actualtext :" + actualText);
            Thread.sleep(1000);
             expectedText="https://www.orangehrm.com/";

            Assert.assertEquals(actualText, expectedText);
            Thread.sleep(1000);
        }catch(Exception e){e.getMessage();}

    }

    @Test
    public void testLogout(){

        String usr="Admin"; String pwd = "admin123";
        String actualText;
        try {
            /*******hover and click logout *********/
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(usr);
            Thread.sleep(500);
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
            Thread.sleep(500);
            String loginBtn = "//button[contains(@class,\"orangehrm-login-button\")]";
            driver.findElement(By.xpath(loginBtn)).click();

            /******* click on profile picture space for logout *********/
            Thread.sleep(1000);
            driver.findElement(By.xpath("//li[@class='oxd-userdropdown']")).click();
            String strLogoutXpath = "//a[normalize-space()='Logout']";


            /******* hover down list and click logout *********/
            Actions action = new Actions(driver);
            WebElement mainMenu = driver.findElement(By.xpath(strLogoutXpath));
            Thread.sleep(1000);
            action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath(strLogoutXpath))).click().build().perform();
            Thread.sleep(1000);
            System.out.println(driver.getPageSource().contains("Login") ? "Logout successful!!": "Logout failed");

            /*******Assert that in login page *********/
            Assert.assertTrue(driver.getPageSource().contains("Login"));
        }catch(Exception e){e.getMessage();}
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }


}
