package kr.or.ddit.mp.view.admin;

import java.io.IOException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.mp.service.admin.AdminService;
import kr.or.ddit.mp.vo.admin.MemberVO;

public class BlackController implements Initializable {


	@FXML
	TableView<MemberVO> tableview;
	@FXML
	TableColumn<MemberVO, String> tb_blackName;
	@FXML
	TableColumn<MemberVO, String> tb_blackId;
	@FXML
	TableColumn<MemberVO, String> tb_blackHp;
	@FXML
	TableColumn<MemberVO, String> tb_countNoShow;
	@FXML
	Pagination page_allMember;
	@FXML
	Button btn_deleteBlack;
	@FXML
	Button btn_blackCheck;
	@FXML
	AnchorPane an_adminMain;
	
	private Registry reg;
	private AdminService as ;

	private int from, to, itemsForPage;
	private ObservableList<MemberVO> allTableData, currentPageData;

	
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

		assert tableview != null : "fx:id=\"tableview\" was not injected : check your FXML file 'holic.fxml'.";
		assert page_allMember != null : "fx:id=\"page_allMember\" was not injected : check your FXML file 'holic.fxml'.";
		
        
		tb_blackName.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		tb_blackId.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		tb_blackHp.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
		tb_countNoShow.setCellValueFactory(new PropertyValueFactory<>("no_show"));
		
		allTableData = FXCollections.observableArrayList();
		ArrayList<MemberVO> list = new ArrayList<>();

		try {
			list = (ArrayList<MemberVO>) as.getAllBlackMember();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allTableData.setAll(list);

		tableview.setItems(allTableData);
		tableview.getSelectionModel().getSelectedItem();
		
		itemsForPage = 19;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_allMember.setPageCount(totPageCount);	
				
				page_allMember.setPageFactory(this::createPage);
		
		
	}
	
	
	private ObservableList<MemberVO> getTableviewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); 
		int totSize = allTableData.size();
		for (int i = from; i<= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		
		return currentPageData;
	}
	
	private Node createPage(int pageIndex) {
			
			from = pageIndex * itemsForPage;
			to = from + itemsForPage -1;
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
	public void deleteBlack(ActionEvent event) throws Exception {
		int select_ind = tableview.getSelectionModel().getSelectedIndex();
	      if( select_ind <= -1 ) {
	         errMsg("오류!", "데이터를 선택해주세요!!");
	         return;
	      }
		MemberVO mvo = new MemberVO();
		String mem_no = tableview.getSelectionModel().getSelectedItem().getMem_no();
		mvo.setMem_no(mem_no);
		int cnt = as.deleteBlackMember(mvo);
		if (cnt > 0) {
			infoMsg("블랙해제 결과", cnt + "명이 블랙해제 되었습니다.");
			ArrayList<MemberVO> list = new ArrayList<>();
			list = (ArrayList<MemberVO>) as.getAllBlackMember();
			allTableData.setAll(list);
			page_allMember.setPageFactory(this::createPage);
		} else {
			errMsg("블랙해제 결과", "오류");
			page_allMember.setPageFactory(this::createPage);
		}
		
		
	}
	
	@FXML
	public void blackCheck(ActionEvent event) throws Exception {
		try {
			Parent root =FXMLLoader.load(getClass().getResource("blackReady.fxml"));
			an_adminMain.getChildren().clear();
			an_adminMain.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
