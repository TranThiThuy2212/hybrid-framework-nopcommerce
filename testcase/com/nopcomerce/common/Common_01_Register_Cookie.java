package com.nopcomerce.common;

import commons.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.nopCommerce.user.*;

import java.util.Random;
import java.util.Set;

public class Common_01_Register_Cookie extends BaseTest {
    private WebDriver driver;
    public static Set<Cookie> LoggedCookie;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;

    private String firstName, lastName, password, Email;


    @Parameters("browser")
    @BeforeTest(description="Create new common User for all Classes Test")
    public void User_01_Register(String browerName){
        driver =getBrowserDriver(browerName);
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        Email = "abc"+ganarateNumber()+"@gmail.com";

        userHomePage = PageGenaratorManager.getUserHomePage(driver);
        log.info("Register - Step_01: Navigate to Register page");
        userRegisterPage = userHomePage.clickToRegisterLink();

        log.info("Register - Step_02: Input to first name text box is"+firstName);
        userRegisterPage.inputToFristnameTextbox(firstName);
        log.info("Register - Step_03: Input to last name text box is"+lastName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        log.info("Register - Step_04: Input to email text box is"+Email);
        userRegisterPage.inputToEmailTextbox(Email);
        log.info("Register - Step_05: Input to password text box is"+password);
        userRegisterPage.inputToPasswordTextbox(password);
        log.info("Register - Step_06: Input to confirm password text box is"+password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        log.info("Register - Step_07: Click to Register button");
        userRegisterPage.clickToRegisterButton();
        log.info("Register - Step_08: veryfy register sucess message");
        verifyEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        log.info("Register - Step_09: Click to continue button");
        userHomePage = userRegisterPage.clickToContinueButton();
        LoggedCookie=userHomePage.getAllCookies(driver);
        for (Cookie cookie : LoggedCookie){
            System.out.println("==================");
            System.out.println("Cookie: " + cookie);
        }

    }


    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterTest
    public void afterClass(){
        driver.quit();
    }


}
