package RapChieuPhim;

import javax.swing.text.View;

public class TicketMain {
	public static void main(String[] args) {
		Phim phim = new Phim("Lật mặt 8: Vòng tay nắng", "9:00 09/05/2025", "01", "D3");
		Ticket ticket1 = new Softdrink(new RegularCorn(new HSSV_OldPersonTicket()));
		System.out.println(ticket1.displayInfor());
		System.out.println(ticket1.price());
		ViewDecorator test = new ViewDecorator();
		
	}

}
