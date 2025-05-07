package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.DanhSachPhim;
import model.Phim;
import model.QuanLyPhim;
import view.DSPhimView;
import view.MainView;
import view.QuanLyPhimView;

public class MainController {
    private MainView mainView;
    private QuanLyPhimView quanLyPhimView;

    public MainController(MainView view) {
        this.mainView = view;

        mainView.getManageMoviesMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuanLyPhim();
            }
        });
        
        
        mainView.getListfilmMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSPhimView view = new DSPhimView();
                view.setVisible(true);
                new DSPhimController(view);
            }
        });
    }

    private void showQuanLyPhim() {
        quanLyPhimView = new QuanLyPhimView();
        quanLyPhimView.setVisible(true);
        new QuanLyPhimController(quanLyPhimView);
    }

   
    

}
