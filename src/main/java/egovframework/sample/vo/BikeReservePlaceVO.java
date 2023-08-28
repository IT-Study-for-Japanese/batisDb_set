package egovframework.sample.vo;

public class BikeReservePlaceVO {
	
	private int reservePlaceId; //장소고유번호
	private String reservePlaceName; //장소명
	private String reservePlaceAddr; //주소
	private int count; //대여가능 자전거수 
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getReservePlaceId() {
		return reservePlaceId;
	}
	public void setReservePlaceId(int reservePlaceId) {
		this.reservePlaceId = reservePlaceId;
	}
	public String getReservePlaceName() {
		return reservePlaceName;
	}
	public void setReservePlaceName(String reservePlaceName) {
		this.reservePlaceName = reservePlaceName;
	}
	public String getReservePlaceAddr() {
		return reservePlaceAddr;
	}
	public void setReservePlaceAddr(String reservePlaceAddr) {
		this.reservePlaceAddr = reservePlaceAddr;
	}
	
	@Override
	public String toString() {
		return "BkieReservePlace [reservePlaceId=" + reservePlaceId + ", reservePlaceName=" + reservePlaceName
				+ ", reservePlaceAddr=" + reservePlaceAddr + "]";
	}
	
	public BikeReservePlaceVO(int reservePlaceId, String reservePlaceName, String reservePlaceAddr) {
		super();
		this.reservePlaceId = reservePlaceId;
		this.reservePlaceName = reservePlaceName;
		this.reservePlaceAddr = reservePlaceAddr;
	}
	
	public BikeReservePlaceVO() {
		super();
	}
	
}
