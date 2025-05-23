package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class Ghe extends JFrame {
    private final int rows = 8; // a - h (theo chiều dọc)
    private final int cols = 5; // 1 - 5 (theo chiều ngang)
    private final Set<JButton> selectedSeats = new HashSet<>();
    private final JLabel statusLabel = new JLabel("Ghe da chon: 0");

    public Ghe() {
        setTitle("Chon Ghe Rap Chieu Phim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // Panel chú thích
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        legendPanel.add(createLegendLabel("Chua chon", Color.LIGHT_GRAY));
        legendPanel.add(createLegendLabel("Da chon", Color.GREEN));
        legendPanel.add(createLegendLabel("Da dat", Color.RED));
        add(legendPanel, BorderLayout.NORTH);

        // Tạo panel chứa ghế
        JPanel seatPanel = new JPanel(new GridLayout(rows + 1, cols + 1, 5, 5)); // GridLayout với khoảng cách giữa các ô

        seatPanel.add(new JLabel("")); // Thêm một ô trống ở góc trên trái

        // Cột: 1 - 5 (ngang)
        for (int col = 1; col <= cols; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col), SwingConstants.CENTER);
            seatPanel.add(colLabel);
        }

        // Hàng: a - h (dọc)
        for (int row = 0; row < rows; row++) {
            char rowChar = (char) ('a' + row);
            JLabel rowLabel = new JLabel(String.valueOf(rowChar), SwingConstants.CENTER);
            seatPanel.add(rowLabel); // Thêm label hàng bên trái

            for (int col = 1; col <= cols; col++) {
                String seatLabel = rowChar + String.valueOf(col); // Ví dụ: a1, b2, ...
                JButton seatButton = new JButton(seatLabel);
                seatButton.setBackground(Color.LIGHT_GRAY);
                seatButton.setFocusPainted(false);

                // Đặt kích thước cố định cho nút ghế (ô vuông)
                seatButton.setPreferredSize(new Dimension(40, 40));

                seatButton.addActionListener(e -> {
                    if (seatButton.getBackground().equals(Color.RED)) {
                        return; // Ghế đã đặt
                    }
                    if (selectedSeats.contains(seatButton)) {
                        selectedSeats.remove(seatButton);
                        seatButton.setBackground(Color.LIGHT_GRAY);
                    } else {
                        selectedSeats.add(seatButton);
                        seatButton.setBackground(Color.GREEN);
                    }
                    statusLabel.setText("Ghe da chon: " + selectedSeats.size());
                });

                seatPanel.add(seatButton); // Thêm ghế vào panel
            }
        }

        // Panel dưới
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.WEST);

        JButton confirmButton = new JButton("Dat ghe");
        confirmButton.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ban chua chon ghe nao.", "Thong bao", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Ban co chac muon dat " + selectedSeats.size() + " ghe?",
                    "Xac nhan dat ghe", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                for (JButton seat : selectedSeats) {
                    seat.setBackground(Color.RED);
                    seat.setEnabled(false);
                }
                selectedSeats.clear();
                statusLabel.setText("Ghe da chon: 0");
            }
        });

        bottomPanel.add(confirmButton, BorderLayout.EAST);

        add(seatPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Tạo chú thích màu
    private JLabel createLegendLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(color);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setPreferredSize(new Dimension(80, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ghe app = new Ghe();
            app.setVisible(true);
        });
    }
}

