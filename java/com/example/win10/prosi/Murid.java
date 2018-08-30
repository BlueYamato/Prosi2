package com.example.win10.prosi;

/**
 * Created by WIN 10 on 5/8/2018.
 */

public class Murid {
    String ID_Account;
    String Username;
    String Password;
    String No_Telp;
    String Email;
    String Foto_Profil;
    String Alamat;
    String Tanggal_Lahir;
    String Jenis_Kelamin;
    String Nama;
    public String Nama_Kota;

    public Murid(String ID_Account, String username, String password, String no_Telp, String email, String foto_Profil, String alamat, String tanggal_Lahir, String jenis_Kelamin, String nama,String nama_kota) {
        this.ID_Account = ID_Account;
        Username = username;
        Password = password;
        No_Telp = no_Telp;
        Email = email;
        Foto_Profil = foto_Profil;
        Alamat = alamat;
        Tanggal_Lahir = tanggal_Lahir;
        Jenis_Kelamin = jenis_Kelamin;
        Nama = nama;
        this.Nama_Kota = nama_kota;
    }

    public String getID_Account() {
        return ID_Account;
    }

    public String printall(){
        return this.ID_Account+" "+this.Username+" "+this.Password+" "+
                this.No_Telp+" "+
                this.Email+" "+
                this.Foto_Profil+" "+
                this.Alamat+" "+
                this.Tanggal_Lahir+" "+
                this.Nama;
    }

    public void setID_Account(String ID_Account) {
        this.ID_Account = ID_Account;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNo_Telp() {
        return No_Telp;
    }

    public void setNo_Telp(String no_Telp) {
        No_Telp = no_Telp;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFoto_Profil() {
        return Foto_Profil;
    }

    public void setFoto_Profil(String foto_Profil) {
        Foto_Profil = foto_Profil;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getTanggal_Lahir() {
        return Tanggal_Lahir;
    }

    public void setTanggal_Lahir(String tanggal_Lahir) {
        Tanggal_Lahir = tanggal_Lahir;
    }

    public String getJenis_Kelamin() {
        return Jenis_Kelamin;
    }

    public void setJenis_Kelamin(String jenis_Kelamin) {
        Jenis_Kelamin = jenis_Kelamin;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }
}
