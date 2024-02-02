package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;

public class PageGenaratorManager {
    public static UserHomePageObject getUserHomePage(WebDriver driver){
        return new UserHomePageObject(driver);
    }
    public static UserLoginPageObject getUserLoginPage(WebDriver driver){
        return new UserLoginPageObject(driver);
    }
    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver){
        return new UserRegisterPageObject(driver);
    }
    public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver){
        return new UserCustomerInfoPageObject(driver);
    }
    public static UserMyProductPageReviewObject getUserMyProductReviewPage(WebDriver driver){
        return new UserMyProductPageReviewObject(driver);
    }
    public static UserAddressPageObject getUserAddressPage(WebDriver driver){
        return new UserAddressPageObject(driver);
    }
    public static UserRewardPointObject getUserRewardPointPage(WebDriver driver){
        return new UserRewardPointObject(driver);
    }
    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver){
        return new AdminLoginPageObject(driver);
    }
    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver){
        return new AdminDashboardPageObject(driver);
    }
}
