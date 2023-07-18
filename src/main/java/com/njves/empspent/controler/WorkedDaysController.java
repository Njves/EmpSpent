/**
 * Модуль содержащий контроллер статистики
 */
package com.njves.empspent.controler;

import com.njves.empspent.model.WorkDay;
import com.njves.empspent.model.WorkDayQueryDecorator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;

/**
 * Контроллер окна статистики отработанных дней
 */
public class WorkedDaysController implements Initializable {
    /**
     * Кнопка вызова статистики за месяц
     */
    public Button buttonMonth;

    /**
     * Кнопка вызова статистики за квартал
     */
    public Button buttonQuarter;

    /**
     * Кнопка вызова статистики за полгода
     */
    public Button buttonHalfYear;

    /**
     * Кнопка вызова статистики за год
     */
    public Button buttonYear;

    /**
     * объект список
     */
    public ListView<WorkDay> listView;

    /**
     * объект списка статистики
     */
    public ListView<String> listViewStat;

    /**
     * Объект запроса
     */
    WorkDayQueryDecorator workDayQueryDecorator = new WorkDayQueryDecorator();

    /**
     * Вызывается при инициализации
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listView.setCellFactory(new WorkDayCellFactory());

        buttonMonth.setOnMouseClicked(event -> setWorkDays(workDayQueryDecorator.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1)));
        buttonQuarter.setOnMouseClicked(event -> setWorkDays(workDayQueryDecorator.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1 + 3)));
        buttonHalfYear.setOnMouseClicked(event -> setWorkDays(workDayQueryDecorator.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1 + 6)));
        buttonYear.setOnMouseClicked(event -> setWorkDays(workDayQueryDecorator.getByYearInterval(new GregorianCalendar().get(Calendar.YEAR), new GregorianCalendar().get(Calendar.YEAR))));
    }

    /**
     * внедряет объекты в список
     * @param workDayList список рабочих дней
     */
    private void setWorkDays(List<WorkDay> workDayList) {
        listView.getItems().clear();
        listViewStat.getItems().clear();
        listView.getItems().addAll(workDayList.stream().sorted(Comparator.comparing(o -> o.getEmployee().getName())).toList());

        HashMap<String, Integer> statMapWorked = new HashMap<>();
        HashMap<String, Integer> statMapNotWorked = new HashMap<>();
        HashSet<String> namesList = new HashSet<>();
        workDayList.forEach(workDay -> namesList.add(workDay.getEmployee().getName()));
        namesList.forEach(name -> {
            WorkDay workedMin = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(WorkDay::isWorked).min(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            WorkDay workedMax = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(WorkDay::isWorked).max(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            WorkDay notWorkedMin = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(workDay -> !workDay.isWorked()).min(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            WorkDay notWorkedMax = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(workDay -> !workDay.isWorked()).max(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            if(workedMax != null && workedMin != null && notWorkedMax != null && notWorkedMin != null) {
                statMapWorked.put(name, workedMax.getDate().getDayOfMonth() - workedMin.getDate().getDayOfMonth() + 1);
                statMapNotWorked.put(name, notWorkedMax.getDate().getDayOfMonth() - notWorkedMin.getDate().getDayOfMonth() + 1);
            }

        });
        System.out.println(statMapWorked);
        statMapWorked.forEach((s, integer) -> listViewStat.getItems().add(s + " отработал " + integer + " дней"));
        statMapNotWorked.forEach((s, integer) -> listViewStat.getItems().add(s + " не отработал " + integer + " дней"));
    }
}
