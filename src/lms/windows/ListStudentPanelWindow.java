package lms.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.BooleanConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.converter.BooleanStringConverter;
import lms.Main;
import lms.exceptions.authorization.UserNotFound;
import lms.models.User;
import lms.types.Role;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ListStudentPanelWindow {

      public static Scene getScene() throws Exception {
        return Main.loadScene("list_student");
    }
    @FXML
    private TableView<User> tableView;

    @FXML
    private void initialize() throws SQLException {
        System.out.println("student list panel init");
        ArrayList<User> users = User.all();
        ArrayList<User> students = new ArrayList<>();

        users.forEach((u) -> {
            if (u.role == Role.Student) {
                students.add(u);
            }
        });

        TableColumn<User, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().add(idColumn);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableView.getColumns().add(usernameColumn);

        TableColumn<User, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tableView.getColumns().add(fullNameColumn);

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableView.getColumns().add(roleColumn);

        TableColumn<User, DateCell> createdAt = new TableColumn<>("Created At");
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tableView.getColumns().add(createdAt);

        TableColumn<User, BooleanConverter> canBorrow = new TableColumn<>("Can Borrow");
        canBorrow.setCellValueFactory(new PropertyValueFactory<>("canBorrow"));
        tableView.getColumns().add(canBorrow);



        tableView.getItems().addAll(students);

        System.out.println(users);
    }

    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        System.out.println(Main.currentUser.role);
        switch (Main.currentUser.role) {
            case Administrator:
                scene = AdminPanelWindow.getScene();
                break;
            case Librarian:
                scene = LibrarianPanelWindow.getScene();
                break;
            default:
                scene = StudentPanelWindow.getScene();
                break;

        }
        System.out.println(Main.currentUser.role);

        Main.window.setScene(scene);

    }


    @FXML
    public void addstudent(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void liststudent(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void delete(ActionEvent actionEvent) throws Exception {
        User selectedItem = tableView.getSelectionModel().getSelectedItem();
//        System.out.println(user.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Delete");
        alert.setHeaderText("Delete Item: " + selectedItem.getFullName());
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            tableView.getItems().remove(selectedItem);
            selectedItem.delete();
        } else {
            // ... user chose CANCEL or closed the dialog
            Scene scene;
            scene = ListStudentPanelWindow.getScene();
            Main.window.setScene(scene);

        }
    }
    @FXML
    public void update(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = UpdateStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }

}
