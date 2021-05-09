package io.project.entities;

import java.time.LocalDate;

public class Holiday {
    private int id;
    private String name;
    private LocalDate date;
    private double proceeds;
    private Employee employee;
    private Facility facility;

    public Holiday() {
    }

    public Holiday(int id, String name, LocalDate date, int proceeds, Employee employee, Facility facility) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.proceeds = proceeds;
        this.employee = employee;
        this.facility = facility;
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

    public double getProceeds() {
        return proceeds;
    }

    public void setProceeds(double proceeds) {
        this.proceeds = proceeds;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

}
