/**
 * 
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entity.Client;
import model.services.ClientService;

/**
 * @author ALLAN
 *
 */
/**
 * @author ALLAN
 *
 */
public class ClientListController implements Initializable,DataChangeListener {

	private ClientService clientService;

	private ObservableList<Client> obsList;

	@FXML
	private TableView<Client> tableViewClient;

	@FXML
	private TableColumn<Client, Integer> tableColunId;

	@FXML
	private TableColumn<Client, String> tableColunName;
	
	@FXML
	private TableColumn<Client, Client> tableColunEdit;

	@FXML
	private Button btnNew;

	@FXML
	public void onBtnNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Client client = new Client();
		createDialogForm(client,"/gui/ClientForm.fxml", parentStage);
	}

	public ClientListController() {
	}

	public void updateTableView() {
		if (clientService == null) {
			throw new IllegalArgumentException("Service was null");
		}

		List<Client> list = clientService.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewClient.setItems(obsList);
		initEditButtons();
	}

	/**
	 * @param clientService the clientService to set
	 */
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColunId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColunName.setCellValueFactory(new PropertyValueFactory<>("name"));

		Stage stage = (Stage) Main.getMainscene().getWindow();
		tableViewClient.prefHeightProperty().bind(stage.heightProperty());
	}

	private void createDialogForm(Client client, String absoluteName,Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ClientFormController clientController = loader.getController();
			clientController.setClient(client);
			clientController.setClientService(new ClientService());
			clientController.subscribeDataChangeListener(this);
			clientController.updateDataForm();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Client Data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		} catch (IOException e) {
			Alerts.showAlert("IOException", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
	
	
	private void initEditButtons() {
		tableColunEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColunEdit.setCellFactory(param -> new TableCell<Client, Client>(){
			private final Button button = new Button("Edit");
		
			@Override
			protected void updateItem(Client client, boolean empty) {
				super.updateItem(client, empty);
				if(client==null) {
					setGraphic(null);
					return;
				}
				
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(client, "/gui/ClientForm.fxml", Utils.currentStage(event)));
			}
			
		});
	}
	
	
}
