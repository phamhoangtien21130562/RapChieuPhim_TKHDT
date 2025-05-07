package model;

import java.util.List;

public interface Observer {
	public void capnhat(String thongbao);

	public void capNhat(String thongbao, List<Phim> phims);
	

}
