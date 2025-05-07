package model;

import java.util.ArrayList;
import java.util.List;

public class Phim {
	private String tenPhim;
	private String theloai;
	private String director;
	private int releaseYear;
	private int duration;
	private String trangthai;
	private List<Observer> observers;
	private LichChieu lichChieu;
	private Phim() {
		super();
	}
	public Phim(String tenPhim, String theloai, String director, int releaseYear, int duration) {
		super();
		this.tenPhim = tenPhim;
		this.theloai = theloai;
		this.director = director;
		this.releaseYear = releaseYear;
		this.duration = duration;
		this.trangthai = "Chua chieu";
		this.observers = new ArrayList<>();
		this.lichChieu = new LichChieu();
	}
	
	@Override
	public String toString() {
		return "Ten Phim: " + tenPhim + ", The loai: " + theloai + ", Director: " + director + "\n" + "ReleaseYear: "
				+ releaseYear + ", Duration: " + duration + ", Trang thai: " + trangthai + "\n" + "Lich Chieu: " + lichChieu;
	}
	public String getTenPhim() {
		return tenPhim;
	}
	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}
	public String getTheloai() {
		return theloai;
	}
	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTrangthai() {
		return trangthai;
	}
	  public void setTrangthai(String trangthai) {
	        this.trangthai = trangthai;
//	        thongBaoChoObserver("Phim '" + tenPhim + "' cập nhật trạng thái: " + trangthai);
	    }
	public List<Observer> getObservers() {
		return observers;
	}
	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
	public LichChieu getLichChieu() {
		return lichChieu;
	}
	public void setLichChieu(LichChieu lichChieu) {
		this.lichChieu = lichChieu;
	}
	
	
	

}
