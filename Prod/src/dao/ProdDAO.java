package dao;

import java.util.ArrayList;
import java.util.List;

import data.Database;
import vo.ProdVO;

public class ProdDAO {

	private static ProdDAO dao;
	private Database db;

	private static ProdDAO getInstance() {

		if (dao == null) {
			dao = new ProdDAO();
		}
		return dao;
	}

	public List<ProdVO> prodList() {
		List<ProdVO> list = new ArrayList();
			list = db.prodList();
		return list;
	}

	public ProdVO prodInfo(String id) {
		ProdVO list = db.prodInfo(id);
		return list;
	}
}
