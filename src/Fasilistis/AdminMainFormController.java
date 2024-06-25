package Fasilistis;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminMainFormController implements Initializable {

    // GIVE NAME OF ALL COMPONENTS
    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<PeminjamanData> dashboad_tableView;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private TextField profile_email;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private Button logout_btn;

//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private Image image;
    @FXML
    private Button pj_btn;
    @FXML
    private Button user_btn;
    @FXML
    private Button peminjaman_btn;
    @FXML
    private Button chat_btn;
    @FXML
    private Label dashboard_pj;
    @FXML
    private Label dashboard_user;
    @FXML
    private Label dashboard_jumlah_peminjam;
    @FXML
    private PieChart dashboad_chart_daftar_peminjaman;
    @FXML
    private TableColumn<?, ?> home_peminjaman_id;
    @FXML
    private TableColumn<?, ?> home_peminjaman_nama;
    @FXML
    private TableColumn<?, ?> home_peminjaman_ruang;
    @FXML
    private TableColumn<?, ?> home_peminjaman_status;
    @FXML
    private AnchorPane pj_form;
    @FXML
    private TableView<PJData> pj_tableView;
    @FXML
    private TableColumn<?, ?> pj_id;
    @FXML
    private TableColumn<?, ?> pj_nama;
    @FXML
    private TableColumn<?, ?> pj_no;
    @FXML
    private TableColumn<?, ?> pj_email;
    @FXML
    private TableColumn<?, ?> pj_gedung;
    @FXML
    private TableColumn<?, ?> pj_lantai;
    @FXML
    private TableColumn<?, ?> pj_status;
    @FXML
    private TableColumn<PJData, String> pj_action;
    @FXML
    private Button csv_pj;
    @FXML
    private Button tambah_pj;
    @FXML
    private AnchorPane user_form;
    @FXML
    private TableView<UserData> user_tableView;
    @FXML
    private Button csv_user;
    @FXML
    private AnchorPane peminjaman_form;
    @FXML
    private TableView<PeminjamanData> peminjaman_tableView;
    @FXML
    private TableColumn<?, ?> peminjaman_id;
    @FXML
    private TableColumn<?, ?> peminjaman_nama;
    @FXML
    private TableColumn<?, ?> peminjaman_gedung;
    @FXML
    private TableColumn<?, ?> peminjaman_lantai;
    @FXML
    private TableColumn<?, ?> peminjaman_ruang;
    @FXML
    private TableColumn<?, ?> peminjaman_tanggal;
    @FXML
    private TableColumn<?, ?> peminjaman_status;
    @FXML
    private TableColumn<PeminjamanData,String> peminjaman_action;
    @FXML
    private Button csv_peminjaman;
    @FXML
    private AnchorPane chat_form;
    @FXML
    private TextArea area_chat;
    @FXML
    private TextField text_chat;
    @FXML
    private Button kirim_chat;
    @FXML
    private Label profile_label_adminid;
    @FXML
    private TextField profile_adminid;
    @FXML
    private TextField profile_name;
    @FXML
    private TextField profile_password;
    @FXML
    private Label dashboard_peminjaman_acc;
    @FXML
    private TableColumn<UserData, Integer> user_id_tabel;
    @FXML
    private TableColumn<UserData, String> user_name_tabel;
    @FXML
    private TableColumn<UserData, String> user_email_tabel;
    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;


    public void dashboardPJ() {

        String sql = "SELECT COUNT(pj_id) FROM pj WHERE pj_status = 'Aktif'";

        connect = Database.connectDB();

        int tempAD = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAD = result.getInt("COUNT(pj_id)");
            }
            dashboard_pj.setText(String.valueOf(tempAD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardUser() {

        String sql = "SELECT COUNT(user_id) FROM user";

        connect = Database.connectDB();

        int tempTP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTP = result.getInt("COUNT(user_id)");
            }
            dashboard_user.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardPeminjaman() {

        String sql = "SELECT COUNT(id_peminjaman) FROM peminjaman";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(id_peminjaman)");
            }
            dashboard_jumlah_peminjam.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardPeminjamanACC() {

        String sql = "SELECT COUNT(id_peminjaman) FROM peminjaman WHERE status = 'Disetujui'";

        connect = Database.connectDB();

        int tempTA = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTA = result.getInt("COUNT(id_peminjaman)");
            }
            dashboard_peminjaman_acc.setText(String.valueOf(tempTA));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<PeminjamanData> dashboardGetPeminjaman() {

        ObservableList<PeminjamanData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM peminjaman";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PeminjamanData dData;

            while (result.next()) {
//                DoctorData(String doctorID, String fullName, String specialized, String status)
                dData = new PeminjamanData(result.getInt("id_peminjaman"),
                        result.getString("user_name"), result.getString("ruang"),
                        result.getString("status"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PeminjamanData> dashboardGetPeminjamanListData;

    public void dashboardGetPeminjamanDisplayData() {
        dashboardGetPeminjamanListData = dashboardGetPeminjaman();

        home_peminjaman_id.setCellValueFactory(new PropertyValueFactory<>("id_peminjaman"));
        home_peminjaman_nama.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        home_peminjaman_ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        home_peminjaman_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetPeminjamanListData);

    }

    public void dashboardPeminjamanDataChart() {
        dashboad_chart_daftar_peminjaman.getData().clear();

        String selectDataDiajukan = "SELECT COUNT(id_peminjaman) FROM peminjaman WHERE status = 'Diajukan'";
        String selectDataDisetujui = "SELECT COUNT(id_peminjaman) FROM peminjaman WHERE status = 'Disetujui'";
        String selectDataDibatalkan = "SELECT COUNT(id_peminjaman) FROM peminjaman WHERE status = 'Dibatalkan'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectDataDiajukan);
            result = prepare.executeQuery();
            result.next();
            PieChart.Data diajukan = new PieChart.Data("Diajukan", result.getInt(1));

            prepare = connect.prepareStatement(selectDataDisetujui);
            result = prepare.executeQuery();
            result.next();
            PieChart.Data disetujui = new PieChart.Data("Disetujui", result.getInt(1));

            prepare = connect.prepareStatement(selectDataDibatalkan);
            result = prepare.executeQuery();
            result.next();
            PieChart.Data dibatalkan = new PieChart.Data("Dibatalkan", result.getInt(1));

            dashboad_chart_daftar_peminjaman.getData().addAll(diajukan, disetujui, dibatalkan);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObservableList<PJData> pjListData = FXCollections.observableArrayList();
    
    public ObservableList<PJData> pjGetData() {
        ObservableList<PJData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pj";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            PJData dData;
            while (result.next()) {
                dData = new PJData(result.getInt("pj_id"),
                        result.getString("pj_nama"), result.getString("pj_no"),
                        result.getString("pj_email"), result.getString("pj_gedung"),
                        result.getInt("pj_lantai"), result.getString("pj_pass"),
                        result.getString("pj_image"), result.getString("pj_status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void pjDisplayData() {
        pjListData.clear();
        pjListData.addAll(pjGetData());
        
        for(int i=0; i<pjListData.size();i++){
            pj_id.setCellValueFactory(new PropertyValueFactory<>("pj_id"));
            pj_nama.setCellValueFactory(new PropertyValueFactory<>("pj_nama"));
            pj_no.setCellValueFactory(new PropertyValueFactory<>("pj_no"));
            pj_email.setCellValueFactory(new PropertyValueFactory<>("pj_email"));
            pj_gedung.setCellValueFactory(new PropertyValueFactory<>("pj_gedung"));
            pj_lantai.setCellValueFactory(new PropertyValueFactory<>("pj_lantai"));
            pj_status.setCellValueFactory(new PropertyValueFactory<>("pj_status"));
            
            pj_tableView.setItems(pjListData);
        }
    }

    public void pjActionButton() {

        connect = Database.connectDB();
        pjListData = pjGetData();

        Callback<TableColumn<PJData, String>, TableCell<PJData, String>> cellFactory = (TableColumn<PJData, String> param) -> {
            final TableCell<PJData, String> cell = new TableCell<PJData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Hapus");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                PJData pData = pj_tableView.getSelectionModel().getSelectedItem();
                                int num = pj_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Harap pilih PJ yang ingin diedit");
                                    return;
                                }

                                Data.temp_pjid = String.valueOf(pData.getPj_id());
                                Data.temp_pjname = pData.getPj_nama();
                                Data.temp_pjno = pData.getPj_no();
                                Data.temp_pjemail = pData.getPj_email();
                                Data.temp_pjgedung = pData.getPj_gedung();
                                Data.temp_pjlantai = String.valueOf(pData.getPj_lantai());
                                Data.temp_pjpass = pData.getPj_pass();
                                Data.temp_pjimage = pData.getPj_image();
                                Data.temp_pjstatus = pData.getPj_status();

                                Parent root = FXMLLoader.load(getClass().getResource("EditPJForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            PJData pData = pj_tableView.getSelectionModel().getSelectedItem();
                            int num = pj_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Harap pilih PJ yang ingin dihapus");
                                return;
                            }

                            String deleteData = "DELETE FROM pj WHERE pj_id = '" + pData.getPj_id() + "'";

                            try {
                                if (alert.confirmationMessage("Apa Anda yakin ingin menghapus PJ dengan id: " + pData.getPj_id() + "?")) {
                                    statement = connect.createStatement();
                                    statement.executeUpdate(deleteData);

                                    alert.successMessage("Sukses menghapus PJ!");

                                    // Refresh table
                                    pj_tableView.getItems().remove(num);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            pjDisplayData();
            return cell;
        };

        pj_action.setCellFactory(cellFactory);
        pj_tableView.setItems(pjListData);

    }
    
    public ObservableList<UserData> userListData;

    public void UserDisplayData() {
        userListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                UserData pData = new UserData(
                    result.getInt("user_id"), 
                    result.getString("user_name"), 
                    result.getString("user_email")
                );

                userListData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        user_id_tabel.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        user_name_tabel.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        user_email_tabel.setCellValueFactory(new PropertyValueFactory<>("user_email"));

        user_tableView.setItems(userListData);
    }

    public ObservableList<PeminjamanData> PeminjamanGetData() {
        ObservableList<PeminjamanData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM peminjaman";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            PeminjamanData pData;
            while (result.next()) {
                pData = new PeminjamanData(
                    result.getInt("id_peminjaman"),
                    result.getString("user_name"),
                    result.getString("gedung"),
                    result.getInt("lantai"),
                    result.getString("ruang"),
                    result.getDate("tanggal_peminjaman"),
                    result.getString("status"));

                    listData.add(pData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listData;
    }

    public ObservableList<PeminjamanData> PeminjamanListData;

    public void PeminjamanDisplayData() {
        PeminjamanListData = PeminjamanGetData();

        peminjaman_id.setCellValueFactory(new PropertyValueFactory<>("id_peminjaman"));
        peminjaman_nama.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        peminjaman_gedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        peminjaman_lantai.setCellValueFactory(new PropertyValueFactory<>("lantai"));
        peminjaman_ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        peminjaman_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_peminjaman"));
        peminjaman_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        peminjaman_tableView.setItems(PeminjamanListData);
    }

    public void peminjamanActionButton() {

        connect = Database.connectDB();
        PeminjamanListData = PeminjamanGetData();

        Callback<TableColumn<PeminjamanData, String>, TableCell<PeminjamanData, String>> cellFactory = (TableColumn<PeminjamanData, String> param) -> {
            final TableCell<PeminjamanData, String> cell = new TableCell<PeminjamanData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Hapus");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                PeminjamanData pData = peminjaman_tableView.getSelectionModel().getSelectedItem();
                                int num = peminjaman_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Harap pilih Peminjaman yang ingin diedit");
                                    return;
                                }

                                Data.temp_peminjamanid = String.valueOf(pData.getId_peminjaman());
                                Data.temp_peminjamanuserid = String.valueOf(pData.getUser_id());
                                Data.temp_peminjamanusername = pData.getUser_name();
                                Data.temp_peminjamanuseremail = pData.getUser_email();
                                Data.temp_peminjamangedung = pData.getGedung();
                                Data.temp_peminjamanlantai = String.valueOf(pData.getLantai());
                                Data.temp_peminjamanruang = pData.getRuang();
                                Data.temp_peminjamantanggal = String.valueOf(pData.getTanggal_peminjaman());
                                Data.temp_peminjamanstatus = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditPeminjamanForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();
                                PeminjamanDisplayData();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            PeminjamanData pData = peminjaman_tableView.getSelectionModel().getSelectedItem();
                            int num = peminjaman_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Harap pilih Peminjaman yang ingin dihapus");
                                return;
                            }

                            String deleteData = "DELETE FROM peminjaman WHERE id_peminjaman = '" + pData.getId_peminjaman() + "'";

                            try {
                                if (alert.confirmationMessage("Apa Anda yakin ingin menghapus Peminjaman dengan id: " + pData.getId_peminjaman() + "?")) {
                                    statement = connect.createStatement();
                                    statement.executeUpdate(deleteData);

                                    alert.successMessage("Sukses menghapus Peminjaman!");

                                    // Refresh table
                                    peminjaman_tableView.getItems().remove(num);
                                    PeminjamanDisplayData();

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        PeminjamanDisplayData();
        peminjaman_action.setCellFactory(cellFactory);
        peminjaman_tableView.setItems(PeminjamanListData);

    }
    
    @FXML
    public void insertpjacc(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddPJForm.fxml"));

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
            PeminjamanDisplayData();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void startChat() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    ss = new ServerSocket(3304);
                    s = ss.accept();
                    dis = new DataInputStream(s.getInputStream());
                    dout = new DataOutputStream(s.getOutputStream());

                    String msgin = "";
                    while (!msgin.equals("exit")) {
                        msgin = dis.readUTF();
                        final String message = msgin; // Make message effectively final
                        Platform.runLater(() -> area_chat.appendText("\n" + message));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        // Run the Task
        new Thread(task).start();
    }

    @FXML
    private void msg_sendActionPerformed(ActionEvent event) {
        try {
            final String msg = text_chat.getText();
            if (dout != null) {
                dout.writeUTF("Admin:" + msg);
                text_chat.setText("");
                Platform.runLater(() -> area_chat.appendText("\nAdmin : " + msg));
            } else {
                alert.errorMessage("Connection not established");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void profileUpdateBtn() {
        connect = Database.connectDB();
        if (profile_adminid.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_password.getText().isEmpty()) {
            alert.errorMessage("Harap isi seluruh kolom isian");
        } else {
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE admin SET admin_name = ?, admin_email = ?, admin_pass = ? "
                        + "WHERE admin_id = " + Data.admin_id;

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_password.getText());

                    prepare.executeUpdate();

                    profileDisplayInfo();

                    alert.successMessage("Sukses Mengupdate Profil!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                String updateData = "UPDATE admin SET admin_name = ?, admin_email = ?, admin_pass = ?, admin_image = ? "
                        + "WHERE admin_id = " + Data.admin_id;
                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_password.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Semester 4\\PBO\\TugasAkhir\\Fasilistis\\src\\image\\Admin.png");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    prepare.setString(4, copy.toAbsolutePath().toString());

                    prepare.executeUpdate();
                    profileDisplayInfo();
                    profileDisplayImages();
                    alert.successMessage("Sukses Mengupdate Profil!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        profileDisplayImages();
        profileDisplayInfo();
        displayAdminID();
    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        connect = Database.connectDB();

        String tempPath1 = "";
        String tempPath2 = "";
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                tempPath1 = "File:" + result.getString("admin_image");
                tempPath2 = "File:" + result.getString("admin_image");

                if (result.getString("admin_image") != null) {
                    image = new Image(tempPath1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void profileInsertImage() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 137, 95, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }

    }

    public void profileDisplayInfo() {

        String sql = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        System.out.println(Data.admin_id);
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_adminid.setText(result.getString("admin_id"));
                profile_name.setText(result.getString("admin_name"));
                profile_email.setText(result.getString("admin_email"));

                profile_label_adminid.setText(result.getString("admin_id"));
                profile_label_name.setText(result.getString("admin_name"));
                profile_label_email.setText(result.getString("admin_email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            pj_form.setVisible(false);
            user_form.setVisible(false);
            peminjaman_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(false);

            dashboardPJ();
            dashboardUser();
            dashboardPeminjaman();
            dashboardPeminjamanACC();
            dashboardGetPeminjamanDisplayData();
            dashboardPeminjamanDataChart();

            current_form.setText("Dashboard Form");
        } else if (event.getSource() == pj_btn) {
            dashboard_form.setVisible(false);
            pj_form.setVisible(true);
            user_form.setVisible(false);
            peminjaman_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
            pjDisplayData();
            pjActionButton();

            current_form.setText("Penanggung Jawab Form");
        } else if (event.getSource() == user_btn) {
            dashboard_form.setVisible(false);
            pj_form.setVisible(false);
            user_form.setVisible(true);
            peminjaman_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
            UserDisplayData();
            current_form.setText("User Form");
        } else if (event.getSource() == peminjaman_btn) {
            dashboard_form.setVisible(false);
            pj_form.setVisible(false);
            user_form.setVisible(false);
            peminjaman_form.setVisible(true);
            chat_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            PeminjamanDisplayData();

            current_form.setText("Peminjaman Form");
        } else if (event.getSource() == chat_btn) {
            dashboard_form.setVisible(false);
            pj_form.setVisible(false);
            user_form.setVisible(false);
            peminjaman_form.setVisible(false);
            chat_form.setVisible(true);
            profile_form.setVisible(false);

            current_form.setText("Chat Form");
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            pj_form.setVisible(false);
            user_form.setVisible(false);
            peminjaman_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(true);

            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Profile Form");
        }

    }

    public void displayAdminID() {

        String sql = "SELECT * FROM admin WHERE admin_name = '"
                + Data.admin_name + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nav_adminID.setText(result.getString("admin_id"));
                String tempUsername = result.getString("admin_name");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1);
                nav_username.setText(tempUsername);
                top_username.setText(tempUsername);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logoutBtn() {
        try {
            if (alert.confirmationMessage("Apakah Anda ingin logout?")) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }
    
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
        
    @FXML
    private void ExportCSVPJ(){
        pjDisplayData();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv")); // Menambahkan filter file CSV
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Menambahkan ekstensi .csv jika belum ada
            if (!filePath.toLowerCase().endsWith(".csv")) {
                fileToSave = new File(filePath + ".csv");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {
                // Menulis judul kolom
                Map<String, String> columnFieldMap = new HashMap<>();
                columnFieldMap.put("pj_id", "pj_id");
                columnFieldMap.put("pj_nama", "pj_nama");
                columnFieldMap.put("pj_no", "pj_no");
                columnFieldMap.put("pj_email", "pj_email");
                columnFieldMap.put("pj_gedung", "pj_gedung");
                columnFieldMap.put("pj_lantai", "pj_lantai");
                columnFieldMap.put("pj_status", "pj_status");
                columnFieldMap.put("pj_action", "");
                // add more mappings for each column

                for (int i = 0; i < pj_tableView.getColumns().size(); i++) {
                    bw.write(pj_tableView.getColumns().get(i).getText() + ",");
                }
                bw.newLine();

                // Menulis data ke file
                for (PJData data : pj_tableView.getItems()) {
                    for (int i = 0; i < pj_tableView.getColumns().size(); i++) {
                        String fieldName = columnFieldMap.get(pj_tableView.getColumns().get(i).getId());
                        try {
                            Field field = PJData.class.getDeclaredField(fieldName);
                            field.setAccessible(true); // add this line to avoid IllegalAccessException
                            Object value = field.get(data);
                            if (value != null) {
                                bw.write(value.toString() + ",");
                            } else {
                                bw.write("NULL,");
                            }
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    bw.newLine();
                }

                alert.successMessage("Sukses menyimpan file CSV !");

                // Membuka file setelah penulisan selesai
                openFile(fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                alert.errorMessage("Gagal menyimpan file CSV");
            }
        }
    }
    
    @FXML
    private void ExportCSVUser(){
        UserDisplayData();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv")); // Menambahkan filter file CSV
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Menambahkan ekstensi .csv jika belum ada
            if (!filePath.toLowerCase().endsWith(".csv")) {
                fileToSave = new File(filePath + ".csv");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {
                // Menulis judul kolom
                Map<String, String> columnFieldMap = new HashMap<>();
                columnFieldMap.put("user_id_tabel", "user_id");
                columnFieldMap.put("user_name_tabel", "user_name");
                columnFieldMap.put("user_email_tabel", "user_email");
                // add more mappings for each column

                for (int i = 0; i < user_tableView.getColumns().size(); i++) {
                    bw.write(user_tableView.getColumns().get(i).getText() + ",");
                }
                bw.newLine();

                // Menulis data ke file
                for (UserData data : user_tableView.getItems()) {
                    for (int i = 0; i < user_tableView.getColumns().size(); i++) {
                        String fieldName = columnFieldMap.get(user_tableView.getColumns().get(i).getId());
                        try {
                            Field field = UserData.class.getDeclaredField(fieldName);
                            field.setAccessible(true); // add this line to avoid IllegalAccessException
                            Object value = field.get(data);
                            if (value != null) {
                                bw.write(value.toString() + ",");
                            } else {
                                bw.write("NULL,");
                            }
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    bw.newLine();
                }

                alert.successMessage("Sukses menyimpan file CSV !");

                // Membuka file setelah penulisan selesai
                openFile(fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                alert.errorMessage("Gagal menyimpan file CSV");
            }
        }
    }
    
       @FXML
    private void ExportCSVpeminjaman(){
        PeminjamanDisplayData();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv")); // Menambahkan filter file CSV
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Menambahkan ekstensi .csv jika belum ada
            if (!filePath.toLowerCase().endsWith(".csv")) {
                fileToSave = new File(filePath + ".csv");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {
                // Menulis judul kolom
                Map<String, String> columnFieldMap = new HashMap<>();
                columnFieldMap.put("peminjaman_id", "id_peminjaman");
                columnFieldMap.put("peminjaman_nama", "user_name");
                columnFieldMap.put("peminjaman_gedung", "gedung");
                columnFieldMap.put("peminjaman_lantai", "lantai");
                columnFieldMap.put("peminjaman_ruang", "ruang");
                columnFieldMap.put("peminjaman_tanggal", "tanggal_peminjaman");
                columnFieldMap.put("peminjaman_status", "status");
                columnFieldMap.put("peminjaman_action", "");
                // add more mappings for each column

                for (int i = 0; i < peminjaman_tableView.getColumns().size(); i++) {
                    bw.write(peminjaman_tableView.getColumns().get(i).getText() + ",");
                }
                bw.newLine();

                // Menulis data ke file
                for (PeminjamanData data : peminjaman_tableView.getItems()) {
                    for (int i = 0; i < peminjaman_tableView.getColumns().size(); i++) {
                        String fieldName = columnFieldMap.get(peminjaman_tableView.getColumns().get(i).getId());
                        try {
                            Field field = PeminjamanData.class.getDeclaredField(fieldName);
                            field.setAccessible(true); // add this line to avoid IllegalAccessException
                            Object value = field.get(data);
                            if (value != null) {
                                bw.write(value.toString() + ",");
                            } else {
                                bw.write("NULL,");
                            }
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    bw.newLine();
                }

                alert.successMessage("Sukses menyimpan file CSV !");

                // Membuka file setelah penulisan selesai
                openFile(fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                alert.errorMessage("Gagal menyimpan file CSV");
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Data.admin_name);
        runTime();
        displayAdminID();

        dashboardPJ();
        dashboardUser();
        dashboardPeminjaman();
        dashboardPeminjamanACC();
        dashboardGetPeminjamanDisplayData();
        dashboardPeminjamanDataChart();

        pjDisplayData();
        pjActionButton();

        UserDisplayData();

        PeminjamanDisplayData();
        peminjamanActionButton();
        
        startChat();
        
        profileDisplayInfo();
        profileDisplayImages();
    }
}
