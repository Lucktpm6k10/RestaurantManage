package com.example.restaurantmanage.Model;

public class User {
    private String tenTaiKhoan;
    private String matKhau;
    private int chucVu;

    public User() {
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public User(String tenTaiKhoan, String matKhau, int chucVu) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

}
