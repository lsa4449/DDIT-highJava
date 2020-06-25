package kr.or.ddit.mp.service.join;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.zipcode.ZipcodeVO;

public interface IJoinService extends Remote{
	
	
	public List<MemberVO> selectId(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> selectNick(MemberVO mv) throws RemoteException;
	
	public List<ZipcodeVO> selectZipcode(ZipcodeVO zv) throws RemoteException;
	
	public int insertMember(MemberVO mv) throws RemoteException;
}
