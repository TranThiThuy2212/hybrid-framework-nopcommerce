package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_Page_Object_Register {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;

    private String firstName, lastName, password, emailAddress;
    private String projectPath=System.getProperty("user.dir");


    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        homePage =new HomePageObject(driver);
        firstName="Tran";
        lastName="Thuy";
        password="123456";
        emailAddress = "abc"+ganarateNumber()+"@gmail.com";

    }

    @Test
    public void Register_01_Empty_Data(){
        System.out.println("Register_01 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new RegisterPageObject(driver);

        System.out.println("Register_01 - Step_02: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_01 - Step_03: Verify error message dislayed");
        Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Email is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email(){
        System.out.println("Register_02 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new RegisterPageObject(driver);

        System.out.println("Register_02 - Step_02: Input to required fields");
        registerPage.inputToFristnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox("abc123@123$$");
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_02 - Step_03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_02 - Step_04: Verify error message dislayed");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Wrong email");
    }
    @Test
    public void Register_03_Success(){
        System.out.println("Register_03 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new RegisterPageObject(driver);

        System.out.println("Register_03 - Step_02: Input to required fields");
        registerPage.inputToFristnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
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
    public void Register_04_Exiting_Email(){
        System.out.println("Register_04 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new RegisterPageObject(driver);

        System.out.println("Register_04 - Step_02: Input to required fields");
        registerPage.inputToFristnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_04 - Step_03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_04 - Step_04: Verify error existing email message dislayed");
        Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),"The specified email already exists");
    }
    @Test
    public void Register_05_Password_Less_Than_6_Chars(){
        System.out.println("Register_05 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new RegisterPageObject(driver);

        System.out.println("Register_05 - Step_02: Input to required fields");
        registerPage.inputToFristnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("1234");
        registerPage.inputToConfirmPasswordTextbox("1234");

        System.out.println("Register_05 - Step_03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_05 - Step_04: Verify error message dislayed");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password must meet the following rules:\nmust have at least 6 characters");

    }
    @Test
    public void Register_06_Invalid_Confirm_Password(){
        System.out.println("Register_06 - Step_01: Click to Register link");
        homePage.clickToRegisterLink();

        registerPage =new RegisterPageObject(driver);

        System.out.println("Register_06 - Step_02: Input to required fields");
        registerPage.inputToFristnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox("123466");

        System.out.println("Register_06 - Step_03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_06 - Step_04: Verify error message dislayed");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"The password and confirmation password do not match.");

    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
