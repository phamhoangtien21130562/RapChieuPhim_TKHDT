package model;

import java.util.ArrayList;
import java.util.List;

public class QuanLyPhim implements Subject  {
	private List<Phim> phims;
	private List<DanhSachPhim> danhsachPhim;
	private List<Observer> observers;
	
	public QuanLyPhim() {
		super();
		this.phims = new ArrayList<>();
		this.danhsachPhim = new ArrayList<>();
		this.observers = new ArrayList<>();
	}
	



	public List<Phim> getPhims() {
		return phims;
	}

	public void setPhims(List<Phim> phims) {
		this.phims = phims;
	}

	public List<DanhSachPhim> getDanhsachPhim() {
		return danhsachPhim;
	}

	public void setDanhsachPhim(List<DanhSachPhim> danhsachPhim) {
		this.danhsachPhim = danhsachPhim;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
	@Override
	public void dangKyObserver(Observer obs) {
		 if (!observers.contains(obs)) {
	            observers.add(obs);
	        }
		
	}

	@Override
	public void huyObserver(Observer obs) {
		observers.remove(obs);
		
	}

	
	@Override
	public void thongBaoCapNhat(String thongbao) {
		for (Observer obs : observers) {
            obs.capNhat(thongbao, phims);
        }		
	}
	
	
	  public void themPhim(Phim phim) {
	        phims.add(phim);
	        thongBaoCapNhat("Đã thêm phim: " + phim.getTenPhim());
	    }
//
//	    public void xoaPhim(Phim phims) {
////	        dsPhim.xoaPhim(phim);
//	        phims.remove(phim);
//	        thongBaoChoObserver("Đã xóa phim: " + phim.getTenPhim());
//	    }
//
//	    public void capNhatTrangThai(Phim phims, String trangThaiMoi) {
//	        phims.setTrangThai(trangThaiMoi);
//	        thongBaoChoObserver("Phim " + phim.getTenPhim() + " cập nhật trạng thái: " + trangThaiMoi);
//	    }

	

	
	

}
