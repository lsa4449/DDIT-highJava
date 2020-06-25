package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.ProdDao;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdService {
private ProdDao dao;
	
	private static ProdService service;
	
	private ProdService() {
		dao = ProdDao.getInstance();
	}

	public static ProdService getInstance() {
		if(service == null) {
			service = new ProdService();
		}
		return service;
	}
	
	public List<ProdVO> getProd1(String prod_lgu) {
		return dao.getProd1(prod_lgu);
	}
	
	
	
	public List<ProdVO> getProd2(String prod_name) {
		return dao.getProd2(prod_name);
	}
}
