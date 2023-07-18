/**
 * Модуль содеражащий класс контроллера сбора статистика по з/п сотрудников
 */
package com.njves.empspent.controler;

import com.njves.empspent.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.*;

/**
 * Контроллер сбора статистика по з/п сотрудников
 */
public class MoneyStatisticController implements Initializable {
    /**
     * График зарплат
     */
    public StackedBarChart<String, Double> stackedBarChartMoney;

    /**
     * Чойс бокс типа вывода
     */
    public ChoiceBox<String> choiceBoxType;

    /**
     * кнопка вывода данных за месяц
     */
    public Button buttonMonth;

    /**
     * кнопка вывода данных за квартал
     */
    public Button buttonQuarter;

    /**
     * кнопка вывода данных за полгода
     */
    public Button buttonHalfYear;

    /**
     * кнопка вывода данных за год
     */
    public Button buttonYear;

    /**
     * Модель выгрузки данных
     */
    private DataBarChart dataBarChart = new WorkMoneyBarChart();

    /**
     * Функция инициализаци контроллера
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WorkDayQueryDecorator workDayQuery = new WorkDayQueryDecorator();
        List<String> types = new ArrayList<>();
        types.add("Для всех");
        types.add("Для специальности");
        choiceBoxType.setItems(FXCollections.observableList(types));
        choiceBoxType.setValue(types.get(0));
        choiceBoxType.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("Для специальности"))
                dataBarChart = new SpecialityBarChart();
            else
                dataBarChart = new WorkMoneyBarChart();
        });
        stackedBarChartMoney.setAnimated(false);

        buttonMonth.setOnMouseClicked(event -> dataBarChart.setData(stackedBarChartMoney,
                workDayQuery.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1)));
        buttonQuarter.setOnMouseClicked(event -> dataBarChart.setData(stackedBarChartMoney, workDayQuery.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1,
                new GregorianCalendar().get(Calendar.MONTH) + 1 + 3)));
        buttonHalfYear.setOnMouseClicked(event -> dataBarChart.setData(stackedBarChartMoney, workDayQuery.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1,
                new GregorianCalendar().get(Calendar.MONTH) + 1 + 6)));
        buttonYear.setOnMouseClicked(event -> dataBarChart.setData(stackedBarChartMoney, workDayQuery.getByYearInterval(new GregorianCalendar().get(Calendar.YEAR),
                new GregorianCalendar().get(Calendar.YEAR))));

    }
}
