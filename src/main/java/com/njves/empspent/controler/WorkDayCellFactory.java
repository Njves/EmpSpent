package com.njves.empspent.controler;

import com.njves.empspent.model.WorkDay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class WorkDayCellFactory implements Callback<ListView<WorkDay>, ListCell<WorkDay>> {

    @Override
    public ListCell<WorkDay> call(ListView<WorkDay> workDayListView) {
        return new WorkDayCell();
    }
}
