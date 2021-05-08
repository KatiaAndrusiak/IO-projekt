package io.project;

import io.project.entities.Employee;
import io.project.entities.Facility;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public final class Global {
    private static Employee employee;
    private static Facility facility;
    private static Employee currentUser;



    @FXML
    private static AnchorPane managerPane;

    @FXML
    private static AnchorPane viewPane;

    public static Facility getFacility() {
        return facility;
    }

    public static void setFacility(Facility facility) {
        Global.facility = facility;
    }

    public static Employee getEmployee() {
        return employee;
    }

    public static void setEmployee(Employee employee) {
        Global.employee = employee;
    }

    public static Employee getCurrentUser() { return currentUser; }

    public static void setCurrentUser(Employee currentUser) { Global.currentUser = currentUser; }

    public static AnchorPane getManagerPane() {
        return managerPane;
    }

    public static void setManagerPane(AnchorPane managerPane) {
        Global.managerPane = managerPane;
    }

    public static AnchorPane getViewPane() {
        return viewPane;
    }

    public static void setViewPane(AnchorPane viewPane) {
        Global.viewPane = viewPane;
    }


}
