package lms.windows.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lms.Main;
import lms.models.User;
import lms.types.Role;
import lms.windows.panels.AdminPanelWindow;
import lms.windows.panels.LibrarianPanelWindow;
import lms.windows.panels.StudentPanelWindow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ListStudentPanelWindow {

      public static Scene getScene() throws Exception {
        return Main.loadScene("student/list");
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


        TableColumn<User, String> createdAt = new TableColumn<>("Created At");
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tableView.getColumns().add(createdAt);

        TableColumn<User, String> canBorrow = new TableColumn<>("Can Borrow");
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
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing selected");
            alert.setHeaderText("Select Student");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete Student: " + selectedItem.getFullName());
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get()==ButtonType.OK) {
                tableView.getItems().remove(selectedItem);
                selectedItem.delete();
            } else {
                Scene scene;
                scene = ListStudentPanelWindow.getScene();
                Main.window.setScene(scene);
            }
        }
    }

    @FXML
    public void update(ActionEvent actionEvent) throws Exception {
        User selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing selected");
            alert.setHeaderText("Select Student");
            alert.showAndWait();
        } else {
            Main.editingUser = selectedItem;
            Scene scene;
            scene = UpdateStudentPanelWindow.getScene();
            Main.window.setScene(scene);
        }
    }

}
