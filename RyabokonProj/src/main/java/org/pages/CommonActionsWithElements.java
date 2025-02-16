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
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //initializes element described in FindBy, this allows flexibility
        //if we come form login page to home page, or login page or profile page this will return the page we need
        //and it allows us to reload the page and have an actual state of the page
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

    //method for select value in dropdown

    protected void selectValueInDD(WebElement dropDownElement, String valueInDD) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByValue(valueInDD);
            logger.info(valueInDD + " value was selected in DropDown " + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //method for clearing and entering text into the element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was input into element" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        try {
            elementName = webElement.getAccessibleName();
        } catch (Exception e) {
            elementName = "";
        }
        return elementName;
    }


    //method for clicking on the element
    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //method for clicking on the element
    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            logger.error("Cannot work with element " + elementName);
            printErrorAndStopTest(e);
        }
    }

    //isElementVisible method

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
                logger.info(getElementName(webElement) + "Element is visible");
            } else {
                logger.info(getElementName(webElement) + "Element is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not found");
            return false;
        }
    }

    //check if the element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue(getElementName(webElement) + " Element is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisible(locator));
    }
//check Text in Element

    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element" + getElementName(webElement) + "is not expected", text, webElement.getText());
        logger.info("Text in element is expected");
    }

    //acceptAlert
    protected void acceptAlert() {
        try {
            webDriverWait10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            logger.error("There is no alert");
            printErrorAndStopTest(e);
        }
    }

    //scroll to element using Actions
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
//open new tab using JS
    protected void openNewTab() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);

    }

    public void selectCheckBox(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox is selected");
        } else {
            logger.info("Checkbox was already selected");

        }
    }

    public void unselectCheckbox(WebElement checkbox) {
        if (checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox is unselected");
        } else {
            logger.info("Checkbox was already unselected");
        }
    }

    public void setCheckboxState(WebElement checkbox, String state) {
        if (state.equals("check")) {
            selectCheckBox(checkbox);
        } else if (state.equals("uncheck")) {
            unselectCheckbox(checkbox);
        } else {
            logger.error("Invalid state: " + state);
        }
    }
}


