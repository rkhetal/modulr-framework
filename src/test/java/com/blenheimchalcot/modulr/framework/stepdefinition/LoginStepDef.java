package com.blenheimchalcot.modulr.framework.stepdefinition;

import com.blenheimchalcot.modulr.framework.configuration.hooks.Hook;
import com.blenheimchalcot.modulr.framework.pageobject.HomePage;
import com.blenheimchalcot.modulr.framework.pageobject.LoginPage;
import com.blenheimchalcot.modulr.framework.pageobject.ResetAccessPage;
import com.blenheimchalcot.modulr.framework.utilities.ApplicationContext;
import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

import java.util.List;

public class LoginStepDef {

    private final Logger log = Logger.getLogger(LoginStepDef.class);

    ApplicationContext applicationContext;

    @Inject
    public LoginStepDef(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ResetAccessPage resetAccessPage = new ResetAccessPage();

    @Given("As a Modulr customer, I try to login into the Modulr Customer Portal with User ID as {string} and Password as {string}")
    public void asAModulrCustomerITryToLoginIntoTheModulrCustomerPortalWithUserIDAsAndPasswordAs(String userName, String password) throws InterruptedException {
        applicationContext.applicationDetailsMap.put("userName", userName);
        applicationContext.applicationDetailsMap.put("password", password);
        loginPage.login(userName, password);
    }

    @Then("I should not be allowed to login and I should get a message saying {string}")
    public void iShouldNotBeAllowedToLoginAndIShouldGetAMessageSaying(String errorMessage) throws InterruptedException {
        String emptyField = null;
        if (applicationContext.applicationDetailsMap.get("userName").equals("") && applicationContext.applicationDetailsMap.get("password").equals("")) {
            emptyField = "Both";
        } else if (applicationContext.applicationDetailsMap.get("password").equals("")) {
            emptyField = "Password";
        } else if (applicationContext.applicationDetailsMap.get("userName").equals("")){
            emptyField = "User Name";
        }
        loginPage.verifyErrorMessage(errorMessage, emptyField);
    }

    @Given("As a Modulr customer, I click on Forgotten Password link")
    public void asAModulrCustomerIClickOnForgottenPasswordLink() throws InterruptedException {
        loginPage.clickOnForgottenPassword();
    }

    @Then("I should be able to manage my Modulr accounts")
    public void iShouldBeAbleToManageMyModulrAccounts() throws InterruptedException {
        homePage.verifyTitle();
    }

    @And("enter the user name as {string}")
    public void enterTheUserNameAs(String userID) throws InterruptedException {
        resetAccessPage.resetPasswordString(userID);
        Thread.sleep(2000);
    }
}
