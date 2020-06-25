package vo;

import java.util.Date;

public class BookRentalVO {

	private int bookRentalNum; // 대여 번호
	private Date rentalDate; // 대출 일자
	private Date returnDate; // 반납 일자
	private int memNum; // 회원 번호
	private int bookNum; // 도서 번호
	private int bookReserveNum; // 도서 예약 번호

	public int getBookRentalNum() {
		return bookRentalNum;
	}

	public void setBookRentalNum(int bookRentalNum) {
		this.bookRentalNum = bookRentalNum;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public int getBookReserveNum() {
		return bookReserveNum;
	}

	public void setBookReserveNum(int bookReserveNum) {
		this.bookReserveNum = bookReserveNum;
	}

	@Override
	public String toString() {
		return "BookRentalVO [bookRentalNum=" + bookRentalNum + ", rentalDate=" + rentalDate + ", returnDate="
				+ returnDate + ", memNum=" + memNum + ", bookNum=" + bookNum + ", bookReserveNum=" + bookReserveNum
				+ "]";
	}

}
