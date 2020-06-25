package kr.or.ddit.mp.service.eatdealhome;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.CartVO;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.mypage.MileageVO;

public interface IEatdealHomeService extends Remote {
	public List<EatdealVO> getSelectEatdeal(String word) throws RemoteException;
	public int insertCartEatdeal(CartVO vo) throws RemoteException;
	public List<MileageVO> getMileageLast(String word) throws RemoteException;;
}
