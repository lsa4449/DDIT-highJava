package kr.or.ddit.basic.service;

import java.util.List;

import kr.or.ddit.basic.dao.zipDaoImpl;
import kr.or.ddit.basic.vo.zipVO;

public class zipServiceImpl {	
	
	private zipDaoImpl dao;
	private static zipServiceImpl service;

	private zipServiceImpl() {
		dao = zipDaoImpl.getInstance();
	}

	public static zipServiceImpl getInstance() {
		if (service == null) {
			service = new zipServiceImpl();
		}
		return service;
	}

	public List<zipVO> selectCode(String data) {
		return dao.selectCode(data);
	}

	public List<zipVO> selectDong(String data) {
		return dao.selectDong(data);
	}
}
