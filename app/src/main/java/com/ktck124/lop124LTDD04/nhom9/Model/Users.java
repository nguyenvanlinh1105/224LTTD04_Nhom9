package com.ktck124.lop124LTDD04.nhom9.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Users implements Serializable {
    private String maSinhVien;
    private String hoTen;
    private String lop;
    private int gioiTinh;
    private String ngaySinh;
    private String sdt;

    // Constructor
    public Users(String maSinhVien, String hoTen, String lop, int gioiTinh, String ngaySinh, String sdt) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.lop = lop;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
    }

    // Getters and Setters
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    // toString method
    @Override
    public String toString() {
        return "ThongTinThanhVien{" +
                "maSinhVien='" + maSinhVien + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", lop='" + lop + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", ngaySinh=" + ngaySinh +
                ", sdt='" + sdt + '\'' +
                '}';

    }
}
