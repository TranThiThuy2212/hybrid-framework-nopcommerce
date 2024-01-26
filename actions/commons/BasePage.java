package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.admin.AdminLoginPageObject;
import pageObjects.user.*;
import pageUIs.user.BasePageUI;

import java.util.List;
import java.util.Set;

public class BasePage {
    public static BasePage getBasePageObject(){
        return new BasePage();
    }
    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public Alert waitForAlertPresence(WebDriver driver){
        WebDriverWait explicitWait =new WebDriverWait(driver,longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }
    public void cancelAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }
    public String getAlertText(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }
    public void sendkeyToAlert(WebDriver driver, String textValue){
        waitForAlertPresence(driver).sendKeys(textValue);
    }
    public void switchWindowByID(WebDriver driver,String otherID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(otherID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver,String expectedPageTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualPageTitle = driver.getTitle();
            if(actualPageTitle.equals(expectedPageTitle))
            {
                break;
            }
        }
    }
    public void closeAllWindowWithoutParent(WebDriver driver,String otherID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(otherID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(otherID);
    }

    private By getByXpath(String xpathLocator){
        return By.xpath(xpathLocator);
    }
    private WebElement getWebElement(WebDriver driver, String xpathLocator){
        return driver.findElement(getByXpath(xpathLocator));
    }
    private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator)
    {
        return driver.findElements(getByXpath(xpathLocator));
    }
    public void clickToElement(WebDriver driver, String xpathLocator){
        getWebElement(driver,xpathLocator).click();
    }
    public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue){
        WebElement element = getWebElement(driver,xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }
    public String getElementText(WebDriver driver, String xpathLocator){
        return getWebElement(driver,xpathLocator).getText();
    }
    public void selectItemInDefaultDropDown(WebDriver driver, String xpathLocator, String textItem){
        Select select = new Select(getWebElement(driver,xpathLocator));
        select.deselectByValue(textItem);
    }
    public String getSelectItemInDropDown(WebDriver driver, String xpathLocator){
        Select select = new Select(getWebElement(driver,xpathLocator));
        return select.getFirstSelectedOption().getText();
    }
    public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver,xpathLocator));
        return select.isMultiple();
    }
    public void selectItemInDropDown(WebDriver driver,String parentXPath, String allItemXpath, String expectedTextItem )
    {
        getWebElement(driver,parentXPath).click();
        WebDriverWait explicitWait =new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(allItemXpath)));
        List<WebElement> speedDropDownItem = driver.findElements(By.xpath(allItemXpath));
        for (WebElement tempItem : speedDropDownItem) {
            if(tempItem.getText().trim().equals(expectedTextItem))
            {
                tempItem.click();
                break;
            }
        }
    }
    public void sleepInSecond(long timeInSecond)
    {
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName){
        return getWebElement(driver,xpathLocator).getAttribute(attributeName);
    }
    public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName){
        return getWebElement(driver,xpathLocator).getCssValue(propertyName);
    }
    public String getHexaColorFromRGBA(String RgbaValue){
        return Color.fromString(RgbaValue).asHex();
    }
    public int getElementSize(WebDriver driver, String xpathLocator){
        return getListWebElement(driver,xpathLocator).size();
    }
    public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator){
        WebElement element=getWebElement(driver,xpathLocator);
        if(!element.isSelected()){
            element.click();
        }
    }
    public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator){
        WebElement element=getWebElement(driver,xpathLocator);
        if(element.isSelected()){
            element.click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String xpathLocator){
        return getWebElement(driver,xpathLocator).isDisplayed();
    }
    public boolean isElementEnabled(WebDriver driver, String xpathLocator){
        return getWebElement(driver,xpathLocator).isEnabled();
    }
    public boolean isElementSelect(WebDriver driver, String xpathLocator){
        return getWebElement(driver,xpathLocator).isSelected();
    }
    public void switchToFrameIframe(WebDriver driver, String xpathLocator){
        driver.switchTo().frame(getWebElement(driver,xpathLocator));
    }
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverMouseToElement(WebDriver driver, String xpathLocator){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver,xpathLocator)).perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, xpathLocator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
    }

    public void scrollToElement(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
    }

    public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
    }

    public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }
    public void waitForElementVisibile(WebDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }
    public void waitForAllElementVisibile(WebDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
    }
    public void waitForElementInvisibile(WebDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
    }
    public void waitForAllElementInvisibile(WebDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,xpathLocator)));
    }
    public void waitForElementClickable(WebDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
    }
    private long longTimeout=30;
    private long shortTimeout=5;

    public UserAddressPageObject openAddressPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
        return PageGenaratorManager.getUserAddressPage(driver);
    }

    public UserMyProductPageReviewObject openMyProductReviewPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
        return PageGenaratorManager.getUserMyProductReviewPage(driver);
    }
    public UserRewardPointObject openRewardPointPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
        clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
        return PageGenaratorManager.getUserRewardPointPage(driver);
    }

    public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver){
        waitForElementClickable(driver,BasePageUI.LOGOUT_LINK_AT_USER);
        clickToElement(driver,BasePageUI.LOGOUT_LINK_AT_USER);
        return PageGenaratorManager.getUserHomePage(driver);
    }
    public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver){
        waitForElementClickable(driver,BasePageUI.LOGOUT_LINK_AT_ADMIN);
        clickToElement(driver,BasePageUI.LOGOUT_LINK_AT_ADMIN);
        return PageGenaratorManager.getAdminLoginPage(driver);
    }
}
