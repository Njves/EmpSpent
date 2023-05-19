package com.njves.empspent.controler;

import com.njves.empspent.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.*;

public class MoneyStatisticController implements Initializable {
    public StackedBarChart<String, Double> stackedBarChartMoney;
    public ChoiceBox<String> choiceBoxType;
    public Button buttonMonth;
    public Button buttonQuarter;
    public Button buttonHalfYear;
    public Button buttonYear;
    private DataBarChart dataBarChart = new WorkMoneyBarChart();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Employee> employeeQuery = new EmployeeQuery();
        WorkDayQueryAdapter workDayQuery = new WorkDayQueryAdapter();
        List<Employee> employees = employeeQuery.select();
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
