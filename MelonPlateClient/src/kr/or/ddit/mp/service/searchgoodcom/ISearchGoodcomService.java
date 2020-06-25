package kr.or.ddit.mp.service.searchgoodcom;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.ComVO;

public interface ISearchGoodcomService extends Remote{
	public List<ComVO> getResultGoodCom(String word) throws RemoteException;
}
