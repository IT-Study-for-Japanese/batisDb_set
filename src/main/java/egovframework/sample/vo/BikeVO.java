package egovframework.sample.vo;

public class BikeVO {
	
	private int bikeId; //자전거 ID
	private int bikeReservePlaceId; // 자전거대여소 ID
	private	boolean bikeStatus; //자전거 대여가능여부 True면 가능, False는 불가능
	
	public int getBikeId() {
		return bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	public int getBikeReservePlaceId() {
		return bikeReservePlaceId;
	}
	public void setBikeReservePlaceId(int bikeReservePlaceId) {
		this.bikeReservePlaceId = bikeReservePlaceId;
	}
	public boolean isBikeStatus() {
		return bikeStatus;
	}
	public void setBikeStatus(boolean bikeStatus) {
		this.bikeStatus = bikeStatus;
	}

}
