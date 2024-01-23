package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driverBaseTest;
    private String projectPath=System.getProperty("user.dir");
    protected WebDriver getBrowserDriver(String browserName){
        System.out.println("Run on" + browserName);
        if(browserName.equals("firefox")){
            driverBaseTest= WebDriverManager.firefoxdriver().create();
            driverBaseTest=new FirefoxDriver();
        } else if(browserName.equals("chrome")){
            driverBaseTest=WebDriverManager.chromedriver().create();
            driverBaseTest=new ChromeDriver();
        }else if(browserName.equals("edge")){
            driverBaseTest=WebDriverManager.edgedriver().create();
            driverBaseTest=new EdgeDriver();
        }else{
            throw new RuntimeException("Brower name invalid");
        }
        driverBaseTest.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverBaseTest.get("https://demo.nopcommerce.com/");
        return driverBaseTest;
    }

}
