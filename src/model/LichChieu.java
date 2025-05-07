package model;

import java.util.Date;

public class LichChieu {
	public Date ngayChieu; 
	public PhongChieu phongChieu;
	public LichChieu() {
		super();
	}
	public LichChieu(Date ngayChieu, PhongChieu phongChieu) {
		super();
		this.ngayChieu = ngayChieu;
		this.phongChieu = phongChieu;
	}
	@Override
	public String toString() {
		return "Ngay chieu: " + ngayChieu + ", phong chieu: " + phongChieu;
	}
	public Date getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public PhongChieu getPhongChieu() {
		return phongChieu;
	}
	public void setPhongChieu(PhongChieu phongChieu) {
		this.phongChieu = phongChieu;
	}
	
	
	
}
