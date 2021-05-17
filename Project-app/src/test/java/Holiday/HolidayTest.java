package Holiday;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Facility;
import io.project.entities.Holiday;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class HolidayTest {
    @Test
    public void addHolidayWithNegativeFacilityTest(){
        Facility fac = new Facility(-1, "kk",  "String address","String schedule");
        Employee emp = new Employee(33, "firstName", "lastName", "manager", LocalDate.now(), "phone", "Pracownik", "1",
                    "1000", LocalDate.now(), 9876553, LocalDate.now());
        Holiday holiday = new Holiday(1, "ame", LocalDate.now(), 1000, emp, fac);
        Assertions.assertEquals(false, DBManagment.addHoliday(holiday));

    }
    @Test
    public void addHolidayWithNegativeEmployeeTest(){
        Facility fac = new Facility(1, "kk",  "String address","String schedule");
        Employee emp = new Employee(-33, "firstName", "lastName", "manager", LocalDate.now(), "phone", "Pracownik", "1",
                "1000", LocalDate.now(), 9876553, LocalDate.now());
        Holiday holiday = new Holiday(1, "ame", LocalDate.now(), 1000, emp, fac);
        Assertions.assertEquals(false, DBManagment.addHoliday(holiday));

    }
    @Test
    public void addHolidayWithNegativeProceedsTest(){
        Facility fac = new Facility(1, "kk",  "String address","String schedule");
        Employee emp = new Employee(33, "firstName", "lastName", "manager", LocalDate.now(), "phone", "Pracownik", "1",
                "1000", LocalDate.now(), 9876553, LocalDate.now());
        Holiday holiday = new Holiday(1, "ame", LocalDate.now(), -1000, emp, fac);
        Assertions.assertEquals(false, DBManagment.addHoliday(holiday));

    }
    @Test
    public void addNullHolidayTest(){
        Assertions.assertEquals(false, DBManagment.addHoliday(null));

    }
}
