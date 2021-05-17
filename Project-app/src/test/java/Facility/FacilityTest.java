package Facility;

import io.project.database.DBManagment;
import io.project.entities.Facility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

public class FacilityTest {
    @Test
    public void facilityAdditionTest() throws SQLException {
        int sizeBeforeAddition = DBManagment.getFacilityInfo().size();
        Facility facilityToTest = new Facility();
        facilityToTest.setCity("Kraków");
        facilityToTest.setStatus("Apteka");
        facilityToTest.setName("facilityTestName");
        facilityToTest.setAddress("Testaddress, " + (new Random().nextInt(100) + 1));
        facilityToTest.setSchedule("07:00-23:00");
        DBManagment.addFacility(facilityToTest);
        Assertions.assertEquals(sizeBeforeAddition + 1, DBManagment.getFacilityInfo().size());
    }


    @Test
    public void facilityAdditionWithExitingCityAndAddressTest() throws SQLException {
        Facility facilityToTest = new Facility();
        facilityToTest.setCity("Kraków");
        facilityToTest.setStatus("Apteka");
        facilityToTest.setName("facilityTestName");
        facilityToTest.setAddress("Testaddress, " + 33);
        facilityToTest.setSchedule("07:00-23:00");


        Assertions.assertThrows(java.lang.ExceptionInInitializerError.class, () -> DBManagment.addFacility(facilityToTest));
    }
}
