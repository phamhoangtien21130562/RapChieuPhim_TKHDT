package RapChieuPhim;

public abstract class Ticket {
	protected String ticketID;
	protected String typeOfTicket;
	protected Phim phim = new Phim("Lật mặt 8: Vòng tay nắng", "9:00 09/05/2025", "Phòng 1", "D3");

	public Ticket() {
		super();
		typeOfTicket="";
		
	}
	public String displayInfor() {
		return "Tên phim: "+phim.getName()+"\t\t"+"Phòng: "+phim.getRoomID()+"\n"+
				"Hạng vé: "+ typeOfTicket+"\t\t"+"Ghế: "+phim.getSeatID()+"\n"
				+"Suất: "+phim.getPerformance();
//		System.out.println("Tên phim: "+phim.name+"\t\t"+"Phòng: "+roomID);
//		System.out.println("Hạng vé: "+ typeOfTicket+"\t\t"+"Ghế: "+seatID);
//		System.out.println("Suất: "+performance);
	}
	public abstract double price();
	
	
}
