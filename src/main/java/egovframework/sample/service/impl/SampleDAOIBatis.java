package egovframework.sample.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.sample.service.SampleDAO;
import egovframework.sample.service.SampleVO;
import egovframework.sample.vo.BikeReservePlaceVO;

@Repository("daoIBatis")
public class SampleDAOIBatis extends EgovAbstractMapper implements SampleDAO{
	
	@Override
	public void insertSample(SampleVO vo) throws Exception {
		insert("insertSample", vo);	
	}

	@Override
	public List<BikeReservePlaceVO> selectBikePlace() throws Exception {//대여소전체리스트
		
		return selectList("selectBikePlace");
		
	}

	@Override
	public List<BikeReservePlaceVO> selectSearchBikePlace(String reservePlaceName) {//대여소검색리스트
		System.out.println("dao확인");
		return selectList("selectSearchBikePlace");
	}
	
}
