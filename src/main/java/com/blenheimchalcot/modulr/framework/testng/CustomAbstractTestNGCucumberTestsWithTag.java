/**
 * @author rahul.khetal
 */

package com.blenheimchalcot.modulr.framework.testng;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class CustomAbstractTestNGCucumberTestsWithTag {

    private final Logger log = Logger.getLogger(CustomAbstractTestNGCucumberTestsWithTag.class);

    private TestNGCucumberRunner testNGCucumberRunner;


    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

//        new ExtentCucumberFormatter(htmlReportOutputFile);
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios
     * with their associated CucumberFeatureWrapper feature.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    public Iterator<Object[]> scenarios() {
        ArrayList<Object[]> modifiedList = new ArrayList<>();
        if (testNGCucumberRunner == null) {
            return modifiedList.iterator();
        }
        modifiedList = filterTheFeature(testNGCucumberRunner.provideScenarios());
        return modifiedList.iterator();
    }

    // data[0][0] ->> PickleEventWrapper
    // data[0][1] ->> CucumberFeatureWrapper

    /*
     * private method, that will return the array list - Contains the feature which we want to execute
     * iterate over 2d object array.
     * using CucumberFeatureWrapper check,
     * if feature is present
     * 	-- add it to the array list
     * else
     *  -- skip
     * */

    private ArrayList<Object[]> filterTheFeature(Object[][] data) {
        log.info(System.getProperty("tagName"));
        String tagValue = System.getProperty("tagName");
        if (tagValue == null || tagValue.isEmpty()) {
            return getFeatureList(data);
        }

        List<String> tagList = Arrays.asList(tagValue.split(","));
        //List<String> featureList = Arrays.asList("Web Element Functions","Working with Java Script Popup");
        ArrayList<Object[]> modifiedList = new ArrayList<>();

        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
                if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
                    for(int tagCount=0; tagCount<pickleEventWrapper.getPickle().getTags().size(); tagCount++) {
                        for (String aTag : tagList) {
                            if (pickleEventWrapper.getPickle().getTags().get(tagCount).contains(aTag)) {
                                modifiedList.add(data[i]);
                            }
                        }
                    }
                }

            }
        }
        return modifiedList;
    }

    private ArrayList<Object[]> getFeatureList(Object[][] data) {
        ArrayList<Object[]> modifiedList = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                modifiedList.add(data[i]);
            }
        }
        return modifiedList;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }
}