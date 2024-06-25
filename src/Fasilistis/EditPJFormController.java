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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class EditPJFormController implements Initializable {
    private AlertMessage alert = new AlertMessage();
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
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
    private Button editpj_updateBtn;
    @FXML
    private Button editpj_cancelBtn;
    @FXML
    private ImageView edit_pj_imageView;

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

    public void displayPJData() {

        String sql = "SELECT * FROM pj WHERE pj_id = '"
                + edit_pj_id.getText() + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                edit_pj_nama.setText(result.getString("pj_nama"));
                edit_pj_no.setText(result.getString("pj_no"));
                edit_pj_email.setText(result.getString("pj_email"));
                edit_pj_gedung.getSelectionModel().select(result.getString("pj_gedung"));
                edit_pj_lantai.getSelectionModel().select(result.getInt("pj_lantai"));
                edit_pj_pass.setText(result.getString("pj_pass"));
                edit_pj_status.getSelectionModel().select(result.getString("pj_status"));

                image = new Image("File:" + result.getString("pj_image"), 112, 121, false, true);
                edit_pj_imageView.setImage(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateBtn() {
        connect = Database.connectDB();

        if (edit_pj_id.getText().isEmpty()
                || edit_pj_nama.getText().isEmpty()
                || edit_pj_no.getText().isEmpty()
                || edit_pj_email.getText().isEmpty()
                || edit_pj_gedung.getSelectionModel().getSelectedItem() == null
                || edit_pj_lantai.getSelectionModel().getSelectedItem() == null
                || edit_pj_pass.getText().isEmpty()
                || edit_pj_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Harap isi seluruh kolom isian");
        } else {
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE pj SET pj_nama = '"
                        + edit_pj_nama.getText() + "', pj_no = '"
                        + edit_pj_no.getText() + "', pj_email = '"
                        + edit_pj_email.getText() + "', pj_gedung = '"
                        + edit_pj_gedung.getSelectionModel().getSelectedItem() + "', pj_lantai = '"
                        + edit_pj_lantai.getSelectionModel().getSelectedItem() + "', pj_pass = '"
                        + edit_pj_pass.getText() + "', pj_status = '"
                        + edit_pj_status.getSelectionModel().getSelectedItem()
                        + "' WHERE pj_id = '" + edit_pj_id.getText() + "'";
                try {
                    if (alert.confirmationMessage("Apa Anda yakin ingin mengedit PJ dengan id: " + edit_pj_id.getText() + "?")) {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        editpj_updateBtn.getScene().getWindow().hide();
                    } else {
                        alert.errorMessage("Cancelled.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (alert.confirmationMessage("Apa Anda yakin ingin mengedit PJ dengan id: "
                            + edit_pj_id.getText() + "?")) {
                        String path = Data.path;
                        path = path.replace("\\", "\\\\");
                        Path transfer = Paths.get(path);

                        Path copy = Paths.get("C:\\Semester 4\\PBO\\TugasAkhir\\Fasilistis\\src\\image\\"
                                + edit_pj_id.getText() + ".jpg");

                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                        String insertImage = copy.toString();
                        insertImage = insertImage.replace("\\", "\\\\");
                        
                        String updateData = "UPDATE pj SET pj_nama = '"
                        + edit_pj_nama.getText() + "', pj_no = '"
                        + edit_pj_no.getText() + "', pj_email = '"
                        + edit_pj_email.getText() + "', pj_gedung = '"
                        + edit_pj_gedung.getSelectionModel().getSelectedItem() + "', pj_lantai = '"
                        + edit_pj_lantai.getSelectionModel().getSelectedItem() + "', pj_pass = '"
                        + edit_pj_pass.getText() + "', pj_image = '"
                        + insertImage +"', pj_status = '"
                        + edit_pj_status.getSelectionModel().getSelectedItem()
                        + "' WHERE pj_id = '" + edit_pj_id.getText() + "'";

                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        editpj_updateBtn.getScene().getWindow().hide();

                    } else {
                        alert.errorMessage("Gagal mengedit data PJ.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        displayPJData();
    }

    public void cancelBtn() {
        displayPJData();
    }

    public void setField() {
        edit_pj_id.setText(Data.temp_pjid);
        edit_pj_nama.setText(Data.temp_pjname);
        edit_pj_no.setText(Data.temp_pjno);
        edit_pj_email.setText(Data.temp_pjemail);
        edit_pj_gedung.getSelectionModel().select(Data.temp_pjgedung);
        edit_pj_lantai.getSelectionModel().select(Data.temp_pjlantai);
        edit_pj_pass.setText(Data.temp_pjpass);
        edit_pj_status.getSelectionModel().select(Data.temp_pjstatus);
        

        image = new Image("File:" + Data.temp_pjimage, 112, 121, false, true);
        edit_pj_imageView.setImage(image);
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
            String selectedItem = (String) edit_pj_gedung.getSelectionModel().getSelectedItem();
            getLantai(selectedItem);

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
        setField();
        statusList();
        getGedung();
    }
}
