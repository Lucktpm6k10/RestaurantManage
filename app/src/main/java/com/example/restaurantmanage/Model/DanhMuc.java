package com.example.restaurantmanage.Model;

import java.util.ArrayList;

public class DanhMuc {
    private String tenDanhMuc;
    private String hinhAnhDanhMuc;
    private ArrayList<MonAn> listMonAn;

    public DanhMuc() {
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getHinhAnhDanhMuc() {
        return hinhAnhDanhMuc;
    }

    public void setHinhAnhDanhMuc(String hinhAnhDanhMuc) {
        this.hinhAnhDanhMuc = hinhAnhDanhMuc;
    }

    public ArrayList<MonAn> getListMonAn() {
        return listMonAn;
    }

    public void setListMonAn(ArrayList<MonAn> listMonAn) {
        this.listMonAn = listMonAn;
    }

    public DanhMuc(String tenDanhMuc, String hinhAnhDanhMuc, ArrayList<MonAn> listMonAn) {
        this.tenDanhMuc = tenDanhMuc;
        this.hinhAnhDanhMuc = hinhAnhDanhMuc;
        this.listMonAn = listMonAn;
    }
}
