package com.njves.empspent.controler;

import com.njves.empspent.app.WindowFactory;
import com.njves.empspent.model.Query;
import com.njves.empspent.model.RequiredSpeciality;
import com.njves.empspent.model.RequiredSpecialityQuery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class RequiredSpecialityController implements Initializable {
    @FXML
    public ListView<RequiredSpeciality> listView;
    public Button buttonAdd;

    private final WindowFactory factory = new WindowFactory();

    private final Query<RequiredSpeciality> query = new RequiredSpecialityQuery();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateList();

        listView.setOnMouseClicked((event -> {
            RequiredSpeciality requiredSpeciality = null;
            if(listView.getSelectionModel().getSelectedItems().size() > 0) {
                requiredSpeciality = listView.getSelectionModel().getSelectedItems().get(0);
            }
            if(requiredSpeciality == null) {
                return;
            }
            HashMap<String, Object> map = new HashMap<>();
            String[] key = new String[]{"requiredSpeciality"};
            map.put(key[0], requiredSpeciality);
            factory.showWindow("upd-req", map, key, this::updateList);
        }));

        buttonAdd.setOnMouseClicked(event -> factory.showWindow("add-req",null, null, this::updateList));

    }

    private void updateList() {
        listView.getItems().clear();
        List<RequiredSpeciality> list = query.select();
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
