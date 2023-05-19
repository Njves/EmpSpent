package com.njves.empspent.model;

import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class SpecialityBarChart implements DataBarChart {
    @Override
    public void setData(StackedBarChart<String, Double> stackedBarChartMoney, List<WorkDay> workDayList) {
        stackedBarChartMoney.getXAxis().setLabel("Сотрудники");
        stackedBarChartMoney.getYAxis().setLabel("Сумма з/п");
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        setDataBarChart(stackedBarChartMoney, workDayList, series, series1);
        stackedBarChartMoney.getData().addAll(series, series1);
        stackedBarChartMoney.setLegendVisible(false);
    }



    public void fillSeries(WorkDay workDay, XYChart.Series<String, Double> series, XYChart.Series<String, Double> series1) {
        if(workDay.isWorked())
            series.getData().add(new XYChart.Data<>(workDay.getEmployee().getSpeciality().getTitle(), workDay.getEmployee().getSpeciality().getSalary()));
        else
            series1.getData().add(new XYChart.Data<>(workDay.getEmployee().getSpeciality().getTitle(), workDay.getEmployee().getSpeciality().getSalary()));
    }


}
