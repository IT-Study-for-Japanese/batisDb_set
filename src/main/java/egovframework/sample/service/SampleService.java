package egovframework.sample.service;

import java.util.List;

import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.BikeReservePlaceVO;

public interface SampleService {

	void insertSample(SampleVO vo) throws Exception;

	List<BikeReservePlaceVO> selectBikePlace() throws Exception;//대여소전체리스트

	List<BikeReservePlaceVO> selectSearchBikePlace(String reservePlaceName);//대여소검색리스트

	int selectBikeCount(BikeVO bike);//대여가능한 자전거 수
	
}