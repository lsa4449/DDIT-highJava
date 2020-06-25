package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	private static SqlMapClient smc;

	private static IMemberDao dao;

	private MemberDaoImpl() {

		Charset charSet = Charset.forName("UTF-8");
		Resources.setCharset(charSet);

		try {
			Reader reader = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 자신의 객체를 생성해서 리턴하는 getInstance메소드
	public static IMemberDao getInstance() {

		if (dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	@Override
	public List<MemberVO> selectAll() {

		List<MemberVO> list = null;

		try {
			list = smc.queryForList("member.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public MemberVO selectById(String id) {

		MemberVO vo = null;

		try {
			vo = (MemberVO) smc.queryForObject("member.selectById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

	@Override
	public int insertMember(MemberVO vo) {

		int cnt = 0;

		try {
			Object obj = smc.insert("member.insertMember", vo);
			
			// 저장 성공하면 obj가 null을 리턴 받는다
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
