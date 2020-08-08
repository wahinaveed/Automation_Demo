package com.grip.PageClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class Test extends AbstractPage {
	Test test = new Test(getDriver());
	public Test(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "usernameField")
	public ExtendedWebElement userName;
	
	public  void apple() {
		test.open();
		userName.scrollTo();
		userName.select(1);
	}

}
