package com.example.demo;

public class DiemUngVien {
    public String maUngVien;
    public String hoTen;
    public int kinhNghiem;
    public int trinhDoChuyenMon;
    public int bangCap;
    public float luong;

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

    public int getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(int kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public int getTrinhDoChuyenMon() {
        return trinhDoChuyenMon;
    }

    public void setTrinhDoChuyenMon(int trinhDoChuyenMon) {
        this.trinhDoChuyenMon = trinhDoChuyenMon;
    }

    public int getBangCap() {
        return bangCap;
    }

    public void setBangCap(int bangCap) {
        this.bangCap = bangCap;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public DiemUngVien(String maUngVien, String hoTen, int kinhNghiem, int trinhDoChuyenMon, int bangCap, float luong) {
        this.maUngVien = maUngVien;
        this.hoTen = hoTen;
        this.kinhNghiem = kinhNghiem;
        this.trinhDoChuyenMon = trinhDoChuyenMon;
        this.bangCap = bangCap;
        this.luong = luong;
    }
}
