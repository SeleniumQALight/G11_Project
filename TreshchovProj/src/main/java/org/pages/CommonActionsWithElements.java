package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait_10, webDriverWait_15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //initialize elements described in FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    //method for select visible text in dropdown
    protected void selectTextInDD(WebElement dropDownElement, String textForSelect){
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //select value is DropDown
    protected void selectValueInDD(WebElement dropDownElement, String valueForSelect){
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByValue(valueForSelect);
            logger.info(valueForSelect + " value was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    protected void enterTextIntoInput(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into element " +getElementName(element));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement element) {
        String elementName;
        try {
            elementName = element.getAccessibleName();
        } catch (Exception e) {
            elementName = "";
        }
        return elementName;
    }


    protected void clickOnElement(WebElement element) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element,String elementName) {
        try {
            element.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    protected boolean isElementVisible(String locator) {
        try {
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }


    protected boolean isElementVisible(WebElement element) {
        try {
            boolean tempState = element.isDisplayed();
            if (tempState) {
                logger.info(getElementName(element) + " Element is visible");
            } else {
                logger.info(getElementName(element) + " Element is not visible");
            }
            return tempState;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }


    protected void checkIsElementVisible(WebElement element) {
        Assert.assertTrue(getElementName(element) + " Element is not visible", isElementVisible(element));
    }

    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue(locator + " Element is not visible", isElementVisible(locator));
    }

    protected void checkTextInElement(WebElement element, String text) {
            Assert.assertEquals("Text in " + getElementName(element) +  " element is not expected", text, element.getText());
            logger.info("Text in " + getElementName(element) + " element is expected");
    }


    protected void acceptAlert() {
        try {
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //scroll to element using Actions
    protected void scrollToElement(WebElement element) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(element);
            actions.perform();
            logger.info("Scrolled to element " + getElementName(element));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }




}
