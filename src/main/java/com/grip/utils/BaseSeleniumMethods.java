package com.grip.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class BaseSeleniumMethods extends DriverFactory {

	private static final Logger LOGGER = Logger.getLogger(BaseSeleniumMethods.class);
	private static final long EXPLICIT_TIMEOUT = AccessData.CONFIG.getInt("EXPLICIT_TIMEOUT");
	public static JavascriptExecutor js;
	WebDriver drv = getdriver();

	public void waitForPageLoaded(long timeout) {
		js = (JavascriptExecutor) drv;
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public void waitForElementTeBeVisible(WebElement webEle) {
		waitForElementTeBeVisible(webEle, EXPLICIT_TIMEOUT);
	}

	public void waitForElementTeBeVisible(WebElement webEle, long timeout) {
		try {
			waitForPageLoaded(timeout);
			ExpectedCondition<?> waitCondition = null;
			waitCondition = ExpectedConditions.visibilityOf(webEle);
			Wait<WebDriver> wait = new WebDriverWait(drv, timeout, AccessData.CONFIG.getInt("RETRY_TIMEOUT"))
					.ignoring(WebDriverException.class).ignoring(NoSuchSessionException.class);
			wait.until(waitCondition);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("waitUntil: NoSuchElementException e..." + webEle.toString() + " - " + e.getMessage());
		} catch (TimeoutException e) {
			e.printStackTrace();
			Assert.fail("waitUntil: TimeoutException e..." + webEle + " - " + e.getMessage());
		} catch (WebDriverException e) {
			e.printStackTrace();
			Assert.fail("waitUntil: TimeoutException e..." + webEle + " - " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("waitUntil: e..." + webEle + " - " + e.getMessage());
		}
	}

}
