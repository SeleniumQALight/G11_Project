package org.bdd.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverHelper {
    @Getter
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public WebDriverHelper(){
        if (webDriver == null){
            webDriver = initDriver();
        }
    }

    public void quiteDriver(){
        webDriver.quit();
        logger.info("Browser was closed");
    }

    //створення браузера
    private WebDriver initDriver() {
        String browserFromProperly = System.getProperty("browser");
        logger.info("Browser is " + browserFromProperly);
        if ((browserFromProperly== null) || (browserFromProperly.equalsIgnoreCase("chrome"))){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            logger.info("Default browser is chrome");
        } else if (browserFromProperly.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browserFromProperly.toLowerCase())){
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browserFromProperly)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browserFromProperly)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
        else {
            throw new IllegalArgumentException("Browser " + browserFromProperly + " is not supported");
        }
        return webDriver;
    }

}
