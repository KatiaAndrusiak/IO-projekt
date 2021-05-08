package io.project.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Inspection {
    private int id;
    private Employee employee;
    private LocalDate date;
    private String description;
    private ArrayList<Checkup> checkupList;

    public Inspection() {
    }

    public Inspection(int id, Employee employee, LocalDate date, String description, ArrayList<Checkup> checkupList) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.description = description;
        this.checkupList = checkupList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Checkup> getCheckupList() {
        return checkupList;
    }

    public void setCheckupList(ArrayList<Checkup> checkupList) {
        this.checkupList = checkupList;
    }
}
