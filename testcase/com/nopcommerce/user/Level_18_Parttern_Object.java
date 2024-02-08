package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.lang.reflect.Method;
import java.util.Random;

public class Level_18_Parttern_Object extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;

    private String firstName, lastName, password, Email, day,month,year;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        Email = "abc"+ganarateNumber()+"@gmail.com";
        day="10";
        month="December";
        year="2002";

        userHomePage = PageGenaratorManager.getUserHomePage(driver);


    }

    @Test
    public void User_01_Register(Method method){
        log.info("Register - Step_01: Navigate to Register page");
        userRegisterPage = userHomePage.clickToRegisterLink();

        log.info("Register - Step_02: Input to first name text box is"+firstName);
        userRegisterPage.inputToTextboxByID(driver,"FirstName",firstName);
        log.info("Register - Step_03: Input to last name text box is"+lastName);
        userRegisterPage.inputToTextboxByID(driver,"LastName",lastName);
        log.info("Register - Step_04: Input to email text box is"+Email);
        userRegisterPage.inputToTextboxByID(driver,"Email",Email);
        log.info("Register - Step_05: Input to password text box is"+password);
        userRegisterPage.inputToTextboxByID(driver,"Password",password);
        log.info("Register - Step_06: Input to confirm password text box is"+password);
        userRegisterPage.inputToTextboxByID(driver,"ConfirmPassword",password);

        userRegisterPage.selectToDropDownByName(driver,"DateOfBirthDay",day);
        userRegisterPage.selectToDropDownByName(driver,"DateOfBirthMonth",month);
        userRegisterPage.selectToDropDownByName(driver,"DateOfBirthYear",year);

        log.info("Register - Step_07: Click to Register button");
        userRegisterPage.clickToButtonByText(driver,"Register");
        log.info("Register - Step_08: veryfy register sucess message");
        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        log.info("Register - Step_09: Click to continue button");
        userHomePage = userRegisterPage.clickToContinueButton();




    }

    @Test
    public void User_02_Login(){
        log.info("Login - Step_01: Navigate to login page");
        userLoginPage = userHomePage.clickToLoginLink();

        log.info("Login - Step_02: Input to email text box is"+Email);
        userLoginPage.inputToTextboxByID(driver,"Email",Email);
        log.info("Login - Step_03: Input to password text box is"+password);
        userLoginPage.inputToTextboxByID(driver,"Password",password);

        log.info("Login - Step_04: Click to login button");
        userLoginPage.clickToButtonByText(driver,"Log in");
        userHomePage = PageGenaratorManager.getUserHomePage(driver);
        log.info("Login - Step_05: veryfy my account link is displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        log.info("Login - Step_06: Navigate to My Account page");
        userCustomerInfoPage = userHomePage.ClickToMyAccountLink();
        log.info("Login - Step_07: verify customer info page is displayed");
        Assert.assertTrue(userCustomerInfoPage.isCustomerInfoPageDisplay());
    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }


}
