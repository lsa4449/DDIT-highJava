package kr.or.ddit.mp.service.mypageEatDealQnA;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.mypageEatDealQnA.EatqaVO;

public interface MypageEatDealQnAService extends Remote{

	public List<EatqaVO> getMyEatDealQnA(EatqaVO evo) throws RemoteException; // 내가 작성한 eat deal qna출력
}
	
	
	
	
	
	

