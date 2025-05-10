package RapChieuPhim;

public class Phim {
	private String name;
	private  String performance;
	private  String roomID;
	private String seatID;
	public Phim(String name, String performance, String roomID, String seatID) {
		super();
		this.name = name;
		this.performance = performance;
		this.roomID = roomID;
		this.seatID = seatID;
	}
	
	public Phim() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	
	

}
