package data;

import java.util.ArrayList;
import java.util.List;

import vo.ProdVO;

public class Database {

	private static Database db;

	private static Database getInstance() {

		if (db == null) {
			db = new Database();
		}
		return db;
	}

	public List<ProdVO> prodList() {
		String sql = "select id, name from prod";

		List<ProdVO> list = new ArrayList();
		while (list != null) {
			ProdVO pVo = new ProdVO();
			pVo.setProd_id("id");
			pVo.setProd_name("name");
			pVo.setProd_lgu("lgu");
			pVo.setProd_buyer("buyer");
			pVo.setProd_cost("cost");

			list.add(pVo);

		}
		return list;

	}

	public ProdVO prodInfo(String id) {
		String sql = "select * from prod where prod_id = id";

		ProdVO vo = new ProdVO();
		vo.setProd_id("prod_id");
		vo.setProd_name("prod_name");
		vo.setProd_lgu("prod_lgu");
		vo.setProd_buyer("prod_buyer");
		vo.setProd_cost("prod_cost");

		return vo;
	}
}