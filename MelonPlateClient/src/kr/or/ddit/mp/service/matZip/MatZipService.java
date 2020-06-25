package kr.or.ddit.mp.service.matZip;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.matZip.ReviewVO;

public interface MatZipService extends Remote{

	public List<ReviewVO> getGoodCom(ReviewVO rvo) throws RemoteException; // 리뷰수 및 평점 평균 가져오기
}
	
	
	
	
	
	

