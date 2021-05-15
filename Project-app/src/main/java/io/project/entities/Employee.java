package io.project.entities;

import java.time.LocalDate;


public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private LocalDate DOB;
    private String phone;
    private String position;
    private String category;
    private String salary;
    private Integer salaryInt;
    private LocalDate PPE;
    private int courseHoursSum;
    private LocalDate employmentDate;
    private String username;
    private String password;

    public Employee() {
    }
    public Employee( int id, String firstName,String lastName, LocalDate DOB, String phone, String position, String category, int salary, LocalDate PPE, int courseHoursSum, LocalDate employmentDate){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.DOB = DOB;
        this.position = position;
        this.category = category;
        this.salaryInt = salary;
        this.PPE = PPE;
        this.courseHoursSum = courseHoursSum;
        this.employmentDate = employmentDate;
    }

    public Employee(int id, String firstName, String lastName, String role, LocalDate DOB, String phone, String position, String category, String salary, LocalDate PPE, int courseHoursSum, LocalDate employmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.DOB = DOB;
        this.phone = phone;
        this.position = position;
        this.category = category;
        this.salary = salary;
        this.PPE = PPE;
        this.courseHoursSum = courseHoursSum;
        this.employmentDate = employmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Integer getSalaryInt() {
        return salaryInt;
    }

    public void setSalaryInt(Integer salaryInt) {
        this.salaryInt = salaryInt;
    }

    public LocalDate getPPE() {
        return PPE;
    }

    public void setPPE(LocalDate PPE) {
        this.PPE = PPE;
    }

    public int getCourseHoursSum() {
        return courseHoursSum;
    }

    public void setCourseHoursSum(int courseHoursSum) {
        this.courseHoursSum = courseHoursSum;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return employee.getId()==this.getId();
    }

}
