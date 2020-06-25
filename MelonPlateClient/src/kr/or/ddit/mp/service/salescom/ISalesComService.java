package kr.or.ddit.mp.service.salescom;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.SalesVO;

public interface ISalesComService extends Remote{
	public List<SalesVO> getSalesComEatdeal(String word) throws RemoteException;
}
