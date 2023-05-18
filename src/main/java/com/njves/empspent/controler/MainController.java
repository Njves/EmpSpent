package com.njves.empspent.controler;

import com.njves.empspent.Application;
import com.njves.empspent.app.Toast;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Tab tabEmployee;
    public Tab tabSpeciality;
    public Tab tabWorkDays;
    public TabPane tabPane;
    public Tab tabRequirements;
    public Tab tabWork;

    private Tab selectedTab;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<Tab, String> tabMap = new HashMap<>();
        tabMap.put(tabEmployee, "add-emp.fxml");
        tabMap.put(tabSpeciality, "add-spec.fxml");
        tabMap.put(tabWorkDays, "add-work.fxml");
        tabMap.put(tabRequirements, "req-spec.fxml");
        tabMap.put(tabWork, "worked-days.fxml");
        loadContentToTab(tabMap.get(tabEmployee));
        tabPane.getSelectionModel().selectedItemProperty().addListener(event -> {
            loadContentToTab(tabMap.get(tabPane.getSelectionModel().getSelectedItem()));
        });

    }

    private void loadContentToTab(String document) {
        try {
            tabPane.getSelectionModel().getSelectedItem().setContent(FXMLLoader.load(Application.class.getResource(document)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
