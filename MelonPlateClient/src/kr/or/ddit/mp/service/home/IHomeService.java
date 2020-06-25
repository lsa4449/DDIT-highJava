package kr.or.ddit.mp.service.home;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;

public interface IHomeService extends Remote{
	public List<MemberVO> homeBestTop10() throws RemoteException;
	public List<ZoneVO> myZoneSelect(String word) throws RemoteException;
	public List<EatdealVO> homeEatBest5() throws RemoteException;
	public List<ComVO> homeComeBest5(ZoneVO vo) throws RemoteException;
}
