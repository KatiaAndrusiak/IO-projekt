package io.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacilityAdditionController implements Initializable
{
	@FXML
	private AnchorPane facilityAdditionPane;
	
	@FXML
	private TextField facilityNameTF;
	
	@FXML
	private TextField facilityCityTF;
	
	@FXML
	private TextField facilityAddressTF;
	
	@FXML
	private ComboBox<?> facilityStatusComboBox;
	
	@FXML
	private ComboBox<?> facilityCategoryComboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/facilityAddition.fxml"));
		try {
			Parent root = loader.load();
			facilityAdditionPane.getChildren().clear();
			facilityAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
