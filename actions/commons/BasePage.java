package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.*;
import pageUIs.jQuery.UploadFile.BasePageUIuploadFile;
import pageUIs.nopCommerce.user.BasePageUI;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    public Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }
    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
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

    public By getByLocator(String locatorType){
        By by = null;
        if(locatorType.startsWith("id=") || locatorType.startsWith("ID=")||locatorType.startsWith("Id=")){
            by = By.id(locatorType.substring(3));
        } else if(locatorType.startsWith("class=")||locatorType.startsWith("CLASS=")||locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if(locatorType.startsWith("name=")||locatorType.startsWith("NAME=")||locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));
        } else if(locatorType.startsWith("css=")||locatorType.startsWith("CSS=")||locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if(locatorType.startsWith("xpath=")||locatorType.startsWith("XPATH=")||locatorType.startsWith("XPath=")||locatorType.startsWith("Xpath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;
    }
    public String getDYnamicXpath(String locatorType, String... values) {
        if(locatorType.startsWith("xpath=")||locatorType.startsWith("XPATH=")||locatorType.startsWith("XPath=")||locatorType.startsWith("Xpath=")){
            locatorType = String.format(locatorType,(Object[]) values);
        }
        return locatorType;
    }
    public WebElement getWebElement(WebDriver driver, String locatorType){
        return driver.findElement(getByLocator(locatorType));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String locatorType)
    {
        return driver.findElements(getByLocator(locatorType));
    }
    public void clickToElement(WebDriver driver, String locatorType){
        getWebElement(driver,locatorType).click();
    }
    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues){
        getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)).click();
    }
    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue){
        WebElement element = getWebElement(driver,locatorType);
        element.clear();
        element.sendKeys(textValue);
    }
    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues){
        WebElement element = getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }
    public String getElementText(WebDriver driver, String locatorType){
        return getWebElement(driver,locatorType).getText();
    }
    public String getElementText(WebDriver driver, String locatorType, String... dynamicValues){
        return getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)).getText();
    }
    public void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String textItem){
        Select select = new Select(getWebElement(driver,locatorType));
        select.selectByVisibleText(textItem);
    }
    public void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String textItem, String... dynamicValues){
        Select select = new Select(getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)));
        select.selectByVisibleText(textItem);
    }
    public String getSelectItemInDropDown(WebDriver driver, String locatorType){
        Select select = new Select(getWebElement(driver,locatorType));
        return select.getFirstSelectedOption().getText();
    }
    public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver,locatorType));
        return select.isMultiple();
    }
    public void selectItemInDropDown(WebDriver driver,String parentXPath, String allItemXpath, String expectedTextItem )
    {
        getWebElement(driver,parentXPath).click();
        WebDriverWait explicitWait =new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
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
    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName){
        return getWebElement(driver,locatorType).getAttribute(attributeName);
    }
    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues){
        return getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)).getAttribute(attributeName);
    }
    public String getElementCssValue(WebDriver driver, String locatorType, String propertyName){
        return getWebElement(driver,locatorType).getCssValue(propertyName);
    }
    public String getElementCssValue(WebDriver driver, String locatorType, String propertyName, String... dynamicValues){
        return getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)).getCssValue(propertyName);
    }
    public String getHexaColorFromRGBA(String RgbaValue){
        return Color.fromString(RgbaValue).asHex();
    }
    public int getElementSize(WebDriver driver, String locatorType){
        return getListWebElement(driver,locatorType).size();
    }
    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues){
        return getListWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)).size();
    }
    public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType){
        WebElement element=getWebElement(driver,locatorType);
        if(!element.isSelected()){
            element.click();
        }
    }
    public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues){
        WebElement element=getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues));
        if(!element.isSelected()){
            element.click();
        }
    }
    public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType){
        WebElement element=getWebElement(driver,locatorType);
        if(element.isSelected()){
            element.click();
        }
    }
    public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues){
        WebElement element=getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues));
        if(element.isSelected()){
            element.click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String locatorType){
        return getWebElement(driver,locatorType).isDisplayed();
    }

    public boolean isElementUndisplayed(WebDriver driver, String locatorType){
        overrideImplicitTimeout(driver,shortTimeout);
        List<WebElement> elements = getListWebElement(driver,locatorType);
        overrideImplicitTimeout(driver,longTimeout);
        if(elements.size()==0){
            System.out.println("Element khong co trong DOM");
            return true;
        }
        else if(elements.size()>0 && !elements.get(0).isDisplayed()){
            System.out.println("Element co trong DOM nhung khong display");
            return true;
        }
        else {
            System.out.println("Element co trong DOM va display");
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues){
        overrideImplicitTimeout(driver,shortTimeout);
        List<WebElement> elements = getListWebElement(driver,getDYnamicXpath(locatorType,dynamicValues));
        overrideImplicitTimeout(driver,longTimeout);
        if(elements.size()==0){
            System.out.println("Element khong co trong DOM");
            return true;
        }
        else if(elements.size()>0 && !elements.get(0).isDisplayed()){
            System.out.println("Element co trong DOM nhung khong display");
            return true;
        }
        else {
            System.out.println("Element co trong DOM va display");
            return false;
        }
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }


    public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues){
        return getWebElement(driver,getDYnamicXpath(locatorType,dynamicValues)).isDisplayed();
    }
    public boolean isElementEnabled(WebDriver driver, String locatorType){
        return getWebElement(driver,locatorType).isEnabled();
    }
    public boolean isElementSelect(WebDriver driver, String locatorType){
        return getWebElement(driver,locatorType).isSelected();
    }
    public void switchToFrameIframe(WebDriver driver, String locatorType){
        driver.switchTo().frame(getWebElement(driver,locatorType));
    }
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverMouseToElement(WebDriver driver, String locatorType){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver,locatorType)).perform();
    }
    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver,locatorType),key).perform();
    }
    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.sendKeys(getDYnamicXpath(locatorType,dynamicValues),key).perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
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

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDYnamicXpath(locatorType,dynamicValues)));
        if (status) {
            return true;
        } else {
            return false;
        }
    }
    public void waitForElementVisibile(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }
    public void waitForElementVisibile(WebDriver driver, String locatorType, String... dynamicValues){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDYnamicXpath(locatorType,dynamicValues))));
    }
    public void waitForAllElementVisibile(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }
    public void waitForAllElementVisibile(WebDriver driver, String locatorType, String... dynamicValues){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDYnamicXpath(locatorType,dynamicValues))));
    }
    public void waitForElementInvisibile(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }
    /*
        Wait for element undisplayed in DOM or not in DOM and override implicit wait
     */
    public void waitForElementUndisplayed(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver,shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver,longTimeout);
    }
    public void waitForElementInvisibile(WebDriver driver, String locatorType, String... dynamicValues){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDYnamicXpath(locatorType,dynamicValues))));
    }
    public void waitForAllElementInvisibile(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locatorType)));
    }
    public void waitForAllElementInvisibile(WebDriver driver, String locatorType, String... dynamicValues){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,getDYnamicXpath(locatorType,dynamicValues))));
    }
    public void waitForElementClickable(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }
    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDYnamicXpath(locatorType,dynamicValues))));
    }
    private long longTimeout=GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout=GlobalConstants.SHORT_TIMEOUT;

    public void uploadMultipleFiles(WebDriver driver, String... fileNames){
        String filePath=GlobalConstants.UPLOAD_FILE_FOLDER;
        String fullFileName="";
        for(String file:fileNames){
            fullFileName=fullFileName+filePath+file+"\n";
        }
        fullFileName=fullFileName.trim();
        getWebElement(driver, BasePageUIuploadFile.UPLOAD_FILE).sendKeys(fullFileName);
    }



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
    public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName){
        waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK, pageName);
        clickToElement(driver, BasePageUI.REWARD_POINT_LINK, pageName);
        switch (pageName){
            case "Addresses":
                return PageGenaratorManager.getUserAddressPage(driver);
            case "My product reviews":
                return PageGenaratorManager.getUserMyProductReviewPage(driver);
            case "Reward points":
                return PageGenaratorManager.getUserRewardPointPage(driver);
            default:
                throw new RuntimeException("Invalid page name at My Account area");
        }
    }
    public void openPagesAtMyAccountByPageName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK, pageName);
        clickToElement(driver, BasePageUI.REWARD_POINT_LINK, pageName);
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
