package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.WatchEvent;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані FindBy
    }

    //method for selecting text in dropdown
    protected void selectTextInDropdown(WebElement dropdown, String text) {
        try {
            Select options = new Select(dropdown);
            options.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        }

        //select value in dropdown
        public void selectValueInDropdown(WebElement dropdown, String value) {
        try {
            Select options = new Select(dropdown);
            options.selectByValue(value);
            logger.info(value + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //method for clearing and typing text in input
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputed into input" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName;
        try {
            elementName = webElement.getAccessibleName();
        } catch (Exception e) {
            elementName = "";
        }
        return elementName;
    }

    //method for clicking on element
    protected void clickOnElement(WebElement webElement) {
        try {
            String elementName = webElement.getTagName();
            webElement.click();
            logger.info(elementName + " was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webElement.click();
            logger.info(elementName + " was clicked");
        } catch (Exception e) {
            logger.error("Cannot work with element " + elementName);
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(String locator) {
        try {
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    //method for checking if element is displayed
    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " is visible");
            } else {
                logger.info(getElementName(webElement) + " is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    //check if the element is visible and return result
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisible(locator));
    }


    //method for checking text in element
    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element" + getElementName(webElement) + " not expected", text, webElement.getText());
        logger.info("Text in element " + getElementName(webElement) + " is expected");
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }


}
