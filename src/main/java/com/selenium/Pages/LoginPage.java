package com.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage extends BaseClass {

    public LoginPage() {}

    public void loginToWebsite(String usr, String pass) throws InterruptedException {
        enterUsername(usr);
        enterPassword(pass);
        clickLoginButton();
        System.out.println(driver.getPageSource().contains("Login") ? "login failed" : " Login passed");
        //   driver.quit();
    }

    public void enterUsername(String user) throws InterruptedException {
        wait(1000);
        driver.findElement(By.name("username")).sendKeys("Admin");

    }

    public void enterPassword(String pass) throws InterruptedException {
        wait(1000);
        driver.findElement(By.name("password")).sendKeys("admin123");

    }

    public void clickLoginButton() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        wait(1000);
    }

    void wait(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }

    public void logoutOfWebsite(String usr, String pass) throws InterruptedException {
        loginToWebsite(usr, pass);
        wait(1000);
        logoutWebsite();
    }

    private void logoutWebsite() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li")).click();
        String strLogoutXpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/ul/li[4]/a";

        //hover and click logout */
        Actions action = new Actions(driver);
        WebElement mainMenu = driver.findElement(By.xpath(strLogoutXpath));
        action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath(strLogoutXpath))).click().build().perform();
        wait(1000);
        System.out.println(driver.getPageSource().contains("Login") ? "Logout successful!!": "Logout failed");
        driver.quit();
    }

    public void sideMenuHovering(String usr, String pass) throws InterruptedException {
        loginToWebsite(usr, pass);
        wait(1000);
        String cssSel = "ul[class='oxd-main-menu']";
        List<WebElement> myElements = driver.findElements(By.cssSelector(cssSel));
        //  int x = 0;
        //  String sxPath;
        int listLength = myElements.iterator().next().getText().split(System.getProperty("line.separator")).length;
        WebElement searchBtn=null;
        for (int i=1; i<=listLength; i++) {
            //    sxPath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li["+ (++x) +"]/a";
            searchBtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li["+ i +"]/a"));
            wait(100);
            Actions action = new Actions(driver);
            wait(100);
            action.moveToElement(searchBtn).perform();
        }
        wait(100);
        System.out.println((searchBtn.getText().equals("Buzz")) ? "Hovering PASS" : " Hovering FAILED");
        driver.quit();
    }

    public void forgotPasswordLink() throws InterruptedException {
        wait(1000);
        WebElement searchBtn = driver.findElement(By.cssSelector(".oxd-text.oxd-text--p.orangehrm-login-forgot-header"));
        wait(1000);
        System.out.println(searchBtn.getText());

        Actions action = new Actions(driver);
        // wait(3000);
        action.moveToElement(searchBtn);
        wait(3000);
        action.click().perform();
        wait(1000);
        System.out.println(driver.getCurrentUrl());
        if(driver.getPageSource().contains("Reset Password"))
            System.out.println("ForgotPassword link - PASS");
        else
            System.out.println("ForgotPassword link - FAIL");
        driver.quit();
    }

    public void openCloseSideMenuButton(String usr, String pass) throws InterruptedException{
        loginToWebsite(usr, pass);
        menuCollapse("//i[@class='oxd-icon bi-chevron-left']");
        menuCollapse("//i[@class='oxd-icon bi-chevron-right']");

        driver.quit();
    }


    private void menuCollapse(String cssButtonClass)throws InterruptedException{
        wait(1000);
        driver.findElement(By.xpath(cssButtonClass)).click();

        wait(1000);
        String sClassName= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]")).getAttribute("class");

        wait(1000);
        System.out.println("collapse="+sClassName);
        if(sClassName.contains("--collapse"))
            System.out.println("Menu --collapse PASS");
        else
            System.out.println("Menu OPEN PASS");
    }

    public void showHomePage() throws InterruptedException{
        if (driver.getTitle().equals("OrangeHRM")){

            System.out.println( "Orange HRM Tile - PASS");}
        else
            System.out.println("Orange HRM Title - FAILED");
        wait(3000);
        driver.quit();
    }

    private static void waitHere(int timeout, WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait (driver, java.time.Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleIs("OrangeHRM"));
    }
}



