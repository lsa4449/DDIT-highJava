package kr.or.ddit.mp.service.buylist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.buylist.RequestVO;


public interface IBuylistService extends Remote {
	
	// 로그인 세션의 멤버 넘버와 오더넘을 받아서 결제 내역을 삭제
	public int deleteRequest(RequestVO rvo) throws RemoteException;
	
	// 테이블의 데이터를 가져와서 구매내역 리스트에 반환하는 메서드
	public List<RequestVO> getAllBuyList(RequestVO rvo) throws RemoteException;
	
	public int updateOrder_st(RequestVO rvo) throws RemoteException;
	
}
