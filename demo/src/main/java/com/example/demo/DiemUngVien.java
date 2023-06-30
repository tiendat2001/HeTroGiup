package com.example.demo;

public class DiemUngVien {
    public String maUngVien;
    public String hoTen;
    public double kinhNghiem;
    public double trinhDoChuyenMon;
    public double bangCap;
    public double luong;

    public double diemPhuHop;

    public double getDiemPhuHop() {
        return diemPhuHop;
    }

    public void setDiemPhuHop(double diemPhuHop) {
        this.diemPhuHop = diemPhuHop;
    }

    public DiemUngVien(String maUngVien, String hoTen, double kinhNghiem, double trinhDoChuyenMon, double bangCap, double luong) {
        this.maUngVien = maUngVien;
        this.hoTen = hoTen;
        this.kinhNghiem = kinhNghiem;
        this.trinhDoChuyenMon = trinhDoChuyenMon;
        this.bangCap = bangCap;
        this.luong = luong;
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

    public double getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(double kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public double getTrinhDoChuyenMon() {
        return trinhDoChuyenMon;
    }

    public void setTrinhDoChuyenMon(double trinhDoChuyenMon) {
        this.trinhDoChuyenMon = trinhDoChuyenMon;
    }

    public double getBangCap() {
        return bangCap;
    }

    public void setBangCap(double bangCap) {
        this.bangCap = bangCap;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
}
