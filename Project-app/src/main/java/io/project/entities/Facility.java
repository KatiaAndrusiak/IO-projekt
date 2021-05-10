package io.project.entities;

import java.util.Set;

public class Facility {
    private int id;
    private String name;
    private String status;
    private String address;
    private String city;
    private Set<Employee> employees;
    private String schedule;

    public Facility() {
    }
    public Facility( String city, String name,  String address,String schedule) {
        this.city = city;
        this.name = name;
        this.address = address;
        this.schedule = schedule;
    }

    public Facility(int id, String name, String status, String address, String city, Set<Employee> employees, String schedule) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.address = address;
        this.city = city;
        this.employees = employees;
        this.schedule = schedule;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setEmployees(Set<Employee> employees) { this.employees = employees; }

    public Set<Employee> getEmployees() { return employees; }

}
