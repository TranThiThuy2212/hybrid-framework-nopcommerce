package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;


import java.util.Random;

public class Level_07_Switch_Page extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;
    private UserMyProductPageReviewObject userMyProductReviewPage;
    private UserAddressPageObject userAddressPage;
    private UserRewardPointObject userRewardPointPage;
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
    @Test
    public void User_02_Login(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(Email);
        userLoginPage.inputToPasswordTextbox(password);

        userLoginPage.clickToLoginButton();
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }
    @Test
    public void User_03_My_Account(){
        userCustomerInfoPage = userHomePage.ClickToMyAccountLink();
        Assert.assertTrue(userCustomerInfoPage.isCustomerInfoPageDisplay());
    }
    @Test
    public void User_04_Switch_Page(){
        userAddressPage = userCustomerInfoPage.openAddressPage(driver);
        userMyProductReviewPage = userAddressPage.openMyProductReviewPage(driver);
        userRewardPointPage = userMyProductReviewPage.openRewardPointPage(driver);
    }
    @Test
    public void User_05_Switch_Role(){

    }
    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
