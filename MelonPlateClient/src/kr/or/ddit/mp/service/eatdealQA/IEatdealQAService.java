package kr.or.ddit.mp.service.eatdealQA;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.mp.vo.eatdeal.EatqaVO;

public interface IEatdealQAService extends Remote{
	
	/**
	 * 1. 잇딜 게시판 전체 조회 
	 */
	public List<EatqaVO> selectQA(String eat_no) throws RemoteException;
	
	/**
	 * 2. 잇딜 게시판 상세보기 
	 */
	public List<EatqaVO> selectQAdetail(EatqaVO eatqa_no) throws RemoteException;
	
	/**
	 * 3. 잇딜 게시글 작성하기 
	 */
	public int insertQA(EatqaVO vo) throws RemoteException;
	
}
