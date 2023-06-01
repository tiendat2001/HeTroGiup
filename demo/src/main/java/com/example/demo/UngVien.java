package com.example.demo;

public class UngVien {
    public String maUngVien;
    public String hoTen;

    public int namsinh;

    public UngVien(String maUngVien, String hoTen) {
        this.maUngVien = maUngVien;
        this.hoTen = hoTen;
    }

    public String getMaUngVien() {
        return maUngVien;
    }

    public void setMaUngVien(String maUngVien) {
        this.maUngVien = maUngVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }


}
