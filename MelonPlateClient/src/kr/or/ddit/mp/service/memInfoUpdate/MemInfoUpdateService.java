package kr.or.ddit.mp.service.memInfoUpdate;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.memInfoUpdate.MemberVO;
import kr.or.ddit.mp.vo.memInfoUpdate.ZiptbVO;

public interface MemInfoUpdateService extends Remote{

		// 관리 페이지로 들어가기 위한 비밀 번호 입력
		public MemberVO getPw(MemberVO mvo) throws RemoteException;  // 비밀번호값 가져오기
		
		
		// 관리 페이지에서 들어온 후
		public MemberVO getDefaultInfo(MemberVO mvo) throws RemoteException; // 수정 불가한 기본 정보 가져오기
		public MemberVO getNickCheck(String str) throws RemoteException; // 닉네임 중복체크
		public List<ZiptbVO> getSearchZipCode(ZiptbVO zvo) throws RemoteException; // 우편번호 찾기
		public int deleteMember(MemberVO mvo) throws RemoteException; // 회원 탈퇴 
		public int updateMember(MemberVO mvo) throws RemoteException; // 회원 정보 수정
	
}
	
	
	
	
	
	

