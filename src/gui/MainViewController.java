/**
 * 
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * @author ALLAN
 *
 */
public class MainViewController implements Initializable {

	@FXML
	private MenuItem client;
	@FXML
	private MenuItem product;
	@FXML
	private MenuItem about;
	
	@FXML
	public void onMenuItemClientAction() {
		loadView("/gui/ClientList.fxml");
	}
	
	@FXML
	public void onMenuItemProductAction() {
		System.out.println("Product");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	private synchronized void loadView(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			Scene mainScene = Main.getMainscene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent(); 
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("IOExceptions", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}

}
