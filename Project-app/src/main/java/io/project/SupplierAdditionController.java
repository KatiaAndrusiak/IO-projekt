package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Supplier;
import io.project.screenloader.ChangeScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
		try {
//			if (FieldValidation.validateCharField(supplierNameTF) ||
//					FieldValidation.validateEmail(supplierEmailTF) ||
//					FieldValidation.validateNum(supplierPhoneTF)) {
//				AlertBox.errorAlert("Błąd", "Sprawdź poprawność danych");
//			} else {
				Supplier supplier = new Supplier(supplierNameTF.getText(),
						supplierPhoneTF.getText(),
						supplierEmailTF.getText());
				if (DBManagment.addSupplier(supplier)) {
					AlertBox.infoAlert("OK", "Udało się!", "Dodano dostawcę ");
					supplierEmailTF.clear();
					supplierNameTF.clear();
					supplierPhoneTF.clear();
					ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("deliveryList.fxml")));

				} else {
					throw  new Exception("Nie udało się dodać dostawcę");
				}
//			}
		}
		catch (Exception e){
			AlertBox.errorAlert("Błąd", e.getMessage());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
