package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseAdditionController implements Initializable
{
	@FXML
	private AnchorPane courseAdditionPane;
	
	@FXML
	private TextField courseNameTF;
	
	@FXML
	private DatePicker courseDateTF;
	
	@FXML
	private TextField courseHoursTF;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/courseAddition.fxml"));
		try {
			Parent root = loader.load();
			courseAdditionPane.getChildren().clear();
			courseAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
