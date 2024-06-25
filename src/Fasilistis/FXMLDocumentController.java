package Fasilistis;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    // LETS NAME ALL COMPONENTS WE HAVE ON ADMIN PAGE 
    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    @FXML
    private ComboBox<?> login_user;

//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    @FXML
    public void loginAccount() {
        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Username/Password Salah");
        } else {
            String sql = "SELECT * FROM admin WHERE admin_name = ? AND admin_pass = ?";

            try {
                connect = Database.connectDB();
                if (connect == null) {
                    alert.errorMessage("Failed to connect to the database");
                    return;
                }
                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    Data.admin_name = login_username.getText();
                    Data.admin_id = Integer.parseInt(result.getString("admin_id"));

                    alert.successMessage("Login Sukses!");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainForm.fxml"));
                    Parent root = loader.load();
                    AdminMainFormController controller = loader.getController();
                    controller.startChat(); // Call startChat() on the instance
                    controller.initialize(null, null);
                    Stage stage = new Stage();

                    stage.setTitle("Fasilistis | Admin Portal");
                    stage.setScene(new Scene(root));
                    stage.show();

                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    alert.errorMessage("Username/Password Salah");
                }

            } catch (Exception e) {
                alert.errorMessage("Error connecting to the database: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Close the resources
                try {
                    if (result != null) result.close();
                    if (prepare != null) prepare.close();
                    if (connect != null) connect.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    @FXML
    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Fasilistis");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "User Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Fasilistis");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        login_user.getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
    }

}
