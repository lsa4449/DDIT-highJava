package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;

public interface IBuyerDao {

	public List<BuyerVO> search(BuyerVO bv);

	public BuyerVO selectById(String id);
	
	public int insert(BuyerVO bv);

}
