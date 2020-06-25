package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	private SqlMapClient smc;

	private static IMemberDao dao;

	private MemberDaoImpl() {

		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig2.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!~@");
			e.printStackTrace();
		}
	}

	public static IMemberDao getInstance() {
		if (dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	private Scanner scan = new Scanner(System.in);

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;

		try {
			Object obj = smc.insert("member.insertMember", mv);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {

		boolean chk = false;

		try {
			MemberVO mv = (MemberVO) smc.queryForObject("member.getMember", memId);

			if (mv != null) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			memList = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt = 0;

		try {
			cnt = smc.update("member.updateMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;

		try {
			cnt = smc.delete("member.deleteMember", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<>();

		try {
			memList = smc.queryForList("member.getSearchMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

}
