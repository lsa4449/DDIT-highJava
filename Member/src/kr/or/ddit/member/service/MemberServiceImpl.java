package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private static IMemberDao dao;
	private static IMemberService service;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}

	public static IMemberService getInstance() {

		if (service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public List<MemberVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public MemberVO selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public int insertMember(MemberVO mv) {
		return dao.insertMember(mv);
	}

}
