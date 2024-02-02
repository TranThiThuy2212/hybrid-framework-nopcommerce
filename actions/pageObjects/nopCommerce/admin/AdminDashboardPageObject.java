package pageObjects.nopCommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;
    public AdminDashboardPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isDashboardHeaderDisplay() {
        waitForElementVisibile(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
        return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
    }
}
