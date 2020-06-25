package vo;

public class PurchaseExpectBookVO {

	private int purchaseExpectNum; // 구매 예정 도서
	private int wannaBookNum; // 희망 도서 번호

	public int getPurchaseExpectNum() {
		return purchaseExpectNum;
	}

	public void setPurchaseExpectNum(int purchaseExpectNum) {
		this.purchaseExpectNum = purchaseExpectNum;
	}

	public int getWannaBookNum() {
		return wannaBookNum;
	}

	public void setWannaBookNum(int wannaBookNum) {
		this.wannaBookNum = wannaBookNum;
	}

	@Override
	public String toString() {
		return "PurchaseExpectBookVO [purchaseExpectNum=" + purchaseExpectNum + ", wannaBookNum=" + wannaBookNum + "]";
	}

}
