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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eudald Daví and Dídac Martínez
 * 
 *         The Deposit class represents the deposit functionality of the
 *         application.
 * 
 *         It allows users to deposit money into their bank accounts.
 */
public class Deposit {

	@FXML
	private Button depositButton2;
	@FXML
	private Button closeButtonDep;
	@FXML
	private Button backDep;
	@FXML
	private TextField depositField;
	@FXML
	private Label depositBalance;

	private Stage primaryStage;
	private String loggedInUser;
	private String loggedInPassword;

	/**
	 * 
	 * Constructs a new Deposit object with the specified primaryStage.
	 * 
	 * @param primaryStage : the primary stage of the application
	 */
	public Deposit(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 
	 * Shows the deposit page and initializes its components.
	 * 
	 * @throws IOException if an error occurs during initialization
	 */
	public void showPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DepositPage.fxml"));
		Pane depositPage = loader.load();

		depositButton2 = (Button) depositPage.lookup("#depositButton2");
		closeButtonDep = (Button) depositPage.lookup("#closeButtonDep");
		backDep = (Button) depositPage.lookup("#backDep");
		depositField = (TextField) depositPage.lookup("#depositField");
		depositBalance = (Label) depositPage.lookup("#depositBalance");

		double currentBalance = getBalanceFromDatabase(loggedInUser, loggedInPassword);
		depositBalance.setText("Balance: " + currentBalance + " €");

		depositButton2.setOnAction(event -> handleDeposit());

		closeButtonDep.setOnAction(event -> primaryStage.close());

		backDep.setOnAction(event -> showPreviousPage());

		Scene scene = new Scene(depositPage);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 
	 * Handles the deposit action when the deposit button is clicked.
	 */
	private void handleDeposit() {
		String depositAmount = depositField.getText();
		if (isValidAmount(depositAmount)) {
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter("E:\\Programació\\JavaFX\\project\\res\\errors.txt"))) {
				double amount = Double.parseDouble(depositAmount);
				if (amount <= 5000) {
					depositMoney(amount);
					showAlert("Successful deposit", "Successfully deposited: " + depositAmount + " €");

					double currentBalance = getBalanceFromDatabase(loggedInUser, loggedInPassword);
					depositBalance.setText("Balance: " + currentBalance + " €");

					depositField.clear();
				} else {
					bw.write("The maximum amount allowed is 5000 euros");
					bw.flush();
					showAlert("Deposit error", "The maximum amount allowed is 5000 euros");
				}
			} catch (NumberFormatException e) {
				showAlert("Deposit error", "Please, enter a valid amount");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			showAlert("Deposit error", "Please, enter a valid amount");
		}
	}

	/**
	 * 
	 * Checks if the provided amount is valid.
	 * 
	 * @param amount : the amount that the user puts to be validated
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
	 * @param userName : the username of the user
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
	 * Deposits the specified amount into the user's bank account.
	 * 
	 * @param amount : the amount to be deposited by the user
	 */
	private void depositMoney(double amount) {
		try {
			Connection myConnection = DriverManager
					.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7620873", "sql7620873", "Q4PSUDzElU");

			String updateQuery = "UPDATE BankAccount bal JOIN User usr ON usr.ID_user = bal.Id_user SET bal.Balance = bal.Balance + ? WHERE usr.user_name = ? AND usr.Password = ?";

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
	 * Shows the previous page (Page2DW) after deposit completion or cancellation.
	 */
	private void showPreviousPage() {
		Page2DW page2DW = new Page2DW(primaryStage);
		try {
			page2DW.setLoggedInUser(loggedInUser);
			page2DW.setLoggedInPassword(loggedInPassword);
			page2DW.showPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Sets the logged-in user for the deposit page.
	 * 
	 * @param user : the logged-in user
	 */
	public void setLoggedInUser(String user) {
		this.loggedInUser = user;
	}

	/**
	 * 
	 * Sets the logged-in password for the deposit page.
	 * 
	 * @param password : the logged-in password
	 */
	public void setLoggedInPassword(String password) {
		this.loggedInPassword = password;
	}
}