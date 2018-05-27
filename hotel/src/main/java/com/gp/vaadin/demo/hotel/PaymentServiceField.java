package com.gp.vaadin.demo.hotel;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.server.Sizeable;

@SuppressWarnings("serial")
public class PaymentServiceField extends CustomField<PaymentMethod> {

	final CheckBox creditCard = new CheckBox("Credit Card");
	final CheckBox cash = new CheckBox("Cash");
	final TextField creditCardValue = new TextField();
	Label cashMessage = new Label("Payment will be made directly in the hotel");

	private PaymentMethod value;
	private String caption;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public PaymentServiceField(String caption) {
		super();
		this.caption = caption;
	}

	public PaymentServiceField() {
		super();
	}

	public void setValue(PaymentMethod value) {
		this.value = value;
	}

	@Override
	public PaymentMethod getValue() {
		return value;
	}

	@Override
	protected Component initContent() {
		super.setCaption(caption);
		creditCardValue.setVisible(false);
		cashMessage.setVisible(false);
		HorizontalLayout paymentValue = new HorizontalLayout();
		HorizontalLayout paymentMethod = new HorizontalLayout();
		VerticalLayout content = new VerticalLayout();
		cashMessage.setWidth(100, Sizeable.Unit.PERCENTAGE);

		content.setMargin(false);
		paymentMethod.setMargin(false);
		paymentValue.setMargin(false);

		paymentValue.setWidth(100, Sizeable.Unit.PERCENTAGE);
		paymentMethod.setWidth(100, Sizeable.Unit.PERCENTAGE);

		content.setWidth(100, Sizeable.Unit.PERCENTAGE);
		content.setHeight(70, Sizeable.Unit.PIXELS);
		creditCardValue.setWidth(100, Sizeable.Unit.PERCENTAGE);

		paymentValue.addComponents(creditCardValue, cashMessage);
		paymentMethod.addComponents(creditCard, cash);
		content.addComponents(paymentMethod, paymentValue);
		content.setExpandRatio(paymentMethod, 30);
		content.setExpandRatio(paymentValue, 50);

		creditCard.setDescription("Credit card");
		cash.setDescription("Cash");
		creditCard.setStyleName("Guaranty Deposit");

		creditCard.addValueChangeListener(e -> {
			value.setCreditCard(e.getValue());
			if (e.getValue()) {
				cash.setValue(false);
				creditCardValue.setVisible(true);
			} else {
				creditCardValue.setVisible(false);
			}
		});

		cash.addValueChangeListener(e -> {
			value.setCash(e.getValue());
			if (e.getValue()) {
				creditCard.setValue(false);
				value.setCreditCardValue("");
				cashMessage.setVisible(true);
			} else {
				cashMessage.setVisible(false);
			}
		});

		creditCardValue.addValueChangeListener(e -> {
			value.setCreditCardValue(e.getValue());
			Notification.show("It was " + e.getOldValue().toString() + "%. Has become " + e.getValue() + "%",
					Type.HUMANIZED_MESSAGE);
		});

		return content;
	}

	private void updateValues() {
		super.setCaption(caption);
		if (getValue() != null) {
			creditCard.setValue(value.getCreditCard());
			cash.setValue(value.getCash());
			creditCardValue.setValue(value.getCreditCardValue());
		}
	}

	@Override
	public void doSetValue(PaymentMethod value) {
		if (value != null) {
			this.value = new PaymentMethod(value.getCreditCard(), value.getCash(), value.getCreditCardValue());
		} else {
			this.value = new PaymentMethod(false, false, "");
		}
		updateValues();

	}

}
