package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserAddressPageObject extends BasePage {
    WebDriver driver;
    public UserAddressPageObject(WebDriver driver){
        this.driver = driver;
    }
}
