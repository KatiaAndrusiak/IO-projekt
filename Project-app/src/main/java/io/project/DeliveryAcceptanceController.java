package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Delivery;
import io.project.entities.Facility;
import io.project.entities.Supplier;
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
	private ComboBox<String> deliverySupplierComboBox;
	
	@FXML
	private DatePicker deliveryDateTF;
	
	@FXML
	private TextField deliveryPaymentDelayTF;
	
	@FXML
	private TextField deliveryPaymentTF;

	public void acceptDelivery(){
		try {
			if (deliveryDateTF.isShowWeekNumbers() ||
					deliveryPaymentDelayTF.getText().isEmpty() ||
					deliveryPaymentTF.getText().isEmpty()) {
				AlertBox.errorAlert("Błąd", "Wypełnij wszystkie pola");
			} else {
				Supplier supplier = new Supplier();

				System.out.println("start");
				supplier.setId(Integer.parseInt(deliverySupplierComboBox.getSelectionModel().getSelectedItem().split(" | ")[0]));
				System.out.println("supplier added");
				System.out.println(Global.getCurrentUser().getId());
				Facility facility = DBManagment.getFacilityByManagerId(Global.getCurrentUser().getId());
				System.out.println("facility created");
				Delivery delivery = new Delivery(supplier,
						deliveryDateTF.getValue(),
						Integer.parseInt(deliveryPaymentDelayTF.getText()),
						Integer.parseInt(deliveryPaymentTF.getText()),
						false,
						facility);
				System.out.println("facility created");

				if (DBManagment.addDelivery(delivery)) {
					AlertBox.infoAlert("OK", "Udało się!", "Dodano dostawę ");
					deliveryPaymentDelayTF.clear();
					deliveryPaymentTF.clear();
					deliveryDateTF.getEditor().clear();
					deliverySupplierComboBox.getSelectionModel().clearSelection();
				} else {
					throw  new Exception("Nie udało się dodać dostawcę");
				}
			}
		}catch (Exception e){
			AlertBox.errorAlert("Błąd contr", e.getMessage());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBManagment.getSupplierToComboBox(deliverySupplierComboBox);
	}
}
