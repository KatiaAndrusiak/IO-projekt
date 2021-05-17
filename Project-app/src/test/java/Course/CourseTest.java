package Course;

import io.project.database.DBManagment;
import io.project.entities.Course;
import io.project.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CourseTest {
    @Test
    public void addNullCourseTest(){
        Assertions.assertEquals(false, DBManagment.deleteEmployee(null));
    }
    @Test
    public void addCourseWithNegativeHoursTest(){
        Employee emp  = new Employee(19999999, "test", "test", "manager", LocalDate.now(), "phone", "Pracownik", "1",
                "1000", LocalDate.now(), 76553, LocalDate.now());
        Course course = new Course(11, "String ", LocalDate.now(), -14, emp);
        Assertions.assertEquals(false, DBManagment.addCourseToEmployee(course));
    }
    @Test
    public void addCourseWithNegativeEmployeeTest(){
        Employee emp  = new Employee(-1, "test", "test", "manager", LocalDate.now(), "phone", "Pracownik", "1",
                "1000", LocalDate.now(), 76553, LocalDate.now());
        Course course = new Course(11, "String ", LocalDate.now(), 14, emp);
        Assertions.assertEquals(false, DBManagment.addCourseToEmployee(course));
    }
}
