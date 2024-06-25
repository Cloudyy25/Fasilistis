package Fasilistis;

import java.util.Date;

public class PeminjamanData {
    private int id_peminjaman;
    private int user_id;
    private String user_name;
    private String user_email;
    private String gedung;
    private int lantai;
    private String ruang;
    private Date tanggal_peminjaman;
    private String status;

    public PeminjamanData(int id_peminjaman, int user_id, String user_name, String user_email, String gedung, int lantai, String ruang, Date tanggal_peminjaman, String status) {
        this.id_peminjaman = id_peminjaman;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.gedung = gedung;
        this.lantai = lantai;
        this.ruang = ruang;
        this.tanggal_peminjaman = tanggal_peminjaman;
        this.status = status;
    }

    public PeminjamanData(int id_peminjaman, String user_name, String ruang, String status) {
        this.id_peminjaman = id_peminjaman;
        this.user_name = user_name;
        this.ruang = ruang;
        this.status = status;
    }

    public PeminjamanData(int id_peminjaman, String gedung, int lantai, String ruang, Date tanggal_peminjaman, String status) {
        this.id_peminjaman = id_peminjaman;
        this.gedung = gedung;
        this.lantai = lantai;
        this.ruang = ruang;
        this.tanggal_peminjaman = tanggal_peminjaman;
        this.status = status;
    }

    public PeminjamanData(int id_peminjaman, String user_name, String gedung, int lantai, String ruang, Date tanggal_peminjaman, String status) {
        this.id_peminjaman = id_peminjaman;
        this.user_name = user_name;
        this.gedung = gedung;
        this.lantai = lantai;
        this.ruang = ruang;
        this.tanggal_peminjaman = tanggal_peminjaman;
        this.status = status;
    }
    
    public int getId_peminjaman() {
        return id_peminjaman;
    }

    public void setId_peminjaman(int id_peminjaman) {
        this.id_peminjaman = id_peminjaman;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getGedung() {
        return gedung;
    }

    public void setGedung(String gedung) {
        this.gedung = gedung;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public Date getTanggal_peminjaman() {
        return tanggal_peminjaman;
    }

    public void setTanggal_peminjaman(Date tanggal_peminjaman) {
        this.tanggal_peminjaman = tanggal_peminjaman;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
