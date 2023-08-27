package egovframework.sample.service.impl;

import egovframework.sample.service.SampleDAO;
import egovframework.sample.service.SampleService;
import egovframework.sample.service.SampleVO;
import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("sampleService")
public class SampleServiceImpl  implements SampleService {
	
	@Resource(name ="daoIBatis") 
	private SampleDAO sampleDAO;
	
	public SampleServiceImpl() {
		System.out.println(" SampleServiceImpl()  ");
	}
	public void insertSample(SampleVO vo) throws Exception{
		sampleDAO.insertSample(vo);
	}
	@Override
	public List<BikeReservePlaceVO> selectBikePlace() throws Exception {//대여소전체리스트
		
		return sampleDAO.selectBikePlace();
	}
	@Override
	public List<BikeReservePlaceVO> selectSearchBikePlace(String reservePlaceName) {//대여소검색리스트
		
		return sampleDAO.selectSearchBikePlace(reservePlaceName);
	}
	@Override
	public int selectBikeCount(BikeVO bike) { //대여가능 자전거 수
		
		return sampleDAO.selectBikeCount(bike);
	}
	
}
