package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controller.MainController;

public class PageAdmin extends JFrame {
	private JMenuItem manageMoviesMenuItem;
	private JMenuItem revenueStatisticssMenuItem;
	private JMenuItem listfilmMenuItem;

	public PageAdmin() {
		setTitle("Hệ thống đặt vé phim");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Tạo menu bar
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Menu");
		manageMoviesMenuItem = new JMenuItem("Quản lý phim");
		revenueStatisticssMenuItem = new JMenuItem("Thống kê doanh thu");
		listfilmMenuItem = new JMenuItem("Danh sách phim");

		menu.add(manageMoviesMenuItem);
		menu.add(revenueStatisticssMenuItem);
		menu.add(listfilmMenuItem);
		menuBar.add(menu);

		setJMenuBar(menuBar);

		// Nội dung chính
		JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với trang quản lý!", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(welcomeLabel);
		
		// GẮN SỰ KIỆN MỞ GIAO DIỆN THỐNG KÊ
		revenueStatisticssMenuItem.addActionListener(e -> {
		    ViewThongKeDoanhThu view = new ViewThongKeDoanhThu();
		    view.setVisible(true);
		});
		
		manageMoviesMenuItem.addActionListener(e -> {
		    QuanLyPhimView view = new QuanLyPhimView();
		    view.setVisible(true);
		});
	}

	public JMenuItem getManageMoviesMenuItem() {
		return manageMoviesMenuItem;
	}

	public JMenuItem getRevenueStatisticssMenuItem() {
		return revenueStatisticssMenuItem;
	}
	public JMenuItem getListfilmMenuItem() {
		return listfilmMenuItem;
	}
	

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PageAdmin view = new PageAdmin();
				new PageAdmin();
				view.setVisible(true);
			}
		});
	}

}
