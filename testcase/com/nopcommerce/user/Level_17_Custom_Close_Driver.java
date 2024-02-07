package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Level_17_Custom_Close_Driver extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;

    private String firstName, lastName, password, Email;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        Email = "abc"+ganarateNumber()+"@gmail.com";

        userHomePage = PageGenaratorManager.getUserHomePage(driver);

        log.info("Pre-Condition - Step_01: Navigate to Register page");
        userRegisterPage = userHomePage.clickToRegisterLink();

        log.info("Pre-Condition - Step_02: Input to first name text box is"+firstName);
        userRegisterPage.inputToFristnameTextbox(firstName);
        log.info("Pre-Condition - Step_03: Input to last name text box is"+lastName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        log.info("Pre-Condition - Step_04: Input to email text box is"+Email);
        userRegisterPage.inputToEmailTextbox(Email);
        log.info("Pre-Condition - Step_05: Input to password text box is"+password);
        userRegisterPage.inputToPasswordTextbox(password);
        log.info("Pre-Condition - Step_06: Input to confirm password text box is"+password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        log.info("Pre-Condition - Step_07: Click to Register button");
        userRegisterPage.clickToRegisterButton();
        log.info("Pre-Condition - Step_08: veryfy register sucess message");
        verifyEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        log.info("Pre-Condition - Step_09: Click to continue button");
        userHomePage = userRegisterPage.clickToContinueButton();

        log.info("Pre-Condition - Step_10: Navigate to login page");
        userLoginPage = userHomePage.clickToLoginLink();

        log.info("Pre-Condition - Step_11: Input to email text box is"+Email);
        userLoginPage.inputToEmailTextbox(Email);
        log.info("Pre-Condition - Step_12: Input to password text box is"+password);
        userLoginPage.inputToPasswordTextbox(password);

        log.info("Pre-Condition - Step_13: Click to login button");
        userLoginPage.clickToLoginButton();
        log.info("Pre-Condition - Step_14: veryfy my account link is displayed");
        verifyTrue(userHomePage.isMyAccountLinkDisplayed());

        log.info("Pre-Condition - Step_15: Navigate to My Account page");
        userCustomerInfoPage = userHomePage.ClickToMyAccountLink();
        log.info("Pre-Condition - Step_16: verify customer info page is displayed");
        verifyTrue(userCustomerInfoPage.isCustomerInfoPageDisplay());

    }

    @Test
    public void User_01_Register(){


    }

    @Test
    public void User_02_Login(){

    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }


}
