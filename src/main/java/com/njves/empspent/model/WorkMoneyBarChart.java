/**
 * Модуль содержащий реализацию заполнения зарплат сотрудников
 */
package com.njves.empspent.model;

import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Реализация заполнения зарплат сотрудников
 */
public class WorkMoneyBarChart implements DataBarChart {
    /**
     * Задает данные в столбачтую диаграмму
     * @param stackedBarChartMoney столбчатая диаграмма
     * @param workDayList список рабочих дней
     */
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

    /**
     * Заполняет серии данными
     * @param workDay рабочий день
     * @param series рабочая серия
     * @param series1 не рабочая серия
     * @param workLengthMap длина серии для конкретного сотрудника
     */
    @Override
    public void fillSeries(WorkDay workDay, XYChart.Series<String, Double> series, XYChart.Series<String, Double> series1, HashMap<String, Integer> workLengthMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(workDay.isWorked())
            series.getData().add(new XYChart.Data<>(workDay.getEmployee().getName(),
                    workDay.getEmployee().getSpeciality().getSalary() / (max - workLengthMap.get(workDay.getEmployee().getName()))));
        else
            series1.getData().add(new XYChart.Data<>(workDay.getEmployee().getName(), workDay.getEmployee().getSpeciality().getSalary() / (max - workLengthMap.get(workDay.getEmployee().getName()))));
    }


}
