package com.njves.empspent.controler;

import com.njves.empspent.model.WorkDay;
import com.njves.empspent.model.WorkDayQueryAdapter;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;

public class WorkedDaysController implements Initializable {

    public Button buttonMonth;
    public Button buttonQuarter;
    public Button buttonHalfYear;
    public Button buttonYear;
    public ListView<WorkDay> listView;
    public ListView<String> listViewStat;

    WorkDayQueryAdapter workDayQueryAdapter = new WorkDayQueryAdapter();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listView.setCellFactory(new WorkDayCellFactory());

        buttonMonth.setOnMouseClicked(event -> setWorkDays(workDayQueryAdapter.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1)));
        buttonQuarter.setOnMouseClicked(event -> setWorkDays(workDayQueryAdapter.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1 + 3)));
        buttonHalfYear.setOnMouseClicked(event -> setWorkDays(workDayQueryAdapter.getByMonthInterval(new GregorianCalendar().get(Calendar.MONTH) + 1, new GregorianCalendar().get(Calendar.MONTH) + 1 + 6)));
        buttonYear.setOnMouseClicked(event -> setWorkDays(workDayQueryAdapter.getByYearInterval(new GregorianCalendar().get(Calendar.YEAR), new GregorianCalendar().get(Calendar.YEAR))));
    }

    private void setWorkDays(List<WorkDay> workDayList) {
        listView.getItems().clear();
        listViewStat.getItems().clear();
        listView.getItems().addAll(workDayList);

        HashMap<String, Integer> statMapWorked = new HashMap<>();
        HashMap<String, Integer> statMapNotWorked = new HashMap<>();
        HashSet<String> namesList = new HashSet<>();
        workDayList.forEach(workDay -> namesList.add(workDay.getEmployee().getName()));
        namesList.forEach(name -> {
            WorkDay workedMin = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(WorkDay::isWorked).min(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            WorkDay workedMax = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(WorkDay::isWorked).max(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            WorkDay notWorkedMin = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(workDay -> !workDay.isWorked()).min(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            WorkDay notWorkedMax = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(workDay -> !workDay.isWorked()).max(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).orElse(null);
            statMapWorked.put(name, workedMax.getDate().getDayOfMonth() - workedMin.getDate().getDayOfMonth() + 1);
            statMapNotWorked.put(name, notWorkedMax.getDate().getDayOfMonth() - notWorkedMin.getDate().getDayOfMonth() + 1);
        });
        statMapWorked.forEach((s, integer) -> listViewStat.getItems().add(s + " отработал " + integer + " дней"));
        statMapNotWorked.forEach((s, integer) -> listViewStat.getItems().add(s + " не отработал " + integer + " дней"));
    }
}
