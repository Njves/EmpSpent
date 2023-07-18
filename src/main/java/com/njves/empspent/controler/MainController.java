/**
 * модуль содержащий основной контроллер
 */
package com.njves.empspent.controler;

import com.njves.empspent.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Контроллер основного окна
 */
public class MainController implements Initializable {
    /**
     * таб окна добавления сотрудника
     */
    public Tab tabEmployee;

    /**
     * таб окна добавления специальности
     */
    public Tab tabSpeciality;

    /**
     * таб окна добавления рабочего дня
     */
    public Tab tabWorkDays;

    /**
     * таб пейн содержащий все табы
     */
    public TabPane tabPane;

    /**
     * таб окна добавления требуемых сотрудников
     */
    public Tab tabRequirements;

    /**
     * таб окна добавления рабочего дня
     */
    public Tab tabWork;

    /**
     * таб окна просмотра денег
     */
    public Tab tabMoney;

    /**
     * Вызывается при инициализации контроллера
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<Tab, String> tabMap = new HashMap<>();
        tabMap.put(tabEmployee, "add-emp.fxml");
        tabMap.put(tabSpeciality, "add-spec.fxml");
        tabMap.put(tabWorkDays, "add-work.fxml");
        tabMap.put(tabRequirements, "req-spec.fxml");
        tabMap.put(tabWork, "worked-days.fxml");
        tabMap.put(tabMoney, "money-calc.fxml");
        loadContentToTab(tabMap.get(tabEmployee));
        tabPane.getSelectionModel().selectedItemProperty().addListener(event -> {
            loadContentToTab(tabMap.get(tabPane.getSelectionModel().getSelectedItem()));
        });

    }

    /**
     * Загружает экрна из верстки
     * @param document файл верстки
     */
    private void loadContentToTab(String document) {
        try {
            tabPane.getSelectionModel().getSelectedItem().setContent(FXMLLoader.load(Application.class.getResource(document)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
