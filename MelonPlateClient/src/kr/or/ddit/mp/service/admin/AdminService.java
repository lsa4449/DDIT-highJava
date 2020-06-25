package kr.or.ddit.mp.service.admin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.admin.BlackVO;
import kr.or.ddit.mp.vo.admin.HolicVO;
import kr.or.ddit.mp.vo.admin.MemberVO;
import kr.or.ddit.mp.vo.admin.ReadyComVO;

public interface AdminService extends Remote{


		//전체 회원 정보 CRUD
	//전체 회원 정보 CRUD
		public List<MemberVO> getAllMemberCom() throws RemoteException; //전체회원 조회(일반,회원)
		public int deleteMember(MemberVO mvo) throws RemoteException; //회원 정보 삭제
		
		//관리자 추가
		public int insertAdmin(MemberVO mvo) throws RemoteException; //관리자 정보 삽입
		
		//일반 회원 정보 
		public List<MemberVO> getAllMember() throws RemoteException; //일반회원 조회
		
		//업체 회원 정보 
		public List<MemberVO> getAllCom() throws RemoteException; //업체 회원 조회
		public List<ReadyComVO> getReadyCom() throws RemoteException; //대기중인 업체 회원 조회

		//업체회원 가입 신청 승인/반려
		public int insertJoinCom1(ReadyComVO rcvo) throws RemoteException; //업체회원 가입 승인
		public int insertJoinCom2(ReadyComVO rcvo) throws RemoteException; //업체회원 가입 승인
		public int deleteJoinCom(ReadyComVO rcvo) throws RemoteException; //업체회원 가입 반려
		
		//일반회원 블랙리스트 CRUD
		public List<MemberVO> getReadyBlackMember() throws RemoteException; //블랙 대기 회원 조회
		public List<MemberVO> getAllBlackMember() throws RemoteException; //전체 블랙 회원 조회
		public int insertBlackMember(MemberVO mvo) throws RemoteException; //블랙 회원 정보 삽입
		public int deleteBlackMember(MemberVO mvo) throws RemoteException; //블랙 회원 정보 삭제 (회원번호 사용)
		
		//일반회원 홀릭 회원 CRUD
		public List<MemberVO> getReadyHolicMember() throws RemoteException; //홀릭 대기 회원 조회
		public List<MemberVO> getAllHolicMember() throws RemoteException; //전체 홀릭 회원 조회
		public int insertHolicMember(MemberVO mvo) throws RemoteException; //홀릭 회원 정보 삽입
		public int deleteHolicMember(MemberVO mvo) throws RemoteException; //홀릭 회원 정보 삭제 (회원번호 사용)
		
		//업체회원 우수 맛집리스트 CRUD
		public List<MemberVO> getAllGoodCom() throws RemoteException; //전체 우수 맛집 조회회
	
}
	
	
	
	
	
	

