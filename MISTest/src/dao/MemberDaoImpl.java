package dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	
	private SqlMapClient smc;
	
	private static IMemberDao dao;
	
	private MemberDaoImpl() {
		
		Reader rd;
		
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("실패");
			e.printStackTrace();
		}
	
	}
	
	public static IMemberDao getInstance() {
		
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	private Scanner scan = new Scanner(System.in);

	@Override
	public int insertMember(MemberVO mVo) {

		int cnt = 0;

		try {
			Object obj = smc.insert("member.insertMember", mVo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getMemberAll() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			memList = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}


	@Override
	public List<MemberVO> getSearchMember(MemberVO mVo) {
		
		List<MemberVO> memList = new ArrayList<>();

		try {
			memList = smc.queryForList("member.getSearchMember", mVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}
}
