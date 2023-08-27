package egovframework.sample.service;

import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;

import java.util.List;

public interface SampleDAO {
	
	void insertSample(SampleVO vo) throws Exception;
	
	List<BikeReservePlaceVO> selectBikePlace() throws Exception;//대여소전체리스트

	List<BikeReservePlaceVO> selectSearchBikePlace(String reservePlaceName);//대여소검색리스트

	int selectBikeCount(BikeVO bike);//대여가능 자전거 수
	
}