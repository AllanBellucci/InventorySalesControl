/**
 * 
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DBException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entity.Client;
import model.exceptions.ValidationExceptions;
import model.services.ClientService;

/**
 * @author ALLAN
 *
 */
public class ClientFormController implements Initializable {

	private Client client;

	private ClientService clientService;

	private List<DataChangeListener> dataChangeListener = new ArrayList<DataChangeListener>();

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private Label lblErrorName;

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @param clientService the clientService to set
	 */
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListener.add(listener);
	}

	@FXML
	public void onBtnSaveAction(ActionEvent event) {
		if (client == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (clientService == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			client = getFormData();
			clientService.saveOrUpdate(client);
			notifyChangeListener();
			Utils.currentStage(event).close();
		}catch (ValidationExceptions e) {
			setErrorsMessage(e.getErrors());
		
		} catch (DBException e) {
			Alerts.showAlert("Error saving Client", null, e.getMessage(), AlertType.ERROR);
		}

	}

	private void notifyChangeListener() {
		for (DataChangeListener listener : dataChangeListener) {
			listener.onDataChanged();
		}

	}

	private Client getFormData() {
		Client obj = new Client();
		ValidationExceptions validationExceptions = new ValidationExceptions("Validation Error");
		obj.setId(Utils.tryParseInt(txtId.getText()));
		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			validationExceptions.addErrors("name", "Field can't be empty");
		}
		obj.setName(txtName.getText());

		if (validationExceptions.getErrors().size() > 0) {
			throw validationExceptions;
		}

		return obj;
	}

	@FXML
	public void onBtnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLenght(txtName, 50);

	}

	public void updateDataForm() {
		if (client == null) {
			throw new IllegalArgumentException("Entity was null");
		}
		txtId.setText(String.valueOf(client.getId()));
		txtName.setText(client.getName());
	}

	private void setErrorsMessage(Map<String,String> errors) {
		Set<String> fields = errors.keySet();
		if(fields.contains("name")) {
			lblErrorName.setText(errors.get("name"));
		}
	}
	
}
