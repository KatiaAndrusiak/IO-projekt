package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierAdditionController implements Initializable
{

	
	@FXML
	private TextField supplierNameTF;
	
	@FXML
	private TextField supplierEmailTF;
	
	@FXML
	private TextField supplierPhoneTF;

	public void addSupplier(){
		if(supplierNameTF.getText().isEmpty() ||
				supplierEmailTF.getText().isEmpty() ||
				supplierPhoneTF.getText().isEmpty()){
			AlertBox.errorAlert("Błąd", "Wypełnij wszystkie pola");
		}else{
			Supplier supplier = new Supplier(supplierNameTF.getText(),
					supplierPhoneTF.getText(),
					supplierEmailTF.getText());
			if(DBManagment.addSupplier(supplier)){
				AlertBox.infoAlert("OK", "Udało się!", "Dodano dostawcę ");
			}else {
				AlertBox.errorAlert("Błąd", "Nie udało się dodać dostawcę");
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
