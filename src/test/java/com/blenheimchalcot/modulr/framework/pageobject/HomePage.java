package com.blenheimchalcot.modulr.framework.pageobject;

import com.blenheimchalcot.modulr.framework.configuration.hooks.Hook;
import com.blenheimchalcot.modulr.framework.utilities.ApplicationContext;
import com.blenheimchalcot.modulr.framework.utilities.ElementRetry;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class HomePage extends Hook {

    private final Logger log = Logger.getLogger(HomePage.class);

    public HomePage() {
        log.info("Page Load Time Out for Login Page : " + PAGE_LOAD_TIMEOUT_PO);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(PAGE_LOAD_TIMEOUT_PO), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(EXPLICIT_WAIT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[contains(text(),'Accounts')]")
    WebElement accountsTitle;


    public void verifyTitle() throws InterruptedException {
        ElementRetry.verifyIfDisplayed(accountsTitle, wait);
    }
}
