package com.grip.PageClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.grip.utils.BaseSeleniumMethods;
import com.grip.utils.DriverFactory;

import junit.framework.Assert;

public class LoginPage extends BaseSeleniumMethods {
	DriverFactory driverFactory = new DriverFactory();

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "usernameFiel")
	public WebElement userName;

	@FindBy(id = "passwordField")
	public WebElement password;

	@FindBy(xpath = "//button[@data-ga-track=\"spa-event|login|login|Save\"]")
	public WebElement login;

	@FindBy(xpath = "//div[text()='UPDATE PROFILE']")
	public WebElement updateProfile;

	@FindBy(xpath = "//span[@class='fullname']")
	public WebElement fullName;

	@FindBy(xpath = "//a[contains(@href,'HomePage')]")
	public WebElement myNaukri;

	@FindBy(xpath = "//a[contains(@href,'logout')]")
	public WebElement logout;

	@FindBy(xpath = "(//button[@title='Create account on Naukri.com for free'])[1]")
	public WebElement registerForFree;

	public void login() {
		try {
			waitForElementTeBeVisible(userName);
			userName.sendKeys("wahi16namo@gmail.com");
			password.sendKeys("Welcome@123");
			login.click();
			waitForElementTeBeVisible(updateProfile);
			updateProfile.click();
			waitForElementTeBeVisible(fullName);
			Assert.assertTrue(fullName.getText().contains("Mohamed Wahid M"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to login to the application - " + e.getMessage());
		}
	}

	public void logout() {
		try {
			Actions actions = new Actions(getdriver());
			actions.moveToElement(myNaukri).build().perform();
			waitForElementTeBeVisible(logout);
			logout.click();
			waitForElementTeBeVisible(registerForFree);
			Assert.assertTrue(registerForFree.getText().contains("Register"));
			System.out.println("Verifivation completed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to logout form the application - " + e.getMessage());
		}
	}
}
