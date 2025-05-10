package RapChieuPhim;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewDecorator extends JFrame{
	//Khai bao cac bien
	private JPanel panel_infor, panel_combo, panel_rice_button, panel_center, panel_button2, panel_button1_;
	private JLabel lable_name, lable_performance, label_room, lable_seat, lable_rice;
	private JButton button_continue, button_back, button_remove, button_complete;
	private JRadioButton rd_adult, rd_student_OldPerson;
	private ButtonGroup group;
	private JCheckBox cb_corn_caramel, cb_corn_regular, cb_corn_cheses, cb_softdrink;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int stt;
	private Ticket ticket = new AdultTicket();
	
	public ViewDecorator() {
		setTitle("Decorator");
		setSize(480, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Thong tin ve
		panel_infor = new JPanel();
		panel_infor.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));
		panel_infor.setLayout(new GridLayout(3,2));
		
		lable_name = new JLabel("Tên phim: "+ticket.phim.getName());
		lable_performance = new JLabel("Suất: "+ticket.phim.getPerformance());
		label_room = new JLabel("Phòng chiếu: "+ticket.phim.getRoomID());
		lable_seat = new JLabel("Ghế: "+ ticket.phim.getSeatID());
		
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
		button_remove = new JButton("Xóa");
		// Xử lý sự kiện cho việc chọn combo
		button_remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton src = (JButton) e.getSource();
				if(src==button_remove) {
					int index = table.getSelectedRow();
					model.removeRow(index);
					--stt;
					table.setModel(model);
					table.revalidate();
				}
			}
		});
		button_complete = new JButton("Hoàn thành");
		button_complete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rd_adult.isSelected()) {
			      ticket = new AdultTicket();
				}else {
					if(rd_student_OldPerson.isSelected()) {
						ticket = new HSSV_OldPersonTicket();
					}
				}
	                model.setRowCount(0);
	                stt = 0;

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
	                lable_rice.setText("Tổng đơn hàng: " + ticket.price() + " đồng");
	                JOptionPane.showMessageDialog(null, "Chi tiết: " + ticket.displayInfor());
	            }
	        });
		panel_button1_.add(button_remove);
		panel_button1_.add(button_complete);
		
		panel_center.add(panel_combo,BorderLayout.NORTH);
		panel_center.add(scroll, BorderLayout.CENTER);
		panel_center.add(panel_button1_,BorderLayout.SOUTH);
		
		// Thao tác
		panel_rice_button = new JPanel();
		panel_rice_button.setBorder(BorderFactory.createTitledBorder("Thao tác"));
		panel_rice_button.setLayout(new BorderLayout());
		
		lable_rice = new JLabel("Tổng đơn hàng", JLabel.LEFT);
		button_back = new JButton("Quay lại");
		button_continue = new JButton("Tiếp tục");
		
		panel_rice_button.add(lable_rice, BorderLayout.WEST);
		panel_button2 = new JPanel();
		panel_button2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel_button2.add(button_back);
		panel_button2.add(button_continue);
		panel_rice_button.add(panel_button2,BorderLayout.EAST);
		
		
		
		setLayout(new BorderLayout());
		getContentPane().add(panel_infor,BorderLayout.NORTH);
		getContentPane().add(panel_center,BorderLayout.CENTER);
		getContentPane().add(panel_rice_button,BorderLayout.SOUTH);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new ViewDecorator();
	}

}
