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
        PageFactory.initElements(webDriver, this); //initializes element described in FindBy
    }

    //method for clearing and entering text into the element
    protected void clearAndEnterTextIntoElement(WebElement webElementelement, String text) {
        try {
            webElementelement.clear();
            webElementelement.sendKeys(text);
            System.out.println(text + " was input into element");
        } catch (Exception e) {
            System.out.println("Cannot work with element " + webElementelement);
            Assert.fail("Cannot work with element " + webElementelement);
        }
    }


    //method for clicking on the element
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
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


    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }


}


