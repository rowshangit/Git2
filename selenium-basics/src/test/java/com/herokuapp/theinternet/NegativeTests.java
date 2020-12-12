package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
	
	@Test(priority=1, enabled=false)
	public void incorrectUsernameTest() {

		System.out.println("Starting incorrectUsernameTest");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		String url = "http://the-internet.herokuapp.com/login";
		browser.get(url);
		System.out.println("Page is Open");
		sleep();

		browser.manage().window().maximize();
		sleep();

		browser.findElement(By.id("username")).sendKeys("tomsmith1");
		sleep();

		browser.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");
		sleep();

		browser.findElement(By.cssSelector("#login > button > i")).click();
		sleep();

		WebElement errorMessage = browser.findElement(By.id("flash"));
		String expectedErrorMessage = "Your username is invalid!";
		String actualErrorMessage = errorMessage.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual Error Message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);
		
		browser.close();
	}
	
		@Test(priority=2)
		public void incorrectPasswordTest() {

			System.out.println("Starting incorrectPasswordTest");
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			WebDriver browser = new FirefoxDriver();
			String url = "http://the-internet.herokuapp.com/login";
			browser.get(url);
			System.out.println("Page is Open");
			sleep();

			browser.manage().window().maximize();
			sleep();

			browser.findElement(By.id("username")).sendKeys("tomsmith");
			sleep();

			browser.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword");
			sleep();

			browser.findElement(By.cssSelector("#login > button > i")).click();
			sleep();

			WebElement errorMessage = browser.findElement(By.id("flash"));
			String expectedErrorMessage = "Your password is invalid!";
			String actualErrorMessage = errorMessage.getText();
			Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
					"Actual Error Message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
							+ expectedErrorMessage);
			
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
