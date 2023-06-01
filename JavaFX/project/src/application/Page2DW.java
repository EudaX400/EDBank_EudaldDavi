package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Eudald Daví and Dídac Martínez 
 * 
 * 			The Page2DW class represents the
 *         second page of the application, which provides options for withdrawal
 *         and deposit.
 * 
 *         Users can choose to withdraw or deposit money from their bank
 *         accounts.
 */
public class Page2DW {

	@FXML
	private Button withdrawButton1;
	@FXML
	private Button depositButton1;
	@FXML
	private Button closeButton1;

	private Stage primaryStage;
	private String loggedInUser;
	private String loggedInPassword;

	/**
	 * 
	 * Constructs a new Page2DW object with the specified primaryStage.
	 * 
	 * @param primaryStage the primary stage of the application
	 */
	public Page2DW(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 
	 * Shows the second page and initializes its components.
	 * 
	 * @throws IOException if an error occurs during initialization
	 */
	public void showPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Page2.fxml"));
		Pane page2 = loader.load();

		withdrawButton1 = (Button) page2.lookup("#withdrawButton1");
		depositButton1 = (Button) page2.lookup("#depositButton1");
		closeButton1 = (Button) page2.lookup("#closeButton1");

		withdrawButton1.setOnAction(event -> {
			try {
				Withdraw withdraw = new Withdraw(primaryStage);
				withdraw.setLoggedInUser(loggedInUser);
				withdraw.setLoggedInPassword(loggedInPassword);
				withdraw.showPage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		depositButton1.setOnAction(event -> {
			try {
				Deposit deposit = new Deposit(primaryStage);
				deposit.setLoggedInUser(loggedInUser);
				deposit.setLoggedInPassword(loggedInPassword);
				deposit.showPage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		closeButton1.setOnAction(event -> {
			primaryStage.close();
		});

		Scene scene = new Scene(page2);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 
	 * Sets the logged-in user for the second page.
	 * 
	 * @param loggedInUser the logged-in user
	 */
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/**
	 * 
	 * Sets the logged-in password for the second page.
	 * 
	 * @param loggedInPassword the logged-in password
	 */
	public void setLoggedInPassword(String loggedInPassword) {
		this.loggedInPassword = loggedInPassword;
	}
}
