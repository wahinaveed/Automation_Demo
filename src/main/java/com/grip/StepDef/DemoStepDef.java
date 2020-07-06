package com.grip.StepDef;

import com.grip.utils.AccessData;

import cucumber.api.java.en.Given;

public class DemoStepDef {
	
	@Given("^User logs into the applictaion$")
	public void user_logs_into_the_applictaion()  {
		System.out.println("Message is - User logs into the applictaion");
		System.out.println("Browser is - " + AccessData.CONFIG.get("browser"));
		System.out.println("Username is - " + AccessData.TESTDATA.get("username"));
	}

	@Given("^verify Home page$")
	public void verify_Home_page()  {
		System.out.println("Message is - verify Home page");
	}

}
