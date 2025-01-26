package org.loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestAllStepsInOneClass {

    //об'єкти з якими будемо працювати
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

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

    }

    //post-conditions
    @After
    public void tearDown() {
        //потрібно закривати вебдрайвер після кожного використання!!!
        webDriver.quit();
        logger.info("Browser was closed");
    }

    //це вже сам тест
    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        //знайти інпут, почистити його, ввести нашу інфу:

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));  //адреса до єлемента
        inputUserName.clear(); //почистити поле input
        inputUserName.sendKeys("qaauto");//посилаємо логін
        logger.info("qaauto was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Password was inputted into input password");

        //знайти кнопку і клікнути по ній
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SighIn was clicked");

        //який в нас очікуваний результат? як перевірити автоматично, що дія відбулась?
        //це метод перевірки true/false
        //в скобках перше - це повідомлення коли тест не пройдено, а друге як будемо перевіряти:
        Assert.assertTrue("Button SighOut isn't visible", isButtonSighOutVisible());


    }

    //знайти кнопку, якщо вона є - true, якщо її немає - false
    private boolean isButtonSighOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            //isDisplayed - якщо кнопка показана - true
            logger.info(state + " is element visible");
            return state;
        } catch (Exception e) {
            logger.info("Element isn't found");
            return false;
        }
    }
}
