package kr.or.ddit.mp.view.wantgo;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.wantgo.IWantgoService;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.WantgoVO;
import oracle.net.aso.i;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;

public class WantgoMypageController implements Initializable{

	@FXML TableView<WantgoVO> tb_wantGo;
	@FXML TableColumn<WantgoVO, String> col_wantNo;
	@FXML TableColumn<WantgoVO, String> col_wantName;
	@FXML TableColumn<WantgoVO, String> col_wantAdd;
	@FXML TableColumn<WantgoVO, String> col_wantDate;
	@FXML Text txt_want;
	@FXML Pagination page_wantGo;
	@FXML JFXButton btn_wantDel;
	
	
	private Registry reg;
	private IWantgoService wantgoService;
	
	private int from, to, itemsForPage;
	private ObservableList<WantgoVO> allTableData, currentPageData;
	private WantgoVO wv = new WantgoVO(); 
	private MemberVO mv = LoginSession.session;
	
	ArrayList<WantgoVO> list = new ArrayList<>();
	@FXML AnchorPane anpane_wantgo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_wantGo.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		mv = LoginSession.session;
		
		///------------------------RMI
		
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			wantgoService = (IWantgoService) reg.lookup("wantgoService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		///------------------------RMI
		
		assert tb_wantGo != null	 : "fx:id=\"tb_wantGo\" was not injected : check your FXML file 'mypage_wantGo.fxml'.";
		assert page_wantGo != null	 : "fx:id=\"searchTxt\" was not injected : check your FXML file 'mypage_wantGo.fxml'.";
		
		col_wantNo.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_wantName.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		col_wantAdd.setCellValueFactory(new PropertyValueFactory<>("com_addr"));
		col_wantDate.setCellValueFactory(new PropertyValueFactory<>("wantgo_indate"));
		//-----------------------------------------------------
		
		allTableData = FXCollections.observableArrayList();
		
		////////////////////// 로그인 세션에서 가져온 것
		String id = mv.getMem_id();
//		String mem_no = "10";
		String mem_no = mv.getMem_no();
		wv.setMem_no(mem_no);
		try {
			list = (ArrayList<WantgoVO>) wantgoService.selectWantgo(wv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		itemsForPage = 5;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_wantGo.setPageCount(totPageCount);	
		page_wantGo.setPageFactory(this::createPage);
		
		
		
	}
	
	private ObservableList<WantgoVO> getTableViewData(int from, int to){
		
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
		tb_wantGo.setItems(getTableViewData(from, to));
		
		return tb_wantGo;
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

	@FXML public void ac_delwantgo(ActionEvent event) throws RemoteException {
		
		
		
		System.out.println("여기까지 성공");
		int select_ind = tb_wantGo.getSelectionModel().getSelectedIndex();
		System.out.println("선택된 행 : " + select_ind);
		if( select_ind <= -1 ) {
			errMsg("오류!", "삭제할 맛집을 선택하세요!");
			return;
		}
		WantgoVO del_wv= list.get(select_ind);
		System.out.println(del_wv.getWantgo_no());
		int cnt = wantgoService.deleteWantgo(del_wv);
		if(cnt > 0) {
			infoMsg("가고싶은 맛집 삭제 안내", "선택된 맛집이 삭제되었습니다.");
			ArrayList<WantgoVO> list = new ArrayList<>();
			list = (ArrayList<WantgoVO>) wantgoService.selectWantgo(wv);
			allTableData.setAll(list);
			page_wantGo.setPageFactory(this::createPage);
		}else {
			errMsg("가고싶은 맛집 삭제 안내 결과", "삭제된 맛집이 없습니다.");
			page_wantGo.setPageFactory(this::createPage);
		}
		
	}
	
	
	
}
