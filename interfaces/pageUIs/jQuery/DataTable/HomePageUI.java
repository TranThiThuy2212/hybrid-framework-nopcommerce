package pageUIs.jQuery.DataTable;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER="xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL="xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String PAGINATION_PAGE_ACTION_BY_NUMBER="xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String TOTAL_PAGINATION="xpath=//ul[@class='qgrd-pagination-ul']/li";
    public static final String PAGINATION_PAGE_BY_INDEX="xpath=//ul[@class='qgrd-pagination-ul']/li[%s]/a";
    public static final String ALL_ROW_EACH_PAGE="xpath=//table[@class='qgrd']//tbody/tr";
    public static final String ALL_ROW_EACH_COUNTRY_PAGE="xpath=//table[@class='qgrd']//tbody/tr/td[@data-key='country']";

    public static final String COLUMN_INDEX_BY_NAME="xpath=//thead/tr/th[text()='%s']/preceding-sibling::th";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX="xpath=//tbody/tr[%s]/td[%s]/input";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX="xpath=//tbody/tr[%s]/td[%s]/div/select";
    public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX="xpath=//tbody/tr[%s]/td[%s]/label/input";
    public static final String ICON_BY_ROW_INDEX="xpath=//tbody/tr[%s]//button[@title='%s']";

}
