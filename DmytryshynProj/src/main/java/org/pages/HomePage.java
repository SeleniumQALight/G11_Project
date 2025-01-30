package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage {
    // private Logger logger = Logger.getLogger(getClass());



    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSingOutVisible();
        //TODO check current url
        return this;
    }






//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info(state + " is element visible");
//            return state;
//        } catch (Exception e){
//            logger.info("Element is not found");
//            return false;
//        }
//    }
}
