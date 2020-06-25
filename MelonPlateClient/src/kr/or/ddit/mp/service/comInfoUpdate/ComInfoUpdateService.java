package kr.or.ddit.mp.service.comInfoUpdate;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.comInfoUpdate.ComVO;
import kr.or.ddit.mp.vo.comInfoUpdate.MemberVO;
import kr.or.ddit.mp.vo.comInfoUpdate.ZiptbVO;

public interface ComInfoUpdateService extends Remote{

		// 관리 페이지로 들어가기 위한 비밀 번호 입력
		public MemberVO getPw(MemberVO mvo) throws RemoteException; // 비밀번호값 가져오기
		
		
		// 관리 페이지에서 들어온 후
		public ComVO getDefaultInfo(ComVO cvo) throws RemoteException; // 수정 불가한 기본 정보 가져오기
		public List<ZiptbVO> getSearchZipCode(ZiptbVO zvo) throws RemoteException; // 우편번호 찾기
		public int updateCom(ComVO cvo) throws RemoteException; // 회원 정보 수정
		public int updatePhoto(ComVO cvo) throws RemoteException; // 사진 경로값 넣기
}
	
	
	
	
	
	

