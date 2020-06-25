package kr.or.ddit.mp.service.recogeatdeal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.recogeatdeal.EatdealVO;


public interface IRecogeatdealService extends Remote {
	
	// 테이블의 데이터를 가져와서 장바구니 리스트에 반환하는 메서드
	public List<EatdealVO> getAllEatList() throws RemoteException;
	
	public int updateOnY(EatdealVO evo) throws RemoteException;
	
	public int updateOnB(EatdealVO evo) throws RemoteException;
	
}
