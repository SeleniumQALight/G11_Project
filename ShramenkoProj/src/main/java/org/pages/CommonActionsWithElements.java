package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
        //ініціалізує елементи, описані FindBy
        //інакше вони будуть Null
        //і вони також оновляться в той момент, коли до них буде звертання
    }

    //method for select visible text in dropdown
    protected void selectTextInDD(WebElement dropDownElement, String textForSelect) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in DropDown "
                    + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for select value in dropdown
    protected void selectValueInDD(WebElement dropDownElement, String valueInDD) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByValue(valueInDD);
            logger.info(valueInDD + " value was selected in DropDown "
                    + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    //method for clearing and entering text into the element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //це метод для logger, щоб записи були більш зрозумілі, якщо на сайті присутнє ім'я елементу

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
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            logger.error("Can not work with element " + elementName);
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + " Element isn't displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    protected boolean isElementVisible(String locator) {
        try {
            return  isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    //check if element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue(getElementName(webElement) + " Element isn't visible", isElementVisible(webElement));
    }
    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element isn't visible", isElementVisible(locator));
    }

    //check if element is not visible
    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse(getElementName(webElement) + " Element is visible", isElementVisible(webElement));
    }

    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element " + getElementName(webElement) + " is not expected", text, webElement.getText());
        logger.info("Text in element " + getElementName(webElement) + " is expected");
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void makeCheckBoxSelected(WebElement webElement) {
        if (webElement.isSelected()) {
            logger.info("Checkbox is already checked");
        } else {
            webElement.click();
            logger.info("Checkbox is checked");
        }
    }

    protected void makeCheckBoxNotSelected(WebElement webElement) {
        if (webElement.isSelected()) {
            webElement.click();
            logger.info("Checkbox is unchecked");
        } else {
            logger.info("Checkbox isn't already selected");
        }
    }

    protected void checkboxState(WebElement webElement, String neededState) {
        switch (neededState) {
            case "check":
                makeCheckBoxSelected(webElement);
                break;
            case "uncheck":
                makeCheckBoxNotSelected(webElement);
                break;
        }
    }

}
