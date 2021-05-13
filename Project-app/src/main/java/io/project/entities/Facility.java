package io.project.entities;

public class Facility {
    private int id;
    private String name;
    private String status;
    private String address;
    private String city;
    private String schedule;

    public Facility() {
    }
    public Facility( int id, String city, String name,  String address,String schedule) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.address = address;
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

}
