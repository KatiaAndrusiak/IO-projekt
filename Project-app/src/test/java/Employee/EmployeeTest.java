package Employee;


import io.project.database.DBManagment;
import io.project.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void loginCEOTest() {
        Employee CEO = DBManagment.login("admin", "admin");
        Assertions.assertEquals("Admin", CEO.getLastName());
        Assertions.assertEquals("Adminowski", CEO.getFirstName());
        Assertions.assertEquals("CEO", CEO.getRole());
        Assertions.assertEquals("1995-05-01", CEO.getDOB().format(formatter));
        Assertions.assertEquals("12345678910", CEO.getPhone());
        Assertions.assertEquals("wyższa", CEO.getCategory());
        Assertions.assertEquals("5000", CEO.getSalary());
        Assertions.assertEquals(80, CEO.getCourseHoursSum());
        Assertions.assertEquals("2017-05-30", CEO.getEmploymentDate().format(formatter));
        Assertions.assertEquals("2017-05-31", CEO.getPPE().format(formatter));
    }

    @Test
    public void loginAccountantTest() {
        Employee accountant = DBManagment.login("acc", "acc");
        Assertions.assertEquals("Accountant", accountant.getLastName());
        Assertions.assertEquals("Accountant", accountant.getFirstName());
        Assertions.assertEquals("accountant", accountant.getRole());
        Assertions.assertEquals("1995-01-01", accountant.getDOB().format(formatter));
        Assertions.assertEquals("12345678910", accountant.getPhone());
        Assertions.assertEquals("wyższa", accountant.getCategory());
        Assertions.assertEquals("5000", accountant.getSalary());
        Assertions.assertEquals(40, accountant.getCourseHoursSum());
        Assertions.assertEquals("2020-02-01", accountant.getEmploymentDate().format(formatter));
        Assertions.assertEquals("2020-02-02", accountant.getPPE().format(formatter));
    }

    @Test
    public void loginManagerTest() {
        Employee manager = DBManagment.login("man", "man");
        Assertions.assertEquals("Manager", manager.getLastName());
        Assertions.assertEquals("Managerowski", manager.getFirstName());
        Assertions.assertEquals("manager", manager.getRole());
        Assertions.assertEquals("1990-02-19", manager.getDOB().format(formatter));
        Assertions.assertEquals("1234567890", manager.getPhone());
        Assertions.assertEquals("wyższa", manager.getCategory());
        Assertions.assertEquals("4567", manager.getSalary());
        Assertions.assertEquals(80, manager.getCourseHoursSum());
        Assertions.assertEquals("2014-08-11", manager.getEmploymentDate().format(formatter));
        Assertions.assertEquals("2014-08-12", manager.getPPE().format(formatter));
    }

    @Test
    public void loginEmployeeTest() {
        Employee employee = DBManagment.login("empl", "empl");
        Assertions.assertEquals("Employee", employee.getLastName());
        Assertions.assertEquals("Employeewski", employee.getFirstName());
        Assertions.assertEquals("employee", employee.getRole());
        Assertions.assertEquals("1995-01-01", employee.getDOB().format(formatter));
        Assertions.assertEquals("12345678910", employee.getPhone());
        Assertions.assertEquals("wyższa", employee.getCategory());
        Assertions.assertEquals("5000", employee.getSalary());
        Assertions.assertEquals(65, employee.getCourseHoursSum());
        Assertions.assertEquals("2019-02-01", employee.getEmploymentDate().format(formatter));
        Assertions.assertEquals("2019-02-02", employee.getPPE().format(formatter));
    }

    @Test
    public void employeeAdditionTest() throws SQLException {
        int sizeBeforeAddition = DBManagment.getEmployees().size();
        long employeeIndex = new Timestamp(System.currentTimeMillis()).getTime();
        Employee employeeToTest = new Employee();
        employeeToTest.setFirstName("testFirstName");
        employeeToTest.setLastName("testLastName");
        employeeToTest.setDOB(LocalDate.parse("1990-07-07"));
        employeeToTest.setEmploymentDate(LocalDate.parse("2018-10-10"));
        employeeToTest.setPPE(LocalDate.parse("2018-10-11"));
        employeeToTest.setSalary("3000");
        employeeToTest.setRole("employee");
        employeeToTest.setCategory("1");
        employeeToTest.setPosition("Pracownik");
        employeeToTest.setCourseHoursSum(90);
        employeeToTest.setPhone("9988776655");
        employeeToTest.setUsername("testUserName" + employeeIndex);
        employeeToTest.setPassword("testPassword" + employeeIndex);
        DBManagment.addEmployee(employeeToTest);
        Assertions.assertEquals(sizeBeforeAddition + 1, DBManagment.getEmployees().size());
    }

    @Test
    public void employeeAdditionWithExitingUsernameTest() {
        Employee employeeToTest = new Employee();
        employeeToTest.setFirstName("testFirstName");
        employeeToTest.setLastName("testLastName");
        employeeToTest.setDOB(LocalDate.parse("1990-07-07"));
        employeeToTest.setEmploymentDate(LocalDate.parse("2018-10-10"));
        employeeToTest.setPPE(LocalDate.parse("2018-10-11"));
        employeeToTest.setSalary("3000");
        employeeToTest.setRole("employee");
        employeeToTest.setCategory("1");
        employeeToTest.setPosition("Pracownik");
        employeeToTest.setCourseHoursSum(90);
        employeeToTest.setPhone("9988776655");
        employeeToTest.setUsername("empl");
        employeeToTest.setPassword("empl");
        Assertions.assertThrows(java.lang.ExceptionInInitializerError.class, () -> DBManagment.addEmployee(employeeToTest));
    }

    @Test
    public void checkCompanyBudgetTest() {
        Assertions.assertTrue(DBManagment.getAccountMoney() > 0);
    }
}
