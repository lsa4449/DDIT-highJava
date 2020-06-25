package kr.or.ddit.prod.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.prod.vo.ProdVO;

public class ProdDao {

	private SqlMapClient smc;
	
	private static ProdDao dao;
	
	private ProdDao() {
		Reader rd;
		
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("sqlmapclient객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static ProdDao getInstance() {
		if(dao == null) {
			dao = new ProdDao();
		}
		return dao;
	}
	
	
	public List<ProdVO> getProd1(String prod_lgu) {
		List<ProdVO> prodList = new ArrayList<ProdVO>();
		try {
			prodList = smc.queryForList("prod.getProd1",prod_lgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodList;
	}
		
	public List<ProdVO> getProd2(String prod_name) {				
		List<ProdVO> List = new ArrayList<>();
		try {
			List = smc.queryForList("prod.getProd2", prod_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}	
}
