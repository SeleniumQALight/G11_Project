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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.pages.PageProvider;
import org.utils.ConfigProvider;
import org.utils.ScreenShot;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    private String symbols = "-------------";

    //preconditions
    @Before
    public void setup() throws MalformedURLException {
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



    private WebDriver initDriver() throws MalformedURLException {
        String browserFromProperty = System.getProperty("browser");
        logger.info("Browser is " + browserFromProperty);
        if ((browserFromProperty== null) || (browserFromProperty.equalsIgnoreCase("chrome"))){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            logger.info("Default browser is chrome");
        } else if (browserFromProperty.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browserFromProperty.toLowerCase())){
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
            else if ("remote".equals(browserFromProperty)) {
            logger.info("Remote browser");

            // WebDriverManager.chromedriver().setup();
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName("chrome");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(cap);
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        chromeOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if ("remote-sauce".equals(browserFromProperty)) {
            logger.info("Remote browser");
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("latest");
            HashMap<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("username", "oauth-radulenko-c446e");

            //  sauceOptions.put("accessKey", ConfigProvider.configHiddenProperties.saucelabs_pass());
            sauceOptions.put("build", "Taras-build1");
            sauceOptions.put("name", testName.getMethodName());
            browserOptions.setCapability("sauce:options", sauceOptions);

            // start the session
            URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
            webDriver = new RemoteWebDriver(url, browserOptions);
        }


        else {
            throw new IllegalArgumentException("Browser " + browserFromProperty + " is not supported");
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
