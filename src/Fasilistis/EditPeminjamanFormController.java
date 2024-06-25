package Fasilistis;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditPeminjamanFormController implements Initializable{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    
    private AlertMessage alert = new AlertMessage();
    @FXML
    private TextField edit_peminjaman_id;
    @FXML
    private TextField edit_peminjaman_nama;
    @FXML
    private Button peminjaman_updateBtn;
    @FXML
    private Button peminjaman_cancelBtn;
    @FXML
    private ComboBox<String> edit_peminjaman_status;
    @FXML
    private ComboBox<String> edit_peminjaman_gedung;
    @FXML
    private ComboBox<String> edit_peminjaman_lantai;
    @FXML
    private ComboBox<String> edit_peminjaman_ruang;

    public void displayFields(){
        edit_peminjaman_id.setText(Data.temp_peminjamanid);
        edit_peminjaman_nama.setText(Data.temp_peminjamanusername);
        edit_peminjaman_gedung.getSelectionModel().select(Data.temp_peminjamangedung);
        edit_peminjaman_lantai.getSelectionModel().select(Data.temp_peminjamanlantai);
        edit_peminjaman_ruang.getSelectionModel().select(Data.temp_peminjamanruang);
        edit_peminjaman_status.getSelectionModel().select(Data.temp_peminjamanstatus);
    }
    
        @FXML
    public void updateBtn() {
        connect = Database.connectDB();

        if (edit_peminjaman_id.getText().isEmpty()
                || edit_peminjaman_nama.getText().isEmpty()
                || edit_peminjaman_gedung.getSelectionModel().getSelectedItem() == null
                || edit_peminjaman_lantai.getSelectionModel().getSelectedItem() == null
                || edit_peminjaman_ruang.getSelectionModel().getSelectedItem() == null
                || edit_peminjaman_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Harap isi seluruh kolom isian");
        } else {
            String updateData = "UPDATE peminjaman SET gedung = '"
                + edit_peminjaman_gedung.getSelectionModel().getSelectedItem() + "', ruang = '"
                + edit_peminjaman_ruang.getSelectionModel().getSelectedItem() + "', lantai = '"
                + edit_peminjaman_lantai.getSelectionModel().getSelectedItem() + "', status = '"
                + edit_peminjaman_status.getSelectionModel().getSelectedItem()
                + "' WHERE id_peminjaman = '" + edit_peminjaman_id.getText() + "'";
            try {
                if (alert.confirmationMessage("Apa Anda yakin ingin mengedit Peminjaman dengan id: " + edit_peminjaman_id.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();
                    alert.successMessage("Sukses!");
                    peminjaman_updateBtn.getScene().getWindow().hide();
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        displayFields();
    }

    @FXML
    public void cancelBtn() {
        displayFields();
    }
    
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

            edit_peminjaman_gedung.setItems(listData);
            edit_peminjaman_gedung.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    getLantai(newValue);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLantai(String selectedItem) {
        if (selectedItem != null) {
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

                edit_peminjaman_lantai.setItems(listData);
                edit_peminjaman_lantai.getSelectionModel().getSelectedItem();
                getRuang(edit_peminjaman_gedung.getSelectionModel().getSelectedItem(),edit_peminjaman_lantai.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  
    
    public void getRuang(String selectedGedung, String selectedLantai) {
        if (selectedGedung != null) {
            String sql = "SELECT DISTINCT ruang FROM stis WHERE gedung = ? AND lantai = ? ORDER BY ruang ASC";
            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, selectedGedung);
                prepare.setString(2, selectedLantai);
                result = prepare.executeQuery();

                ObservableList<String> listData = FXCollections.observableArrayList();
                while (result.next()) {
                    listData.add(result.getString("ruang"));
                }

                edit_peminjaman_ruang.setItems(listData);
                edit_peminjaman_ruang.getSelectionModel().getSelectedItem();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.statusPeminjaman) {
            statusL.add(data);
        }
        ObservableList listData = FXCollections.observableList(statusL);
        edit_peminjaman_status.setItems(listData);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getGedung();
        getLantai(edit_peminjaman_gedung.getSelectionModel().getSelectedItem());
        getRuang(edit_peminjaman_gedung.getSelectionModel().getSelectedItem(),edit_peminjaman_lantai.getSelectionModel().getSelectedItem());
        statusList();
        displayFields();
    }

    @FXML
    private void doctorList(ActionEvent event) {
    }
}
