package io.project;

import java.time.LocalDate;

public class Violation {
    private int id;
    private String note;
    private Employee employee;
    private LocalDate correctionTerm;
    private LocalDate correctionDate;

    public Violation() {
    }

    public Violation(int id, String note, Employee employee, LocalDate correctionTerm, LocalDate correctionDate) {
        this.id = id;
        this.note = note;
        this.employee = employee;
        this.correctionTerm = correctionTerm;
        this.correctionDate = correctionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getCorrectionTerm() {
        return correctionTerm;
    }

    public void setCorrectionTerm(LocalDate correctionTerm) {
        this.correctionTerm = correctionTerm;
    }

    public LocalDate getCorrectionDate() {
        return correctionDate;
    }

    public void setCorrectionDate(LocalDate correctionDate) {
        this.correctionDate = correctionDate;
    }
}
