package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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
        PageFactory.initElements(webDriver, this); // ініціалізує елементи описані FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    // method for select visible text in dropdown
    protected void selectTextInDropdown(WebElement dropdown, String text) {
        try {
            Select optionsFromDropdown = new Select(dropdown);
            optionsFromDropdown.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropdown, String valueDD) {
        try {
            Select optionsFromDropdown = new Select(dropdown);
            optionsFromDropdown.selectByValue(valueDD);
            logger.info(valueDD + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for cleaning and entering text into element
    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into element" + getElementName(webElement));
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

    // method for clicking on element
    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info("Element " + elementName + " was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element " + elementName + " was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //checkTextInElement
    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element is not expected", text, webElement.getText());
        logger.info("Text in element is expected");
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    public boolean isElementVisible(String locator) {
        try {
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    // method for checking visibility of element
    public boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element " + getElementName(webElement) + " is visible");
            } else {
                logger.info("Element " + getElementName(webElement) + " is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    // method for checking visibility of element
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element " + getElementName(webElement) + " is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisible(locator));
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
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
        boolean currentState = webElement.isSelected();
        if (neededState.equals("check")) {
            selectCheckbox(webElement);
        } else if (neededState.equals("uncheck")) {
            unselectCheckbox(webElement);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
        }
    }

    //acceptAlert
    protected void acceptAlert() {
        try {
            webDriverWait_10.until(ExpectedConditions.alertIsPresent());
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
            logger.info("Scrolled to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    // open new tab using JS
    protected void openNewTab() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

}
