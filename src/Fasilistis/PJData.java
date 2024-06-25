package Fasilistis;

public class PJData {

    public int pj_id;
    public String pj_nama;
    public String pj_no;
    public String pj_email;
    public String pj_gedung;
    public int pj_lantai;
    public String pj_pass;
    public String pj_image;
    public String pj_status;

    public PJData(int pj_id, String pj_nama, String pj_no, String pj_email, String pj_gedung, int pj_lantai, String pj_pass, String pj_image, String pj_status) {
        this.pj_id = pj_id;
        this.pj_nama = pj_nama;
        this.pj_no = pj_no;
        this.pj_email = pj_email;
        this.pj_gedung = pj_gedung;
        this.pj_lantai = pj_lantai;
        this.pj_pass = pj_pass;
        this.pj_image = pj_image;
        this.pj_status = pj_status;
    }

    public PJData(int pj_id, String pj_nama, String pj_no, String pj_email, String pj_gedung, int pj_lantai, String pj_status) {
        this.pj_id = pj_id;
        this.pj_nama = pj_nama;
        this.pj_no = pj_no;
        this.pj_email = pj_email;
        this.pj_gedung = pj_gedung;
        this.pj_lantai = pj_lantai;
        this.pj_status = pj_status;
    }

    public PJData(int pj_id, String pj_nama, String pj_gedung, int pj_lantai, String pj_no, String pj_image) {
        this.pj_id = pj_id;
        this.pj_nama = pj_nama;
        this.pj_gedung = pj_gedung;
        this.pj_lantai = pj_lantai;
        this.pj_no = pj_no;
        this.pj_image=pj_image;
    }

    public int getPj_id() {
        return pj_id;
    }

    public void setPj_id(int pj_id) {
        this.pj_id = pj_id;
    }

    public String getPj_nama() {
        return pj_nama;
    }

    public void setPj_nama(String pj_nama) {
        this.pj_nama = pj_nama;
    }

    public String getPj_no() {
        return pj_no;
    }

    public void setPj_no(String pj_no) {
        this.pj_no = pj_no;
    }

    public String getPj_email() {
        return pj_email;
    }

    public void setPj_email(String pj_email) {
        this.pj_email = pj_email;
    }

    public String getPj_gedung() {
        return pj_gedung;
    }

    public void setPj_gedung(String pj_gedung) {
        this.pj_gedung = pj_gedung;
    }

    public int getPj_lantai() {
        return pj_lantai;
    }

    public void setPj_lantai(int pj_lantai) {
        this.pj_lantai = pj_lantai;
    }

    public String getPj_pass() {
        return pj_pass;
    }

    public void setPj_pass(String pj_pass) {
        this.pj_pass = pj_pass;
    }

    public String getPj_image() {
        return pj_image;
    }

    public void setPj_image(String pj_image) {
        this.pj_image = pj_image;
    }

    public String getPj_status() {
        return pj_status;
    }

    public void setPj_status(String pj_status) {
        this.pj_status = pj_status;
    }

    
}
