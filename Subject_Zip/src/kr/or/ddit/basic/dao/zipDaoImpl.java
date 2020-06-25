package kr.or.ddit.basic.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.basic.vo.zipVO;

public class zipDaoImpl {

	private SqlMapClient smc;
	private static zipDaoImpl dao;

	private zipDaoImpl() {

		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!~@");
			e.printStackTrace();
		}
	}

	public static zipDaoImpl getInstance() {
		if (dao == null) {
			dao = new zipDaoImpl();
		}
		return dao;
	}

	public List<zipVO> selectCode(String data) {
		List<zipVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("zip.selectCode", data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<zipVO> selectDong(String data) {
		List<zipVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("zip.selectDong", data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
