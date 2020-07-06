package com.grip.RunnerFiles;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", 
					glue="com.grip.StepDef",
					tags="@Tag1",
					plugin= {"pretty",
							"html:target/cucumber-html-report", 
							"json:target/cucumber.json", 
							"json:target/cucumber.xml"},
					monochrome = true)
@Test
public class Runner1 extends AbstractTestNGCucumberTests{

}
