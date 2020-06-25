package kr.or.ddit.mp.service.qnacomeatdeal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.EatqaVO;

public interface IQnacomEatdealService extends Remote{
	public List<EatqaVO> getAllQnaComeatdealList(String com_id) throws RemoteException;
	public List<EatqaVO> getNotQnaComeatdealList(String com_id) throws RemoteException;
	public int ansQna(EatqaVO vo) throws RemoteException;
}
