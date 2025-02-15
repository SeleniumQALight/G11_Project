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
    protected WebDriver webDriver; //create constructor
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи, описані FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    // method for select visible text in dropdown
    protected void selectTextInDD(WebElement dropDownElement, String textForSelect) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in DropDown " + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for select value in dropdown
    protected void selectValueInDD(WebElement dropDownElement, String valueInDD) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByValue(valueInDD);
            logger.info(valueInDD + " was selected in DropDown " + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for cleaning and entering text into the element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
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

    // method for clicking on the element
    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            logger.error("Cannot work with element " + elementName);
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(String locator) {
        try {
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not found ");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found ");
            return false;
        }
    }

    // check if the element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisible(locator));
    }

    // check if the element is not visible
    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    protected void checkIsElementNotVisible(String locator) {
        Assert.assertFalse("Element is visible", isElementVisible(locator));
    }

    // checkTextInElement method
    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element " + getElementName(webElement) + " is not expected", text, webElement.getText());
        logger.info("Text in element " + getElementName(webElement) + " is expected");
    }

    //acceptAlert method
    protected void acceptAlert() {
        try {
            webDriverWait10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
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
            logger.info("Scroll to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // open new tab using JS
    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    // method for select CheckBox
    protected void selectCheckBox(WebElement checkBox, String nameOfCheckBox) {
        try {
            if (!checkBox.isSelected()) {
                checkBox.click();
                logger.info("CheckBox " + nameOfCheckBox + " was selected");
            } else {
                logger.info("CheckBox " + nameOfCheckBox + " is already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for unselect CheckBox
    protected void unselectCheckBox(WebElement checkBox, String nameOfCheckBox) {
        try {
            if (checkBox.isSelected()) {
                checkBox.click();
                logger.info("CheckBox " + nameOfCheckBox + " was unselected");
            } else {
                logger.info("CheckBox " + nameOfCheckBox + " is already unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for setting a given state of a checkbox (if "check"  selectCheckBox, if "uncheck" unselectCheckBox)
    protected void setCheckBoxState(WebElement checkBox, String state, String nameOfCheckBox) {
        switch (state) {
            case "check":
                selectCheckBox(checkBox, nameOfCheckBox);
                break;
            case "uncheck":
                unselectCheckBox(checkBox, nameOfCheckBox);
                break;
            default:
                logger.info("State " + state + " is not correct");
                break;
        }
    }

}
