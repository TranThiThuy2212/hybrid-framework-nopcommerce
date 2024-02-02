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

public class Level_09_Dynamic_Locator extends BaseTest {
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
    public void User_01_Register_Login(){
        userRegisterPage = userHomePage.clickToRegisterLink();

        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(Email);
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        userRegisterPage.clickToRegisterButton();
        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        userHomePage = userRegisterPage.clickToContinueButton();

        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(Email);
        userLoginPage.inputToPasswordTextbox(password);

        userLoginPage.clickToLoginButton();
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage = userHomePage.ClickToMyAccountLink();
        Assert.assertTrue(userCustomerInfoPage.isCustomerInfoPageDisplay());
    }
    @Test
    public void User_02_Switch_Page(){
        userAddressPage = userCustomerInfoPage.openAddressPage(driver);
        userMyProductReviewPage = userAddressPage.openMyProductReviewPage(driver);
        userRewardPointPage = userMyProductReviewPage.openRewardPointPage(driver);
    }
    @Test
    public void User_03_Dynamic_Locator_01(){
        userAddressPage = (UserAddressPageObject) userRewardPointPage.openPagesAtMyAccountByName(driver,"Addresses");
        userMyProductReviewPage = (UserMyProductPageReviewObject) userAddressPage.openPagesAtMyAccountByName(driver,"My product reviews");
        userRewardPointPage = (UserRewardPointObject) userMyProductReviewPage.openPagesAtMyAccountByName(driver,"Reward points");
    }
    @Test
    public void User_03_Dynamic_Locator_02(){
        userRewardPointPage.openPagesAtMyAccountByPageName(driver,"My product reviews");
        userMyProductReviewPage =PageGenaratorManager.getUserMyProductReviewPage(driver);

        userMyProductReviewPage.openPagesAtMyAccountByPageName(driver,"Addresses");
        userAddressPage= PageGenaratorManager.getUserAddressPage(driver);

    }


    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
