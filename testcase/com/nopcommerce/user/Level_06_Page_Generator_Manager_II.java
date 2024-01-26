package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;

import java.util.Random;

public class Level_06_Page_Generator_Manager_II extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;

    private String firstName, lastName, password, Email,invalidEmail,foundEmail,incorrectPassword;
    private String projectPath=System.getProperty("user.dir");

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        incorrectPassword="456789";
        Email = "abc"+ganarateNumber()+"@gmail.com";
        foundEmail="qwe"+ganarateNumber()+"@gmail.com";
        invalidEmail="123@456&&@@";

        userHomePage =new UserHomePageObject(driver);

        System.out.println("Register_03 - Step_01: Click to Register link");
        userRegisterPage = userHomePage.clickToRegisterLink();

        System.out.println("Register_03 - Step_02: Input to required fields");
        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(Email);
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_03 - Step_03: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_03 - Step_04: Verify sucess message dislayed");
        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        System.out.println("Register_03 - Step_04: Click to Continue button");
        userHomePage = userRegisterPage.clickToContinueButton();
    }

    @Test
    public void Login_01_Empty_Data(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.clickToLoginButton();

        Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(),"Please enter your email");
    }
    @Test
    public void Login_02_Invalid_Email(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(invalidEmail);

        userLoginPage.clickToLoginButton();

        Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(),"Wrong email");
    }
    @Test
    public void Login_03_Email_Not_Found(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(foundEmail);

        userLoginPage.clickToLoginButton();
        Assert.assertEquals(userLoginPage.getErrorMessageUnsucessful(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }
    @Test
    public void Login_04_Exiting_Email_Empty_Password(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(Email);
        userLoginPage.inputToPasswordTextbox(" ");

        userLoginPage.clickToLoginButton();
        Assert.assertEquals(userLoginPage.getErrorMessageUnsucessful(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
    @Test
    public void Login_05_Exiting_Email_Incorrect_Password(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(Email);
        userLoginPage.inputToPasswordTextbox(incorrectPassword);
        userLoginPage.clickToLoginButton();
        Assert.assertEquals(userLoginPage.getErrorMessageUnsucessful(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
    @Test
    public void Login_06_Valid_Email_And_Password(){
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTextbox(Email);
        userLoginPage.inputToPasswordTextbox(password);
        userLoginPage.clickToLoginButton();
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
