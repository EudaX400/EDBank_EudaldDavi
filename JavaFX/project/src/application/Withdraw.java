package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eudald Daví and Dídac Martínez 
 * 		   The Withdraw class represents the
 *         withdrawal functionality of the application.
 * 
 *         It allows users to withdraw money from their bank accounts.
 */
public class Withdraw {

	@FXML
	private Button withdrawButton2;
	@FXML
	private Button closeButtonWD;
	@FXML
	private Button backWithd;
	@FXML
	private TextField withdrawField;
	@FXML
	private Label withdrawBalance;

	private Stage primaryStage;
	private String loggedInUser;
	private String loggedInPassword;

	/**
	 * 
	 * Constructs a new Withdraw object with the specified primaryStage.
	 * 
	 * @param primaryStage : the primary stage of the application
	 */
	public Withdraw(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 
	 * Shows the withdrawal page and initializes its components.
	 * 
	 * @throws IOException if an error occurs during initialization
	 */
	public void showPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/WithdrawPage.fxml"));
		Pane withdrawPage = loader.load();

		withdrawButton2 = (Button) withdrawPage.lookup("#withdrawButton2");
		closeButtonWD = (Button) withdrawPage.lookup("#closeButtonWD");
		backWithd = (Button) withdrawPage.lookup("#backWithd");
		withdrawField = (TextField) withdrawPage.lookup("#withdrawField");
		withdrawBalance = (Label) withdrawPage.lookup("#withdrawBalance");

		double currentBalance = getBalanceFromDatabase(loggedInUser, loggedInPassword);
		withdrawBalance.setText("Balance: " + currentBalance + " €");

		withdrawButton2.setOnAction(event -> handleWithdraw());

		closeButtonWD.setOnAction(event -> primaryStage.close());

		backWithd.setOnAction(event -> showPreviousPage());

		Scene scene = new Scene(withdrawPage);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 
	 * Handles the withdrawal action when the withdraw button is clicked.
	 */
	private void handleWithdraw() {
		String withdrawAmount = withdrawField.getText();
		if (isValidAmount(withdrawAmount)) {
			try {
				double amount = Double.parseDouble(withdrawAmount);
				double currentBalance = getBalanceFromDatabase(loggedInUser, loggedInPassword);

				if (amount <= currentBalance) {
					if (amount <= 5000) {
						withdrawMoney(amount);
						showAlert("Successful withdrawal", "Successfully removed: " + withdrawAmount + " €");

						currentBalance = getBalanceFromDatabase(loggedInUser, loggedInPassword);
						withdrawBalance.setText("Balance: " + currentBalance + " €");

						withdrawField.clear();
					} else {
						showAlert("Withdrawal error", "The maximum amount allowed is 5000 euros");
					}
				} else {
					showAlert("Withdrawal error", "Insufficient balance");
				}
			} catch (NumberFormatException e) {
				showAlert("Withdrawal error", "Please, enter a valid amount");
			}
		} else {
			showAlert("Withdrawal error", "Please, enter a valid amount");
		}
	}

	/**
	 * 
	 * Checks if the provided amount is valid.
	 * 
	 * @param amount : the amount of the user to be validated
	 * @return true if the amount is valid, false otherwise
	 */
	private boolean isValidAmount(String amount) {
		try {
			double value = Double.parseDouble(amount);
			return value >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 
	 * Retrieves the balance from the database for the specified user.
	 * 
	 * @param userName : the user name of the user
	 * 
	 * @param password : the password of the user
	 * 
	 * @return the current balance of the user's bank account
	 */
	private double getBalanceFromDatabase(String userName, String password) {
		double balance = 0;
		try {
			Connection myConnection = DriverManager
					.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7620873", "sql7620873", "Q4PSUDzElU");

			String query = "SELECT bal.Balance FROM BankAccount bal JOIN User usr ON usr.ID_user = bal.Id_user WHERE usr.user_name = ? AND usr.Password = ?";
			PreparedStatement statement = myConnection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				balance = resultSet.getDouble("Balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	/**
	 * 
	 * Withdraws the specified amount from the user's bank account.
	 * 
	 * @param amount : the amount to be withdrawn writed by the user
	 */
	private void withdrawMoney(double amount) {
		try {
			Connection myConnection = DriverManager
					.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7620873", "sql7620873", "Q4PSUDzElU");

			String updateQuery = "UPDATE BankAccount bal JOIN User usr ON usr.ID_user = bal.Id_user SET bal.Balance = bal.Balance - ? WHERE usr.user_name = ? AND usr.Password = ?";

			PreparedStatement updateStatement = myConnection.prepareStatement(updateQuery);
			updateStatement.setDouble(1, amount);
			updateStatement.setString(2, loggedInUser);
			updateStatement.setString(3, loggedInPassword);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Displays an alert dialog with the specified title and message.
	 * 
	 * @param title   : the title of the alert
	 * @param message : the content of the alert
	 */
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * 
	 * Shows the previous page (Page2DW) after withdrawal completion or
	 * cancellation.
	 */
	private void showPreviousPage() {
		Page2DW page2DW = new Page2DW(primaryStage);
		try {
			page2DW.showPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Sets the logged-in user for the withdrawal page.
	 * 
	 * @param user : the logged-in user
	 */
	public void setLoggedInUser(String user) {
		this.loggedInUser = user;
	}

	/**
	 * 
	 * Sets the logged-in password for the withdrawal page.
	 * 
	 * @param password : the logged-in password
	 */
	public void setLoggedInPassword(String password) {
		this.loggedInPassword = password;
	}
}
