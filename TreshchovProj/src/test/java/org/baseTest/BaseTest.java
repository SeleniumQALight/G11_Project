package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.pages.PageProvider;
import org.utils.ConfigProvider;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    @Before
    public void setup() {
        //WebDriverManager.chromedriver().setup();
        //webDriver = new ChromeDriver();
        webDriver = initWebDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT(), TimeUnit.SECONDS);
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);


    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");

    }

    protected WebDriver initWebDriver() {
        String browserFromProperty = System.getProperty("browser");
        logger.info("browser from property = " + browserFromProperty);
        if ((browserFromProperty == null) || ("chrome".equalsIgnoreCase(browserFromProperty))) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browserFromProperty.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browserFromProperty)){
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
        else {
            throw new IllegalArgumentException("Browser " + browserFromProperty + " is not supported");
        }
        return webDriver;
    }


}
