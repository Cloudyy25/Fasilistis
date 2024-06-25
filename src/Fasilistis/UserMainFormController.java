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
import java.sql.SQLException;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
public class UserMainFormController implements Initializable {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;
    
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
    private Button logout_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private TableView<PeminjamanData> home_peminjaman_tableView;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_name;

    @FXML
    private TextField profile_name;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private TextField profile_password;

    private TextArea profile_address;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    @FXML
    private Button pj_btn;
    @FXML
    private Button chat_btn;
    @FXML
    private Button peminjaman_btn;
    @FXML
    private Label peminjaman_nama;
    @FXML
    private Label peminjaman_email;
    @FXML
    private Label peminjaman_hasilruang;
    @FXML
    private Label peminjaman_hasilgedung;
    @FXML
    private Label peminjaman_hasillantai;
    @FXML
    private Label peminjaman_hasiltanggal;
    @FXML
    private ComboBox<String> peminjaman_gedung;
    @FXML
    private ComboBox<Integer> peminjaman_lantai;
    @FXML
    private AnchorPane detailGedung;
    @FXML
    private AnchorPane g2l4;
    @FXML
    private AnchorPane g2l5;
    @FXML
    private AnchorPane g2l6;
    @FXML
    private AnchorPane g3l2;
    @FXML
    private AnchorPane gadt;
    @FXML
    private Button b241,b242,b251,b252,b253,b254,b255,b256,b257,b261,b262,b263,b264,b265,b266,b267,b321,b322,b323,b324,b325,b326,b327,b328,b331,b332,b333,b334,b335,b336,b337,b338,b341,b342,b343,b344,b345,b346,b347,b348,ba;
    @FXML
    private ImageView i241,i242,i251,i252,i253,i254,i255,i256,i257,i261,i262,i263,i264,i265,i266,i267,i321,i322,i323,i324,i325,i326,i327,i328,i331,i332,i333,i334,i335,i336,i337,i338,i341,i342,i343,i344,i345,i346,i347,i348,ia;
    @FXML
    private Label profile_label_email;
    @FXML
    private TextField profile_email;
    @FXML
    private Label nav_userid;
    @FXML
    private Label nav_username;
    @FXML
    private TableColumn<?, ?> home_peminjaman_id;
    @FXML
    private TableColumn<?, ?> home_peminjaman_nama;
    @FXML
    private TableColumn<?, ?> home_peminjaman_gedung;
    @FXML
    private TableColumn<?, ?> home_peminjaman_lantai;
    @FXML
    private TableColumn<?, ?> home_peminjaman_ruang;
    @FXML
    private TableColumn<?, ?> home_peminjaman_tanggal;
    @FXML
    private TableColumn<?, ?> home_peminjaman_status;
    @FXML
    private TableView<PeminjamanData> home_peminjaman_anda_tableView;
    @FXML
    private TableColumn<?, ?> home_peminjaman_anda_id;
    @FXML
    private TableColumn<?, ?> home_peminjaman_anda_gedung;
    @FXML
    private TableColumn<?, ?> home_peminjaman_anda_lantai;
    @FXML
    private TableColumn<?, ?> home_peminjaman_anda_ruang;
    @FXML
    private TableColumn<?, ?> home_peminjaman_anda_tanggal;
    @FXML
    private TableColumn<?, ?> home_peminjaman_anda_status;
    @FXML
    private ScrollPane pj_scrollPane;
    @FXML
    private Button peminjamanBtn;
    @FXML
    private Button cekbtn;
    @FXML
    private DatePicker peminjaman_tanggal;
    @FXML
    private Button resetbtn;
    @FXML
    private Button csv_peminjaman;
    @FXML
    private Label profile_label_userid;
    @FXML
    private TextField profile_userid;
    @FXML
    private AnchorPane g3l3;
    @FXML
    private AnchorPane g3l4;
    @FXML
    private GridPane pj_gridPane;
    @FXML
    private AnchorPane pj_form;
    @FXML
    private AnchorPane peminjaman_form;
    @FXML
    private AnchorPane riwayat_form;
    @FXML
    private AnchorPane chat_form;
    @FXML
    private Button riwayat_btn;
    
    Image imageroom = new Image("image\\room.png");
    Image image1 = new Image("image\\room_hover.png");
    Image image2 = new Image("image\\room_booked.png");
    @FXML
    private TextArea area_chat;
    @FXML
    private TextField text_chat;
    @FXML
    private Button kirim_chat;
    @FXML
    private Button hapus_peminjaman;
    @FXML
    private TableView<PeminjamanData> peminjaman_anda_tableView;
    @FXML
    private TableColumn<?, ?> peminjaman_anda_id;
    @FXML
    private TableColumn<?, ?> peminjaman_anda_gedung;
    @FXML
    private TableColumn<?, ?> peminjaman_anda_lantai;
    @FXML
    private TableColumn<?, ?> peminjaman_anda_ruang;
    @FXML
    private TableColumn<?, ?> peminjaman_anda_tanggal;
    @FXML
    private TableColumn<?, ?> peminjaman_anda_status;
    @FXML
    private ImageView denah;
    @FXML
    private Label title;
    
    @FXML
    private void onMouseEntered(javafx.scene.input.MouseEvent event) {
        String sourceId = ((BorderPane) event.getSource()).getId();
        ImageView imageView = getImageView(sourceId);
        if (imageView!= null) {
            imageView.setImage(image1);
        }
    }

    @FXML
    private void onMouseExited(javafx.scene.input.MouseEvent event) {
        String sourceId = ((BorderPane) event.getSource()).getId();
        ImageView imageView = getImageView(sourceId);
        if (imageView!= null &&!sourceId.equals(((BorderPane) event.getSource()).getId())) {
            imageView.setImage(imageroom);
        }
    }
    
    @FXML
    private void onMouseClicked(javafx.scene.input.MouseEvent event) {
        String sourceId = ((BorderPane) event.getSource()).getId();
        ImageView imageView = getImageView(sourceId);
        if (imageView!= null) {
            imageView.setImage(image2);
        }
    }

    private ImageView getImageView(String sourceId) {
        switch (sourceId) {
            case "b241": return i241;
            case "b242": return i242;
            case "b251": return i251;
            case "b252": return i252;
            case "b253": return i253;
            case "b254": return i254;
            case "b255": return i255;
            case "b256": return i256;
            case "b257": return i257;
            case "b261": return i261;
            case "b262": return i262;
            case "b263": return i263;
            case "b264": return i264;
            case "b265": return i265;
            case "b266": return i266;
            case "b267": return i267;
            case "b321": return i321;
            case "b322": return i322;
            case "b323": return i323;
            case "b324": return i324;
            case "b325": return i325;
            case "b326": return i326;
            case "b327": return i327;
            case "b328": return i328;
            case "b331": return i331;
            case "b332": return i332;
            case "b333": return i333;
            case "b334": return i334;
            case "b335": return i335;
            case "b336": return i336;
            case "b337": return i337;
            case "b338": return i338;
            case "b341": return i341;
            case "b342": return i342;
            case "b343": return i343;
            case "b344": return i344;
            case "b345": return i345;
            case "b346": return i346;
            case "b347": return i347;
            case "b348": return i348;
            case "ba": return ia;
            default: return null;
        }
    }
    
    public ObservableList<PeminjamanData> PeminjamanAndaGetData() {

        ObservableList<PeminjamanData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM peminjaman WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PeminjamanData pData;
            while (result.next()) {
                pData = new PeminjamanData(
                        result.getInt("id_peminjaman"),
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
    
    public ObservableList<PeminjamanData> PeminjamanAndaListData;

    public void homePeminjamanAndaDisplayData() {
        PeminjamanAndaListData = PeminjamanAndaGetData();

        home_peminjaman_anda_id.setCellValueFactory(new PropertyValueFactory<>("id_peminjaman"));
        home_peminjaman_anda_gedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        home_peminjaman_anda_lantai.setCellValueFactory(new PropertyValueFactory<>("lantai"));
        home_peminjaman_anda_ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        home_peminjaman_anda_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_peminjaman"));
        home_peminjaman_anda_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        home_peminjaman_anda_tableView.setItems(PeminjamanAndaListData);
    }

    public ObservableList<PeminjamanData> homePeminjamanGetData() {
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

    public ObservableList<PeminjamanData> homePeminjamanListData;

    public void homePeminjamanDisplayData() {
        homePeminjamanListData = homePeminjamanGetData();

        home_peminjaman_id.setCellValueFactory(new PropertyValueFactory<>("id_peminjaman"));
        home_peminjaman_nama.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        home_peminjaman_gedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        home_peminjaman_lantai.setCellValueFactory(new PropertyValueFactory<>("lantai"));
        home_peminjaman_ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        home_peminjaman_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_peminjaman"));
        home_peminjaman_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        home_peminjaman_tableView.setItems(homePeminjamanListData);
    }

    private ObservableList<PJData> PjList = FXCollections.observableArrayList();

    public ObservableList<PJData> pjGetData() {

        String sql = "SELECT * FROM pj WHERE pj_status = 'Aktif'";

        connect = Database.connectDB();

        ObservableList<PJData> listData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PJData dData;

            while (result.next()) {
                dData = new PJData(result.getInt("pj_id"),
                        result.getString("pj_nama"),
                        result.getString("pj_gedung"),
                        result.getInt("pj_lantai"),
                        result.getString("pj_no"),
                        result.getString("pj_image"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void pjShowCard() {
        PjList.clear();
        PjList.addAll(pjGetData());

        pj_gridPane.getChildren().clear();
        pj_gridPane.getColumnConstraints().clear();
        pj_gridPane.getRowConstraints().clear();

        int row = 0, column = 0;

        for (int q = 0; q < PjList.size(); q++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("PJCard.fxml"));
                StackPane stack = loader.load();

                PJCardController dController = loader.getController();
                dController.setData(PjList.get(q));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                pj_gridPane.add(stack, column++, row);

                GridPane.setMargin(stack, new Insets(15));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void peminjamanInfoDisplay() {
        String sql = "SELECT * FROM user WHERE user_id = " + Data.temp_userid;

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                peminjaman_nama.setText(result.getString("user_name"));
                peminjaman_email.setText(result.getString("user_email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            peminjaman_gedung.setItems(listData);
            peminjaman_gedung.getSelectionModel().getSelectedItem();
            String selectedItem = (String) peminjaman_gedung.getSelectionModel().getSelectedItem();
            getLantai(selectedItem);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getLantai(String selectedItem) {
        if (peminjaman_gedung.getSelectionModel().getSelectedItem() != null) {
            String sql = "SELECT DISTINCT lantai FROM stis WHERE gedung = '" +selectedItem+ "'ORDER BY lantai ASC";
            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                ObservableList<Integer> listData = FXCollections.observableArrayList();
                while (result.next()) {
                    listData.add(result.getInt("lantai"));
                }

                peminjaman_lantai.setItems(listData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void getLantai(){}
    
    @FXML
    public void peminjamanConfirmBtn() {
        if (peminjaman_gedung.getSelectionModel().isEmpty()) {
            alert.errorMessage("Harap masukkan opsi gedung");
        }if (peminjaman_lantai.getSelectionModel().isEmpty()) {
            alert.errorMessage("Harap masukkan opsi lantai");
        }if (peminjaman_tanggal.getValue()==null) {
            alert.errorMessage("Harap masukkan tanggal peminjaman");
        }
        else {
            peminjaman_hasilgedung.setText(peminjaman_gedung.getSelectionModel().getSelectedItem());
            peminjaman_hasillantai.setText(String.valueOf(peminjaman_lantai.getSelectionModel().getSelectedItem()));
            peminjaman_hasiltanggal.setText(String.valueOf(peminjaman_tanggal.getValue()));

            String sql = "SELECT * FROM stis WHERE gedung = '"
                    + peminjaman_gedung.getSelectionModel().getSelectedItem() + "' AND lantai = '"
                    + peminjaman_lantai.getSelectionModel().getSelectedItem() + "'";

            connect = Database.connectDB();
            String tempSpecialized = "";
            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                if (result.next()) {
                    String selectedGedung = peminjaman_gedung.getSelectionModel().getSelectedItem();
                    int selectedLantai = peminjaman_lantai.getSelectionModel().getSelectedItem();

                    if (selectedGedung.equals("2") && selectedLantai == 4) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(true);
                        g2l5.setVisible(false);
                        g2l6.setVisible(false);
                        gadt.setVisible(false);
                        g3l2.setVisible(false);
                        g3l3.setVisible(false);
                        g3l4.setVisible(false);
                    } else if (selectedGedung.equals("2") && selectedLantai == 5) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(false);
                        g2l5.setVisible(true);
                        g2l6.setVisible(false);
                        gadt.setVisible(false);
                        g3l2.setVisible(false);
                        g3l3.setVisible(false);
                        g3l4.setVisible(false);
                    } else if (selectedGedung.equals("2") && selectedLantai == 6) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(false);
                        g2l5.setVisible(false);
                        g2l6.setVisible(true);
                        gadt.setVisible(false);
                        g3l2.setVisible(false);
                        g3l3.setVisible(false);
                        g3l4.setVisible(false);
                    } else if (selectedGedung.equals("Auditorium") && selectedLantai == 1) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(false);
                        g2l5.setVisible(false);
                        g2l6.setVisible(false);
                        gadt.setVisible(true);
                        g3l2.setVisible(false);
                        g3l3.setVisible(false);
                        g3l4.setVisible(false);
                    } else if (selectedGedung.equals("3") && selectedLantai == 2) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(false);
                        g2l5.setVisible(false);
                        g2l6.setVisible(false);
                        gadt.setVisible(false);
                        g3l2.setVisible(true);
                        g3l3.setVisible(false);
                        g3l4.setVisible(false);
                    } else if (selectedGedung.equals("3") && selectedLantai == 3) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(false);
                        g2l5.setVisible(false);
                        g2l6.setVisible(false);
                        gadt.setVisible(false);
                        g3l2.setVisible(false);
                        g3l3.setVisible(true);
                        g3l4.setVisible(false);
                    } else if (selectedGedung.equals("3") && selectedLantai == 4) {
                        detailGedung.setVisible(false);
                        g2l4.setVisible(false);
                        g2l5.setVisible(false);
                        g2l6.setVisible(false);
                        gadt.setVisible(false);
                        g3l2.setVisible(false);
                        g3l3.setVisible(false);
                        g3l4.setVisible(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void buttonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String roomNumber = button.getId().replace("b", "");
        ImageView imageView = getImageView2(roomNumber);

        if (isRoomBooked(peminjaman_hasilgedung.getText(), peminjaman_hasillantai.getText(), peminjaman_hasilruang.getText(), peminjaman_hasiltanggal.getText())) {
            JOptionPane.showMessageDialog(null, "Ruang sudah dipinjam, Silakan pilih ruang yang lain");
        } else {
            if(roomNumber.equals("a")){
                peminjaman_hasilruang.setText("Auditorium");
            }
            else{
                peminjaman_hasilruang.setText(roomNumber);
            }
            imageView.setImage(image2);
        }
    }
    
    private ImageView getImageView2(String roomNumber) {
        switch (roomNumber) {
            case "241": return i241;
            case "242": return i242;
            case "251": return i251;
            case "252": return i252;
            case "253": return i253;
            case "254": return i254;
            case "255": return i255;
            case "256": return i256;
            case "257": return i257;
            case "261": return i261;
            case "262": return i262;
            case "263": return i263;
            case "264": return i264;
            case "265": return i265;
            case "266": return i266;
            case "267": return i267;
            case "321": return i321;
            case "322": return i322;
            case "323": return i323;
            case "324": return i324;
            case "325": return i325;
            case "326": return i326;
            case "327": return i327;
            case "328": return i328;
            case "331": return i331;
            case "332": return i332;
            case "333": return i333;
            case "334": return i334;
            case "335": return i335;
            case "336": return i336;
            case "337": return i337;
            case "338": return i338;
            case "341": return i341;
            case "342": return i342;
            case "343": return i343;
            case "344": return i344;
            case "345": return i345;
            case "346": return i346;
            case "347": return i347;
            case "348": return i348;
            case "a": return ia;
            default: return null;
        }
    }

    @FXML
    public void peminjamanClearBtn() {
        peminjaman_gedung.getSelectionModel().clearSelection();
        peminjaman_lantai.getSelectionModel().clearSelection();
        peminjaman_tanggal.setValue(null);

        peminjaman_hasilgedung.setText("");
        peminjaman_hasillantai.setText("");
        peminjaman_hasilruang.setText("");
        peminjaman_hasiltanggal.setText("");
        
        detailGedung.setVisible(true);
        g2l4.setVisible(false);
        g2l5.setVisible(false);
        g2l6.setVisible(false);
        gadt.setVisible(false);
        g3l2.setVisible(false);
        g3l3.setVisible(false);
        g3l4.setVisible(false);
    }

    @FXML
    public void AjukanPeminjamanBtn() {
        connect = Database.connectDB();

        if (peminjaman_hasilgedung.getText().isEmpty()
                || peminjaman_hasillantai.getText().isEmpty()
                || peminjaman_hasilruang.getText().isEmpty()
                || peminjaman_hasiltanggal.getText().isEmpty()) {
            alert.errorMessage("Invalid, harap cek kembali pengajuan Anda");
        } else if(isRoomBooked(peminjaman_hasilgedung.getText(), peminjaman_hasillantai.getText(), peminjaman_hasilruang.getText(), peminjaman_hasiltanggal.getText())){
            alert.errorMessage("Ruangan sudah dipinjam, silakan pilih ruangan lain.");
        } else {
            String selectData = "SELECT MAX(id_peminjaman) FROM peminjaman";

            int tempAppID = 0;

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    tempAppID = result.getInt("MAX(id_peminjaman)") + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String insertData = "INSERT INTO peminjaman (id_peminjaman, user_id, user_name, user_email"
                    + ", gedung, lantai, ruang, tanggal_peminjaman, status) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            try {
                if (alert.confirmationMessage("Apa Anda ingin meminjam?")) {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(tempAppID));
                    prepare.setString(2, String.valueOf(Data.temp_userid));
                    prepare.setString(3, String.valueOf(Data.temp_username));
                    prepare.setString(4, String.valueOf(Data.temp_useremail));
                    prepare.setString(5, peminjaman_hasilgedung.getText());
                    prepare.setString(6, peminjaman_hasillantai.getText());
                    prepare.setString(7, peminjaman_hasilruang.getText());
                    prepare.setString(8, peminjaman_hasiltanggal.getText());
                    prepare.setString(9, "Diajukan");

                    prepare.executeUpdate();
                    alert.successMessage("Sukses mengajukan peminjaman!");
                    homePeminjamanAndaDisplayData();
                    PeminjamanAndaDisplayData();
                    homePeminjamanDisplayData();

                    peminjamanClearBtn();
                } else {
                    alert.errorMessage("Gagal mengajukan peminjaman.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRoomBooked(String gedung, String lantai, String ruang, String tanggal) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM peminjaman WHERE gedung =? AND lantai =? AND ruang =? AND tanggal_peminjaman =? AND (status = 'Diajukan' OR status = 'Disetujui')";

            connect = Database.connectDB();
            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, gedung);
            pstmt.setString(2, lantai);
            pstmt.setString(3, ruang);
            pstmt.setString(4, tanggal);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true; // room is booked
            } else {
                return false; // room is available
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // assume room is available if there's an error
        }
    }
    
    @FXML
    private void hapusPeminjaman(){                                     
        PeminjamanData pData = peminjaman_anda_tableView.getSelectionModel().getSelectedItem();
        int num = peminjaman_anda_tableView.getSelectionModel().getSelectedIndex();
        connect = Database.connectDB();
        String sql = "DELETE FROM peminjaman WHERE id_peminjaman = '" + pData.getId_peminjaman() + "'";

        if (num != -1) {
            if (alert.confirmationMessage("Apakah Anda Yakin Menghapus Data Ini?")) {
                try {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert.successMessage("Sukses menghapus peminjaman!");

                    // Refresh table
                    peminjaman_anda_tableView.getItems().remove(num);
                    homePeminjamanAndaDisplayData();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            alert.errorMessage("Silakan pilih baris tabel terlebih dahulu.");
        }  
    }
    

    public ObservableList<PeminjamanData> PeminjamanAnda2GetData() {

        ObservableList<PeminjamanData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM peminjaman WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PeminjamanData pData;
            while (result.next()) {
                pData = new PeminjamanData(
                        result.getInt("id_peminjaman"),
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
    
    public ObservableList<PeminjamanData> PeminjamanAnda2ListData;
    
    public void PeminjamanAndaDisplayData() {
        PeminjamanAnda2ListData = PeminjamanAnda2GetData();

        peminjaman_anda_id.setCellValueFactory(new PropertyValueFactory<>("id_peminjaman"));
        peminjaman_anda_gedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        peminjaman_anda_lantai.setCellValueFactory(new PropertyValueFactory<>("lantai"));
        peminjaman_anda_ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        peminjaman_anda_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_peminjaman"));
        peminjaman_anda_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        peminjaman_anda_tableView.setItems(PeminjamanAnda2ListData);
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
    private void ExportCSVpeminjaman(){
        PeminjamanAndaDisplayData();
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
                columnFieldMap.put("peminjaman_anda_id", "id_peminjaman");
                columnFieldMap.put("peminjaman_anda_gedung", "gedung");
                columnFieldMap.put("peminjaman_anda_lantai", "lantai");
                columnFieldMap.put("peminjaman_anda_ruang", "ruang");
                columnFieldMap.put("peminjaman_anda_tanggal", "tanggal_peminjaman");
                columnFieldMap.put("peminjaman_anda_status", "status");
                // add more mappings for each column

                for (int i = 0; i < peminjaman_anda_tableView.getColumns().size(); i++) {
                    bw.write(peminjaman_anda_tableView.getColumns().get(i).getText() + ",");
                }
                bw.newLine();

                // Menulis data ke file
                for (PeminjamanData data : peminjaman_anda_tableView.getItems()) {
                    for (int i = 0; i < peminjaman_anda_tableView.getColumns().size(); i++) {
                        String fieldName = columnFieldMap.get(peminjaman_anda_tableView.getColumns().get(i).getId());
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
    
    public void startChat() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    s = new Socket("127.0.0.1", 3304);
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
                dout.writeUTF(Data.temp_username + " : " + msg);
                text_chat.setText("");
                Platform.runLater(() -> area_chat.appendText("\n" + Data.temp_username + " : " + msg));
            } else {
                // Handle the case where dout is null
                alert.errorMessage("Connection not established");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void profileDisplayFields() {
        String sql = "SELECT * FROM user WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_userid.setText(result.getString("user_id"));
                profile_name.setText(result.getString("user_name"));
                profile_email.setText(result.getString("user_email"));
                profile_password.setText(result.getString("user_pass"));

                if (result.getString("user_image") != null) {
                    String imagePath = "File:" + result.getString("user_image");
                    image = new Image(imagePath, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
                profileDisplayLabels();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileDisplayLabels() {
        String sql = "SELECT * FROM user WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_userid.setText(result.getString("user_id"));
                profile_label_name.setText(result.getString("user_name"));
                profile_label_email.setText(result.getString("user_email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileDisplayImages() {
        String sql = "SELECT * FROM user WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();

        String tempPath1 = "";
        String tempPath2 = "";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempPath1 = "File:" + result.getString("user_image");
                tempPath2 = "File:" + result.getString("user_image");

                if (result.getString("user_image") != null || "".equals(result.getString("user_image"))) {
                    image = new Image(tempPath1, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                    image = new Image(tempPath2, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void profileUpdateBtn() {
        connect = Database.connectDB();

        if (profile_userid.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_password.getText().isEmpty()) {
            alert.errorMessage("Harap isi seluruh kolom isian");
        } else {
            if (alert.confirmationMessage("Ingin mengupdate profil Anda??")) {
                if (Data.path == null || "".equals(Data.path)) {
                    String updateData = "UPDATE user SET user_name = '" 
                            + profile_name.getText() + "', user_email = '"
                            + profile_email.getText() + "', user_pass = '"
                            + profile_password.getText() + "' WHERE user_id = " 
                            + Data.temp_userid;
                    try {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();

                        alert.successMessage("Sukses Mengupdate Profil!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Semester 4\\PBO\\TugasAkhir\\Fasilistis\\src\\image\\User.png");

                    String copyPath = copy.toAbsolutePath().toString();
                    copyPath = copyPath.replace("\\", "\\\\");

                    String updateData = "UPDATE user SET "
                            + "user_name = '" + profile_name.getText() + "', user_email = '"
                            + profile_email.getText() + "', user_pass = '"
                            + profile_password.getText() + "', user_image = '"
                            + copyPath + "' WHERE user_id = " + Data.temp_userid;

                    try {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();

                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                        alert.successMessage("Sukses Mengupdate Profil!");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                alert.errorMessage("Gagal mengupdate profil.");
            }

        }
        profileDisplayImages();
        profileDisplayLabels();
        displayUserID();
        displayUser();
    }

    @FXML
    public void profileImportBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 137, 95, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }

    }

    @FXML
    void logoutBtn(ActionEvent event) {
        try {
            if (alert.confirmationMessage("Apakah Anda ingin logout?")) {
                Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            home_form.setVisible(true);
            pj_form.setVisible(false);
            peminjaman_form.setVisible(false);
            riwayat_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(false);
            current_form.setText("Dashboard Form");
        } else if (event.getSource() == pj_btn) {
            home_form.setVisible(false);
            pj_form.setVisible(true);
            peminjaman_form.setVisible(false);
            riwayat_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(false);
            current_form.setText("Penanggung Jawab Form");
        } else if (event.getSource() == peminjaman_btn) {
            home_form.setVisible(false);
            pj_form.setVisible(false);
            peminjaman_form.setVisible(true);
            riwayat_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(false);
            current_form.setText("Peminjaman Form");
        } else if (event.getSource() == riwayat_btn) {
            home_form.setVisible(false);
            pj_form.setVisible(false);
            peminjaman_form.setVisible(false);
            riwayat_form.setVisible(true);
            chat_form.setVisible(false);
            profile_form.setVisible(false);
            current_form.setText("Riwayat Peminjaman Form");
        }else if (event.getSource() == chat_btn) {
            home_form.setVisible(false);
            pj_form.setVisible(false);
            peminjaman_form.setVisible(false);
            riwayat_form.setVisible(false);
            chat_form.setVisible(true);
            profile_form.setVisible(false);
            current_form.setText("Chat Form");
        } else if (event.getSource() == profile_btn) {
            home_form.setVisible(false);
            pj_form.setVisible(false);
            peminjaman_form.setVisible(false);
            riwayat_form.setVisible(false);
            chat_form.setVisible(false);
            profile_form.setVisible(true);
            current_form.setText("Profile Form");
        }
    }

    public void displayUserID(){
        String sql = "SELECT * FROM user WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                nav_userid.setText(result.getString("user_id"));
                nav_username.setText(result.getString("user_name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void displayUser(){
        String sql = "SELECT * FROM user WHERE user_id = " + Data.temp_userid;
        connect = Database.connectDB();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                top_username.setText(result.getString("user_name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void appointmentBookBtn(){
    }
    public void appointmentConfirmBtn(){
    }
    public void appointmentClearBtn(){
    }
   
    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Data.temp_userid);
        runTime();
        displayUserID();
        displayUser();

        homePeminjamanAndaDisplayData();
        homePeminjamanDisplayData();

        pjShowCard();

        peminjamanInfoDisplay();
        getGedung();
        
        PeminjamanAndaDisplayData();
        
        profileDisplayFields();
        profileDisplayImages();
    }
}
