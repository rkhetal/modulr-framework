package com.blenheimchalcot.modulr.framework.pageobject;

import com.blenheimchalcot.modulr.framework.configuration.hooks.Hook;
import com.blenheimchalcot.modulr.framework.utilities.ElementRetry;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ResetAccessPage extends Hook {

    private final Logger log = Logger.getLogger(ResetAccessPage.class);

    public ResetAccessPage() {
        log.info("Page Load Time Out for Login Page : " + PAGE_LOAD_TIMEOUT_PO);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(PAGE_LOAD_TIMEOUT_PO), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(EXPLICIT_WAIT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='usernameInput']")
    WebElement userName;

    @FindBy(xpath = "//span[contains(text(),'Request a reset')]")
    WebElement requestAReset;

    private void clickResetRequestButton() throws InterruptedException {
        ElementRetry.click(requestAReset, wait);
    }

    private void enterUserID(String userID) throws InterruptedException {
        ElementRetry.enterText(userName, wait, userID);
    }

    public void resetPasswordString(String userID) throws InterruptedException {
        enterUserID(userID);
        clickResetRequestButton();
    }
}
