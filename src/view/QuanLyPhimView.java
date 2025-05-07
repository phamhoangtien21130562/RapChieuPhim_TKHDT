package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import model.Phim;

public class QuanLyPhimView extends JFrame {
    private JTextField tenPhimField, theloaiPhimField, daodienPhimField, namSXPhim, thoiluongPhim ;
    private JButton themButton, xoaButton;
    private JTextArea outputArea;

    public QuanLyPhimView() {
        setTitle("Quản lý phim");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Tên phim:"));
        tenPhimField = new JTextField();
        inputPanel.add(tenPhimField);

        inputPanel.add(new JLabel("Thể loại:"));
        theloaiPhimField = new JTextField();
        inputPanel.add(theloaiPhimField);

        inputPanel.add(new JLabel("Đạo diễn:"));
        daodienPhimField = new JTextField();
        inputPanel.add(daodienPhimField);
        
        inputPanel.add(new JLabel("Năm SX:"));
        namSXPhim = new JTextField();
        inputPanel.add(namSXPhim);
        
        inputPanel.add(new JLabel("Thời lượng:"));
        thoiluongPhim = new JTextField();
        inputPanel.add(thoiluongPhim);

        // Nút thêm/xoá
        themButton = new JButton("Thêm phim");
        xoaButton = new JButton("Xoá phim");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(themButton);
        buttonPanel.add(xoaButton);

        // Vùng hiển thị kết quả
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Thêm các thành phần vào cửa sổ
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    public String getTenPhim() {
        return tenPhimField.getText();
    }

    public String getTheLoai() {
        return theloaiPhimField.getText();
    }

    public String getDirector() {
        return daodienPhimField.getText();
    }
    
    public int getReleaseYear() {
        try {
            return Integer.parseInt(namSXPhim.getText());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Năm sản xuất không hợp lệ");
            return 0;  
        }
    }

    public int getDuration() {
        try {
            return Integer.parseInt(thoiluongPhim.getText());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Thời lượng phim không hợp lệ");
            return 0; 
        }
    }

    public void addThemPhimListener(ActionListener listener) {
        themButton.addActionListener(listener);
    }

    public void addXoaPhimListener(ActionListener listener) {
        xoaButton.addActionListener(listener);
    }

    public void appendOutput(String text) {
        outputArea.append(text + "\n");
    }
   
    
    public void hienThiDanhsachPhim(List<Phim> danhSachPhim) {
        outputArea.setText("");  // Clear previous output

        if (danhSachPhim == null || danhSachPhim.isEmpty()) {
            outputArea.append("Danh sách phim trống.\n");
        } else {
            outputArea.append("Danh sách phim:\n");
            for (Phim phim : danhSachPhim) {
                outputArea.append("Tên phim: " + phim.getTenPhim() + "\n");
                outputArea.append("Thể loại: " + phim.getTheloai() + "\n");
                outputArea.append("Đạo diễn: " + phim.getDirector() + "\n");
                outputArea.append("Thời lượng: " + phim.getDuration() + " phút\n");
                outputArea.append("Số lượng: " + phim.getReleaseYear() + "\n\n");
            }
        }
    }

    






}
