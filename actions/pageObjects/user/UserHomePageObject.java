package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.user.HomePageUI;

public class UserHomePageObject extends BasePage {
    private WebDriver driver;
    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }
    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
        return PageGenaratorManager.getUserRegisterPage(driver);
    }

    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
        return PageGenaratorManager.getUserLoginPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInfoPageObject ClickToMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return PageGenaratorManager.getUserCustomerInfoPage(driver);
    }
}
