package com.blenheimchalcot.modulr.framework.configuration.hooks;

import com.blenheimchalcot.modulr.framework.configuration.browsersetup.Setup;
import com.blenheimchalcot.modulr.framework.utilities.ApplicationContext;
import com.blenheimchalcot.modulr.framework.utilities.ReadProperties;
import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hook {
    private final Logger log = Logger.getLogger(Hook.class);

    public static final String PROJECT_PROPERTIES_FILE_NAME = "projectconfig.properties";
    public static final String FRAMEWORK_PROPERTIES_FILE_NAME = "config.properties";
    public static final String PROJECT_PROPERTY_FILE_LOCATION = "\\src\\test\\resources\\property-files\\";
    public static final String FRAMEWORK_PROPERTY_FILE_LOCATION = "\\src\\main\\resources\\framework-property-files\\";
    public static final String SYSTEM_OS = System.getProperty("os.name").toLowerCase();
    public static final String USER_DIRECTORY = System.getProperty("user.dir");

    public static final String CHROME_DRIVER_PATH = USER_DIRECTORY + ReadProperties.getProperty(FRAMEWORK_PROPERTIES_FILE_NAME,
            FRAMEWORK_PROPERTY_FILE_LOCATION,
            "chrome-driver");

    public static final String IMPLICIT_WAIT_TIME = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
            PROJECT_PROPERTY_FILE_LOCATION,
            "implicit-wait");

    public static final String APPLICATION_URL = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
            PROJECT_PROPERTY_FILE_LOCATION,
            "web-application-url");

    public static final String PAGE_LOAD_TIMEOUT = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
            PROJECT_PROPERTY_FILE_LOCATION,
            "page-load-timeout-application");

    public static final String PAGE_LOAD_TIMEOUT_PO = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
            PROJECT_PROPERTY_FILE_LOCATION,
            "page-load-timeout-po");

    public static final String EXPLICIT_WAIT_TIMEOUT = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
            PROJECT_PROPERTY_FILE_LOCATION,
            "webdriver-wait");

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void setup() {
        Setup setup = new Setup();
        this.driver = setup.setupBrowser();
        log.info("**********BROWSER LAUNCHED************");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
        log.info("**********BROWSER CLOSED************");
    }

    @AfterStep
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("Capturing Screenshot...");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            log.info("Adding screenshot to report...");
            //This attach the specified screenshot to the test
            scenario.attach(sourcePath, "image/png", "image");
            //Reporter.addScreenCaptureFromPath(destinationPath.toString());
        }
    }

}

