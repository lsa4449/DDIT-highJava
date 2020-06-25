package kr.or.ddit.mp.service.goodcom;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;

public interface IGoodcomService extends Remote{
	public List<ComVO> getSearchGoodCom(ComVO cv) throws RemoteException;
	public List<MemberVO> isVipCom(String word) throws RemoteException;
}
