package org.pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //initializes element described in FindBy, this allows flexibility
        //if we come form login page to home page, or login page or profile page this will return the page we need
        //and it allows us to reload the page and have an actual state of the page
    }

    //method for clearing and entering text into the element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was input into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    //method for clicking on the element
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }



    //isElementVisible method

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is visible");
            } else {
                logger.info("Element is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element either is not found");
            return false;
        }
    }

    //check if the element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

//check Text in Element

    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element is not expected", text, webElement.getText());
        logger.info("Text in element is expected");
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

}


