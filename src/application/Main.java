package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainscene;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollpane = loader.load();
			scrollpane.setFitToHeight(true);
			scrollpane.setFitToWidth(true);
			mainscene = new Scene(scrollpane);
			primaryStage.setScene(mainscene);
			primaryStage.setTitle("APP");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the mainscene
	 */
	public static Scene getMainscene() {
		return mainscene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
