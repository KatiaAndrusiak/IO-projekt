package io.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierAdditionController implements Initializable
{
	@FXML
	private AnchorPane supplierAdditionPane;
	
	@FXML
	private TextField supplierNameTF;
	
	@FXML
	private TextField supplierEmailTF;
	
	@FXML
	private TextField supplierPhoneTF;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
