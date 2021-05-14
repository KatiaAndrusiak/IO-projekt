package io.project;

import io.project.database.DBManagment;
import io.project.entities.Delivery;
import io.project.entities.Facility;
import io.project.entities.Supplier;
import io.project.screenloader.ChangeScreen;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DeliveryListController implements Initializable {
    @FXML
    private AnchorPane viewPane;

    @FXML
    private TableView<Delivery> table;

    @FXML
    private TableColumn<Delivery, Supplier> supplierCol;

    @FXML
    private TableColumn<Delivery, LocalDate> dateCol;

    @FXML
    private TableColumn<Delivery, Integer> paymentDelayCol;

    @FXML
    private TableColumn<Delivery, Integer> amountCol;

    @FXML
    private TableColumn<Delivery, String> statusCol;

    @FXML
    private ComboBox<Facility> facilityCB;

    @FXML
    private Button supplierAddButton;

    @FXML
    private Button payButton;

    public void openSupplierAddition(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("supplierAddition.fxml")));
    }

    public void deliveryList() {
        supplierCol.setCellValueFactory(new PropertyValueFactory<Delivery, Supplier>("supplierName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Delivery, LocalDate>("date"));
        paymentDelayCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("paymentDelay"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("amountToPay"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Delivery, String>("status"));

        ObservableList<Delivery> list;
        list = DBManagment.getDeliveryInfo();
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deliveryList();
    }
}
