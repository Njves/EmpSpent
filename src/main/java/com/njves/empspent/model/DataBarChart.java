/**
 * Модуль содержащий интерфейс представления графика
 */
package com.njves.empspent.model;

import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.List;

/**
 * интерфейс представления графика статистики
 */
public interface DataBarChart {
    /**
     * Задает данные в столбачтую диаграмму
     * @param stackedBarChart столбчатая диаграмма
     * @param workDayList список рабочих дней
     */
    void setData(StackedBarChart<String, Double> stackedBarChart, List<WorkDay> workDayList);

    /**
     * Задает произвольные в столбчатую диаграмму
     * @param stackedBarChartMoney столбчатая диаграмма
     * @param workDayList список рабочих дней
     * @param series серия рабочих дней
     * @param series1 серия нерабочих дней
     */
    default void setDataBarChart(StackedBarChart<String, Double> stackedBarChartMoney, List<WorkDay> workDayList, XYChart.Series<String, Double> series, XYChart.Series<String, Double> series1) {
        series.getData().clear();
        series1.getData().clear();
        stackedBarChartMoney.getData().clear();
        HashMap<String, Integer> workLengthMap = new HashMap<>();
        workDayList.forEach(work -> {
            String name = work.getEmployee().getName();
            if(workLengthMap.containsKey(name)) {
                Integer count = workLengthMap.get(name);
                workLengthMap.put(name, count + 1);
            } else {
                workLengthMap.put(name, 1);
            }
        });
        workDayList.forEach(workDay -> fillSeries(workDay, series, series1, workLengthMap));
    }

    /**
     * Заполняет серии данными
     * @param workDay рабочий день
     * @param series рабочая серия
     * @param series1 не рабочая серия
     * @param workLengthMap длина серии для конкретного сотрудника
     */
    default void fillSeries(WorkDay workDay, XYChart.Series<String, Double> series, XYChart.Series<String, Double> series1, HashMap<String, Integer> workLengthMap) {

    }
}
