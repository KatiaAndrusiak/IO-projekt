package io.project.database;

import io.project.alert.AlertBox;
import io.project.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class DBManagment {


    public DBManagment() {
    }


    private static Connection conn = connect();

    public static Connection connect() {
        String propFileName = "config.properties";

        String dbURL;
        String dbUser;
        String dbPassword;

        Connection conn = null;


        dbURL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/gvfmqimz";
        dbUser = "gvfmqimz";
        dbPassword = "kEfzcAjK-loVLrWOQUfwniLf8TQvZmnf";

        try {
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.print("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return conn;
    }

    public static void close() throws SQLException {
        conn.close();
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        DBManagment.conn = conn;
    }

    public static void closeAll(ResultSet res, PreparedStatement pst) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
            }
        }
    }

    public static Employee login(String username, String password) {
        try {
            String sql = "select * from employee_data where username ='" + username + "' and password ='" + password + "';";
            PreparedStatement ps = conn.prepareStatement(sql);
            Employee user = null;
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                user = new Employee();
                user.setId(res.getInt("id_employee"));
                String sql1 = "select * from employee_data_view where id_employee =  " + res.getInt("id_employee");
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ResultSet res1 = ps1.executeQuery();
                if (res1.next()) {
                    user.setId(res1.getInt("id_employee"));
                    user.setRole(res1.getString("role"));
                    user.setFirstName(res1.getString("first_name"));
                    user.setLastName(res1.getString("last_name"));
                    user.setDOB(res1.getDate("dob").toLocalDate());
                    user.setPhone(res1.getString("phone"));
                    user.setPosition(res1.getString("position"));
                    user.setCategory(res1.getString("category"));
                    user.setSalary(res1.getString("salary"));
                    user.setPPE(res1.getDate("ppe").toLocalDate());
                    user.setCourseHoursSum(res1.getInt("course_hours_sum"));
                    user.setEmploymentDate(res1.getDate("employment_date").toLocalDate());
                }
                closeAll(res1, ps1);
            } else {
                AlertBox.errorAlert("Brak uzytkownika w bazie!", "Sprawd?? swoje dane i sprobuj jeszcze raz");
            }
            closeAll(res, ps);
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void setEmployeeAdditionData(ComboBox<String> employeePosition, ComboBox<String> employeeRole, ComboBox<String> employeeCategory) {
        try {
            String sql = "select * from employee_enums_view";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                switch (res.getString("typname")) {
                    case "employee_category":
                        employeeCategory.getItems().add(res.getString("enumlabel"));
                        break;
                    case "employee_position":
                        employeePosition.getItems().add(res.getString("enumlabel"));
                        break;
                    case "employee_role":
                        employeeRole.getItems().add(res.getString("enumlabel"));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + res.getString("typname"));
                }
            }
            DBManagment.closeAll(res, ps);
        } catch (SQLException e) {
            AlertBox.errorAlert("B??ad", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static boolean addEmployee(Employee employee) {
        try {
            String sql = "select addEmployee(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, employee.getFirstName());
            pst.setString(2, employee.getLastName());
            pst.setString(3, employee.getUsername());
            pst.setString(4, employee.getPassword());
            pst.setObject(5, employee.getRole(), Types.OTHER);
            pst.setDate(6, Date.valueOf(employee.getDOB()));
            pst.setString(7, employee.getPhone());
            pst.setDate(8, Date.valueOf(employee.getEmploymentDate()));
            pst.setObject(9, employee.getPosition(), Types.OTHER);
            pst.setObject(10, employee.getCategory(), Types.OTHER);
            pst.setInt(11, Integer.parseInt(employee.getSalary()));
            pst.setDate(12, Date.valueOf(employee.getPPE()));
            pst.setInt(13, employee.getCourseHoursSum());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return false;
    }

    public static void fillFacilityAdditionData(ComboBox<String> status) {
        String sql = "SELECT e.enumlabel FROM pg_type t, pg_enum e WHERE t.oid = e.enumtypid AND typname = 'facility_status';";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                status.getItems().add(res.getString("enumlabel"));
            }
            closeAll(res, pst);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
    }

    public static boolean addFacility(Facility facility) {
        try {
            String sql = "select addFacility(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, facility.getName());
            pst.setString(2, facility.getAddress());
            pst.setObject(3, facility.getStatus(), Types.OTHER);
            pst.setString(4, facility.getSchedule());
            pst.setString(5, facility.getCity());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return false;
    }


    public static ObservableList<Employee> getEmployeeInfo(){
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM employee_data_view";
            getEmployeeInfoInner(list, sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return list;
    }

    private static void getEmployeeInfoInner(ObservableList<Employee> list, String sql) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        ps = getConn().prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Employee(
                    rs.getInt("id_employee"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("dob").toLocalDate(),
                    rs.getString("phone"),
                    rs.getString("position"),
                    rs.getString("category"),
                    rs.getInt("salary"),
                    rs.getDate("ppe").toLocalDate(),
                    rs.getInt("course_hours_sum"),
                    rs.getDate("employment_date").toLocalDate()
            ));
        }
        System.out.println(list);
        closeAll(rs, ps);
    }

    public static ObservableList<Employee> getEmployeeInfoByFacilityId(int id){
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            String sql = "select employee_data_view.* from employee_data_view JOIN employee_facility ON employee_data_view.id_employee = employee_facility.id_employee WHERE id_facility = " + id;
            getEmployeeInfoInner(list, sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return list;
    }

    public static ObservableList<Facility> getFacilityInfo() {
        ObservableList<Facility> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM facility_data_view2";
            ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Facility(
                        rs.getInt("id_facility"),
                        rs.getString("city"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("schedule")));
            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }

    public static ObservableList<Course> getCourseInfo() {
        ObservableList<Course> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM employee_course_view";
            ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Course(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("name"),
                        rs.getDate("course_date").toLocalDate(),
                        rs.getInt("hours")));
            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }
    public static void fillHolidayAdditionDataHoliday(ComboBox<String> holidayName) {
        String sql = "SELECT e.enumlabel FROM pg_type t, pg_enum e WHERE t.oid = e.enumtypid AND typname = 'holiday_name';";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                holidayName.getItems().add(res.getString("enumlabel"));
            }
            closeAll(res, pst);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
    }
    public static void fillHolidayAdditionDataEmployee(ComboBox<Employee> employeeComboBox) {
        try {
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Employee empToAdd = new Employee();
                empToAdd.setId(res.getInt(1));
                empToAdd.setFirstName(res.getString(2));
                empToAdd.setLastName(res.getString(3));
                employeeComboBox.getItems().add(empToAdd);
            }
            ps.close();
        } catch (SQLException e) {
            AlertBox.errorAlert("B??ad", e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    public static boolean addHoliday(Holiday holiday) {
        if(Objects.isNull(holiday) || holiday.getEmployee().getId() < 0 ||
            holiday.getFacility().getId() < 0 || holiday.getProceeds() < 0){
            return false;
        }
        try {
            String sql = "select addHolidayForFacility(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, holiday.getFacility().getId());
            pst.setInt(2, holiday.getEmployee().getId());
            pst.setObject(3, holiday.getName(), Types.OTHER);
            pst.setDate(4, Date.valueOf(holiday.getDate()));
            pst.setDouble(5, holiday.getProceeds());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return false;
    }
    public static boolean deleteEmployee(Employee employee) {
        if(Objects.isNull(employee) || employee.getId() < 0){
            return false;
        }
        String sql = "SELECT deleteEmployee(?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean addCourseToEmployee(Course courseToAdd) {
        if(Objects.isNull(courseToAdd) || courseToAdd.getEmployee().getId() < 0 || courseToAdd.getHours() <0){
            return false;
        }
        String sql = "SELECT addCourse(?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, courseToAdd.getEmployee().getId());
            ps.setString(2, courseToAdd.getName());
            ps.setInt(3, courseToAdd.getHours());
            ps.setDate(4, Date.valueOf(courseToAdd.getDate()));
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static ObservableList<Employee> getEmployees() throws SQLException {
        String sql = "select * from employee";// tutaj select wszystkich pracownik??w powinien byc
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet res1 = ps.executeQuery();
        ObservableList<Employee> result = FXCollections.observableArrayList();
        while (res1.next()) {
            Employee employee = new Employee();
            employee.setId(res1.getInt("id_employee"));
            employee.setFirstName(res1.getString("first_name"));
            employee.setLastName(res1.getString("last_name"));
            result.add(employee);
            }
        return result;
    }

    public static ObservableList<Employee> getEmployeesByFacilityID(int id) throws SQLException {
        String sql = "select * from employee_facility, employee where id_facility= "+id+" AND employee_facility.id_employee = employee.id_employee ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet res1 = ps.executeQuery();
        ObservableList<Employee> result = FXCollections.observableArrayList();
        while (res1.next()) {
            Employee employee = new Employee();
            employee.setId(res1.getInt("id_employee"));
            employee.setFirstName(res1.getString("first_name"));
            employee.setLastName(res1.getString("last_name"));
            result.add(employee);
        }
        return result;
    }


    public static boolean addEmployeeToFacility(int employeeId, int facilityID) {
        String sql = "SELECT addemployeetofacilitybyid(?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.setInt(2, facilityID);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public static boolean addInspection(int employeeId, int facilityID, Date date, String descr) {
        String sql = "SELECT addinspection(?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.setInt(2, facilityID);
            ps.setDate(3, date);
            ps.setString(4, descr);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public static boolean addCheckup(String question, String answer, Date date, int empid, String descr) {
        String idQuery = "SELECT id_inspection FROM inspection ORDER BY id_inspection DESC LIMIT 1";
        String sql = "SELECT addcheckup(?, ?, ?, ?, ?, ?)";
        String inspectionId = "";
        try {
            PreparedStatement pst = conn.prepareStatement(idQuery);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                inspectionId = res.getString("id_inspection");
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(inspectionId));
            ps.setObject(2, question,Types.OTHER);
            ps.setObject(3, answer,Types.OTHER);
            ps.setDate(4, date);
            ps.setInt(5, empid);
            ps.setString(6, descr);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public static ObservableList<Violation> getViolationInfo(Employee employee) {
        ObservableList<Violation> list = FXCollections.observableArrayList();
        try{
            int id = employee.getId();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM violation WHERE id_employee = ?";

            ps = getConn().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Date date1 = rs.getDate("correction_date");
                list.add(new Violation(
                        rs.getInt("id_violation"),
                        rs.getString("description"),
                        employee,
                        rs.getDate("correction_term").toLocalDate(),
                        Objects.isNull(date1) ? LocalDate.of(1970,1,1) : date1.toLocalDate()
                       ));

            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }
    public static boolean approveResponsibility(Violation item){
        String sql = "UPDATE violation SET correction_date = ? WHERE id_violation = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, item.getId());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public static double getAccountMoney() {
        String sql = "select get_account_money()";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                return res.getDouble("get_account_money");
            }
            closeAll(res, ps);
        } catch (SQLException e) {
            AlertBox.errorAlert("B????d", e.getMessage().split("Where")[0]);
        }
        return 0.0;
    }

    public static ObservableList<Delivery> getDeliveryInfo(){
        ObservableList<Delivery> list = FXCollections.observableArrayList();;
        try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM delivery_supplier_facility_view";
             ps = getConn().prepareStatement(sql);
             rs = ps.executeQuery();
            while(rs.next()){
                Supplier sup = new Supplier( rs.getInt("id_supplier"),
                        rs.getString("supName"),
                        rs.getString("supPhone"),
                        rs.getString("supEmail"));

                Facility fac = new Facility(rs.getInt("id_facility"),
                        rs.getString("fasCity"),
                        rs.getString("fasName"),
                        rs.getString("fasAddress"),
                       rs.getString("fasSchedule"));
                list.add(new Delivery(
                        rs.getInt("id_delivery_record"),
                        sup,
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getInt("payment_delay"),
                        Math.round(rs.getFloat("amount_to_pay")),
                        rs.getBoolean("is_paid"),
                        fac));
            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }

    public static void payToSupplier(int id_supplier, int amountToPay){
        try {
            String sql = "select payToSupplier(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id_supplier);
            ps.setInt(2, amountToPay);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            AlertBox.errorAlert("B????d", e.getMessage().split("Where")[0]);
        }
    }

    public static void payForDelivery(int id_delivery_record, int id_supplier, int amountToPay){
        double currentTotal = getAccountMoney();
        try {
            String sql = "CREATE OR REPLACE FUNCTION  get_account_money() returns double precision" +
                    " language plpgsql" +
                    " as" +
                    " $$" +
                    " begin" +
                    " return "+ (currentTotal - amountToPay) +";" +
                    " end;" +
                    " $$;";
            conn.createStatement().execute(sql);
            payToSupplier(id_supplier,amountToPay);
            String sql1 = "update delivery_record set is_paid = ? where id_delivery_record = ?";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setBoolean(1, true);
            ps.setInt(2, id_delivery_record);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            AlertBox.errorAlert("B????d op??aty", e.getMessage().split("Where")[0]);
        }

    }

    public static boolean addSupplier(Supplier supplier){
        if(Objects.isNull(supplier) ){
            return false;
        }
        String sql = "select addSupplier(?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getPhone());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            AlertBox.errorAlert("B????d", e.getMessage().split("Where")[0]);
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void getSupplierToComboBox(ComboBox<String> deliverySupplierComboBox){
        try{
            String sql = "select * from supplier";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String tmp = rs.getInt("id_supplier") +
                        " | "+ rs.getString("name") +
                        " | " + rs.getString("email");
                deliverySupplierComboBox.getItems().add(tmp);
            }

            ps.close();
            rs.close();

        }
        catch (Exception e){
            AlertBox.errorAlert("B????d", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static Facility getFacilityByManagerId(int id_manager) throws SQLException {
        Facility facility = new Facility();
        try{
            String sql = "select f.id_facility,  f.address, f.schedule, f.city from employee_facility ef, facility_info f " +
                    "where ef.id_employee = ? and ef.id_facility = f.id_facility";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_manager);
            ResultSet rs = ps.executeQuery();
            rs.next();
            facility = new Facility(rs.getInt("id_facility"),
                    rs.getString("city"),
                    rs.getString("address"),
                    rs.getString("schedule")
                    );
            closeAll(rs,ps);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new SQLException("Nie uda??o si?? pobra?? danych obiektu");
        }
        return facility;
    }

    public static boolean addDelivery(Delivery delivery){
        if(Objects.isNull(delivery) ){
            return false;
        }
        String sql = "select addDelivery(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, delivery.getSupplier().getId());
            ps.setInt(2, delivery.getFacility().getId());
            ps.setDate(3, Date.valueOf(delivery.getDate()));
            ps.setInt(4, delivery.getPaymentDelay());
            ps.setInt(5, delivery.getAmountToPay());
            ps.setBoolean(6, delivery.isPaid());
            ps.execute();
            ps.close();
                return true;

        } catch (SQLException e) {
            AlertBox.errorAlert("B????d", e.getMessage().split("Where")[0]);
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void addFacilityToComboBox(ComboBox<String> box){
        try{
            String sql = "SELECT * FROM facility_data_view2";
            PreparedStatement ps = getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String tmp = rs.getInt("id_facility")+
                        " | " + rs.getString("name") +
                        " | " + rs.getString("city") +
                        " | " +   rs.getString("address");
                box.getItems().add(tmp);
            }
            closeAll(rs,ps);
        }catch(Exception e){
            AlertBox.errorAlert("B??ad", e.getMessage());
        }

    }

    public static ArrayList<Integer> getEmployeeByFacilityID(int facilityId){
        ArrayList<Integer> employeeIds = new ArrayList<>();
        try{
            String sql = "SELECT id_employee FROM employee_facility where id_facility = ?";
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, facilityId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               employeeIds.add(rs.getInt("id_employee"));
            }
            closeAll(rs,ps);
        }catch(Exception e){
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return employeeIds;
    }


    public static ObservableList<String> getHolidayInfoByFacilityID(int id) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT h.* from facility_holiday fh JOIN holiday h ON fh.id_holiday=h.id_holiday WHERE fh.id_facility = " + id;
            ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Date date1 = rs.getDate("date");
                list.add(date1.toString() + "  " + rs.getString("name"));
            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }


    public static ObservableList<String> getDeliveryInfoByFacilityID(int id) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * from delivery_supplier_facility_view WHERE id_facility = " + id;
            ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Date date1 = rs.getDate("delivery_date");
                Float amount = rs.getFloat("amount_to_pay");
                list.add("Dzie??: " + date1.toString() + "     Dostawca:  " + rs.getString("supemail") + "     Koszt: " + amount.toString());
            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }

    public static ObservableList<String> getInspectionInfoByFacilityID( int id ) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * from inspection WHERE id_facility = " + id;
            ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Date date1 = rs.getDate("date");
                int employeeid = rs.getInt("id_employee");
                list.add("Pracownik o ID: " + employeeid + " Dzien: " + date1.toString() + " Opis: " + rs.getString("description") );
            }
            closeAll(rs,ps);

        }catch(Exception e){
            System.out.println(e.getMessage());
            AlertBox.errorAlert("B??ad", e.getMessage());
        }
        return  list;
    }

    public static List<String> getEmploymentPlace(int id) {
        List<String> employmentPlace = new ArrayList<>();
        try {
            String sql = "select * from employee_facility ef join facility_data_view2 fv on ef.id_facility = fv.id_facility where ef.id_employee = " + id;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                employmentPlace.add(res.getString("name") + ", " + res.getString("city") + ", " + res.getString("address"));
            }
            closeAll(res, pst);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employmentPlace;
    }

}

