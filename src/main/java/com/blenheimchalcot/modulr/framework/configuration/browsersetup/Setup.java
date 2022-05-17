package com.blenheimchalcot.modulr.framework.configuration.browsersetup;

import com.blenheimchalcot.modulr.framework.configuration.hooks.Hook;
import com.blenheimchalcot.modulr.framework.utilities.ApplicationContext;
import com.blenheimchalcot.modulr.framework.utilities.ReadProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Setup extends Hook {

    private WebDriver driver = null;

    private final Logger log = Logger.getLogger(Setup.class);

    public WebDriver setupBrowser() {
        String browser = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
                PROJECT_PROPERTY_FILE_LOCATION,
                "browser");
        log.info("Executing on : " + browser);

        String headless = ReadProperties.getProperty(PROJECT_PROPERTIES_FILE_NAME,
                PROJECT_PROPERTY_FILE_LOCATION,
                "headless-execution");
        log.info("Headless Execution : " + headless);

        log.info("System OS : " + SYSTEM_OS);

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-notifications");

            if (headless.equalsIgnoreCase("Yes")) {
                // Start - To run chrom headless

                options.addArguments("headless");

                // End - To headless
            }

            HashMap<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            //Driver path based on operating system
            if (SYSTEM_OS.indexOf("win") >= 0) {
                log.info("Chrome Driver Path : " + CHROME_DRIVER_PATH);
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            } else {
                log.error("Unknown OS");
                Assert.fail("OS Configuration not done : " + SYSTEM_OS);
            }
            //System.setProperty("webdriver.chrome.driver", driverPathChrome);
            driver = new ChromeDriver(capabilities);
            // driver = new RemoteWebDriver(new
            // URL("http://localhost:5566/wd/hub"), capabilities);
            log.info("Implicit Wait Value : " + IMPLICIT_WAIT_TIME);
            driver.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICIT_WAIT_TIME), TimeUnit.SECONDS);
            log.info("***********Chrome Browser Initiated***********");

        }

        log.info("Launching : " + APPLICATION_URL);
        driver.navigate().to(APPLICATION_URL);

        log.info("Page Load TimeOut Value : " + PAGE_LOAD_TIMEOUT);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(PAGE_LOAD_TIMEOUT), TimeUnit.SECONDS);
        // JavaScriptExecutor.waitUntilPageLoad(driver);
        //	driver.manage().window().maximize();
        return driver;
    }


}
