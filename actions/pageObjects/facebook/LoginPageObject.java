package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewAccount() {
        waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }

    public boolean isEmailAddressDisplayed() {
        waitForAllElementVisibile(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
    }

    public void enterToEmailAddressTextbox(String emailAddress) {
        waitForElementClickable(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public boolean isConfirmEmailAddressDisplayed() {
        return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public void clickCloseIconAtRegisterPopup() {
        waitForElementClickable(driver, LoginPageUI.CLOSE_ICON);
        clickToElement(driver, LoginPageUI.CLOSE_ICON);
    }

    public boolean isConfirmEmailAddressUndisplayed() {
        return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }
}
