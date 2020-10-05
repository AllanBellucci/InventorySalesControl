/**
 * 
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entity.Client;

/**
 * @author ALLAN
 *
 */
public class ClientListController implements Initializable {

	@FXML
	private TableView<Client> tableViewClient;

	@FXML
	private TableColumn<Client, Integer> tableColunId;
	
	@FXML
	private TableColumn<Client, String> tableColunName;
	
	@FXML
	private Button btnNew;
	
	@FXML
	public void onBtnNewAction() {
		System.out.println("btnNewAction");
	}
	
	
	public ClientListController() {
			}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
			initializeNodes();
	}


	private void initializeNodes() {
		tableColunId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColunName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage =(Stage) Main.getMainscene().getWindow();
		tableViewClient.prefHeightProperty().bind(stage.heightProperty());
		
		
	}

}
