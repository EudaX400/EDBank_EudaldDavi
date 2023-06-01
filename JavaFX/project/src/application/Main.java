package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Eudald Daví and Dídac Martínez
 * 
 *         The Main class represents the entry point of the JavaFX application.
 * 
 *         It initializes the login screen and launches the application.
 */
public class Main extends Application {

	/**
	 * 
	 * The start method is called when the application is launched. It creates an
	 * instance of the Login class and starts the login screen.
	 * 
	 * @param primaryStage : the primary stage of the application
	 * @throws Exception if an error occurs during application start
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Login login = new Login(primaryStage);
		login.start();
	}

	/**
	 * 
	 * The main method is the entry point of the application. It launches the JavaFX
	 * application.
	 * 
	 * @param args : the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
