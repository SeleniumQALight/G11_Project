package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParantPage {



    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }
    public HomePage checkIsRedirectOnHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();;
        //TODO check current url
        return this;
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        if (getHeaderElement().isButtonSignOutVisible()) {
           logger.info("User was logined in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN)
                    .enterTextIntoPassword(TestData.VALID_PASSWORD)
                    .clickInButtomSignIn();
            this.checkIsRedirectOnHomePage();
            logger.info("User was logined in")
            ;
        }
        return this;
    }
}
