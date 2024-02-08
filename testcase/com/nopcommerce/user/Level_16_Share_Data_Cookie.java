package com.nopcommerce.user;

import com.nopcomerce.common.Common_01_Register_Cookie;
import com.nopcomerce.common.Common_01_Register_New_Account;
import commons.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Level_16_Share_Data_Cookie extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;

    private String password, Email;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);
        userHomePage = PageGenaratorManager.getUserHomePage(driver);
        password= Common_01_Register_New_Account.password;
        Email = Common_01_Register_New_Account.Email;

        log.info("Pre-Condition - Step_01: Navigate to login page");
        userLoginPage = userHomePage.clickToLoginLink();

        log.info("Pre-Condition - Step_02: Set cookie and reload page");
        userLoginPage.setCookies(driver, Common_01_Register_Cookie.LoggedCookie);
        for (Cookie cookie : Common_01_Register_Cookie.LoggedCookie){
            System.out.println("==================");
            System.out.println("Cookie: " + cookie);
        }
        userLoginPage.refreshCurrentPage(driver);

        //log.info("Login - Step_03: verify my account link is displayed");
        //verifyTrue(userHomePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void Search_01_Empty_Data(){

    }
    @Test
    public void Search_02_Relative_Product_Name(){

    }
    @Test
    public void Search_03_Absolute_Product_Name(){

    }
    @Test
    public void Search_04_Parent_Category(){

    }
    @Test
    public void Search_05_Incorrect_Manufactorer(){

    }
    @Test
    public void Search_06_Correct_Manufactorer(){

    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
