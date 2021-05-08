package io.project.entities;

import java.time.LocalDate;

public class Course {
    private int id;
    private String name;
    private LocalDate date;
    private int hours;
    private Employee employee;

    public Course() {
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
}
