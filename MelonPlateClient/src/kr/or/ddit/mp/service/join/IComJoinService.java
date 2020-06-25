package kr.or.ddit.mp.service.join;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.member.ReadyComVO;
import kr.or.ddit.mp.vo.zipcode.ZipcodeVO;

public interface IComJoinService extends Remote{

	public List<MemberVO> selectId(MemberVO mvo) throws RemoteException;
	
	public List<MemberVO> selectNick(MemberVO mvo) throws RemoteException;
	
	public List<ZipcodeVO> selectZipcode(ZipcodeVO zv) throws RemoteException;
	
	public int insertReadyComMember(ReadyComVO rvo) throws RemoteException;
	
	public List<ComVO> selectComId(ComVO cvo) throws RemoteException;

}
