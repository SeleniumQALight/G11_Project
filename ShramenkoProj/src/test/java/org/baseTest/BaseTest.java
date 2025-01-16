package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PageProvider;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    //preconditions
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        webDriver.quit();
        logger.info("Browser was closed");
    }


}
