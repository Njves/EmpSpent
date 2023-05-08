package com.njves.empspent.controler;

import com.njves.empspent.model.Database;
import com.njves.empspent.model.Employee;
import com.njves.empspent.model.Speciality;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML
    public TextField textFieldName;
    @FXML
    public TextField textFieldLastName;
    @FXML
    public MenuButton menuSpeciality;
    @FXML
    public Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String, Speciality> map = new HashMap<>();
        for (Speciality speciality : Database.getInstance().getSpecialities()) {
            map.put(speciality.getTitle(), speciality);
            MenuItem item = new MenuItem(speciality.getTitle());
            item.setOnAction((event -> menuSpeciality.setText(item.getText())));
            menuSpeciality.getItems().add(item);
        }

        addButton.setOnMouseClicked((event -> {
            String builder = textFieldName.getText() +
                    " " +
                    textFieldLastName.getText();
            System.out.println(menuSpeciality.getText());
            Database.getInstance().insertEmployee(new Employee(builder, map.get(menuSpeciality.getText())));
        }));
    }

    private boolean validate() {
        boolean valid = true;

        return valid;
    }
}
