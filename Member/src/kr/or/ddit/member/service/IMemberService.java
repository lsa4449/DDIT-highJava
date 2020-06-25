package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {
	
	public List<MemberVO> selectAll();

	public MemberVO selectById(String id);

	public int insertMember(MemberVO mv);

}
