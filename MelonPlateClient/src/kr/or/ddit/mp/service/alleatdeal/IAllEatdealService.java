package kr.or.ddit.mp.service.alleatdeal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;

public interface IAllEatdealService extends Remote {
	public List<EatdealVO> eatlistByMyzone(ZoneVO vo) throws RemoteException;
	public List<EatdealVO> eatlistByQty() throws RemoteException;
	public List<ZoneVO> zoneForlist(String word) throws RemoteException;
	public List<EatdealVO> eatlistByNew() throws RemoteException;
}
