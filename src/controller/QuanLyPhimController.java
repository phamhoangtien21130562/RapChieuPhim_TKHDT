package controller;

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
        Phim p = new Phim(
            view.getId(), view.getTenPhim(), view.getTheLoai(),
            view.getDaoDien(), view.getNamSX(), view.getThoiLuong()
        );
        qlpmodel.themPhim(p);
    }

    private void xoaPhimTheoId() {
        String id = view.getId();
        qlpmodel.xoaPhimTheoId(id);
    }

}

