package Employee;



import io.project.database.DBManagment;
import io.project.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    @Test
    public void loginTest() {
        Employee test = DBManagment.login("admin", "admin");
        Assertions.assertEquals("Admin", test.getLastName());
    }
}
