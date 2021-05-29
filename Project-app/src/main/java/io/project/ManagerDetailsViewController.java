package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Violation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
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
		managerResponsibilitiesList.setCellFactory(new Callback<>() {
			@Override
			public ListCell<Violation> call(ListView<Violation> ListView) {
				return new ListCell<>() {
					@Override
					protected void updateItem(Violation item, boolean empty) {
						if(item != null) {
							super.updateItem(item, empty);
							if (item.getCorrectionDate() == null) {
								setDisable(true);
							} else {
								setDisable(false);
							}
							setText(item.toString());
						}
					}
				};
			}
		});
	}
}
