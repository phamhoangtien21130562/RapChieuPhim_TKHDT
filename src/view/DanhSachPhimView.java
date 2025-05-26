package view;

import model.Observer;
import model.Phim;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class DanhSachPhimView extends JFrame implements Observer {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnKiemTraChon;

    public DanhSachPhimView() {
        setTitle("Danh sách phim");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columns = {"Chọn", "Tên phim", "Thể loại", "Đạo diễn", "Năm SX", "Thời lượng", "Lịch chiếu", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép cột "Chọn" (cột 0) được chỉnh sửa (checkbox)
                return column == 0;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;  // cột checkbox
                }
                return String.class;  // các cột còn lại là String
            }
        };
        table = new JTable(tableModel);
        btnKiemTraChon = new JButton("Chọn xong");

        // Panel chứa bảng và nút
       JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(btnKiemTraChon, BorderLayout.SOUTH);

        add(panel);
       

//        //đăng ký sự kiện cho nút kiểm tra
//        btnKiemTraChon.addActionListener(e -> {
//            StringBuilder sb = new StringBuilder("Dòng được chọn:\n");
//            for (int i = 0; i < tableModel.getRowCount(); i++) {
//                Boolean checked = (Boolean) tableModel.getValueAt(i, 0);
//                if (checked != null && checked) {
//                    sb.append(" - ").append(tableModel.getValueAt(i, 1)).append(": ")  // ID phim
//                      .append(tableModel.getValueAt(i, 2)).append("\n");  // Tên phim
//                }
//            }
//            JOptionPane.showMessageDialog(this, sb.toString());
//        });
    }

    @Override
    public void capNhatDanhSachPhim(List<Phim> danhSachPhim) {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        for (Phim p : danhSachPhim) {
            Object[] rowData = {
                false,
                p.getTenPhim(),
                p.getTheloai(),
                p.getDirector(),
                p.getReleaseYear(),
                p.getDuration(),
                p.getLichChieu(),
                p.getTrangthai()
              
            };
            tableModel.addRow(rowData);
        }
    }

    @Override
    public void capNhat(String thongbao, List<Phim> phims) {
        // Có thể cập nhật thêm khi cần
    }
}
