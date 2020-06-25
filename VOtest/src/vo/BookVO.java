package vo;

import java.util.Date;

public class BookVO {

	private int bookNum; // 도서 번호
	private String bookName; // 도서명
	private String author; // 저자
	private Date publicationDate; // 출간일
	private String isbn; // ISBN(바코드)
	private Date registerDate; // 등록일
	private String publisher; // 출판사
	private String translator; // 역자
	private String price; // 가격
	private int categoryNum; // 분류 번호
	private boolean bookState; // 도서 상태

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}

	public boolean isBookState() {
		return bookState;
	}

	public void setBookState(boolean bookState) {
		this.bookState = bookState;
	}

	@Override
	public String toString() {
		return "BookVO [bookNum=" + bookNum + ", bookName=" + bookName + ", author=" + author + ", publicationDate="
				+ publicationDate + ", isbn=" + isbn + ", registerDate=" + registerDate + ", publisher=" + publisher
				+ ", translator=" + translator + ", price=" + price + ", categoryNum=" + categoryNum + ", bookState="
				+ bookState + "]";
	}

}
