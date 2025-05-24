package model;

import view.MainView;

public abstract class Ticket {
	protected String ticketID;
	protected String typeOfTicket;
	protected Phim phim = new Phim(ticketID, typeOfTicket, ticketID, ticketID, 0, 0);

	public Ticket() {
		super();
		typeOfTicket="";
		
	}
	public String displayInfor() {
		return "Tên phim: "+phim.getTenPhim()+"\t\t"+ "Hạng vé: "+ typeOfTicket;
	}
	public abstract double price();
}
