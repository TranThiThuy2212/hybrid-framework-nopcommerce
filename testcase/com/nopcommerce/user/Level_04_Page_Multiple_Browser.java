package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Level_04_Page_Multiple_Browser extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;

    private String firstName, lastName, password, emailAddress;
    private String projectPath=System.getProperty("user.dir");

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browerName){
        driver =getBrowserDriver(browerName);

        userHomePage =new UserHomePageObject(driver);
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        emailAddress = "abc"+ganarateNumber()+"@gmail.com";

    }

    @Test
    public void Register_01_Empty_Data(){
        System.out.println("Register_01 - Step_01: Click to Register link");
        userHomePage.clickToRegisterLink();

        userRegisterPage =new UserRegisterPageObject(driver);

        System.out.println("Register_01 - Step_02: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_01 - Step_03: Verify error message dislayed");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtEmailTextbox(),"Email is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(),"Password is required.");
    }

    //@Test
    public void Register_02_Invalid_Email(){
        System.out.println("Register_02 - Step_01: Click to Register link");
        userHomePage.clickToRegisterLink();

        userRegisterPage =new UserRegisterPageObject(driver);

        System.out.println("Register_02 - Step_02: Input to required fields");
        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox("abc123@123$$");
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_02 - Step_03: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_02 - Step_04: Verify error message dislayed");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtEmailTextbox(),"Wrong email");
    }
    //@Test
    public void Register_03_Success(){
        System.out.println("Register_03 - Step_01: Click to Register link");
        userHomePage.clickToRegisterLink();

        userRegisterPage =new UserRegisterPageObject(driver);

        System.out.println("Register_03 - Step_02: Input to required fields");
        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(emailAddress);
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_03 - Step_03: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_03 - Step_04: Verify sucess message dislayed");
        Assert.assertEquals(userRegisterPage.getRegisterSucessMessage(),"Your registration completed");

        System.out.println("Register_03 - Step_04: Click to Continue button");
        userRegisterPage.clickToContinueButton();

    }
    //@Test
    public void Register_04_Exiting_Email(){
        System.out.println("Register_04 - Step_01: Click to Register link");
        userHomePage.clickToRegisterLink();

        userRegisterPage =new UserRegisterPageObject(driver);

        System.out.println("Register_04 - Step_02: Input to required fields");
        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(emailAddress);
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_04 - Step_03: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_04 - Step_04: Verify error existing email message dislayed");
        Assert.assertEquals(userRegisterPage.getErrorExistingEmailMessage(),"The specified email already exists");
    }
    //@Test
    public void Register_05_Password_Less_Than_6_Chars(){
        System.out.println("Register_05 - Step_01: Click to Register link");
        userHomePage.clickToRegisterLink();

        userRegisterPage =new UserRegisterPageObject(driver);

        System.out.println("Register_05 - Step_02: Input to required fields");
        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(emailAddress);
        userRegisterPage.inputToPasswordTextbox("1234");
        userRegisterPage.inputToConfirmPasswordTextbox("1234");

        System.out.println("Register_05 - Step_03: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_05 - Step_04: Verify error message dislayed");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(),"Password must meet the following rules:\nmust have at least 6 characters");

    }
    //@Test
    public void Register_06_Invalid_Confirm_Password(){
        System.out.println("Register_06 - Step_01: Click to Register link");
        userHomePage.clickToRegisterLink();

        userRegisterPage =new UserRegisterPageObject(driver);

        System.out.println("Register_06 - Step_02: Input to required fields");
        userRegisterPage.inputToFristnameTextbox(firstName);
        userRegisterPage.inputToLastnameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(emailAddress);
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox("123466");

        System.out.println("Register_06 - Step_03: Click to Register button");
        userRegisterPage.clickToRegisterButton();

        System.out.println("Register_06 - Step_04: Verify error message dislayed");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(),"The password and confirmation password do not match.");

    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
