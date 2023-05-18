package com.njves.empspent.controler;

import com.njves.empspent.app.CustomController;
import com.njves.empspent.app.OpenableWindow;
import com.njves.empspent.model.Database;
import com.njves.empspent.model.Query;
import com.njves.empspent.model.RequiredSpeciality;
import com.njves.empspent.model.RequiredSpecialityQuery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UpdateRequiredSpecialityController extends CustomController implements Initializable {
    @FXML
    public TextField textFieldEmpCapacity;
    @FXML
    public Button buttonUpdate;

    public RequiredSpeciality requiredSpeciality;

    private Query<RequiredSpeciality> query = new RequiredSpecialityQuery();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonUpdate.setOnMouseClicked((event -> {
            requiredSpeciality.setEmployeesCapacity(Integer.parseInt(textFieldEmpCapacity.getText()));
            query.update(requiredSpeciality);
            ((Stage) (textFieldEmpCapacity.getScene().getWindow())).close();
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
