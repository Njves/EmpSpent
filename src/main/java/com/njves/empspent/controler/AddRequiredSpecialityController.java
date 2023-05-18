package com.njves.empspent.controler;

import com.njves.empspent.app.CustomController;
import com.njves.empspent.app.OpenableWindow;
import com.njves.empspent.app.Toast;
import com.njves.empspent.model.Database;
import com.njves.empspent.model.RequiredSpeciality;
import com.njves.empspent.model.Speciality;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddRequiredSpecialityController extends CustomController implements Initializable {

    @FXML
    public MenuButton menuButtonSpeciality;

    @FXML
    public TextField textFieldEmpCapacity;

    @FXML
    public Button buttonAdd;

    @Override
    public void setArguments(HashMap<String, Object> args, String[] keys) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String, Speciality> map = new HashMap<>();
        for (Speciality speciality : Database.getInstance().getSpecialities()) {
            map.put(speciality.getTitle(), speciality);
            MenuItem item = new MenuItem(speciality.getTitle());
            item.setOnAction((event -> menuButtonSpeciality.setText(item.getText())));
            menuButtonSpeciality.getItems().add(item);
            menuButtonSpeciality.setText(speciality.getTitle());
        }

        buttonAdd.setOnMouseClicked(event -> {
            try {
                Database.getInstance().addRequirementSpeciality(new RequiredSpeciality(map.get(menuButtonSpeciality.getText()),
                        Integer.parseInt(textFieldEmpCapacity.getText())));
            } catch (SQLException e) {
                Toast.makeText((Stage) menuButtonSpeciality.getScene().getWindow(), "Такая профессия уже зафиксирована");
                return;
            }
            ((Stage) (textFieldEmpCapacity.getScene().getWindow())).close();
        });
    }


}
