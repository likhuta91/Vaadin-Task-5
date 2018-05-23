package com.gp.vaadin.demo.hotel;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AbstractUITest {
	protected WebDriver driver;
	protected static final String BASE_URL = "http://localhost:8080";

	public AbstractUITest() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	}

	@Before
	public void initDriver() {
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	public void setTextFieldValue(String xpath, String cssSelector, String value) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(xpath)).findElement(By.cssSelector(cssSelector));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		element.clear();
		actions.sendKeys(value);
		actions.build().perform();
		Thread.sleep(500);
	}

	public void buttonClick(String xpath) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
		Thread.sleep(500);
	}

	public void checkBoxClick(String xpath, String cssSelector, String tagName) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(xpath)).findElement(By.cssSelector(cssSelector))
				.findElement(By.tagName(tagName));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.build().perform();
		Thread.sleep(500);
	}

	public void setSelectedItem(String xpath, String cssSelector, String value) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(xpath)).findElement(By.cssSelector(cssSelector));
		Select select = new Select(element);
		select.selectByVisibleText(value);
		Thread.sleep(500);
	}

	public int takeTableSize(String xpath, String tagName) {
		return driver.findElement(By.xpath(xpath)).findElements(By.tagName(tagName)).size();
	}

}
