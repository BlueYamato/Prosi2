package com.example.win10.prosi;

public class Pengajar {

    protected String nama;
    protected String pelajaran;

    public Pengajar(String nama, String pelajaran){
        this.nama = nama;
        this.pelajaran = pelajaran;
    }

    public void setPelajaran(String pelajaran){
        this.pelajaran = pelajaran;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return this.nama;
    }

    public String getPelajaran(){
        return this.pelajaran;
    }
}
