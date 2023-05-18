package com.njves.empspent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SelectableByDate<T> {
    List<T> getByMonthInterval(int monthStart, int monthEnd);
    List<T> getByYearInterval(int monthStart, int monthEnd);
}
