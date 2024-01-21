package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver)
    {
        this.driver=driver;
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisibile(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisibile(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
    }

    public String getErrorMessageUnsucessful() {
        waitForElementVisibile(driver, LoginPageUI.LOGIN_ERROR_MESSAGE_UNSUCESSFUL);
        return getElementText(driver, LoginPageUI.LOGIN_ERROR_MESSAGE_UNSUCESSFUL);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisibile(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }


}
