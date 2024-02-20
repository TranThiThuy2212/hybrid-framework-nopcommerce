package pageObjects.saucedemo;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucedemo.ProducPageUI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProducPageObject extends BasePage {
    WebDriver driver;
    public ProducPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItemProductSortDropdown(String textItem) {
        waitForElementClickable(driver, ProducPageUI.PRODUCT_CONTAIN_DRODOWN);
        selectItemInDefaultDropDown(driver, ProducPageUI.PRODUCT_CONTAIN_DRODOWN, textItem);
    }

    public boolean isProductNameSortByAscending() {
        ArrayList<String> productUIList = new ArrayList<String>();
        List<WebElement> productNameText= getListWebElement(driver,ProducPageUI.PRODUCT_NAME_TEXT);
        for (WebElement productName : productNameText) {
            productUIList.add(productName.getText());
        }
        ArrayList<String> productSortList = new ArrayList<String>();
        for (String product : productUIList) {
            productSortList.add(product);
        }
        Collections.sort(productSortList);
        return productSortList.equals(productUIList);
    }

    public boolean isProductNameSortByDescending() {
        ArrayList<String> productUIList = new ArrayList<String>();
        List<WebElement> productNameText= getListWebElement(driver,ProducPageUI.PRODUCT_NAME_TEXT);
        for (WebElement productName : productNameText) {
            productUIList.add(productName.getText());
        }
        ArrayList<String> productSortList = new ArrayList<String>();
        for (String product : productUIList) {
            productSortList.add(product);
        }
        Collections.sort(productSortList);
        Collections.reverse(productSortList);
        return productSortList.equals(productUIList);
    }

    public boolean isProductPriceSortByAscending() {
        ArrayList<Float> productUIList = new ArrayList<Float>();
        List<WebElement> productPriceText= getListWebElement(driver,ProducPageUI.PRODUCT_PRICE_TEXT);
        for (WebElement productPrice : productPriceText) {
            productUIList.add(Float.valueOf(productPrice.getText().replace("$","")));
        }
        ArrayList<Float> productSortList = new ArrayList<Float>();
        for (Float product : productUIList) {
            productSortList.add(product);
        }
        Collections.sort(productSortList);
        return productSortList.equals(productUIList);
    }

    public boolean isProductPriceSortByDescending() {
        ArrayList<Float> productUIList = new ArrayList<Float>();
        List<WebElement> productPriceText= getListWebElement(driver,ProducPageUI.PRODUCT_PRICE_TEXT);
        for (WebElement productPrice : productPriceText) {
            productUIList.add(Float.valueOf(productPrice.getText().replace("$","")));
        }
        ArrayList<Float> productSortList = new ArrayList<Float>();
        for (Float product : productUIList) {
            productSortList.add(product);
        }
        Collections.sort(productSortList);
        Collections.reverse(productSortList);
        return productSortList.equals(productUIList);
    }
}
