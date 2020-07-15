package com.grip.RunnerFiles;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.grip.utils.DriverFactory;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/" }, glue = "com.grip.StepDef", tags = "@Tag1", plugin = { "pretty",
		"html:target/cucumber-html-report", "json:target/cucumber-json-report.json", "json:target/cucumber.xml",
		"rerun:target/failed_Regular_txt" }, monochrome = true, strict = true, dryRun = false)
@Test
public class Runner1 extends AbstractTestNGCucumberTests {
	DriverFactory driverFactory = new DriverFactory();

	@BeforeTest
	public void openBrowser() {
		driverFactory.initializeDriver();
	}

	@AfterMethod
	public void quitDriver() {
		driverFactory.quitDriver();
	}
	
	@AfterTest
	public void logout() {
		driverFactory.quitDriver();
	}
	
	

}
