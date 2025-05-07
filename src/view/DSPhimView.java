package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Phim;

public class DSPhimView extends JFrame {
	private JTable table;
	private DefaultTableModel tableModel;
	
	public DSPhimView() {
		setTitle("Danh sách phim");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        String[] columnNames = {"Tên phim", "Thể loại","Đạo diễn","Năm SX", "Thời lượng phim", "Trạng thái"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);




        getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	 public void themPhimVaoTable(Phim phim) {
	        Object[] rowData = {
	            phim.getTenPhim(),
	            phim.getTheloai(),
	            phim.getDirector(),
	            phim.getReleaseYear(),
	            phim.getDuration() + " phút",
	            phim.getTrangthai()
	        };
	        tableModel.addRow(rowData);
	    }

}
