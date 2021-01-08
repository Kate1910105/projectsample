module lms {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens lms;
    opens lms.windows;
}