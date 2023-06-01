package application;

import java.sql.Date;

/**
 * @author Eudald Daví and Dídac Martínez 
 * 
 * Represents a Credit Card
 */
public class CreditCard {
	private int cardNumber;
	private String iban;
	private int pin;
	private Date expirationDate;
	private int CVV;

	/**
	 * Constructs a new CreditCard object with the specified details.
	 *
	 * @param cardNumber     : The credit card number.
	 * @param iban           : The International Bank Account Number (IBAN).
	 * @param pin            : The personal identification number (PIN) for the
	 *                       credit card.
	 * @param expirationDate : The expiration date of the credit card.
	 * @param CVV            : The Card Verification Value (CVV) of the credit card.
	 */
	public CreditCard(int cardNumber, String iban, int pin, Date expirationDate, int CVV) {
		this.cardNumber = cardNumber;
		this.iban = iban;
		this.pin = pin;
		this.expirationDate = expirationDate;
		this.CVV = CVV;
	}

	/**
	 * Retrieves the credit card number.
	 *
	 * @return The credit card number.
	 */
	public int getCardNumber() {
		return cardNumber;
	}

	/**
	 * Retrieves the International Bank Account Number (IBAN) associated with the
	 * credit card.
	 *
	 * @return The IBAN.
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Retrieves the personal identification number (PIN) for the credit card.
	 *
	 * @return The PIN.
	 */
	public int getPin() {
		return pin;
	}

	/**
	 * Sets the personal identification number (PIN) for the credit card.
	 *
	 * @param pin The PIN to set.
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}

	/**
	 * Retrieves the expiration date of the credit card.
	 *
	 * @return The expiration date.
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the expiration date of the credit card.
	 *
	 * @param expirationDate : The expiration date to set.
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Retrieves the Card Verification Value (CVV) of the credit card.
	 *
	 * @return The CVV.
	 */
	public int getCVV() {
		return CVV;
	}
}
