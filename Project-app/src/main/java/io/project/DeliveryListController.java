package io.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

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
    private TableColumn<Delivery, String> paymentDelayCol;

    @FXML
    private TableColumn<Delivery, Integer> amountCol;

    /// ---------------------
    /// W tej kolumnie trzeba umiescic buttony do oplaty dostawy.
    /// Jezeli jest oplacona, to button ma tekst "Oplacono" i jets disable.
    /// Jezeli nie, to tekst "Zaplac" i enable.
    /// Tu jest przykladowa implementacja:
    /// https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view
    /// ---------------------
    @FXML
    private TableColumn<Delivery, String> statusCol;

    @FXML
    private ComboBox<Facility> facilityCB;

    @FXML
    private Button supplierAddButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
