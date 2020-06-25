package kr.or.ddit.mp.service.melonageCheck;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.melonageCheck.MileageVO;

public interface MileageCheckService extends Remote{

	// 마일리지 조회
	public List<MileageVO> getAddUsedMelonage(MileageVO mvo) throws RemoteException; // 사용한 마일리지 조회
	public List<MileageVO> getAllMelonage(MileageVO mvo) throws RemoteException; // 적립된 마일리지 조회

	
}
	
	
	
	
	
	

