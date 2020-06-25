package kr.or.ddit.mp.service.reservation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.reservation.ReserOptionVO;
import kr.or.ddit.mp.vo.reservation.ReservationVO;
import kr.or.ddit.mp.vo.review.ReviewVO;

public interface IReservationService extends Remote{
	/**
	 * 1. 예약 옵션 설정
	 */
	public int insertReserOption(ReserOptionVO vo) throws RemoteException;
	
	/**
	 * 2. 예약 현황 전체 조회
	 */
	public List<ReservationVO> selectReserList(String com_id) throws RemoteException;
	
	/**
	 * 3. 예약 승인 조회
	 */
	public List<ReservationVO> selectReserApprove(String com_id) throws RemoteException;
	
	/**
	 * 4. 예약 대기 조회
	 */
	public List<ReservationVO> selectReserWait(String com_id) throws RemoteException;
	
	/**
	 * 5. 예약 반려 조회
	 */
	public List<ReservationVO> selectReserBack(String com_id) throws RemoteException;
	
	/**
	 * 6. 예약 노쇼 조회
	 */
	public List<ReservationVO> selectReserNoshow(String com_id) throws RemoteException;
	
	/**
	 * 7. 예약 승인하기
	 */
	public int approveReservation(ReservationVO vo) throws RemoteException;
	
	/**
	 * 8. 예약 반려하기
	 */
	public int backReservation(ReservationVO vo) throws RemoteException;
	
	/**
	 * 9. 예약 노쇼하기
	 */
	public int noshowReservation(ReservationVO vo) throws RemoteException;
	
	/**
	 * 10. 예약하기
	 */
	public int insertReservation(ReservationVO vo) throws RemoteException;
	
	/**
	 * 11. 사용자 예약 조회하기
	 */
	public List<ReservationVO> selectMyReservation(String mem_no) throws RemoteException;
	
	/**
	 * 12. 사용자 나의예약 삭제
	 */
	public int deleteReservation(ReservationVO vo) throws RemoteException;
	
	/**
	 * 13. 예약 옵션 조회
	 */
	public List<ReserOptionVO> selectComOption(String com_id) throws RemoteException;
	
	/**
	 * 14. 업체 오픈, 마감시간 조회
	 */
	public List<ComVO> chiceComResOption(String com_id) throws RemoteException;
	
	/**
	 * 15. 업체 가입시 오픈, 마감시간 조회
	 */
	public List<ComVO> selectComFirstOption(String com_id) throws RemoteException;
}
