package org.loginTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.pages.ParentPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.pages.ParentPage.baseUrl;

public class LoginTestWithSelenide {
    @Test
    public void validLogin() {

        // Should be in LoginPage
        SelenideElement inputUserName = $(By.xpath(".//input[@placeholder='Username']"));
        SelenideElement inputPassword = $(By.xpath(".//input[@placeholder='Password']"));
        SelenideElement buttonSignIn = $(By.xpath(".//button[text()='Sign In']"));
// Should be in HomePage
        SelenideElement buttonMyProfile = $(By.xpath("//img[@alt='My profile']"));
        WebDriverManager.chromedriver().setup();
        // Open the login page
        open(baseUrl);
        // Enter valid username and password
        inputUserName.setValue("qaauto");
        inputPassword.setValue("123456qwerty");
        // Click the Sign In button
        buttonSignIn.click();

        buttonMyProfile.shouldBe(Condition.visible);


    }

}
