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

public class UserPageController implements Initializable {

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

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();
    @FXML
    private Hyperlink login_registerHere;
    @FXML
    private AnchorPane register_form;
    @FXML
    private TextField register_username;
    @FXML
    private PasswordField register_password;
    @FXML
    private TextField register_showPassword;
    @FXML
    private CheckBox register_checkBox;
    @FXML
    private Button register_signUpBtn;
    @FXML
    private Hyperlink register_loginHere;
    @FXML
    private TextField register_email;

    @FXML
    public void loginAccount() {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {

            String sql = "SELECT * FROM user WHERE user_name = ? AND user_pass = ?";
            connect = Database.connectDB();

            try {

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
                    Data.temp_username = login_username.getText();
                    Data.temp_userid = Integer.parseInt(result.getString("user_id"));
                    Data.temp_useremail = result.getString("user_email");
                    Data.temp_userpass = result.getString("user_pass");

                    alert.successMessage("Login Successfully!");
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMainForm.fxml"));
                    Parent root = loader.load();
                    UserMainFormController controller = loader.getController();
                    controller.startChat(); // Call startChat() on the instance
                    controller.initialize(null, null);
                    Stage stage = new Stage();

                    stage.setTitle("Fasilistis | User Portal");
                    stage.setScene(new Scene(root));
                    stage.show();

                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    alert.errorMessage("Incorrect Username/Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void loginShowPassword(ActionEvent event) {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_showPassword.setVisible(true);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }

    public void userList() {
        List<String> listU = new ArrayList<>();
        
        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listUserData = FXCollections.observableList(listU);
        login_user.setItems(listUserData);
    }

    @FXML
    void switchPage(ActionEvent event) {
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
        }else if (login_user.getSelectionModel().getSelectedItem() == "User Portal") {
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
    
    public void registerAccount() {

        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()) {

            alert.errorMessage("Harap isi seluruh kolom isian");
        } else {

            String checkUsername = "SELECT * FROM user WHERE user_name = '"
                    + register_username.getText() + "'";

            connect = Database.connectDB();

            try {

                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_username.getText() + " is already exist!");
                } else if (register_password.getText().length() < 7) {
                    alert.errorMessage("Password invalid, masukan minimal 8 karakter");
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO user (user_email, user_name, user_pass) VALUES(?,?,?)";
                    
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());

                    prepare.executeUpdate();

                    alert.successMessage("Registrasi Akun Sukses!");
                    registerClear();

                    // TO SWITCH THE FORM INTO LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {

        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }
    }
    
    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList();
    }
}
