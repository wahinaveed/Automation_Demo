package com.grip.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	public static WebDriver driver;
	public static String BROWSER_TYPE = AccessData.CONFIG.get("browser");

	public void initializeDriver() {
		switch (BROWSER_TYPE) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		default:
			break;
		}
		System.out.println("Driver initialized successfully-");
		driver.get(AccessData.CONFIG.get("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public WebDriver getdriver() {
		if (driver != null)
			return driver;
		else
			throw new RuntimeException("driver is null-");
	}

	public void quitDriver() {
		driver.quit();
	}
	
	
}