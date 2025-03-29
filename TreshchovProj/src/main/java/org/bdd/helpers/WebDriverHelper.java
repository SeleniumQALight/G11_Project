package org.bdd.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverHelper {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public WebDriverHelper(){
        if (webDriver == null){
            webDriver = initDriver();
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void quitDriver(){
        webDriver.quit();
        logger.info("Browser was closed");
    }

    protected WebDriver initDriver() {
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
