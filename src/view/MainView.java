package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.DSPhimController;
import controller.MainController;
import controller.PaymentController;
import model.AdultTicket;
import model.BasicTicket;
import model.CaramelCorn;
import model.ChesesCorn;
import model.HSSV_OldPersonTicket;
import model.Phim;
import model.RegularCorn;
import model.Softdrink;
import model.Ticket;

public class MainView extends JFrame {
	private JMenuItem manageMoviesMenuItem;
	private JMenuItem bookTicketsMenuItem;
	private JMenuItem listfilmMenuItem;
	private JMenuItem viewHistoryMenuItem;
	private JMenuItem loginMenuItem;
	
	//Truong
	private JTable tablee;
	private DefaultTableModel tableModel;
	private JButton button_cont;
	private JPanel pane_DSP, panel_but, panel_chung;
	private JButton button_cancel;
	
	//Khanh
    private final int rows = 8; // a - h (theo chiều dọc)
    private final int cols = 5; // 1 - 5 (theo chiều ngang)
    private final Set<JButton> selectedSeats = new HashSet<>();
    private final JLabel statusLabel = new JLabel("Ghe da chon: 0");
    private JPanel panel_ghe, pan_but;
    private JButton button_conti, button_qlai;
    private List<String> seatList = new ArrayList<String>();
    private JPanel panel_chung1, panel_chung2;
	
    //An
	private JPanel panel_infor, panel_combo, panel_rice_button, panel_center, panel_button2, panel_button1_ ,panel_card, card_common,
	card_home, card_menu_dsp, card_menu_;
	private JLabel lable_name, lable_performance, label_room, lable_seat, lable_rice;
	private JButton button_continue, button_back, button_remove, button_complete;
	private JRadioButton rd_adult, rd_student_OldPerson;
	private ButtonGroup group;
	private JCheckBox cb_corn_caramel, cb_corn_regular, cb_corn_cheses, cb_softdrink;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int stt;
//	private Ticket ticket = new AdultTicket();
	
	
	//phuoc
	private PaymentController paymentController;
	

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
	//	listfilmMenuItem = new JMenuItem("Danh sách phim");
		viewHistoryMenuItem = new JMenuItem("Xem lịch sử đặt vé");
		loginMenuItem = new JMenuItem("Đăng nhập");

		menu.add(manageMoviesMenuItem);
		menu.add(bookTicketsMenuItem);
	//	menu.add(listfilmMenuItem);
		menu.add(viewHistoryMenuItem);
		menu.add(loginMenuItem);
		menuBar.add(menu);
		panel_chung1 = new JPanel();
		
		
		
		
		// Danh sach phim-Truong
			pane_DSP = new JPanel();
			pane_DSP.setLayout(new BorderLayout());
		   String[] columnNames = {"Tên phim", "Thể loại","Đạo diễn","Năm SX", "Thời lượng phim", "Trạng thái"};
	        tableModel = new DefaultTableModel(columnNames, 0);
	        table = new JTable(tableModel);
	        table.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		int selecteRow = table.getSelectedRow();
	        		if(selecteRow!=-1) {
	        			String name = (String) model.getValueAt(selecteRow, 0).toString();
	        			lable_name.setText("Tên phim: "+name);
	        		}
	        	}
			});

	        JScrollPane scrollPane = new JScrollPane(table);
	        
	        panel_but = new JPanel();
	        panel_but.setLayout(new BorderLayout());
	        panel_chung = new JPanel();
	        panel_chung.setLayout(new BorderLayout());
	        panel_chung.add(scrollPane, BorderLayout.CENTER);
	        button_cont = new JButton("Chọn xong");
	        button_cancel = new JButton("Hủy");
	        panel_but.add(button_cancel, BorderLayout.WEST);
	        panel_but.add(button_cont, BorderLayout.EAST);
	        
	        
	        pane_DSP.add(panel_chung,BorderLayout.CENTER);
	        pane_DSP.add(panel_but, BorderLayout.SOUTH);
	        
	      
	        
	        
	        //Ghe-Khanh
	        // Panel chú thích
	        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
	        legendPanel.add(createLegendLabel("Chua chon", Color.LIGHT_GRAY));
	        legendPanel.add(createLegendLabel("Da chon", Color.GREEN));
	        legendPanel.add(createLegendLabel("Da dat", Color.RED));
	       // add(legendPanel, BorderLayout.NORTH);

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
	             String    seatLable = rowChar + String.valueOf(col); // Ví dụ: a1, b2, ...
	                JButton seatButton = new JButton(seatLable);
	                seatButton.setBackground(Color.LIGHT_GRAY);
	                seatButton.setFocusPainted(false);

	                // Đặt kích thước cố định cho nút ghế (ô vuông)
	                seatButton.setPreferredSize(new Dimension(40, 40));

	                seatButton.addActionListener(e -> {
	                    if (seatButton.getBackground().equals(Color.RED)) {
	                    	//seatList.add(seatButton);
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
	        pan_but = new JPanel();
	        button_conti = new JButton("Tiếp tục");
	        button_qlai = new JButton("Quay lại chọn phim");
	        pan_but.add(confirmButton);
	        pan_but.add(button_qlai);
	        pan_but.add(button_conti);
	        button_conti.addActionListener(new handleSeat());
	        bottomPanel.add(pan_but, BorderLayout.EAST);

	        panel_ghe = new JPanel();
	        panel_ghe.setLayout(new BorderLayout());
	        panel_ghe.add(legendPanel, BorderLayout.NORTH);
	        panel_ghe.add(seatPanel, BorderLayout.CENTER);
	        panel_ghe.add(bottomPanel,BorderLayout.SOUTH);
	        
		
		
	        
	        
	        
	        //Thong tin ve-An
				panel_infor = new JPanel();
				panel_infor.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));
				panel_infor.setLayout(new GridLayout(3,2));
				
				lable_name = new JLabel("Tên phim: ");
				lable_performance = new JLabel("Suất: ");
				label_room = new JLabel("Phòng chiếu: ");
				lable_seat = new JLabel("Ghế: ");
				
				panel_infor.add(lable_name);panel_infor.add(label_room);
				panel_infor.add(lable_performance);panel_infor.add(lable_seat);
				
				rd_adult= new JRadioButton("Người lớn");
				rd_student_OldPerson = new JRadioButton("Hssv-Người cao tuổi");
				group = new ButtonGroup();
				group.add(rd_adult);group.add(rd_student_OldPerson);
				
				panel_infor.add(rd_adult);
				panel_infor.add(rd_student_OldPerson);
				//Thong tin combo
				panel_center = new JPanel();
				panel_center.setBorder(BorderFactory.createTitledBorder("Combo"));
				panel_center.setLayout(new BorderLayout());
				panel_combo = new JPanel();
				panel_combo.setLayout(new GridLayout(3,3));
				
				cb_corn_regular = new JCheckBox("Bắp thường(40000)");
				cb_corn_caramel = new JCheckBox("Bắp caramel(45000)");
				cb_corn_cheses = new JCheckBox("Bắp phô mai(60000)");
				cb_softdrink = new JCheckBox("Nước ngọt(28000)");
				
				panel_combo.add(cb_corn_regular);panel_combo.add(cb_softdrink);
				panel_combo.add(cb_corn_caramel);panel_combo.add(cb_corn_cheses);
				
				
				model = new DefaultTableModel();
				model.addColumn("STT");
				model.addColumn("Tên sản phẩm");
				model.addColumn("Đơn giá");
				table = new JTable(model);
				scroll = new JScrollPane(table);
				
				panel_button1_ = new JPanel();
				button_complete = new JButton("Hoàn thành");
				button_complete.addActionListener(new ActionListener() {
					// Tao ra loai ve trung gian de cac decor phủ lên và tách gái vé riêng ra nhằm tránh các vé bị phủ lên = với số lượng vé.
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String textGhe = lable_seat.getText();
						textGhe = textGhe.replace("Ghế: ", "").trim();
						String[] gheArray = textGhe.split(",");
						int soLuongVe = gheArray.length;
						
						int giaVe =0;
						if(rd_adult.isSelected()) {
							
					      giaVe = (int) new AdultTicket().price();
						}else {
							if(rd_student_OldPerson.isSelected()) {
								giaVe = (int) new HSSV_OldPersonTicket().price();
							}
						}
						int tongGiaVe = giaVe*soLuongVe;
			                model.setRowCount(0);
			                stt = 0;
			                
			                Ticket ticket = new BasicTicket();
			                if (cb_corn_regular.isSelected()) {
			                    ticket = new RegularCorn(ticket);
			                    model.addRow(new Object[]{++stt, "Bắp thường", 40000});
			                }
			                if (cb_corn_caramel.isSelected()) {
			                    ticket = new CaramelCorn(ticket);
			                    model.addRow(new Object[]{++stt, "Bắp caramel", 45000});
			                }
			                if (cb_corn_cheses.isSelected()) {
			                    ticket = new ChesesCorn(ticket);
			                    model.addRow(new Object[]{++stt, "Bắp phô mai", 60000});
			                }
			                if (cb_softdrink.isSelected()) {
			                    ticket = new Softdrink(ticket);
			                    model.addRow(new Object[]{++stt, "Nước ngọt", 28000});
			                }
			                int tongCombo = (int) ticket.price();
			                int tongTien = tongGiaVe+tongCombo;
			                lable_rice.setText("Tổng đơn hàng: " + tongTien + " đồng");
			              //  JOptionPane.showMessageDialog(null, "Chi tiết: " + ticket.displayInfor());
			            }
			        });
			//	panel_button1_.add(button_remove);
				panel_button1_.add(button_complete);
				
				panel_center.add(panel_combo,BorderLayout.NORTH);
				panel_center.add(scroll, BorderLayout.CENTER);
				panel_center.add(panel_button1_,BorderLayout.SOUTH);
				
				// Thao tác
				panel_rice_button = new JPanel();
				panel_rice_button.setBorder(BorderFactory.createTitledBorder("Thao tác"));
				panel_rice_button.setLayout(new BorderLayout());
				
				lable_rice = new JLabel("Tổng đơn hàng", JLabel.LEFT);
				button_back = new JButton("Quay lại chọn ghế");
				button_continue = new JButton("Tiếp tục");
				
				panel_rice_button.add(lable_rice, BorderLayout.WEST);
				panel_button2 = new JPanel();
				panel_button2.setLayout(new FlowLayout(FlowLayout.RIGHT));
				panel_button2.add(button_back);
				panel_button2.add(button_continue);
				
				
				//phuoc  them thong tin 
				button_continue.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        // Get thông tin từ UI hiện tại
				        String movieName = lable_name.getText().replace("Tên phim: ", "");
				        String room = label_room.getText().replace("Phòng: ", "");
				        String seats = lable_seat.getText().replace("Ghế: ", "");
				        
				        // Determine ticket type
				        String ticketType = "";
				        if (rd_adult.isSelected()) {
				            ticketType = "Người lớn";
				        } else if (rd_student_OldPerson.isSelected()) {
				            ticketType = "Học sinh - Người cao tuổi";
				        }
				        
				        // Get combo details từ table
				        StringBuilder comboDetails = new StringBuilder();
				        for (int i = 0; i < model.getRowCount(); i++) {
				            String productName = (String) model.getValueAt(i, 1);
				            int price = (Integer) model.getValueAt(i, 2);
				            comboDetails.append(productName).append(" - ").append(price).append(" VNĐ\n");
				        }
				        
				        // Get total amount từ label
				        String totalText = lable_rice.getText().replace("Tổng đơn hàng: ", "").replace(" đồng", "");
				        double totalAmount = 0;
				        try {
				            totalAmount = Double.parseDouble(totalText);
				        } catch (NumberFormatException ex) {
				            totalAmount = 0;
				        }
				        
				        // Set default data nếu chưa có
				        if (movieName.isEmpty() || movieName.equals("Tên phim: ")) {
				            movieName = "Avengers: Endgame"; // Default movie
				            lable_name.setText("Tên phim: " + movieName);
				        }
				        
				        if (seats.isEmpty() || seats.equals("Ghế: ")) {
				            seats = "A1, A2"; // Default seats if none selected
				            lable_seat.setText("Ghế: " + seats);
				        }
				        
				        if (ticketType.isEmpty()) {
				            ticketType = "Người lớn"; // Default ticket type
				            rd_adult.setSelected(true);
				        }
				        
				        if (room.isEmpty() || room.equals("Phòng: ")) {
				            room = "Phòng 1"; // Default room
				            label_room.setText(room);
				        }
				        
				        // Nếu chưa có combo nào, tính lại tổng tiền chỉ với vé
				        if (totalAmount == 0) {
				            int ticketPrice = ticketType.equals("Người lớn") ? 60000 : 45000;
				            String[] seatArray = seats.split(",");
				            totalAmount = ticketPrice * seatArray.length;
				            lable_rice.setText("Tổng đơn hàng: " + (int)totalAmount + " đồng");
				        }
				        
				        // Tạo PaymentController và chuyển sang màn hình thanh toán
				        paymentController = new PaymentController(
				            movieName, room, seats, ticketType, 
				            comboDetails.toString(), totalAmount
				        );
				        
				        // Đóng cửa sổ hiện tại
				        dispose();
				    }
				});
				panel_rice_button.add(panel_button2,BorderLayout.EAST);
				
				panel_card = new JPanel();
				panel_card.setLayout(new BorderLayout());
				panel_card.add(panel_infor,BorderLayout.NORTH);
				panel_card.add(panel_center,BorderLayout.CENTER);
				panel_card.add(panel_rice_button,BorderLayout.SOUTH);
				
				// Nội dung chính
				JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với hệ thống đặt vé phim!", SwingConstants.CENTER);
				welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
				card_home = new JPanel();
				card_home.add(welcomeLabel, BorderLayout.CENTER);
				
				CardLayout card = new CardLayout();
				card_common = new JPanel(card);
				card_common.add("Menu",card_home);
				card_common.add("Đặt vé", pane_DSP);
				card_common.add("Chọn xong",panel_ghe);
				card_common.add("Tiếp tục", panel_card);
				bookTicketsMenuItem.addActionListener(e-> card.show(card_common	, "Đặt vé"));
				button_cont.addActionListener(e->card.show(card_common, "Chọn xong"));
				button_qlai.addActionListener(e-> card.show(card_common, "Đặt vé"));
				button_conti.addActionListener(e->card.show(card_common, "Tiếp tục"));
				button_back.addActionListener(e-> card.show(card_common, "Chọn xong"));
				button_cancel.addActionListener(e-> card.show(card_common, "Menu"));
			
				
				getContentPane().add(card_common);
				
				//tạo dữ liệu mẫu phước
//				lable_name.setText("Tên phim: Avengers: Endgame");
//				label_room.setText("Phòng: 1");
//				lable_seat.setText("Ghế: A1, A2");
//				rd_adult.setSelected(true);
//				lable_rice.setText("Tổng đơn hàng: 120000 đồng");
				setVisible(true);

		setJMenuBar(menuBar);


	}
	 public void themPhimVaoTable(Phim phim) {
	        Object[] rowData = {
	            phim.getTenPhim(),
	            phim.getTheloai(),
	            phim.getDirector(),
	            phim.getReleaseYear(),
	            phim.getDuration() + " phút",
	            phim.getTrangthai(),
	        };
	        tableModel.addRow(rowData);
	    }
	 private JLabel createLegendLabel(String text, Color color) {
	        JLabel label = new JLabel(text);
	        label.setOpaque(true);
	        label.setBackground(color);
	        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        label.setPreferredSize(new Dimension(80, 25));
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        return label;
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
	
	//phuoc
	// Getter và setter methods cho PaymentController
    public JLabel getMovieNameLabel() { return lable_name; }
    public JLabel getRoomLabel() { return label_room; }
    public JLabel getSeatLabel() { return lable_seat; }
    public JLabel getTotalLabel() { return lable_rice; }
    public JRadioButton getAdultRadio() { return rd_adult; }
    public JRadioButton getStudentRadio() { return rd_student_OldPerson; }
    public DefaultTableModel getComboTableModel() { return model; }
    
    // Method để restore data
    public void restoreData(String movieName, String room, String seats, 
                           String ticketType, String comboDetails, double totalAmount) {
        lable_name.setText("Tên phim: " + movieName);
        label_room.setText("Phòng: " + room);
        lable_seat.setText("Ghế: " + seats);
        lable_rice.setText("Tổng đơn hàng: " + (int)totalAmount + " đồng");
        
        if (ticketType.equals("Người lớn")) {
            rd_adult.setSelected(true);
        } else {
            rd_student_OldPerson.setSelected(true);
        }
        
        // Clear và restore combo table
        model.setRowCount(0);
        if (!comboDetails.trim().isEmpty()) {
            String[] combos = comboDetails.split("\n");
            int stt = 0;
            for (String combo : combos) {
                if (!combo.trim().isEmpty()) {
                    String[] parts = combo.split(" - ");
                    if (parts.length == 2) {
                        String productName = parts[0];
                        String priceStr = parts[1].replace(" VNĐ", "");
                        try {
                            int price = Integer.parseInt(priceStr);
                            model.addRow(new Object[]{++stt, productName, price});
                        } catch (NumberFormatException ex) {
                            // Ignore invalid price
                        }
                    }
                }
            }
        }
    }
    
    // Method để chuyển đến màn hình combo
    public void showComboScreen() {
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) card_common.getLayout();
        cardLayout.show(card_common, "Tiếp tục");
    }
    
	private class handleSeat implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton src = (JButton) e.getSource();
			if(src==button_conti) {
				for (JButton jButton : selectedSeats) {
					seatList.add(jButton.getText());
				}
				String seat =String.join(",", seatList);
				lable_seat.setText("Ghế: "+seat);
				Random ran = new Random();
				int numRoom = ran.nextInt(5)+1;
				label_room.setText("Phòng: "+numRoom);
				
		}
		
	}
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
