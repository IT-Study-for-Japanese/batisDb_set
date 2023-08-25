package egovframework.sample.service;

import java.util.List;

public interface SampleService {

	void insertSample(SampleVO vo) throws Exception;

	List<BikeReservePlaceVO> selectBikePlace() throws Exception;//대여소전체리스트

	List<BikeReservePlaceVO> selectSearchBikePlace(String reservePlaceName);//대여소검색리스트
	
}