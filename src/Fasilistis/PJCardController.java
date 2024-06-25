package Fasilistis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class PJCardController implements Initializable {
    private Image image;
    @FXML
    private Label pj_id;
    @FXML
    private Label pj_nama;
    @FXML
    private Label pj_gedung;
    @FXML
    private Label pj_lantai;
    @FXML
    private Label pj_no;
    @FXML
    private Circle pj_circle;

    public void setData(PJData dData) {

        if (dData.getPj_image() != null) {
            image = new Image("File:" + dData.getPj_image(), 52, 52, false, true);
            pj_circle.setFill(new ImagePattern(image));
        }

        pj_id.setText(String.valueOf(dData.getPj_id()));
        pj_nama.setText(dData.getPj_nama());
        pj_gedung.setText(dData.getPj_gedung());
        pj_lantai.setText(String.valueOf(dData.getPj_lantai()));
        pj_no.setText(String.valueOf(dData.getPj_no()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
