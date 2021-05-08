package io.project;

import io.project.entities.Employee;
import io.project.entities.Facility;


public final class Global {
    private static Employee employee;
    private static Facility facility;
    private static Employee currentUser;

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


}
