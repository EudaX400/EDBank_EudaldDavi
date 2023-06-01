package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Eudald Daví and Dídac Martínez
 * 
 *         The Login class represents the login functionality of the
 *         application.
 * 
 *         It handles user authentication and navigation to the main page.
 */
public class Login {

	@FXML
	private TextField accountName;
	@FXML
	private PasswordField password;
	@FXML
	private Button enterButton;

	private Stage primaryStage;

	/**
	 * 
	 * Constructs a new Login object with the specified primaryStage.
	 * 
	 * @param primaryStage : the primary stage of the application
	 */
	public Login(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 
	 * Starts the login process and initializes the main page.
	 * 
	 * @throws Exception if an error occurs during initialization
	 */
	public void start() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Project.fxml"));
		Pane projectPage = loader.load();

		Scene scene = new Scene(projectPage, 600, 574);
		primaryStage.setScene(scene);
		primaryStage.setTitle("EDBank");
		primaryStage.show();

		accountName = (TextField) projectPage.lookup("#accountName");
		password = (PasswordField) projectPage.lookup("#passwordF");
		enterButton = (Button) projectPage.lookup("#enterButton");

		Page2DW page2DW = new Page2DW(primaryStage);

		initialize(page2DW);
	}

	/**
	 * 
	 * Initializes the login page and sets up event handling for the enter button.
	 * 
	 * @param page2DW : the instance of Page2DW to navigate to after successful
	 *                login
	 */
	public void initialize(Page2DW page2DW) {
		enterButton.setOnAction(event -> {
			String account = accountName.getText();
			String passwordText = password.getText();

			if (authenticateUser(account, passwordText)) {
				try {
					page2DW.setLoggedInUser(account);
					page2DW.setLoggedInPassword(passwordText);
					page2DW.showPage();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				showAlert("Authentication Error", "Invalid credentials. Try again.");
			}
		});
	}

	/**
	 * 
	 * Authenticates the user by checking the provided account and password against
	 * the stored credentials.
	 * 
	 * @param account  : the user account name
	 * 
	 * @param password : the user password
	 * 
	 * @return true if the user is authenticated, false otherwise
	 */
	private boolean authenticateUser(String account, String password) {
		try (BufferedReader br = new BufferedReader(
				new FileReader("E:\\Programació\\JavaFX\\project\\res\\Password.txt"));
				BufferedReader br2 = new BufferedReader(
						new FileReader("E:\\Programació\\JavaFX\\project\\res\\user.txt"));) {
			String userReader = br2.readLine();
			String passwordReader = br.readLine();

			Connection myConnection = DriverManager
					.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7620873", userReader, passwordReader);

			Statement myStatement = myConnection.createStatement();

			String query = "SELECT * FROM User WHERE user_name = '" + account + "' AND Password = '" + password + "'";
			ResultSet resultSet = myStatement.executeQuery(query);

			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 
	 * Displays an alert dialog with the specified title and message.
	 * 
	 * @param title   : the title of the alert
	 * @param message : the content of the alert
	 */

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
