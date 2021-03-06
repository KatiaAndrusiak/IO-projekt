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

public class AccountantDetailsViewController implements Initializable {

    @FXML
    private AnchorPane accountantPane;

    @FXML
    private Label accountantFirstName;

    @FXML
    private Label accountantLastName;

    @FXML
    private Label accountantBirthDate;

    @FXML
    private Label accountantPhoneNumber;

    @FXML
    private Label accountantEmploymentPlace;

    @FXML
    private Label accountantPosition;

    @FXML
    private Label accountantSalary;

    @FXML
    private Label accountantCategory;

    @FXML
    private Label accountantSOO;

    @FXML
    private Label accountantCoursesHours;

    @FXML
    private Label accountantCompanyAccountBalance;

    @FXML
    private ListView<Violation> accountantResponsibilitiesList;

    @FXML
    private Button accountantConfirmButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Employee currUser = Global.getCurrentUser();
        accountantFirstName.setText(currUser.getFirstName());
        accountantLastName.setText(currUser.getLastName());
        accountantBirthDate.setText(currUser.getDOB().format(formatter));
        accountantEmploymentPlace.setText(DBManagment.getEmploymentPlace(currUser.getId()).get(0));
        accountantPhoneNumber.setText(currUser.getPhone());
        accountantPosition.setText(currUser.getPosition());
        accountantCategory.setText(currUser.getCategory());
        accountantSalary.setText(currUser.getSalary());
        accountantSOO.setText(currUser.getPPE().format(formatter));
        accountantCoursesHours.setText(String.valueOf(currUser.getCourseHoursSum()));
        ObservableList<Violation> data = DBManagment.getViolationInfo(currUser);
        accountantResponsibilitiesList.setItems(data);
        accountantCompanyAccountBalance.setText(String.valueOf(DBManagment.getAccountMoney()));
        accountantResponsibilitiesList.setCellFactory(new Callback<>() {
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
            }
        });
    }
    public void approveResponsibility(){
        Violation selected = accountantResponsibilitiesList.getSelectionModel().getSelectedItem();
        if(DBManagment.approveResponsibility(selected)) {
            ObservableList<Violation> data = DBManagment.getViolationInfo(Global.getCurrentUser());
            accountantResponsibilitiesList.setItems(data);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Uda??o si??!");
            alert1.setHeaderText("Zatwierdzono pomy??lnie!");
            alert1.showAndWait();
        }else{
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("B????d!");
            alert1.setHeaderText("Nie uda??o si?? zatwierdzi??!");
            alert1.showAndWait();
        }

    }
}
