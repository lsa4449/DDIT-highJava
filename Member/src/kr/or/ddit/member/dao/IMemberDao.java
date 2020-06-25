package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberDao {

	public List<MemberVO> selectAll();
	
	public MemberVO selectById(String id);
	
	public int insertMember(MemberVO mv);
	
}
