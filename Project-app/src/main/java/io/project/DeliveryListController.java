package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Delivery;
import io.project.entities.Supplier;
import io.project.screenloader.ChangeScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private ComboBox<String> facilityCB;

    @FXML
    private Button supplierAddButton;

    @FXML
    private Button payButton;

    ObservableList<Delivery> list;
    Delivery selectedItem;

    public  void onTableClick(){
        enablePay();
    }

    public void openSupplierAddition(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("supplierAddition.fxml")));
    }

    public void deliveryList() {
        supplierCol.setCellValueFactory(new PropertyValueFactory<Delivery, Supplier>("supplierName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Delivery, LocalDate>("date"));
        paymentDelayCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("paymentDelay"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("amountToPay"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Delivery, String>("status"));

        list = DBManagment.getDeliveryInfo();
        table.setItems(list);

        statusCol.setCellFactory((tableColumn) -> {
            TableCell<Delivery, String> tableCell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (item != null) {
                        super.updateItem(item, empty);
                        this.setText(null);
                        this.setGraphic(null);
                        if (!empty) {
                            if (item.equals("op??acono")) {
                                setDisable(true);
                            } else {
                                setDisable(false);
                            }
                            setText(item.toString());
                        }
                    }
                }
            };

            return tableCell;
        });

    }

    public void onFacilitySelection(){
        if(!facilityCB.getSelectionModel().isEmpty()){
            int facilityId=Integer.parseInt(facilityCB.getSelectionModel().getSelectedItem().split(" | ")[0]);

            table.setItems(FXCollections.observableArrayList(list
                    .stream()
                    .filter(el -> el.getFacility().getId() == facilityId)
                    .collect(Collectors.toList()))
            );
        }
        else {
            deliveryList();
        }
    }

    public  void  payForDeliveryButton(){
        DBManagment.payForDelivery(selectedItem.getId(),
                selectedItem.getSupplier().getId(),
                selectedItem.getAmountToPay());
        payButton.setDisable(true);
        selectedItem.setPaid(true);
        onFacilitySelection();
    }

    public void enablePay(){
        try {
            if (table.getSelectionModel().getSelectedItem() != null) {
                selectedItem = table.getSelectionModel().getSelectedItem();
                if(!selectedItem.isPaid()){
                    payButton.setDisable(false);
                }else{
                    payButton.setDisable(true);
                }
            }
        }
        catch (Exception e){
            AlertBox.errorAlert("B????d", e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        payButton.setDisable(true);
        deliveryList();
        DBManagment.addFacilityToComboBox(facilityCB);
    }
}
