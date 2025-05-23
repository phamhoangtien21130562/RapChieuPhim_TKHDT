package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.DSPhimView;
import view.HistoryView;
import view.MainView;
import view.QuanLyPhimView;
import view.ViewDangNhap;

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
//
//        mainView.getListfilmMenuItem().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DSPhimView view = new DSPhimView();
//                view.setVisible(true);
               new DSPhimController(view);
//            }
//        });

        mainView.getViewHistoryMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoryView().setVisible(true);
            }
        });

        mainView.getLoginMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDangNhap().setVisible(true);
            }
        });
//        mainView.getBookTicketsMenuItem().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				new ViewDecorator().setVisible(true);
//			}
//		});
    }

    private void showQuanLyPhim() {
        quanLyPhimView = new QuanLyPhimView();
        quanLyPhimView.setVisible(true);
        new QuanLyPhimController(quanLyPhimView);
    }
}
