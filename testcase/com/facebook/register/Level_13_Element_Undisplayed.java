package com.facebook.register;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.jQuery.UploadFile.HomePageObject;

public class Level_13_Element_Undisplayed extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browerName, String appUrl){
        driver =getBrowserDriver(browerName, appUrl);
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Test
    public void TC_01_Verify_Element_Displayed(){
        loginPage.clickToCreateNewAccount();
        verifyTrue(loginPage.isEmailAddressDisplayed());
    }
    @Test
    public void TC_02_Verify_Element_Undisplay_In_DOM(){
        loginPage.enterToEmailAddressTextbox("tranthuy@gmail.com");
        loginPage.sleepInSecond(2);
        verifyTrue(loginPage.isConfirmEmailAddressDisplayed());

        loginPage.enterToEmailAddressTextbox("");
        loginPage.sleepInSecond(2);
        verifyFalse(loginPage.isConfirmEmailAddressDisplayed());
    }
    @Test
    public void TC_03_Verify_Element_Undisplay_Not_In_DOM(){
        loginPage.clickCloseIconAtRegisterPopup();
        loginPage.sleepInSecond(2);
        verifyTrue(loginPage.isConfirmEmailAddressUndisplayed());
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
