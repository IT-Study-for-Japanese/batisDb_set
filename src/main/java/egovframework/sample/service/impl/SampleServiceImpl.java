package egovframework.sample.service.impl;

import egovframework.sample.service.SampleDAO;
import egovframework.sample.service.SampleService;
import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("sampleService")
public class SampleServiceImpl  implements SampleService {
	
	@Resource(name ="daoIBatis") 
	private SampleDAO sampleDAO;
	
	public SampleServiceImpl() {
		
	}
	
	@Override
	public List<BikeReservePlaceVO> selectBikePlace() throws Exception { //대여소전체리스트
		
		return sampleDAO.selectBikePlace();
	}
	
	@Override
	public List<BikeReservePlaceVO> selectSearchBikePlace(BikeReservePlaceVO reservePlaceName) { //대여소검색리스트
		
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

	@Override
	public int checkReservation(ReservationVO rvCheck) { //예약 내역 확인
		
		return sampleDAO.checkReservation(rvCheck);
	}
	
}
