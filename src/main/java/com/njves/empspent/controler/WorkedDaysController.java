package com.njves.empspent.controler;

import com.njves.empspent.model.Database;
import com.njves.empspent.model.WorkDay;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listView.setCellFactory(new WorkDayCellFactory());

        buttonMonth.setOnMouseClicked(event -> {
            setWorkDays(Database.getInstance().getWorkingDays(new GregorianCalendar().get(Calendar.MONTH) + 1));
        });
        buttonQuarter.setOnMouseClicked(event -> {

        });

    }

    private void setWorkDays(List<WorkDay> workDayList) {
        listView.getItems().addAll(workDayList);

        HashMap<String, Integer> statMapWorked = new HashMap<>();
        HashMap<String, Integer> statMapNotWorked = new HashMap<>();
        HashSet<String> namesList = new HashSet<>();
        workDayList.forEach(workDay -> namesList.add(workDay.getEmployee().getName()));
        namesList.forEach(name -> {
            WorkDay workedMin = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(WorkDay::isWorked).min(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).get();
            WorkDay workedMax = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(WorkDay::isWorked).max(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).get();
            WorkDay notWorkedMin = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(workDay -> !workDay.isWorked()).min(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).get();
            WorkDay notWorkedMax = workDayList.stream().filter(workDay -> workDay.getEmployee().getName().equals(name)).filter(workDay -> !workDay.isWorked()).max(Comparator.comparingInt(o -> o.getDate().getDayOfMonth())).get();
            statMapWorked.put(name, workedMax.getDate().getDayOfMonth() - workedMin.getDate().getDayOfMonth() + 1);
            statMapNotWorked.put(name, notWorkedMax.getDate().getDayOfMonth() - notWorkedMin.getDate().getDayOfMonth() + 1);
        });
        statMapWorked.forEach((s, integer) -> listViewStat.getItems().add(s + " отработал " + integer + " дней"));
        statMapNotWorked.forEach((s, integer) -> listViewStat.getItems().add(s + " не отработал " + integer + " дней"));
    }
}
