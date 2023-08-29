package egovframework.sample.service;

import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;

import java.util.List;

public interface SampleDAO {
	
	
	List<BikeReservePlaceVO> selectBikePlace() throws Exception; //대여소 전체 리스트

	List<BikeReservePlaceVO> selectSearchBikePlace(BikeReservePlaceVO reservePlaceName); //대여소 검색 리스트

	int selectBikeCount(BikeVO bike); //대여가능 자전거 수

	int selectBikeId(BikeVO bike); //대여할 자전거 번호
 
	int insertReservation(ReservationVO rv); //예약 처리

	int changeStatus(int bike_id); //자전거 예약 상태 변경
	
}