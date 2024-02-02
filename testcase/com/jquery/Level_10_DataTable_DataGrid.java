package com.jquery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.DataTable.HomePageObject;
import pageObjects.jQuery.DataTable.PageGeneratorManager;


import java.util.List;

public class Level_10_DataTable_DataGrid extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    List<String> actualAllCountryValue;
    List<String> expectedAllCountryValue;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browerName, String appUrl){
        driver =getBrowserDriver(browerName, appUrl);
        homePage= PageGeneratorManager.getHomePage(driver);
    }

    //@Test
    public void Table_01_Paging(){
        homePage.openPagingByPageNumber("10");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isNumberActive("10"));

        homePage.openPagingByPageNumber("12");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isNumberActive("12"));

        homePage.openPagingByPageNumber("20");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isNumberActive("20"));
    }
    //@Test
    public void Table_02_Enter_To_Header(){
        homePage.refreshCurrentPage(driver);

        homePage.enterToHeaderTextBoxByLabel("Country","Curacao");
        homePage.enterToHeaderTextBoxByLabel("Females","993");
        homePage.enterToHeaderTextBoxByLabel("Males","947");
        homePage.enterToHeaderTextBoxByLabel("Total","1938");
        homePage.sleepInSecond(3);

    }
    //@Test
    public void Table_03_Get_All_Value_Rows(){
        actualAllCountryValue =homePage.getValueEachRowAtAllPage();
        Assert.assertEquals(actualAllCountryValue, expectedAllCountryValue);
    }

    @Test
    public void Table_04_Enter_To_Textbox_At_Any_Row(){
        homePage.enterToTextboxColumnNameAtRowNumber("Company","1","abc1900");
        homePage.enterToTextboxColumnNameAtRowNumber("Contact Person","1","Thuy");
        homePage.enterToTextboxColumnNameAtRowNumber("Order Placed","1","quanao");

        homePage.selectDropDownColumnNameAtRowNumber("Country","1","Japan");
        homePage.sleepInSecond(3);

        homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?","1");
        homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?","3");

        homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?","2");

        homePage.clickToIconByRowNumber("1","Insert Row Above");
        homePage.clickToIconByRowNumber("1","Remove Current Row");
        homePage.clickToIconByRowNumber("1","Move Down");
        homePage.clickToIconByRowNumber("2","Move Up");

        homePage.clickToIconByRowNumber("3","Remove Current Row");
        homePage.clickToIconByRowNumber("2","Remove Current Row");
        homePage.clickToIconByRowNumber("1","Remove Current Row");
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
