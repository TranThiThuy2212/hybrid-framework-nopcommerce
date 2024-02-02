package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Level_08_Switch_Role extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserLoginPageObject userLoginPage;
    private UserRegisterPageObject userRegisterPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;

    private String firstName, lastName, userPassword, userEmailAddress,adminPassword, adminEmailAddress;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);
        firstName="Tran";
        lastName="Thuy";
        userPassword ="123456";
        userEmailAddress = "abc"+ganarateNumber()+"@gmail.com";
        adminPassword="admin";
        adminEmailAddress="admin@yourstore.com";
        userHomePage = PageGenaratorManager.getUserHomePage(driver);

        userRegisterPage = userHomePage.clickToRegisterLink();

        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(userEmailAddress);
        userRegisterPage.inputToPasswordTextbox(userPassword);
        userRegisterPage.inputToConfirmPasswordTextbox(userPassword);

        userRegisterPage.clickToRegisterButton();
        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        userHomePage = userRegisterPage.clickToContinueButton();

    }

    @Test
    public void Role_01_User_To_Admin(){
        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
        userCustomerInfoPage=userHomePage.ClickToMyAccountLink();
        userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

        userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
        adminLoginPage=PageGenaratorManager.getAdminLoginPage(driver);
        adminDashboardPage=adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());
        adminLoginPage=adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
    }
    @Test
    public void Role_02_Admin_To_User(){
        userHomePage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
        userHomePage=PageGenaratorManager.getUserHomePage(driver);

        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage=userHomePage.ClickToMyAccountLink();
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
