package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Phim;
import model.QuanLyPhim;
import view.QuanLyPhimView;

public class QuanLyPhimController {
    private QuanLyPhimView view;
    private QuanLyPhim model;
    public List<Phim> getDanhSachPhim() {
        return model.getPhims();
    }


    public QuanLyPhimController(QuanLyPhimView view) {
        this.view = view;
        this.model = new QuanLyPhim();
        
        
        
        // Tạo dữ liệu mẫu
        Phim phim1 = new Phim("Phim 1", "Hành động", "Đạo diễn 1", 120, 10);
        Phim phim2 = new Phim("Phim 2", "Hài", "Đạo diễn 2", 90, 20);
        Phim phim3 = new Phim("Phim 3", "Kinh dị", "Đạo diễn 3", 110, 5);

        model.themPhim(phim1);
        model.themPhim(phim2);
        model.themPhim(phim3);
        


        this.view.addThemPhimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenPhim = view.getTenPhim();
                String theLoai = view.getTheLoai();
                String daoDien = view.getDirector();
                int nSX = view.getReleaseYear();
                int thoiluong = view.getDuration();

                Phim phim = new Phim(tenPhim, theLoai, daoDien, nSX, thoiluong);
                model.themPhim(phim);
                view.appendOutput("Da them phim: " + tenPhim);
                capNhatDanhSachPhim();
            }
        });

        // xử lý sự kiện xóa phim

        capNhatDanhSachPhim();
    }

    private void capNhatDanhSachPhim() {
    	  List<Phim> danhSach = model.getPhims();
          view.hienThiDanhsachPhim(danhSach);
    }
}

