module com.njves.empspent {
    requires javafx.controls;
    requires javafx.fxml;
    requires sqlite.jdbc;
    requires java.sql;

    opens com.njves.empspent to javafx.fxml, java.sql;
    exports com.njves.empspent;
    exports com.njves.empspent.controler to javafx.fxml;

}