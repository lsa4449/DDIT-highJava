package kr.or.ddit.mp.service.cart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.mp.vo.cart.CartVO;
import kr.or.ddit.mp.vo.cart.EatdealVO;
import kr.or.ddit.mp.vo.cart.MileageVO;
import kr.or.ddit.mp.vo.cart.RequestVO;
import kr.or.ddit.mp.vo.cart.PayVO;


public interface ICartService extends Remote {
	
	// eat딜 이름을 받아서 삭제 성공결과 반환 수정필요 삭제 할 기준이 없음
	public int deleteCart(CartVO cv) throws RemoteException;
	
	// 카트멤버변수를 가진 객체를 받아서 인서트 성공(장바구니에 관심 eat딜 추가)결과 값을 인트로 받는 메서드
	public int insertCart(CartVO cv) throws RemoteException;
	
	// 테이블의 데이터를 가져와서 장바구니 리스트에 반환하는 메서드
	public List<CartVO> getAllCartList(CartVO cvo) throws RemoteException;
	
	// eat딜 테이블 이름을 가져옴
	public List<CartVO> getEatdealName(CartVO cvo) throws RemoteException;
	
	// 마일리지 테이블에서 해당 회원의 마일리지 가져옴
	public List<MileageVO> getMileage(MileageVO mvo) throws RemoteException;
	
	// eat딜 테이블에서 가격을 가져옴
	public List<CartVO> getEatdealprice(CartVO cvo) throws RemoteException;
	
	public List<RequestVO> requestSelect(String word) throws RemoteException;
	
	// eat딜 테이블에서 eat딜의 전체정보 가져옴
	public List<EatdealVO> getEatdealInfo(CartVO cvo) throws RemoteException;
	
	// 리퀘스트 테이블에 결제정보 인서트
	public int requestPayInsert(RequestVO revo) throws RemoteException;
	
	// 결제 정보를 pay테이블에 인서트
	public int payInsert(PayVO pvo) throws RemoteException;
	
	// 마일리지를 사용하지 않았을경우 적립을 해서 마일리지 테이블에 인서트
	public int mileNotuseInsert(MileageVO mvo) throws RemoteException;
	
	// 마일리지를 사용했을 경우 사용 마일리지를 차감해서 마일리지 테이블에 인서트
	public int mileUseInsert(MileageVO mvo) throws RemoteException;
	
	// 결제한 eat딜 수량만큼 eat딜 테이블의 수량에서 감소
	public int eatdealQtyDown(EatdealVO evo) throws RemoteException;
}
