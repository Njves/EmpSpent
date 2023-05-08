package com.njves.empspent.controler;

import com.njves.empspent.Application;
import com.njves.empspent.app.WindowFactory;
import com.njves.empspent.app.WindowListener;
import com.njves.empspent.model.Database;
import com.njves.empspent.model.RequiredSpeciality;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class RequiredSpecialityController implements Initializable {
    @FXML
    public ListView<RequiredSpeciality> listView;
    public Button buttonBack;
    public Button buttonAdd;

    private final WindowFactory factory = new WindowFactory();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateList();

        listView.setOnMouseClicked((event -> {
            RequiredSpeciality requiredSpeciality = null;
            if(listView.getSelectionModel().getSelectedItems().size() > 0) {
                requiredSpeciality = listView.getSelectionModel().getSelectedItems().get(0);
            }
            HashMap<String, Object> map = new HashMap<>();
            String[] key = new String[]{"requiredSpeciality"};
            map.put(key[0], requiredSpeciality);
            factory.showWindow(map, key, this::updateList);


        }));
    }

    private void updateList() {
        listView.getItems().clear();
        List<RequiredSpeciality> list = Database.getInstance().getRequiredSpeciality();
        list.forEach(requiredSpeciality -> listView.getItems().add(requiredSpeciality));
        listView.setCellFactory(param ->  new ListCell<>(){
            @Override
            protected void updateItem(RequiredSpeciality requiredSpeciality, boolean b) {
                super.updateItem(requiredSpeciality, b);
                setText(b ? null : requiredSpeciality.getItemText());
            }
        });

    }
}
