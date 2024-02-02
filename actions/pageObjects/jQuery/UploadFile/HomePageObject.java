package pageObjects.jQuery.UploadFile;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.UploadFile.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFileLoadingByName(String fileName) {
        waitForAllElementVisibile(driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons= getListWebElement(driver, HomePageUI.START_BUTTON);
        for (WebElement startButton : startButtons) {
            startButton.click();
            sleepInSecond(2);
        }
    }

    public boolean isFileLinkUploadByName(String fileName) {
        waitForAllElementVisibile(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
    }
    public boolean isFileImageUploadByName(String fileName) {
        waitForAllElementVisibile(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
        return isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
    }
}
