package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.control.Pagination;

public class PagingController implements Initializable {

	@FXML
	private TableView<MemberVO> tv;
	@FXML
	private TableColumn<MemberVO, String> id;
	@FXML
	private TableColumn<MemberVO, String> name;
	@FXML
	private TableColumn<MemberVO, String> addr;

	@FXML
	Pagination pn;

	private int from, to, itemsForPage;

	private ObservableList<MemberVO> allTableData, currentPageData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		addr.setCellValueFactory(new PropertyValueFactory<>("addr"));

		// 테스트용 데이터 만들기
		allTableData = FXCollections.observableArrayList();

		allTableData.add(new MemberVO("1", "홍길동1", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("2", "홍길동2", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("3", "홍길동3", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("4", "홍길동4", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("5", "홍길동5", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("6", "홍길동6", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("7", "홍길동7", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("8", "홍길동8", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("9", "홍길동9", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("10", "홍길동10", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("11", "홍길동11", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("12", "홍길동12", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("13", "홍길동13", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("14", "홍길동14", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("15", "홍길동15", "대전시 중구 대흥동 인재개발원"));
		allTableData.add(new MemberVO("16", "홍길동16", "대전시 중구 대흥동 인재개발원"));

		// tv.setItems(allTableData);

		itemsForPage = 5; // 한 페이지에 보여줄 항목 수 설정
		int totalCnt = allTableData.size();
		int totalPagecnt = totalCnt % itemsForPage == 0 ? 
				totalCnt / itemsForPage : totalCnt / itemsForPage + 1;

		pn.setPageCount(totalPagecnt); // 전체 페이지 수 설정

		// 방법1 callback타입의 익명객체 생성
		pn.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				tv.setItems(getTableViewData(from, to));

				return tv;
			}

			private ObservableList<MemberVO> getTableViewData(int from, int to) {
				
				currentPageData = FXCollections.observableArrayList();
				int totSize = allTableData.size();
				for(int i = from; i <= to && i < totSize; i++) {
					currentPageData.add(allTableData.get(i));
					
				}
				return currentPageData;
			}
		});

	}

}
