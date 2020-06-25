package kr.or.ddit.mp.main;

import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;

public class LoginSession {
	
	
	
	// 로그인 정보를 임시저장하는 MemberVO 객체
	// 로그아웃시 null로 만들어줘야 함.
	public static MemberVO session = new MemberVO();
	public static ComVO comsession = new ComVO();
	public static int ch_up = 0;
	public static int ch_down = 0;
	public static boolean isClicked = false;
	
}
