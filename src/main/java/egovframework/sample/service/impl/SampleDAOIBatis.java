package egovframework.sample.service.impl;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.sample.service.SampleDAO;
import egovframework.sample.service.SampleVO;
import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("daoIBatis")
public class SampleDAOIBatis extends EgovAbstractMapper implements SampleDAO{
	
	@Override
	public void insertSample(SampleVO vo) throws Exception {
		insert("insertSample", vo);	
	}

	@Override
	public List<BikeReservePlaceVO> selectBikePlace() throws Exception { //대여소전체리스트
		
		return selectList("selectBikePlace");
		
	}

	@Override
	public List<BikeReservePlaceVO> selectSearchBikePlace(BikeReservePlaceVO search) { //대여소검색리스트
		
		return selectList("selectSearchBikePlace", search);
	}

	@Override
	public int selectBikeCount(BikeVO bike) { //대여가능 자전거 수
		
		return selectOne("selectBikeCount", bike);
	
	}

	@Override
	public int selectBikeId(BikeVO bike) { //대여할 자전거번호
		
		return selectOne("selectBikeId", bike);
	
	}

	@Override
	public int insertReservation(ReservationVO rv) { //예약처리
		
		return insert("insertReservation", rv);
		
	}

	@Override
	public int changeStatus(int bike_id) { //자전거 예약 상태 변경
		
		return update("changeStatus", bike_id);
	}
	
}
