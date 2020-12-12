package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void logintest() {
		System.out.println("Starting loginTest");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		String url = "http://the-internet.herokuapp.com/login";
		browser.get(url);
		System.out.println("Page is Open");
		sleep();
		browser.manage().window().maximize();
		sleep();
		browser.findElement(By.id("username")).sendKeys("tomsmith");
		sleep();
		browser.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");
		sleep();
		browser.findElement(By.cssSelector("#login > button > i")).click();
		sleep();
		String expectedurl = "http://the-internet.herokuapp.com/secure";
		String actualurl = browser.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual URL is NOT the SAME as expected");

		WebElement logoutButton = browser
				.findElement(By.xpath("//div[@id='content']//a[@href='/logout']/i[@class='icon-2x icon-signout']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Log Out Button is not Visible");

		WebElement successMessage = browser.findElement(By.cssSelector("#flash"));
		String excpectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		// Assert.assertEquals(actualMessage, excpectedMessage, "Actual Message is NOT
		// the SAME");
		Assert.assertTrue(actualMessage.contains(excpectedMessage),
				"Actual Message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message:" + excpectedMessage);

		System.out.println("Logout Button Visible");

		browser.close();

	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
