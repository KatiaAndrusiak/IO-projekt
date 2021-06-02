package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Violation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManagerDetailsViewController implements Initializable {
	
	@FXML
	private AnchorPane managerPane;
	
	@FXML
	private Label managerFirstName;
	
	@FXML
	private Label managerLastName;
	
	@FXML
	private Label managerBirthDate;
	
	@FXML
	private Label managerPhoneNumber;
	
	@FXML
	private Label managerEmploymentPlace;
	
	@FXML
	private Label managerPosition;
	
	@FXML
	private Label managerSalary;
	
	@FXML
	private Label managerCategory;
	
	@FXML
	private Label managerSOO;
	
	@FXML
	private Label managerCoursesHours;
	
	@FXML
	private ListView<Violation> managerResponsibilitiesList;
	
	@FXML
	private Button managerConfirmButton;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Employee currUser = Global.getCurrentUser();
		managerFirstName.setText(currUser.getFirstName());
		managerLastName.setText(currUser.getLastName());
		managerBirthDate.setText(currUser.getDOB().format(formatter));
		managerPhoneNumber.setText(currUser.getPhone());
		managerPosition.setText(currUser.getPosition());
		managerEmploymentPlace.setText(DBManagment.getEmploymentPlace(currUser.getId()).get(0));
		managerCategory.setText(currUser.getCategory());
		managerSalary.setText(currUser.getSalary());
		managerSOO.setText(currUser.getPPE().format(formatter));
		managerCoursesHours.setText(String.valueOf(currUser.getCourseHoursSum()));
		ObservableList<Violation> data = DBManagment.getViolationInfo(currUser);
		managerResponsibilitiesList.setItems(data);
		System.out.println(data);
		managerResponsibilitiesList.setCellFactory(new Callback<>() {
			@Override
			public ListCell<Violation> call(ListView<Violation> param) {
				return new ListCell<>() {
					@Override
					protected void updateItem(Violation item, boolean empty) {
						if(item != null) {
							super.updateItem(item, empty);
							if (item != null) {
								if (item.getCorrectionDate().isEqual(LocalDate.of(1970, 1, 1))) {
									setDisable(false);
								} else {
									setDisable(true);
								}
								setText(item.toString());
							}
						}
					}
				};
			}});
	}
	public void approveResponsibility(){
		Violation selected = managerResponsibilitiesList.getSelectionModel().getSelectedItem();
		if(DBManagment.approveResponsibility(selected)) {
			ObservableList<Violation> data = DBManagment.getViolationInfo(Global.getCurrentUser());
			managerResponsibilitiesList.setItems(data);
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle("Udało się!");
			alert1.setHeaderText("Zatwierdzono pomyślnie!");
			alert1.showAndWait();
		}else{
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle("Błąd!");
			alert1.setHeaderText("Nie udało się zatwierdzić!");
			alert1.showAndWait();
		}

	}
}
