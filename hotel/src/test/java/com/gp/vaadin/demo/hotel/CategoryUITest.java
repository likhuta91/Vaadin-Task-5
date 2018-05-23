package com.gp.vaadin.demo.hotel;

import org.junit.Assert;
import org.junit.Test;

public class CategoryUITest extends AbstractUITest {

	@Test
	public void addFirstCategoryTest() throws InterruptedException {
		addCategory("Simple House");
	}

	@Test
	public void addSecondCategoryTest() throws InterruptedException {
		addCategory("Hospice");
	}

	@Test
	public void addThirdCategoryTest() throws InterruptedException {
		addCategory("Home");
	}

	private void addCategory(String categoryName) throws InterruptedException {
		Thread.sleep(500);
		driver.get(BASE_URL);

		buttonClick("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[1]/div/span[2]");

		int gridSizeBeforeAddingCategory = takeTableSize(
				"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div/div[3]", "tr");

		buttonClick("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[3]/div/div[1]");

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div[3]/div/table/tbody/tr[1]",
				"input[class=\"v-textfield v-widget v-required v-textfield-required v-has-width\"", categoryName);

		buttonClick(
				"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div[3]/div/table/tbody/tr[2]/td[3]/div/div/div[1]");

		int gridSizeAfterAddingCategory = takeTableSize(
				"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div/div[3]", "tr");
		Assert.assertEquals(gridSizeBeforeAddingCategory + 1, gridSizeAfterAddingCategory);

	}

}
