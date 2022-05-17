package com.blenheimchalcot.modulr.framework.pageobject;

import com.blenheimchalcot.modulr.framework.configuration.hooks.Hook;
import com.blenheimchalcot.modulr.framework.utilities.ElementRetry;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Hook {

    private final Logger log = Logger.getLogger(LoginPage.class);

    public LoginPage() {
        log.info("Page Load Time Out for Login Page : " + PAGE_LOAD_TIMEOUT_PO);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(PAGE_LOAD_TIMEOUT_PO), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(EXPLICIT_WAIT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='username-inp']")
    WebElement userName;

    @FindBy(xpath = "//input[@id='password-inp']")
    WebElement password;

    @FindBy(xpath = "//input[@id='usernameInput']")
    WebElement forgotPasswordPageUserName;

    @FindBy(xpath = "//input[@id='password-inp']")
    WebElement forgotPasswordPagePassword;

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    WebElement signInButton;

    @FindBy(xpath = "//div[contains(text(),'is incorrect.')]")
    WebElement errorMessage;

    @FindBy(xpath = "//app-error-message[contains(@data-qa,'signin-inp-username-error')]/div")
    WebElement fieldRequiredMessageUserName;

    @FindBy(xpath = "//app-error-message[contains(@data-qa,'signin-inp-password-error')]/div")
    WebElement fieldRequiredMessagePassword;

    @FindBy(linkText = "Forgotten password")
    WebElement forgotPassword;

    private void enterUserID(String userID) throws InterruptedException {
        ElementRetry.enterText(userName, wait, userID);
    }

    private void enterPassword(String pwd) throws InterruptedException {
        ElementRetry.enterText(password, wait, pwd);
    }

    private void clickSubmitButton() throws InterruptedException {
        ElementRetry.click(signInButton, wait);
    }

    private void clickForgotPasswordButton() throws InterruptedException {
        ElementRetry.click(forgotPassword, wait);
    }

    public void verifyErrorMessage(String errorText, String emptyField) throws InterruptedException {
        String message = null;
        if (errorText.contains("incorrect")) {
            message = ElementRetry.getText(errorMessage, wait);
        } else {
            if (emptyField.equalsIgnoreCase("User Name")) {
                message = ElementRetry.getText(fieldRequiredMessageUserName, wait);
            } else if (emptyField.equalsIgnoreCase("Password")) {
                message = ElementRetry.getText(fieldRequiredMessagePassword, wait);
            } else {
                message = ElementRetry.getText(fieldRequiredMessageUserName, wait);
                Assert.assertEquals(errorText, message);
                message = ElementRetry.getText(fieldRequiredMessagePassword, wait);
            }
        }
        Assert.assertEquals(errorText, message);
    }

    public void login(String userID, String pwd) throws InterruptedException {
        enterUserID(userID);
        enterPassword(pwd);
        clickSubmitButton();
    }

    public void clickOnForgottenPassword() throws InterruptedException {
        clickForgotPasswordButton();
    }
}
