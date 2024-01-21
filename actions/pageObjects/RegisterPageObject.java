package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getErrorMessageAtFirstnameTextbox() {
        waitForElementVisibile(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtLastnameTextbox() {
        waitForElementVisibile(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisibile(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getErrorMessageAtPasswordTextbox() {
        waitForElementVisibile(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getErrorMessageAtConfirmPasswordTextbox() {
        waitForElementVisibile(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void inputToFristnameTextbox(String firstName) {
        waitForElementVisibile(driver,RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.FIRST_NAME_TEXTBOX,firstName);
    }

    public void inputToLastnameTextbox(String lastName) {
        waitForElementVisibile(driver,RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.LAST_NAME_TEXTBOX,lastName);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisibile(driver,RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX,email);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisibile(driver,RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX,password);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisibile(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,confirmPassword);
    }

    public String getRegisterSucessMessage() {
        waitForElementVisibile(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }


    public String getErrorExistingEmailMessage() {
        waitForElementVisibile(driver, RegisterPageUI.ERROR_EXISTING_EMAIL_MESSAGE);
        return getElementText(driver, RegisterPageUI.ERROR_EXISTING_EMAIL_MESSAGE);
    }

    public void clickToContinueButton() {
        waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
    }
}
