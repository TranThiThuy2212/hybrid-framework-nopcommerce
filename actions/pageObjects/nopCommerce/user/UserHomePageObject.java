package pageObjects.nopCommerce.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
    private WebDriver driver;
    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Navigate to Register page")
    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
        return PageGenaratorManager.getUserRegisterPage(driver);
    }
    @Step("Navigate to Login page")
    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
        return PageGenaratorManager.getUserLoginPage(driver);
    }

    @Step("Verify My Account link is displayed")
    public boolean isMyAccountLinkDisplayed() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

    @Step("Navigate to My Account page")
    public UserCustomerInfoPageObject ClickToMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return PageGenaratorManager.getUserCustomerInfoPage(driver);
    }
}
