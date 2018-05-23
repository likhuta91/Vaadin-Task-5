package com.gp.vaadin.demo.hotel;

import org.junit.Assert;
import org.junit.Test;

public class HotelUITest extends AbstractUITest {

	@Test
	public void addFirstHotelTest() throws InterruptedException {
		addHotel("First name", " First address", "1", "19.05.2018", "70", "Hotel", "First.by", "no description", true);
	}

	@Test
	public void addSecondCategoryTest() throws InterruptedException {
		addHotel("Second name", " Second address", "2", "20.05.2018", null, "Hostel", "Second.by", "no description",
				false);
	}

	@Test
	public void addThirdCategoryTest() throws InterruptedException {
		addHotel("Third name", " Third address", "3", "21.05.2018", null, "Appartments", "Third.by", "no description",
				false);
	}

	private void addHotel(String hotelName, String hotelAddress, String hotelRating, String hotelDate,
			String hotelPaymentValue, String hotelCategory, String hotelUrl, String hotelDescription,
			boolean creditCardCheck) throws InterruptedException {
		Thread.sleep(500);

		driver.get(BASE_URL);
		int gridSizeBeforeAddingHotel = takeTableSize(
				"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div/div/div[3]/table/tbody", "tr");

		buttonClick("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[3]/div/div[5]/div");

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[1]",
				"input[class=\"v-textfield v-widget v-required v-textfield-required v-has-width\"", hotelName);

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[2]",
				"input[class=\"v-textfield v-widget v-required v-textfield-required v-has-width\"", hotelAddress);

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[3]",
				"input[class=\"v-textfield v-widget v-required v-textfield-required v-has-width\"]", hotelRating);

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[4]",
				"input[class=\"v-textfield v-datefield-textfield\"]", hotelDate);

		if (creditCardCheck) {
			checkBoxClick(
					"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[5]/td[3]",
					"span[class=\"v-checkbox v-widget Guaranty v-checkbox-Guaranty Deposit v-checkbox-Deposit\"]",
					"label");

			setTextFieldValue(
					"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[5]/td[3]",
					"input[class=\"v-textfield v-widget v-has-width\"]", hotelPaymentValue);
		} else {
			checkBoxClick(
					"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[5]/td[3]",
					"span[class=\"v-checkbox v-widget\"", "label");
		}

		setSelectedItem("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[6]",
				"select[class=\"v-select-select\"]", hotelCategory);

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[7]",
				"input[class=\"v-textfield v-widget v-required v-textfield-required v-has-width\"", hotelUrl);

		setTextFieldValue("//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[8]",
				"textarea[class=\"v-textarea v-widget v-has-width\"", hotelDescription);

		buttonClick(
				"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div[3]/div/table/tbody/tr[9]/td[3]/div/div/div[1]");

		int gridSizeAfterAddingHotel = takeTableSize(
				"//*[@id=\"ROOT-2521314\"]/div/div[2]/div/div/div[5]/div/div/div/div/div[3]/table/tbody", "tr");

		Assert.assertEquals(gridSizeBeforeAddingHotel + 1, gridSizeAfterAddingHotel);
	}

}
