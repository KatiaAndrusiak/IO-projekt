package io.project;

import io.project.entities.Course;
import io.project.entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<Course, Employee> employeeCol;

    @FXML
    private TableColumn<Course, String> nameCol;

    @FXML
    private TableColumn<Course, LocalDate> dateCol;

    @FXML
    private TableColumn<Course, Integer> hoursCol;

    @FXML
    private TableColumn<Course, LocalDate> nextCourseCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
