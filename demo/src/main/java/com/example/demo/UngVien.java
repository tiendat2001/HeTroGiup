package com.example.demo;

import java.util.Date;

public class UngVien {
    public String maUngVien;
    public String hoTen;

    public Date ngaySinh;
    public String queQuan;
    public String diaChi;
    public int kinhNghiem;
    public String trinhDoChuyenMon;
    private String bangCap;



    private int diemBangCap;
    public float luong;

    public UngVien(String maUngVien, String hoTen, Date ngaySinh, String queQuan, String diaChi, int kinhNghiem, String trinhDoChuyenMon, String bangCap, float luong) {
        this.maUngVien = maUngVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.diaChi = diaChi;
        this.kinhNghiem = kinhNghiem;
        this.trinhDoChuyenMon = trinhDoChuyenMon;
        this.bangCap = bangCap;
        this.luong = luong;

        if(bangCap.equalsIgnoreCase("Xuất sắc")) {
            this.diemBangCap=10;
        }else if(bangCap.equalsIgnoreCase("Giỏi")){
            this.diemBangCap=8;
        }else if(bangCap.equalsIgnoreCase("Khá")){
            this.diemBangCap=6;
        }else if(bangCap.equalsIgnoreCase("Trung bình")){
            this.diemBangCap=4;
        }
        System.out.println(diemBangCap);
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(int kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public String getTrinhDoChuyenMon() {
        return trinhDoChuyenMon;
    }

    public void setTrinhDoChuyenMon(String trinhDoChuyenMon) {
        this.trinhDoChuyenMon = trinhDoChuyenMon;
    }

    public String getBangCap() {
        return bangCap;
    }

    public void setBangCap(String bangCap) {
        this.bangCap = bangCap;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

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

    public Date getngaySinh() {
        return ngaySinh;
    }

    public void setNamsinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getDiemBangCap() {
        return diemBangCap;
    }

    public void setDiemBangCap(int diemBangCap) {
        this.diemBangCap = diemBangCap;
    }

}
