package application;

/**
 * @author Eudald Daví and Dídac Martínez
 * 
 *         Represents a bank account.
 * 
 */
public class BankAccount {
	private String iban;
	private int idUser;
	private Double balance;

	/**
	 * Constructs a new BankAccount object with the specified details.
	 *
	 * @param iban    : The International Bank Account Number (IBAN) associated with
	 *                the account.
	 * @param idUser  : The user ID associated with the account.
	 * @param balance : The current balance of the account.
	 */
	public BankAccount(String iban, int idUser, Double balance) {
		this.iban = iban;
		this.idUser = idUser;
		this.balance = balance;
	}

	/**
	 * Retrieves the International Bank Account Number (IBAN) associated with the
	 * account.
	 *
	 * @return The IBAN.
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Retrieves the user ID associated with the account.
	 *
	 * @return The user ID.
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * Retrieves the current balance of the account.
	 *
	 * @return The account balance.
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * Sets the current balance of the account.
	 *
	 * @param balance : The account balance to set.
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * Withdraws money from the account.
	 *
	 * @param amount : The amount to withdraw.
	 */
	public void withdrawMoney(Double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			System.out.println("Correct withdraw: " + amount);
		} else {
			System.out.println("No se puede realizar el retiro. Saldo insuficiente.");
		}
	}

	/**
	 * Deposits money into the account.
	 *
	 * @param amount : The amount to deposit.
	 */
	public void depositMoney(Double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Correct Deposit: " + amount);
		} else {
			System.out.println("No se puede realizar el depósito. Monto inválido.");
		}
	}
}
