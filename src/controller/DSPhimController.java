package controller;

import java.util.List;

import model.DanhSachPhim;
import model.Phim;
import view.DSPhimView;

public class DSPhimController {
    private DanhSachPhim danhSachPhim;
    private DSPhimView view;

    public DSPhimController(DSPhimView view) {
        this.view = view;
        danhSachPhim = new DanhSachPhim();
        themDuLieuPhim();
        hienThiDanhSachPhim();
    }
    
    public DSPhimController(DSPhimView view, List<Phim> danhSachPhim) {
        this.view = view;
        this.danhSachPhim = new DanhSachPhim();
        for (Phim phim : danhSachPhim) {
            this.danhSachPhim.themPhim(phim);
            view.themPhimVaoTable(phim);
        }
    }


    private void themDuLieuPhim() {
    	danhSachPhim.themPhim(new Phim("van", "hanh dong", "A", 2009, 120));

    }
    private void hienThiDanhSachPhim() {
        for (Phim phim : danhSachPhim.getDanhSach()) {
            view.themPhimVaoTable(phim);
        }
    }
}
