package io.project.validation;

import io.project.alert.AlertBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa służąca do walidacji wprowadzonych danych
 */
public class FieldValidation {

    /**
     * Walidacja pól gdzie oczekujemy tylko litery
     *
     * @param data TextField
     * @return true jeśli wprowadzone dane są ok
     */
    public static boolean validateCharField(TextField data) {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(data.getText());
        if (m.find() && m.group().equals(data.getText())) {
            return true;
        } else {
            AlertBox.errorAlert(" Niepoprawnie wprowadzone dane! ", "Wprowadź prawidłowe dane!!!");
            return false;
        }
    }


    /**
     * Walidacja pól gdzie oczekujemy email
     *
     * @param data TextField
     * @return true jeśli wprowadzone dane są ok
     */
    public static boolean validateEmail(TextField data) {
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(data.getText());

        if (m.find() && m.group().equals(data.getText())) {
            return true;
        } else {
            AlertBox.errorAlert(" Niepoprawnie wprowadzony e-mail! ", "Wprowadź prawidłowe dane!!!");
            return false;
        }
    }

    /**
     * Walidacja ComboBox
     *
     * @param myComboBox ComboBox
     * @return true gdy  zotało coś wybrane
     */
    public static boolean validateComboBox(ComboBox myComboBox) {
        if (!myComboBox.getSelectionModel().isEmpty()) {
            return true;
        } else {
            AlertBox.errorAlert("Nie wprowadzone wszystkie dane! ", "Wybierz z listy informacje!!!");
            return false;
        }
    }

    /**
     * Walidacja pól gdzie oczekujemy liczbę
     *
     * @param data TextField
     * @return true jeśli wprowadzone dane są ok
     */
    public static boolean validateNum(TextField data) {
        Pattern p = Pattern.compile("([0-9]+)");
        Matcher m = p.matcher(data.getText());
        if (m.find() && m.group().equals(data.getText())) {
            return true;
        } else {
            AlertBox.errorAlert(" Niepoprawnie wprowadzona liczba.", "Sprawdź poprawność wprowadzonych danych.");
            return false;
        }
    }
    /**
     * Walidacja pól gdzie oczekujemy liczbę zmiennoprzecinkowa
     *
     * @param data TextField
     * @return true jeśli wprowadzone dane są ok
     */
    public static boolean validateFloatingNum(TextField data) {
        Pattern p = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");
        Matcher m = p.matcher(data.getText());
        if (m.find() && m.group().equals(data.getText())) {
            return true;
        } else {
            AlertBox.errorAlert(" Niepoprawnie wprowadzona liczba.", "Sprawdź poprawność wprowadzonych danych.");
            return false;
        }
    }
    /**
     * Walidacja DatePicker

     * @return true gdy  zotało coś wybrane
     */
    public static boolean validateDatePicker(DatePicker picker) {
        if (!(picker.getValue() == null)) {
            return true;
        } else {
            AlertBox.errorAlert("Nie wprowadzone wszystkie dane!", "Wybierz datę!");
            return false;
        }
    }

    public static void filterForTextFields(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[A-Za-z]*")) {
                textField.setText(newValue.replaceAll("[^[A-Za-z]]", ""));
            }
        });
    }



    public static void filterForNumberFields(TextField textField) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([0-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        textField.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
    }

    public static boolean validateFirstAndLastName(TextField textField) {
        if (textField.getText().length() < 50 && !textField.getText().equals("")) {
            return true;
        } else {
            AlertBox.errorAlert("Niepoprawnie wprowadzone dane! (Pole imię lub nazwisko)", "Sprawdz dane i sprobuj jeszcze raz");
            return false;
        }
    }

    public static boolean validatePhoneNumber(TextField textField) {
        if ( (textField.getText().length() <= 11 && textField.getText().length() >= 9) && !textField.getText().equals("")) {
            return true;
        } else {
            AlertBox.errorAlert("Niepoprawnie wprowadzone dane! Długość numeru telefonu musi być w przedziale 9-11 cyfr", "Sprawdz dane i sprobuj jeszcze raz");
            return false;
        }
    }

    public static boolean validateCoursesHours(TextField textField) {
        if ( (Integer.parseInt(textField.getText()) <= 100 && Integer.parseInt(textField.getText()) >= 0) && !textField.getText().equals("")) {
            return true;
        } else {
            AlertBox.errorAlert("Niepoprawnie wprowadzone dane! Suma godzin na kursach musi być w przedziale [0 - 100]", "Sprawdz dane i sprobuj jeszcze raz");
            return false;
        }
    }

    public static boolean validateLoginAndPassword(TextField textField) {
        if (!textField.getText().equals("") && (textField.getText().length() >= 3 && textField.getText().length() <= 50)) {
            return true;
        } else {
            AlertBox.errorAlert("Niepoprawnie wprowadzone dane! Długość loginu oraz hasła musi być w przedziale [3-50] znaków", "Sprawdz dane i sprobuj jeszcze raz");
            return false;
        }
    }

    public static boolean validateFacilityAddress(TextField textField) {
        if (!textField.getText().equals("") && textField.getText().matches("[a-zA-z]*,[ ]*[0-9]{1,5}[A-Za-z]?")) {
            return true;
        } else {
            AlertBox.errorAlert("Niepoprawnie wprowadzone dane! Adres obiektu musi być w postaci: Miasto, numer budynku", "Sprawdz dane i sprobuj jeszcze raz");
            return false;
        }
    }

    public static boolean validateFacilitySchedule(TextField textField) {
        if (!textField.getText().equals("") && textField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]-(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]")) {
            return true;
        } else {
            AlertBox.errorAlert("Niepoprawnie wprowadzone dane! Czas pracy obiektu musi być w postaci: HH:MM-HH:MM", "Sprawdz dane i sprobuj jeszcze raz");
            return false;
        }
    }




}
