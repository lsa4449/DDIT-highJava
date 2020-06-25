package kr.or.ddit.mp.service.comeatdeal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.EatdealVO;

public interface IComEatdealService extends Remote{
	public List<EatdealVO> getComEatdealList(String word) throws RemoteException;
	public int insertNewNoEatdeal(EatdealVO vo) throws RemoteException;
	public int deleteEatdeal(EatdealVO vo) throws RemoteException;
}
