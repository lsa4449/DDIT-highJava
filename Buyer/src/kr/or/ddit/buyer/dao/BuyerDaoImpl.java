package kr.or.ddit.buyer.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.buyer.vo.BuyerVO;

public class BuyerDaoImpl implements IBuyerDao {

	private static SqlMapClient smc;

	private static IBuyerDao dao;

	private BuyerDaoImpl() {

		Charset charSet = Charset.forName("UTF-8");
		Resources.setCharset(charSet);

		try {
			Reader reader = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 인스턴스 생성
	public static IBuyerDao getInstance() {

		if (dao == null) {
			dao = new BuyerDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BuyerVO> search(BuyerVO bv) {

		List<BuyerVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("buyer.search", bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BuyerVO selectById(String id) {

		BuyerVO bv = null;

		try {
			bv = (BuyerVO) smc.queryForObject("buyer.selectById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bv;
	}

	@Override
	public int insert(BuyerVO bv) {

		int cnt = 0;

		try {
			Object obj = smc.insert("buyer.insert", bv);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
