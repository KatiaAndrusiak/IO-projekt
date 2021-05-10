package io.project;

import io.project.entities.Course;
import io.project.entities.Employee;
import javafx.event.ActionEvent;
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

public class CourseAdditionController implements Initializable {
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

    public void addCourse(ActionEvent actionEvent) {
        Course course = new Course();
        course.setDate(courseDateTF.getValue());
        course.setName(courseNameTF.getText());
        course.setHours(Integer.parseInt(courseHoursTF.getText()));
        Employee employee = Global.getCurrentUser();
        employee.setCourseHoursSum(employee.getCourseHoursSum() + course.getHours());
        //pewnie zapis na bazie czy cos
    }
}
