package pageObjects.nopCommerce.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;
    public UserRegisterPageObject(WebDriver driver) {
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
    @Step("Enter to First Name textbox with valie is {0}")
    public void inputToFristnameTextbox(String firstName) {
        waitForElementVisibile(driver,RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.FIRST_NAME_TEXTBOX,firstName);
    }

    @Step("Enter to Last Name textbox with valie is {0}")
    public void inputToLastnameTextbox(String lastName) {
        waitForElementVisibile(driver,RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.LAST_NAME_TEXTBOX,lastName);
    }

    @Step("Enter to Email textbox with valie is {0}")
    public void inputToEmailTextbox(String email) {
        waitForElementVisibile(driver,RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX,email);
    }

    @Step("Enter to Password textbox with valie is {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisibile(driver,RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX,password);
    }

    @Step("Enter to Confirm Password textbox with valie is {0}")
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

    public UserHomePageObject clickToContinueButton() {
        waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
        return PageGenaratorManager.getUserHomePage(driver);
    }

    public void inputToTextboxByID(String textboxID, String value) {

    }

    public void clickToButtonByText(String s) {
    }

    public void selectToDropDownByName(WebDriver driver, String s, String s1) {
    }
}
