package service;

import java.util.List;

import dao.ProdDAO;
import data.Database;
import vo.ProdVO;

public class Service {

	private static Service service;
	private Database db;
	private ProdDAO dao;

	public static Service getInstance() {

		if (service == null) {
			service = new Service();
		}
		return service;
	}

	public List<ProdVO> prodList() {
		List<ProdVO> list = dao.prodList();
		return list;

	}

	public ProdVO prodInfo(String id) {
		ProdVO list = dao.prodInfo(id);
		return list;

	}

}
