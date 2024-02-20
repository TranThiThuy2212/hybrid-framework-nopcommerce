package com.saucedemo.sort;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;
import pageObjects.saucedemo.LoginPageObject;
import pageObjects.saucedemo.PageGeneratorManager;
import pageObjects.saucedemo.ProducPageObject;

import java.lang.reflect.Method;
import java.util.Random;

public class Level_19_Sort extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private ProducPageObject productPage;

    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browerName, String appUrl) {
        driver =getBrowserDriver(browerName,appUrl);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToUserNameTextbox("standard_user");
        loginPage.inputToPasswordTextbox("secret_sauce");
        productPage=loginPage.clickToLoginButton();

    }

    @Test
    public void Sort_01_Name(){
        productPage.selectItemProductSortDropdown("Name (A to Z)");
        productPage.sleepInSecond(2);
        Assert.assertTrue(productPage.isProductNameSortByAscending());
        productPage.selectItemProductSortDropdown("Name (Z to A)");
        productPage.sleepInSecond(2);
        Assert.assertTrue(productPage.isProductNameSortByDescending());
    }

    @Test
    public void Sort_02_Price(){
        productPage.selectItemProductSortDropdown("Price (low to high)");
        productPage.sleepInSecond(2);
        Assert.assertTrue(productPage.isProductPriceSortByAscending());
        productPage.selectItemProductSortDropdown("Price (high to low)");
        productPage.sleepInSecond(2);
        Assert.assertTrue(productPage.isProductPriceSortByDescending());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }


}
