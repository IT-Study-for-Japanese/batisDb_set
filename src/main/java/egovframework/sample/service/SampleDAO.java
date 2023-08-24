package egovframework.sample.service;

import java.util.List;

public interface SampleDAO {
	
	void insertSample(SampleVO vo) throws Exception;
	
	List<BikeReservePlace> selectBikePlace() throws Exception;//대여소전체리스트

	List<BikeReservePlace> selectSearchBikePlace(String reservePlaceName);//대여소검색리스트

}