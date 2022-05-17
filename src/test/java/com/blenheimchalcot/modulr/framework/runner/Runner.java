/**
 * @author rahul.khetal
 */
package com.blenheimchalcot.modulr.framework.runner;

import com.blenheimchalcot.modulr.framework.testng.CustomAbstractTestNGCucumberTestsWithTag;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:featurefiles"},
        glue = {"com/blenheimchalcot/modulr/framework/stepdefinition",
                "com/blenheimchalcot/modulr/framework/configuration/hooks"},
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "json:target/customgenericrunnerwithtag.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Runner extends CustomAbstractTestNGCucumberTestsWithTag {

}