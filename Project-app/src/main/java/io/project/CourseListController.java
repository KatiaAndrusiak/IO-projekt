package io.project;

import io.project.database.DBManagment;
import io.project.entities.Course;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CourseListController implements Initializable {
    @FXML
    private AnchorPane viewPane;

    @FXML
    private TableView<Course> table;

    @FXML
    private TableColumn<Course, String> employeeNameCol;

    @FXML
    private TableColumn<Course, String> employeeLastNameCol;

    @FXML
    private TableColumn<Course, String> nameCol;

    @FXML
    private TableColumn<Course, LocalDate> dateCol;

    @FXML
    private TableColumn<Course, Integer> hoursCol;

    public void courseList(){
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("employeeName"));
        employeeLastNameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("employeeLastName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Course, LocalDate>("date"));
        hoursCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("hours"));
        ObservableList<Course> list = DBManagment.getCourseInfo();
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList();
    }
}
