package com.grip.StepDef;

import com.grip.PageClass.LoginPage;

import cucumber.api.java.en.Given;

public class DemoStepDef {
	LoginPage loginPage = new LoginPage();
	
	
	@Given("^User logs into the applictaion$")
	public void user_logs_into_the_applictaion()  {
		loginPage.login();
	}

	@Given("^verify Home page$")
	public void verify_Home_page()  {
		loginPage.logout();
	}

}
