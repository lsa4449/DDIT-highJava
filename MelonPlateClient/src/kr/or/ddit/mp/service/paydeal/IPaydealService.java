package kr.or.ddit.mp.service.paydeal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.eatdeal.PayVO;
import kr.or.ddit.mp.vo.eatdeal.RequestVO;
import kr.or.ddit.mp.vo.mypage.MileageVO;

public interface IPaydealService extends Remote{
	public int requestPayInsert(RequestVO vo) throws RemoteException;
	public int requestCartInsert(RequestVO vo) throws RemoteException;
	public List<RequestVO> requestSelect(String word) throws RemoteException;
	public int payInsert(PayVO vo) throws RemoteException;
	public int mileNotuseInsert(MileageVO vo) throws RemoteException;
	public int mileUseInsert(MileageVO vo) throws RemoteException;
	public int eatdealQtyDown(EatdealVO vo) throws RemoteException;
}
