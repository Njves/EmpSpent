package com.njves.empspent.controler;

import com.njves.empspent.model.Database;
import com.njves.empspent.model.Speciality;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSpecialityController implements Initializable {
    @FXML
    public Button addButton;
    @FXML
    public TextField textFieldTitle;
    @FXML
    public TextField textFieldSalary;
    @FXML
    public Text textState;
    @FXML
    public Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addButton.setOnMouseClicked((event -> {
            if(!validate()) return;
            Database.getInstance().insertSpeciality(new Speciality(textFieldTitle.getText(),
                    Double.parseDouble(textFieldSalary.getText())));
        }));
    }

    private boolean validate() {
        boolean valid;
        valid = !textFieldTitle.getText().isEmpty();
        try {
            double salary = Double.parseDouble(textFieldSalary.getText());
        }catch (NumberFormatException e) {
            valid = false;
            System.out.println("Что то плохое");
        }
        return valid;
    }
}
