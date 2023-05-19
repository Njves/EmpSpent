package com.njves.empspent.model;

import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public interface DataBarChart {
    void setData(StackedBarChart<String, Double> stackedBarChart, List<WorkDay> workDayList);

    default void setDataBarChart(StackedBarChart<String, Double> stackedBarChartMoney, List<WorkDay> workDayList, XYChart.Series<String, Double> series, XYChart.Series<String, Double> series1) {
        series.getData().clear();
        series1.getData().clear();
        stackedBarChartMoney.getData().clear();
        workDayList.forEach(workDay -> fillSeries(workDay, series, series1));
    }
    default void fillSeries(WorkDay workDay, XYChart.Series<String, Double> series, XYChart.Series<String, Double> series1) {

    }
}
