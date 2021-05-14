package io.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryAcceptanceController implements Initializable
{
	@FXML
	private AnchorPane deliveryAcceptancePane;
	
	@FXML
	private ComboBox<?> deliverySupplierComboBox;
	
	@FXML
	private DatePicker deliveryDateTF;
	
	@FXML
	private TextField deliveryPaymentDelayTF;
	
	@FXML
	private TextField deliveryPaymentTF;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
