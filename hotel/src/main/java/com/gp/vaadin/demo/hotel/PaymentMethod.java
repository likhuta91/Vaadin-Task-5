package com.gp.vaadin.demo.hotel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PaymentMethod implements Serializable {

	@Column(name = "CREDIT_CARD", insertable = true, updatable = true)
	private Boolean creditCard = Boolean.FALSE;

	@Column(name = "CASH", insertable = true, updatable = true)
	private Boolean cash = Boolean.FALSE;

	@Column(name = "CREDIT_CARD_VALUE", insertable = true, updatable = true)
	private String creditCardValue;

	public Boolean getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Boolean creditCard) {
		this.creditCard = creditCard;
	}

	public Boolean getCash() {
		return cash;
	}

	public void setCash(Boolean cash) {
		this.cash = cash;
	}

	public String getCreditCardValue() {
		return creditCardValue;
	}

	public void setCreditCardValue(String creditCardValue) {
		this.creditCardValue = creditCardValue;
	}

	public PaymentMethod(Boolean creditCard, Boolean cash, String creditCardValue) {
		super();
		this.creditCard = creditCard;
		this.cash = cash;
		this.creditCardValue = creditCardValue;
	}

	public PaymentMethod() {
		super();
	}

}
