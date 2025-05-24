package controller;

import model.LichChieu;
import model.Phim;
import model.QuanLyPhim;
import view.DanhSachPhimView;
import view.QuanLyPhimView;

public class QuanLyPhimController {
    private QuanLyPhim qlpmodel;
    private QuanLyPhimView view;
    private DanhSachPhimView danhSachView;

    public QuanLyPhimController(QuanLyPhim qlpmodel, QuanLyPhimView view) {
        this.qlpmodel = qlpmodel;
        this.view = view;

        // Khởi tạo DanhSachPhimView và đăng ký làm observer
        danhSachView = new DanhSachPhimView();
        qlpmodel.dangKyObserver(danhSachView);
        danhSachView.setVisible(true);

        // Đăng ký observer cho QuanLyPhimView 
        qlpmodel.dangKyObserver(view);

        // Khởi tạo dữ liệu mẫu
        qlpmodel.themPhimMau(qlpmodel);

        // Thêm sự kiện
        view.addThemListener(e -> themPhimMoi());
        view.addXoaTheoIdListener(e -> xoaPhimTheoId());
    }

    private void themPhimMoi() {
        // Lấy dữ liệu từ view
        String id = view.getId();
        String tenPhim = view.getTenPhim();
        String theLoai = view.getTheLoai();
        String daoDien = view.getDaoDien();
        int namSX = view.getNamSX();
        int thoiLuong = view.getThoiLuong();
        String trangThai = view.getTrangThai();
        String lichChieuText = view.getLichChieu();
        Phim p = new Phim(id, tenPhim, theLoai, daoDien, namSX, thoiLuong);
        p.setTrangthai(trangThai);
        p.setLichChieu(new LichChieu(lichChieuText));
        qlpmodel.themPhim(p);
    }


    private void xoaPhimTheoId() {
        String id = view.getId();
        qlpmodel.xoaPhimTheoId(id);
    }

}

