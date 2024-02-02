package pageObjects.jQuery.DataTable;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.DataTable.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPagingByPageNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
    }

    public void enterToHeaderTextBoxByLabel(String headerLabel, String value) {
        waitForElementVisibile(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
        sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL,value, headerLabel);
        pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
    }

    public boolean isNumberActive(String number) {
        waitForElementVisibile(driver, HomePageUI.PAGINATION_PAGE_ACTION_BY_NUMBER, number);
        return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTION_BY_NUMBER, number);
    }

    public List<String> getValueEachRowAtAllPage() {
        int totalPage= getElementSize(driver, HomePageUI.TOTAL_PAGINATION);

        List<String> allRowValueAllPage= new ArrayList<String>();
        for (int index=1;index<=totalPage;index++){
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(index));
            sleepInSecond(1);

            List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_COUNTRY_PAGE);
            for (WebElement eachRow : allRowElementEachPage){
                allRowValueAllPage.add(eachRow.getText());
            }
        }
        for (String value : allRowValueAllPage){
            System.out.println(value);
        }
        return allRowValueAllPage;
    }

    public void enterToTextboxColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        waitForElementVisibile(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        sendkeyToElement(driver,HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value,rowNumber, String.valueOf(columnIndex));
    }

    public void selectDropDownColumnNameAtRowNumber(String columnName, String rowNumber, String valueToSelect) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        selectItemInDefaultDropDown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX,valueToSelect,rowNumber, String.valueOf(columnIndex));

    }

    public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        checkToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
    }

    public void uncheckToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        uncheckToDefaultCheckbox(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
    }

    public void clickToIconByRowNumber(String rowNumber, String iconName) {
        waitForElementClickable(driver, HomePageUI.ICON_BY_ROW_INDEX, rowNumber,iconName);
        clickToElement(driver, HomePageUI.ICON_BY_ROW_INDEX, rowNumber,iconName);
    }
}
