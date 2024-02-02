package com.jquery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.UploadFile.HomePageObject;
import pageObjects.jQuery.UploadFile.PageGeneratorManager;

import java.util.List;

public class Level_11_Upload_Files extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    String beachFileName="beach.jpg";
    String computerFileName="computer.jpg";
    String mountainFileName="mountain.jpg";
    String[] multipleFileNames={beachFileName,computerFileName,mountainFileName};

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browerName, String appUrl){
        driver =getBrowserDriver(browerName, appUrl);
        homePage= PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Upload_One_File(){
        homePage.uploadMultipleFiles(driver, beachFileName);
        Assert.assertTrue(homePage.isFileLoadingByName(beachFileName));
        homePage.clickToStartButton();
        Assert.assertTrue(homePage.isFileLinkUploadByName(beachFileName));
        Assert.assertTrue(homePage.isFileImageUploadByName(beachFileName));
    }
    @Test
    public void Upload_Multiple_File_Per_Time(){
        homePage.refreshCurrentPage(driver);
        homePage.uploadMultipleFiles(driver, multipleFileNames);

        Assert.assertTrue(homePage.isFileLoadingByName(beachFileName));
        Assert.assertTrue(homePage.isFileLoadingByName(computerFileName));
        Assert.assertTrue(homePage.isFileLoadingByName(mountainFileName));

        homePage.clickToStartButton();

        Assert.assertTrue(homePage.isFileLinkUploadByName(beachFileName));
        Assert.assertTrue(homePage.isFileLinkUploadByName(computerFileName));
        Assert.assertTrue(homePage.isFileLinkUploadByName(mountainFileName));

        Assert.assertTrue(homePage.isFileImageUploadByName(beachFileName));
        Assert.assertTrue(homePage.isFileImageUploadByName(computerFileName));
        Assert.assertTrue(homePage.isFileImageUploadByName(mountainFileName));
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
