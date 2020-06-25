package service;

import java.util.List;

import vo.MemberVO;

public interface IMemberService {

	public int insertMember(MemberVO mVo);

	public List<MemberVO> getMemberAll();

	public List<MemberVO> getSearchMember(MemberVO mVo);
}
