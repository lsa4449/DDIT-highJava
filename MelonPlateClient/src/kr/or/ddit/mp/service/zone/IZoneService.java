package kr.or.ddit.mp.service.zone;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.mypage.ZiptbVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;

public interface IZoneService extends Remote{
	
	/**
	 * 1. 등으로 검색하기
	 */
	public List<ZiptbVO> selectZone(String dong) throws RemoteException;
	
	/**
	 * 2. 관심지역1 셋팅하기
	 */
	public int insertZone1(ZoneVO zone1) throws RemoteException;
	
	/**
	 * 3. 관심지역2 셋팅하기
	 */
	public int updateZone2(ZoneVO zone2) throws RemoteException;
	
	/**
	 * 4. 관심지역3 셋팅하기
	 */
	public int updateZone3(ZoneVO zone3) throws RemoteException;
	
	/**
	 * 5. 관심지역3 삭제
	 */
	public int deleteZone3(ZoneVO zone3) throws RemoteException;
	
	/**
	 * 6. 관심지역2 삭제
	 */
	public int deleteZone2(ZoneVO zone2) throws RemoteException;
	
	/**
	 * 7. 관심지역1 삭제
	 */
	public int deleteZone1(ZoneVO zone1) throws RemoteException;
	
	/**
	 *  설정된 값 조회하기
	 */
	public List<ZoneVO> selectZoneChoice(ZoneVO vo) throws RemoteException;
}
