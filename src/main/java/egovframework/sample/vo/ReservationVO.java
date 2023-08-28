package egovframework.sample.vo;

import java.util.Date;

public class ReservationVO {
	
	private int reserveId; //예약ID
	private String userId; //유저ID
	private int bikeId; //자전거ID
	private Date startTime; //예약한시간
	private Date period; //이용시간
	private String textPeriod; //변환전이용시간
	
	private int bikeReservePlaceId;//자전거대여소ID
	
	
	public String getTextPeriod() {
		return textPeriod;
	}
	public void setTextPeriod(String textPeriod) {
		this.textPeriod = textPeriod;
	}
	public int getBikeReservePlaceId() {
		return bikeReservePlaceId;
	}
	public void setBikeReservePlaceId(int bikeReservePlaceId) {
		this.bikeReservePlaceId = bikeReservePlaceId;
	}
	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBikeId() {
		return bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
	}
	

	
	
}
