package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_05_Page_Factory extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;

    private String firstName, lastName, password, Email,invalidEmail,foundEmail,incorrectPassword;
    private String projectPath=System.getProperty("user.dir");

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        incorrectPassword="456789";
        Email = "abc"+ganarateNumber()+"@gmail.com";
        foundEmail="qwe"+ganarateNumber()+"@gmail.com";
        invalidEmail="123@456&&@@";


        homePage =new UserHomePageObject(driver);

        System.out.println("Register_03 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new UserRegisterPageObject(driver);

        System.out.println("Register_03 - Step_02: Input to required fields");
        registerPage.inputToFristnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(Email);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_03 - Step_03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_03 - Step_04: Verify sucess message dislayed");
        Assert.assertEquals(registerPage.getRegisterSucessMessage(),"Your registration completed");

        System.out.println("Register_03 - Step_04: Click to Continue button");
        registerPage.clickToContinueButton();

    }

    @Test
    public void Login_01_Empty_Data(){
        homePage.clickToLoginLink();

        loginPage=new UserLoginPageObject(driver);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Please enter your email");
    }
    @Test
    public void Login_02_Invalid_Email(){
        homePage.clickToLoginLink();

        loginPage=new UserLoginPageObject(driver);
        loginPage.inputToEmailTextbox(invalidEmail);

        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Wrong email");
    }
    @Test
    public void Login_03_Email_Not_Found(){
        homePage.clickToLoginLink();

        loginPage=new UserLoginPageObject(driver);
        loginPage.inputToEmailTextbox(foundEmail);

        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUnsucessful(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }
    @Test
    public void Login_04_Exiting_Email_Empty_Password(){
        homePage.clickToLoginLink();

        loginPage=new UserLoginPageObject(driver);
        loginPage.inputToEmailTextbox(Email);
        loginPage.inputToPasswordTextbox(" ");

        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUnsucessful(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
    @Test
    public void Login_05_Exiting_Email_Incorrect_Password(){
        homePage.clickToLoginLink();

        loginPage=new UserLoginPageObject(driver);
        loginPage.inputToEmailTextbox(Email);
        loginPage.inputToPasswordTextbox(incorrectPassword);
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUnsucessful(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
    @Test
    public void Login_06_Valid_Email_And_Password(){
        homePage.clickToLoginLink();

        loginPage=new UserLoginPageObject(driver);
        loginPage.inputToEmailTextbox(Email);
        loginPage.inputToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
