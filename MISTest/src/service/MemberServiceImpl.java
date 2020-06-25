package service;

import java.util.List;

import dao.IMemberDao;
import dao.MemberDaoImpl;
import vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private IMemberDao dao;

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
	public int insertMember(MemberVO mVo) {

		return dao.insertMember(mVo);
	}

	@Override
	public List<MemberVO> getMemberAll() {

		return dao.getMemberAll();
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mVo) {

		return dao.getSearchMember(mVo);
	}

}
