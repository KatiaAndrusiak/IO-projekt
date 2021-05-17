package Supplier;

import io.project.database.DBManagment;
import io.project.entities.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SupplierTest {

    @Test
    public void addSupplierTest(){
        Supplier supplier = new Supplier("testTest2", "0987748832", "email@test2");
        Assertions.assertTrue(DBManagment.addSupplier(supplier));
    }

    @Test
    public void addSupplierWithNullTest(){
        Assertions.assertFalse(DBManagment.addSupplier(null));
    }
}
