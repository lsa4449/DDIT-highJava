package kr.or.ddit.mp.view.reservation;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.reservation.IReservationService;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.reservation.ReservationVO;
import kr.or.ddit.mp.vo.review.ReviewVO;

public class ReserVationMyResController implements Initializable {

	@FXML AnchorPane 	ap_home;
	@FXML Pagination 	page_chRes;
	@FXML JFXButton  	btn_reCancel;
	@FXML JFXTextField  lab_nick;
	
	@FXML TableView<ReservationVO> tb_chRes;
	@FXML TableColumn<ReservationVO, String>	col_no;
	@FXML TableColumn<ReservationVO, String> 	col_comName;
	@FXML TableColumn<ReservationVO, String> 	col_name;
	@FXML TableColumn<ReservationVO, String> 	col_person;
	@FXML TableColumn<ReservationVO, String> 	col_date;
	@FXML TableColumn<ReservationVO, String> 	col_time;
	@FXML TableColumn<ReservationVO, String> 	col_status;
	
	//예약 서비스 
  	private Registry reg;
  	private IReservationService iros;
  	
  	//vo 객체생성
  	ReservationVO vo = new ReservationVO();
  	
  	//list 객체 생성
  	ArrayList<ReservationVO> list = new ArrayList<>();
  	
  	//로그인유저정보 받기
  	MemberVO mv = LoginSession.session;
  	String user = mv.getMem_no();
  	
  	//페이지네이션
  	private int from, to, itemsForPage;
  	private ObservableList<ReservationVO> allTableData, currentPageData;
  	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_chRes.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			iros = (IReservationService) reg.lookup("reservation");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		col_no.setCellValueFactory(new PropertyValueFactory<>("res_no"));
		col_comName.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		col_person.setCellValueFactory(new PropertyValueFactory<>("res_person"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("res_date"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("res_time"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("res_st"));
		
		allTableData = FXCollections.observableArrayList();
		
		System.out.println("table 테스트");
		
		/**
		 * 예약 현황 출력하기 
		 */
		//로그인유저정보 셋팅
		user = mv.getMem_no();
		vo.setMem_no(user);
		
		try {
			list = (ArrayList<ReservationVO>) iros.selectMyReservation(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
//		System.out.println(list.get(0).getRes_no()+"예약번호");
//		System.out.println(list.get(0).getMem_nick()+"닉네임");
//		System.out.println(list.get(0).getRes_date()+"예약일자");

		lab_nick.setText(mv.getMem_nick()+" 님");
		
		allTableData.setAll(list);
		
		tb_chRes.setItems(allTableData);
		
		System.out.println("selectReview 테스트");
		
		setPagination();
		
		btn_reCancel.setOnAction(event->{
			
			String sel_resNO = tb_chRes.getSelectionModel().getSelectedItem().getRes_no();
			
			vo.setMem_no(user);
			vo.setRes_no(sel_resNO);
			
			int ch_int = 0;
			
			try {
				ch_int = iros.deleteReservation(vo);
				
				if(ch_int > 0) {
					infoMsg("성공!", "예약 취소가 완료되었습니다!");
					refresh();
					
				}else {
					errMsg("실패!", "오류를 확인해주세요.");
					return;
				}
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
		
		refresh();
	}

	//페이지네이션에 필요한 메서드 - 1
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		tb_chRes.setItems(getTableViewData(from, to));
		
		System.out.println("페이지 메서드1");
		
		return tb_chRes;
	}
	
	//페이지네이션에 필요한 메서드 - 2
	private ObservableList<ReservationVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); 
		int totSize = allTableData.size();
		for (int i = from; i<= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		
		System.out.println("페이지 메서드2");
		
		return currentPageData;
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
	
	//페이지네이션----------------------------------------------------	
	private void setPagination() {

		itemsForPage = 16;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_chRes.setPageCount(totPageCount);	
			
				page_chRes.setPageFactory(this::createPage);

	}
	
	//수정이나, 삭제 후 이전화면에 셋팅해주기
	private void refresh() {
		
		try {
			list = (ArrayList<ReservationVO>) iros.selectMyReservation(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_chRes.setItems(allTableData);
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_chRes.setPageCount(totPageCount);	
	
				page_chRes.setPageFactory(this::createPage);
		
	}
}
