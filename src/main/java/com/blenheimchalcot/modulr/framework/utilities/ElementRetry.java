package com.blenheimchalcot.modulr.framework.utilities;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ElementRetry {
    private static Logger log = Logger.getLogger(ElementRetry.class);

    public static void enterText(WebElement element, WebDriverWait wait, String textValue) throws InterruptedException {
        int elementRetry = 1;
        while (elementRetry < 6) {
            log.info("Finding Element : " + element + " :: Search Count : " + elementRetry);
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                element.clear();
                element.sendKeys(textValue);
                break;
            } catch (Exception e) {
                elementRetry++;
                Thread.sleep(100);
                if (elementRetry > 5) {
                    log.error("Element cannot be found even after 5 retries" + element);
                    Assert.fail("Element cannot be found even after 5 retries" + element);
                }
            }
        }
    }

    public static void click(WebElement element, WebDriverWait wait) throws InterruptedException {
        int elementRetry = 1;
        while (elementRetry < 6) {
            log.info("Finding Element : " + element + " :: Search Count : " + elementRetry);
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                element.click();
                break;
            } catch (Exception e) {
                elementRetry++;
                Thread.sleep(100);
                if (elementRetry > 5) {
                    log.error("Element cannot be found even after 5 retries" + element);
                    Assert.fail("Element cannot be found even after 5 retries" + element);
                }
            }
        }
    }

    public static void verifyIfDisplayed(WebElement element, WebDriverWait wait) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.isDisplayed();
        } catch (Exception ex){
            Assert.fail("Element Not Displayed" + element);
        }
    }


    public static String getText(WebElement element, WebDriverWait wait) throws InterruptedException {
        int elementRetry = 1;
        String text = null;
        while (elementRetry < 6) {
            log.info("Finding Element : " + element + " :: Search Count : " + elementRetry);
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                text = element.getText();
                break;
            } catch (Exception e) {
                elementRetry++;
                Thread.sleep(100);
                if (elementRetry > 5) {
                    log.error("Element cannot be found even after 5 retries" + element);
                    Assert.fail("Element cannot be found even after 5 retries" + element);
                }
            }
        }
        return text;
    }


}