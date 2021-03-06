package io.project.entities;

import java.time.LocalDate;

public class Course {
    private int id;
    private String name;
    private LocalDate date;
    private int hours;
    private Employee employee;
    private String employeeName;
    private String employeeLastName;

    public Course() {
    }

    public Course(String employeeName, String employeeLastName, String name, LocalDate date, int hours) {
        this.name = name;
        this.date = date;
        this.hours = hours;
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
    }

    public Course(int id, String name, LocalDate date, int hours, Employee employee) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.hours = hours;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }
}
