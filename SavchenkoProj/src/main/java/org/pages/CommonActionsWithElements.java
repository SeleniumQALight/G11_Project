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
import org.utils.ConfigProvider;

import java.time.Duration;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    protected WebDriverWait webDriverWait_10, webDriverWait_15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елементи, описані FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    //method for select visible text in dropDown
    protected void selectTextInDropDown(WebElement dropDownElement, String textForSelect) {
        try {
            Select optionsFromDropDown = new Select(dropDownElement);
            optionsFromDropDown.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in DropDown " + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //select value in DropDown
    protected void selectValueInDropDown(WebElement dropDownElement, String valueInDD) {
        try {
            Select optionsFromDropDown = new Select(dropDownElement);
            optionsFromDropDown.selectByValue(valueInDD);
            logger.info(valueInDD + "value was selected in DropDown " + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element" + getElementName(webElement));
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

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + "Element was clicked");
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

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + "Element is displayed");
            } else {
                logger.info(getElementName(webElement) + "Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }


    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisible(locator));
    }

    //checkTextInElement
    protected void checkTextInElement(WebElement webElement, String text) {
            Assert.assertEquals("Text in element"+ getElementName(webElement) +"not expected", text, webElement.getText());
            logger.info("Text in element is expected");

    }

    protected void acceptAlert() {
        try {
            webDriverWait_10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //open new tab using JS
    public void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // scroll to element using Actions
    protected void scrollToElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement);
            actions.perform();
            logger.info("Scrolled to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    protected void checkIsElementInvisible(WebElement webElement) {
        webDriverWait_15.until(ExpectedConditions.invisibilityOf(webElement));
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }


    protected void selectCheckbox(WebElement webElement) {
        if (webElement.isSelected() == false) {
            webElement.click();
            logger.info("Checkbox is selected");
        } else {
            logger.info("Checkbox is already selected");
        }
    }

    protected void unselectCheckbox(WebElement webElement) {
        if (webElement.isSelected() == true) {
            webElement.click();
            logger.info("Checkbox is unselected");
        } else {
            logger.info("Checkbox is already unselected");
        }
    }

    protected void setCheckboxState(WebElement webElement, String neededState) {
        if (neededState.equalsIgnoreCase("check")) {
            selectCheckbox(webElement);
        } else if (neededState.equals("uncheck")) {
            unselectCheckbox(webElement);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
        }
    }



}
