package Delivery;

import io.project.database.DBManagment;
import io.project.entities.Delivery;
import io.project.entities.Facility;
import io.project.entities.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeliveryTest {

    @Test
    public void addDeliveryTest(){
        Supplier supplier = new Supplier();
        supplier.setId(5);
        Facility facility = new Facility();
        facility.setId(2);
        Delivery delivery = new Delivery(supplier, LocalDate.now(), 30, 4000, false, facility);
        Assertions.assertTrue(DBManagment.addDelivery(delivery));
    }

    @Test
    public void addDeliveryWithNullTest(){
        Assertions.assertFalse(DBManagment.addDelivery(null));
    }

    @Test
    public void payForDeliveryTest(){
        int deliveryId = 6;
        int supplierId = 4;
        int amountToPay = 1000;
        double companyTotal = DBManagment.getAccountMoney();
        int expectedResult = (int)companyTotal - amountToPay;
        DBManagment.payForDelivery(deliveryId, supplierId, amountToPay);
        Assertions.assertEquals(expectedResult, (int)DBManagment.getAccountMoney());
    }
}
