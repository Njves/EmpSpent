package com.njves.empspent.controler;

import com.njves.empspent.app.OpenableWindow;
import com.njves.empspent.model.Database;
import com.njves.empspent.model.RequiredSpeciality;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UpdateRequiredSpecialityController implements Initializable, OpenableWindow {
    @FXML
    public TextField textFieldEmpCapacity;
    @FXML
    public Button buttonUpdate;

    public RequiredSpeciality requiredSpeciality;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonUpdate.setOnMouseClicked((event -> {
            requiredSpeciality.setEmployeesCapacity(Integer.parseInt(textFieldEmpCapacity.getText()));
            Database.getInstance().updateRequirementSpecialityEmployeeCapacity(requiredSpeciality);
        }));
    }

    public void setRequiredSpeciality(RequiredSpeciality requiredSpeciality) {
        this.requiredSpeciality = requiredSpeciality;
        textFieldEmpCapacity.setText(String.valueOf(requiredSpeciality.getEmployeesCapacity()));
    }

    @Override
    public void setArguments(HashMap<String, Object> args, String[] keys) {
        setRequiredSpeciality((RequiredSpeciality) args.get(keys[0]));
    }
}
