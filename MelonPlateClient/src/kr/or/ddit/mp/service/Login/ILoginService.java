package kr.or.ddit.mp.service.Login;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;

public interface ILoginService extends Remote {
	
	public List<MemberVO> memberLogin(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> idSearch(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> memNoSearch(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> pwSearch(MemberVO mv) throws RemoteException;
	public List<ComVO> iscom(String mem_no) throws RemoteException;
}
