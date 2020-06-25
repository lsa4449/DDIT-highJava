package kr.or.ddit.mp.service.wantgo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.mypage.WantgoVO;

public interface IWantgoService extends Remote{
	public List<WantgoVO> selectWantgo(WantgoVO wv) throws RemoteException;
	public boolean isEmptyWantgo(WantgoVO wv) throws RemoteException;
	public int deleteWantgo(WantgoVO wv) throws RemoteException;
	public int insertWantgo(WantgoVO wv) throws RemoteException;
}
