package pageObjects.saucedemo;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.saucedemo.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUserNameTextbox(String userName) {
        waitForElementVisibile(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, userName);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisibile(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public ProducPageObject clickToLoginButton() {
        waitForElementVisibile(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getProductPage(driver);
    }
}
