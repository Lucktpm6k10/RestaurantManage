package com.example.restaurantmanage.Model;

public class MonAn {
    private String tenMonAn;
    private double giaThanh;
    private String hinhAnhMonAn;
    private int loaiThucAn;
    private int soLuongBan;

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    public String getHinhAnhMonAn() {
        return hinhAnhMonAn;
    }

    public void setHinhAnhMonAn(String hinhAnhMonAn) {
        this.hinhAnhMonAn = hinhAnhMonAn;
    }

    public int getLoaiThucAn() {
        return loaiThucAn;
    }

    public void setLoaiThucAn(int loaiThucAn) {
        this.loaiThucAn = loaiThucAn;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public MonAn() {
    }

    public MonAn(String tenMonAn, double giaThanh, String hinhAnhMonAn, int loaiThucAn, int soLuongBan) {
        this.tenMonAn = tenMonAn;
        this.giaThanh = giaThanh;
        this.hinhAnhMonAn = hinhAnhMonAn;
        this.loaiThucAn = loaiThucAn;
        this.soLuongBan = soLuongBan;
    }
}
