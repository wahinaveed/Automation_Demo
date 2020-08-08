package com.grip.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	public static int i = 1;
	public static RemoteWebDriver driver;
	// public static WebDriver driver;
	public static String BROWSER_TYPE = AccessData.CONFIG.get("browser");

	public void initializeDriver() {
		if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
			driver = new ChromeDriver();
		}
		System.out.println("Driver initialized successfully-");
		driver.get(AccessData.CONFIG.get("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void takeSnap() {
		String clsName = this.getClass().getSimpleName();
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File(System.getProperty("user.dir") + "/snaps/" + clsName + i + ".png"));
		} catch (Exception e) {

		}
		i++;

	}

	public void runInSeleniumGrid() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(BROWSER_TYPE);
		driver = new RemoteWebDriver(new URL("http://18.224.183.177:4455/wd/hub"), dc);
		driver.get(AccessData.CONFIG.get("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		takeSnap();
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