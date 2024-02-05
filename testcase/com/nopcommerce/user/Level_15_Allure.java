package com.nopcommerce.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.Random;

public class Level_15_Allure extends BaseTest {
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

    @Description("Register to system")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_01_Register(){

        userRegisterPage = userHomePage.clickToRegisterLink();


        userRegisterPage.inputToFristnameTextbox(firstName);

        userRegisterPage.inputToLastnameTextbox(lastName);

        userRegisterPage.inputToEmailTextbox(Email);

        userRegisterPage.inputToPasswordTextbox(password);

        userRegisterPage.inputToConfirmPasswordTextbox(password);

        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        userHomePage = userRegisterPage.clickToContinueButton();

    }

    @Description("Login to system")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_02_Login(){

        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(Email);

        userLoginPage.inputToPasswordTextbox(password);

        userLoginPage.clickToLoginButton();

        Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage = userHomePage.ClickToMyAccountLink();

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
