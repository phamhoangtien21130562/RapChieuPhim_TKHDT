package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Subject interface
interface Subject1 {
    void themObserver1(Observer1 obs);
    void xoaObserver1(Observer1 obs);
    void thongBaoCapNhat1();
}

// Observer interface
interface Observer1 {
    void capNhatGhe();
}

// Model class (ghế)
class GheModel implements Subject1 {
    private final String maGhe;
    private boolean daDat = false;
    private final List<Observer1> observers = new ArrayList<>();

    public GheModel(String maGhe) {
        this.maGhe = maGhe;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public boolean isDaDat() {
        return daDat;
    }

    public void datGhe() {
        this.daDat = true;
        thongBaoCapNhat1();
    }

    @Override
    public void themObserver1(Observer1 obs) {
        observers.add(obs);
    }

    @Override
    public void xoaObserver1(Observer1 obs) {
        observers.remove(obs);
    }

    @Override
    public void thongBaoCapNhat1() {
        for (Observer1 obs : observers) {
            obs.capNhatGhe();
        }
    }
}

// View (Button) class
class SeatButtonView extends JButton implements Observer1 {
    private final GheModel model;
    private boolean isSelected = false;

    public SeatButtonView(GheModel model, JLabel statusLabel, List<SeatButtonView> danhSachGhe) {
        super(model.getMaGhe());
        this.model = model;
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(50, 40));

        model.themObserver1(this);

        this.addActionListener(e -> {
            if (model.isDaDat()) return;

            isSelected = !isSelected;
            this.setBackground(isSelected ? Color.GREEN : Color.LIGHT_GRAY);

            long soGheDangChon = danhSachGhe.stream().filter(SeatButtonView::isDangChon).count();
            statusLabel.setText("Ghe da chon: " + soGheDangChon);
        });
    }

    public boolean isDangChon() {
        return isSelected;
    }

    public void xacNhanDat() {
        if (isSelected) {
            model.datGhe();
            isSelected = false;
        }
    }

    @Override
    public void capNhatGhe() {
        setBackground(Color.RED);
        setEnabled(false);
    }
}

// Main application class (public)
public class Ghe extends JFrame {
    private final JLabel statusLabel = new JLabel("Ghe da chon: 0");
    private final List<SeatButtonView> danhSachGhe = new ArrayList<>();

    public Ghe() {
        setTitle("Dat Ghe Rap Chieu Phim - Observer Pattern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 470);
        setLocationRelativeTo(null);

        JPanel seatPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        for (char row = 'A'; row <= 'D'; row++) {
            for (int col = 1; col <= 4; col++) {
                String maGhe = row + String.valueOf(col);
                GheModel ghe = new GheModel(maGhe);
                SeatButtonView btn = new SeatButtonView(ghe, statusLabel, danhSachGhe);
                danhSachGhe.add(btn);
                seatPanel.add(btn);
            }
        }

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.WEST);

        JButton confirmButton = new JButton("Dat ghe");
        confirmButton.addActionListener(e -> {
            long soGheDangChon = danhSachGhe.stream().filter(SeatButtonView::isDangChon).count();

            if (soGheDangChon == 0) {
                JOptionPane.showMessageDialog(this, "Ban chua chon ghe nao.", "Thong bao", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Ban co chac muon dat " + soGheDangChon + " ghe?",
                    "Xac nhan dat ghe", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                for (SeatButtonView btn : danhSachGhe) {
                    if (btn.isDangChon()) {
                        btn.xacNhanDat();
                    }
                }
                statusLabel.setText("Ghe da chon: 0");
            }
        });

        bottomPanel.add(confirmButton, BorderLayout.EAST);

        add(taoBangChuThichMau(), BorderLayout.NORTH);
        add(seatPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel taoBangChuThichMau() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panel.add(taoMucChuThich("Trống", Color.LIGHT_GRAY));
        panel.add(taoMucChuThich("Đang chọn", Color.GREEN));
        panel.add(taoMucChuThich("Đã đặt", Color.RED));
        return panel;
    }

    private JPanel taoMucChuThich(String moTa, Color mau) {
        JPanel muc = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel mauLabel = new JLabel();
        mauLabel.setOpaque(true);
        mauLabel.setBackground(mau);
        mauLabel.setPreferredSize(new Dimension(20, 20));

        JLabel textLabel = new JLabel(moTa);
        muc.add(mauLabel);
        muc.add(textLabel);

        return muc;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ghe().setVisible(true));
    }
}
