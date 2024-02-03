package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName){
        System.out.println("Run on" + browserName);
        if(browserName.equals("firefox")){
            driver = WebDriverManager.firefoxdriver().create();
            driver =new FirefoxDriver();
        } else if(browserName.equals("chrome")){
            driver =WebDriverManager.chromedriver().create();
            driver =new ChromeDriver();
        }else if(browserName.equals("edge")){
            driver =WebDriverManager.edgedriver().create();
            driver =new EdgeDriver();
        }else{
            throw new RuntimeException("Brower name invalid");
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(GlobalConstants.USER_PAGE_URL);
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String appUrl){
        System.out.println("Run on" + browserName);
        if(browserName.equals("firefox")){
            driver = WebDriverManager.firefoxdriver().create();
            driver =new FirefoxDriver();
        } else if(browserName.equals("chrome")){
            driver =WebDriverManager.chromedriver().create();
            driver =new ChromeDriver();
        }else if(browserName.equals("edge")){
            driver =WebDriverManager.edgedriver().create();
            driver =new EdgeDriver();
        }else{
            throw new RuntimeException("Brower name invalid");
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(appUrl);
        return driver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }


    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }


    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

}
