/**
 * 
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("Client");
	}
	
	@FXML
	public void onMenuItemProductAction() {
		System.out.println("Product");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("About");
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}

}
