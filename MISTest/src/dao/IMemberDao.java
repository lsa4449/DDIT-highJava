package dao;

import java.util.List;

import vo.MemberVO;

public interface IMemberDao {

	public int insertMember(MemberVO mVo);
	
	public List<MemberVO> getMemberAll();
	
	public List<MemberVO> getSearchMember(MemberVO mVo);
}
