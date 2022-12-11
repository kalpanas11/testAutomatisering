package com.selenium.TestCases;

import com.selenium.Pages.LoginPage;

public class LoginTest {
    private LoginPage loginPage;
    public LoginTest() throws InterruptedException{
        testShowHomePage();
//        testForgotPasswordLink();
//        testLogin();
//        testSideMenuHovering("Admin","admin123");
//        testOpenClose_SideMenuButton("Admin","admin123");
//        testLogout("Admin","admin123");
    }

    private void testShowHomePage() throws InterruptedException{
        loginPage = new LoginPage();
        loginPage.showHomePage();
        loginPage =null;
    }
    private void testLogin() throws InterruptedException{
        //positive testing
        testLogin("Admin","admin123");

        //negative testing
        testLogin("Admin","Admin123");
        testLogin("","Admin123");
        testLogin("Admin","");
        testLogin("","");
    }

    private void testLogin(String usr, String pass)throws InterruptedException{
        loginPage = new LoginPage();
        loginPage.loginToWebsite(usr,pass);
        loginPage =null;
    }

    private void testSideMenuHovering(String usr, String pass)throws InterruptedException{
        loginPage = new LoginPage();
        loginPage.sideMenuHovering(usr,pass);
        loginPage =null;
    }
    private void testForgotPasswordLink()throws InterruptedException{
        loginPage = new LoginPage();
        loginPage.forgotPasswordLink();
        loginPage =null;
    }

    private void testOpenClose_SideMenuButton(String usr, String pass)throws InterruptedException{
        loginPage = new LoginPage();
        loginPage.openCloseSideMenuButton(usr,pass);
        loginPage =null;
    }

    private void testLogout(String usr, String pass)throws InterruptedException{
        loginPage = new LoginPage();
        loginPage.logoutOfWebsite(usr,pass);
        loginPage =null;
    }

    public static void main(String []args) throws InterruptedException{
        LoginTest loginTest = new LoginTest();
    }
}
