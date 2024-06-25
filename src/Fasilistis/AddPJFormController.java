/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fasilistis;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author WINDOWS 10
 */
public class AddPJFormController implements Initializable {
    private AlertMessage alert = new AlertMessage();
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private TextField edit_pj_id;
    @FXML
    private TextField edit_pj_nama;
    @FXML
    private TextField edit_pj_email;
    @FXML
    private TextField edit_pj_pass;
    @FXML
    private ComboBox<String> edit_pj_gedung;
    @FXML
    private ComboBox<String> edit_pj_lantai;
    @FXML
    private TextField edit_pj_no;
    @FXML
    private Button editpj_importBtn;
    @FXML
    private ComboBox<String> edit_pj_status;
    @FXML
    private Button editpj_cancelBtn;
    @FXML
    private ImageView edit_pj_imageView;
    @FXML
    private Button insertpj_updateBtn;
    private Statement statement;

    @FXML
    public void importBtn() {
        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

        File file = open.showOpenDialog(editpj_importBtn.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 112, 121, false, true);
            edit_pj_imageView.setImage(image);
        }
    }

    @FXML
    public void insertBtn() {
        connect = Database.connectDB();

        if (edit_pj_nama.getText().isEmpty()
                || edit_pj_no.getText().isEmpty()
                || edit_pj_email.getText().isEmpty()
                || edit_pj_gedung.getSelectionModel().getSelectedItem() == null
                || edit_pj_lantai.getSelectionModel().getSelectedItem() == null
                || edit_pj_pass.getText().isEmpty()
                || edit_pj_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Harap isi seluruh kolom isian");
        } else {
            String selectData = "SELECT MAX(pj_id) FROM pj";

            int tempAppID = 0;

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    tempAppID = result.getInt("MAX(pj_id)") + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (Data.path == null || "".equals(Data.path) && alert.confirmationMessage("Apa Anda yakin ingin menambahkan Penanggung Jawab?")) {
                    String insertData = "INSERT INTO pj (pj_id, pj_nama, pj_no, pj_email, pj_gedung, pj_lantai, pj_pass, pj_status) VALUES(?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(tempAppID));
                    prepare.setString(2, edit_pj_nama.getText());
                    prepare.setString(3, edit_pj_no.getText());
                    prepare.setString(4, edit_pj_email.getText());
                    prepare.setString(5, edit_pj_gedung.getSelectionModel().getSelectedItem());
                    prepare.setString(6, edit_pj_lantai.getSelectionModel().getSelectedItem());
                    prepare.setString(7, edit_pj_pass.getText());
                    prepare.setString(8, edit_pj_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();
                    alert.successMessage("Sukses menambahkan penanggung jawab!");
                    insertpj_updateBtn.getScene().getWindow().hide();
                } else {
                    try {
                        if (alert.confirmationMessage("Apa Anda yakin ingin menambahkan Penanggung Jawab? "
                            + edit_pj_id.getText() + "?")) {
                            String path = Data.path;
                            path = path.replace("\\", "\\\\");
                            Path transfer = Paths.get(path);
                            Path copy = Paths.get("C:\\Semester 4\\PBO\\TugasAkhir\\Fasilistis\\src\\image\\"
                                    + String.valueOf(tempAppID) + ".jpg");

                            Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                            String insertImage = copy.toString();
                            insertImage = insertImage.replace("\\", "\\\\");
                            String insertData = "INSERT INTO pj (pj_id, pj_nama, pj_no, pj_email, pj_gedung, pj_lantai, pj_pass, pj_image, pj_status) VALUES(?,?,?,?,?,?,?,?,?)";
                            prepare = connect.prepareStatement(insertData);
                            prepare.setString(1, String.valueOf(tempAppID));
                            prepare.setString(2, edit_pj_nama.getText());
                            prepare.setString(3, edit_pj_no.getText());
                            prepare.setString(4, edit_pj_email.getText());
                            prepare.setString(5, edit_pj_gedung.getSelectionModel().getSelectedItem());
                            prepare.setString(6, edit_pj_lantai.getSelectionModel().getSelectedItem());
                            prepare.setString(7, edit_pj_pass.getText());
                            prepare.setString(8, insertImage);
                            prepare.setString(9, edit_pj_status.getSelectionModel().getSelectedItem());
                            prepare = connect.prepareStatement(insertData);
                            prepare.executeUpdate();
                            alert.successMessage("Sukses menambahkan penanggung jawab!");
                            insertpj_updateBtn.getScene().getWindow().hide();
                        } else {
                            alert.errorMessage("Gagal menambahkan penanggung jawab");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelBtn() {
        edit_pj_nama.setText("");
        edit_pj_no.setText("");
        edit_pj_email.setText("");
        edit_pj_gedung.getSelectionModel().clearSelection();
        edit_pj_lantai.getSelectionModel().clearSelection();
        edit_pj_pass.setText("");
        edit_pj_status.getSelectionModel().clearSelection();
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.statusPJ) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        edit_pj_status.setItems(listData);
    }
    
     @FXML
    public void getGedung() {
        String sql = "SELECT DISTINCT Gedung FROM stis ORDER BY gedung ASC";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList<String> listData = FXCollections.observableArrayList();
            while (result.next()) {
                listData.add(result.getString("gedung"));
            }

            edit_pj_gedung.setItems(listData);
            edit_pj_gedung.getSelectionModel().getSelectedItem();
            getLantai(edit_pj_gedung.getSelectionModel().getSelectedItem());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void getLantai(String selectedItem) {
        if (edit_pj_gedung.getSelectionModel().getSelectedItem() != null) {
            String sql = "SELECT DISTINCT lantai FROM stis WHERE gedung = ? ORDER BY lantai ASC";
            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, selectedItem);
                result = prepare.executeQuery();

                ObservableList<String> listData = FXCollections.observableArrayList();
                while (result.next()) {
                    listData.add(result.getString("lantai"));
                }

                edit_pj_lantai.setItems(listData);
                edit_pj_lantai.getSelectionModel().getSelectedItem();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusList();
        getGedung();
    }

}
