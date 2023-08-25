package egovframework.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.sample.service.SampleDAO;
import egovframework.sample.service.SampleService;
import egovframework.sample.service.SampleVO;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;
import egovframework.sample.vo.BikeReservePlaceVO;
@Service("sampleService")
public class SampleServiceImpl  implements SampleService {
	
	@Resource(name ="daoIBatis") 
	private SampleDAO sampleDAO;
	
	public SampleServiceImpl() {
		
	}
	
	public void insertSample(SampleVO vo) throws Exception{
		sampleDAO.insertSample(vo);
	}
	
	@Override
	public List<BikeReservePlaceVO> selectBikePlace() throws Exception { //대여소전체리스트
		
		return sampleDAO.selectBikePlace();
	}
	
	@Override
	public List<BikeReservePlaceVO> selectSearchBikePlace(String reservePlaceName) { //대여소검색리스트
		
		return sampleDAO.selectSearchBikePlace(reservePlaceName);
	}
	
	@Override
	public int selectBikeCount(BikeVO bike) { //대여가능 자전거 수
		
		return sampleDAO.selectBikeCount(bike);
	}
	
	@Override
	public int selectBikeId(BikeVO bike) { //대여할 자전거 번호
		
		return sampleDAO.selectBikeId(bike);
	}
	
	@Override
	public int insertReservation(ReservationVO rv) { //예약처리
		
		return sampleDAO.insertReservation(rv);
		
	}

	@Override
	public int changeStatus(int bike_id) { //자전거 예약 상태 변경
		
		return sampleDAO.changeStatus(bike_id);
		
	}
	
}
