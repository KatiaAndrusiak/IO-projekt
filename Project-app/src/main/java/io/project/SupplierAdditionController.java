package io.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/supplierAddition.fxml"));
		try {
			Parent root = loader.load();
			supplierAdditionPane.getChildren().clear();
			supplierAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
