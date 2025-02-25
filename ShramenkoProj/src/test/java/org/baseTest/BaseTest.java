package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.pages.PageProvider;
import org.utils.ConfigProvider;
import org.utils.ScreenShot;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    private String symbols = "-------------";

    //preconditions
    @Before
    public void setup() {
        logger.info(symbols + testName.getMethodName() + " was started " + symbols);
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(
                ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT(), TimeUnit.SECONDS);
        //5 секунд це час, щоб він трішки зачекав, імітація дії людини
        //або якщо з першого разу не вийшло, то 5 сек, щоб намагатися виконати команду
        //він буде пробувати робити кожні 0,5 сек ще одну спробу
        //цей час розповсюджується на всі команди
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    //post-conditions
    @After
    public void tearDown() {
        //потрібно закривати вебдрайвер після кожного використання!!!
//        webDriver.quit();
//        logger.info("Browser was closed"); // ці методи переїхали нижче в рулс нижче
        logger.info(symbols + testName.getMethodName() + " was finished " + symbols);
    }

    @Rule                                       //анотація яка буде виконана над/перед тестом
    public TestName testName = new TestName(); //назва тесту, який буде виконуватися



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


    //це правило для зберігання скріншотів
    @Rule()
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };


    //просто метод для скріншота, якщо треба

    protected void takeScreenshot(String screenShotName) {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(screenShotName, screen));
    }
}
