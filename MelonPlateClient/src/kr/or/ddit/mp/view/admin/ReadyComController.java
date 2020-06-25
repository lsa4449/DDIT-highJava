package kr.or.ddit.mp.view.admin;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.mp.service.admin.AdminService;
import kr.or.ddit.mp.vo.admin.ReadyComVO;
import javafx.scene.layout.AnchorPane;

public class ReadyComController  implements Initializable {
    
    
	@FXML
	TableView<ReadyComVO> tableview;
	@FXML
	TableColumn<ReadyComVO, String> tb_comName;
	@FXML
	TableColumn<ReadyComVO, String> tb_memName;
	@FXML
	TableColumn<ReadyComVO, String> tb_comId;
	@FXML
	TableColumn<ReadyComVO, String> tb_comTel;
	@FXML
	TableColumn<ReadyComVO, String> tb_comAddr;
	@FXML
	TableColumn<ReadyComVO, String> tb_comCat;
	@FXML
	Pagination page_allMember;
	@FXML
	Button btn_joinCom;
	@FXML
	Button btn_returnCom;
	

	private Registry reg;
	private AdminService as;

	private int from, to, itemsForPage;
	private ObservableList<ReadyComVO> allTableData, currentPageData;
	@FXML AnchorPane an_adminMain;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);

			as = (AdminService) reg.lookup("AdminService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		assert tableview != null : "fx:id=\"tableview\" was not injected : check your FXML file 'AdminMain.fxml'.";
		assert page_allMember != null : "fx:id=\"page_allMember\" was not injected : check your FXML file 'BoardMain.fxml'.";
		
        
 
		
		tb_comName.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		tb_memName.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		tb_comId.setCellValueFactory(new PropertyValueFactory<>("com_id"));
		tb_comTel.setCellValueFactory(new PropertyValueFactory<>("com_tel"));
		tb_comAddr.setCellValueFactory(new PropertyValueFactory<>("com_addr"));
		tb_comCat.setCellValueFactory(new PropertyValueFactory<>("com_cat"));

		allTableData = FXCollections.observableArrayList();
		ArrayList<ReadyComVO> list = new ArrayList<>();

		try {
			list = (ArrayList<ReadyComVO>) as.getReadyCom();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allTableData.setAll(list);

		tableview.setItems(allTableData);
		tableview.getSelectionModel().getSelectedItem();

		itemsForPage = 19;

		int totPageCount = allTableData.size() % itemsForPage == 0 ? allTableData
				.size() / itemsForPage
				: allTableData.size() / itemsForPage + 1;

				page_allMember.setPageCount(totPageCount);

				page_allMember.setPageFactory(this::createPage);
				tableview.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						if (tableview.getSelectionModel().getSelectedItem() != null) {

							btn_joinCom.setDisable(false);
							btn_returnCom.setDisable(false);

						}
					}
				});

	}

	private ObservableList<ReadyComVO> getTableviewData(int from, int to) {

		currentPageData = FXCollections.observableArrayList();
		int totSize = allTableData.size();
		for (int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}

		return currentPageData;
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		tableview.setItems(getTableviewData(from, to));

		return tableview;
	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}
	
	
	@FXML
	public void joinCom(ActionEvent event) throws Exception {
		int select_ind = tableview.getSelectionModel().getSelectedIndex();
	      if( select_ind <= -1 ) {
	         errMsg("오류!", "데이터를 선택해주세요!!");
	         return;
	      }
		
		ReadyComVO rcvo = new ReadyComVO();
		String mem_no = tableview.getSelectionModel().getSelectedItem().getMem_no();
		rcvo.setMem_no(mem_no);
		
		int cnt1 = as.insertJoinCom1(rcvo);
		int cnt2 = as.insertJoinCom2(rcvo);
	
		if (cnt1 > 0&& cnt2 >0) {
			as.deleteJoinCom(rcvo);
			infoMsg("승인 결과", (cnt1+cnt2-1) + "명이 가입 승인 되었습니다.");
			ArrayList<ReadyComVO> list = new ArrayList<>();
			list = (ArrayList<ReadyComVO>) as.getReadyCom();
			allTableData.setAll(list);
			page_allMember.setPageFactory(this::createPage);
		} else {
			errMsg("승인 결과", "오류");
			page_allMember.setPageFactory(this::createPage);
		}
		
		



	}
	@FXML
	public void returnCom(ActionEvent event) throws Exception {
		int select_ind = tableview.getSelectionModel().getSelectedIndex();
	      if( select_ind <= -1 ) {
	         errMsg("오류!", "데이터를 선택해주세요!!");
	         return;
	      }
	      
		ReadyComVO rcvo = new ReadyComVO();
		String mem_no = tableview.getSelectionModel().getSelectedItem().getMem_no();
		rcvo.setMem_no(mem_no);
		int cnt = as.deleteJoinCom(rcvo);
		
		if (cnt > 0) {
			infoMsg("반려 결과", (cnt) + "명이 가입 반려 되었습니다.");
			ArrayList<ReadyComVO> list = new ArrayList<>();
			list = (ArrayList<ReadyComVO>) as.getReadyCom();
			allTableData.setAll(list);
			page_allMember.setPageFactory(this::createPage);
		} else {
			errMsg("반려 결과", "오류");
			page_allMember.setPageFactory(this::createPage);
		}
		
		
		
	}


}
