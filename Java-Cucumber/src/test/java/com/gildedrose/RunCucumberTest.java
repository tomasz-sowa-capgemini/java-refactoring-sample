package com.gildedrose;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Test runner for executing Cucumber feature files.
 * <p>
 * This class uses the JUnit runner to integrate Cucumber tests.
 * </p>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "com.gildedrose.stepdefinitions",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class RunCucumberTest {
    // This class serves as an entry point for Cucumber tests.
}