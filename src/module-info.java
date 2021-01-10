module lms {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens lms;
    opens lms.models;
    opens lms.windows;
    opens lms.windows.panels;
    opens lms.windows.librarian;
    opens lms.windows.student;
    opens lms.windows.book;
}