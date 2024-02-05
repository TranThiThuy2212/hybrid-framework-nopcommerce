package com.nopcommerce.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;
import reportConfig.ExtentManager;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.Random;

public class Level_15_Extend_Screenshort extends BaseTest {
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


    }

    @Test
    public void User_01_Register(Method method){
        ExtentTestManager.startTest(method.getName(),"Register");
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_01: Navigate to Register page");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_02: Input to first name text box is"+firstName);
        userRegisterPage.inputToFristnameTextbox(firstName);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_03: Input to last name text box is"+lastName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_04: Input to email text box is"+Email);
        userRegisterPage.inputToEmailTextbox(Email);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_05: Input to password text box is"+password);
        userRegisterPage.inputToPasswordTextbox(password);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_06: Input to confirm password text box is"+password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_07: Click to Register button");
        userRegisterPage.clickToRegisterButton();
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_08: veryfy register sucess message");
        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        ExtentTestManager.getTest().log(Status.INFO,"Register - Step_09: Click to continue button");
        userHomePage = userRegisterPage.clickToContinueButton();

    }

    @Test
    public void User_02_Login(Method method){
        ExtentTestManager.startTest(method.getName(),"Login");
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_01: Navigate to login page");
        userLoginPage = userHomePage.clickToLoginLink();

        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_02: Input to email text box is"+Email);
        userLoginPage.inputToEmailTextbox(Email);
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_03: Input to password text box is"+password);
        userLoginPage.inputToPasswordTextbox(password);

        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_04: Click to login button");
        userLoginPage.clickToLoginButton();
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_05: veryfy my account link is displayed");
        Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_06: Navigate to My Account page");
        userCustomerInfoPage = userHomePage.ClickToMyAccountLink();
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step_07: verify customer info page is displayed");
        Assert.assertFalse(userCustomerInfoPage.isCustomerInfoPageDisplay());
    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
