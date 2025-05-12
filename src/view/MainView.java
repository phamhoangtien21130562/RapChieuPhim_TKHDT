package view;

import java.awt.Font;
import javax.swing.*;

import controller.MainController;

public class MainView extends JFrame {
	private JMenuItem manageMoviesMenuItem;
	private JMenuItem bookTicketsMenuItem;
	private JMenuItem listfilmMenuItem;
	private JMenuItem viewHistoryMenuItem;
	private JMenuItem loginMenuItem;

	public MainView() {
		setTitle("Hệ thống đặt vé phim");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Tạo menu bar
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Menu");
		manageMoviesMenuItem = new JMenuItem("Quản lý phim");
		bookTicketsMenuItem = new JMenuItem("Đặt vé");
		listfilmMenuItem = new JMenuItem("Danh sách phim");
		viewHistoryMenuItem = new JMenuItem("Xem lịch sử đặt vé");
		loginMenuItem = new JMenuItem("Đăng nhập");

		menu.add(manageMoviesMenuItem);
		menu.add(bookTicketsMenuItem);
		menu.add(listfilmMenuItem);
		menu.add(viewHistoryMenuItem);
		menu.add(loginMenuItem);
		menuBar.add(menu);

		setJMenuBar(menuBar);

		// Nội dung chính
		JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với hệ thống đặt vé phim!", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(welcomeLabel);
	}

	public JMenuItem getManageMoviesMenuItem() {
		return manageMoviesMenuItem;
	}

	public JMenuItem getBookTicketsMenuItem() {
		return bookTicketsMenuItem;
	}

	public JMenuItem getListfilmMenuItem() {
		return listfilmMenuItem;
	}

	public JMenuItem getViewHistoryMenuItem() {
		return viewHistoryMenuItem;
	}

	public JMenuItem getLoginMenuItem() {
		return loginMenuItem;
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainView view = new MainView();
				new MainController(view);
				view.setVisible(true);
			}
		});
	}
}
