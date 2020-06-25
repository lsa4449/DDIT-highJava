package kr.or.ddit.mp.view.reservation;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.reservation.IReservationService;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.reservation.ReservationVO;

public class ReserComTableController implements Initializable{

	@FXML Pagination page_reservation;
    
    @FXML TableView<ReservationVO> tb_all;
    @FXML TableView<ReservationVO> tb_noshow;
	@FXML TableView<ReservationVO> tb_back;
	@FXML TableView<ReservationVO> tb_approve;
	
	@FXML JFXComboBox combo_select;
	
    @FXML TableColumn<ReservationVO, String> col_no;
    @FXML TableColumn<ReservationVO, String> col_status;
    @FXML TableColumn<ReservationVO, String> col_name;
    @FXML TableColumn<ReservationVO, String> col_time;
    @FXML TableColumn<ReservationVO, String> col_person;
    @FXML TableColumn<ReservationVO, String> col_tel;
    @FXML TableColumn<ReservationVO, String> col_noshow;
    
    @FXML JFXTextField txtf_com_name;    
    @FXML JFXButton btn_print;
    @FXML JFXButton btn_ok;
    @FXML JFXButton btn_back;
    @FXML JFXButton btn_noshow;
	
    //예약 서비스 
  	private Registry reg;
  	private IReservationService iros;
  	
  	//페이지네이션
  	private int from, to, itemsForPage;
  	private ObservableList<ReservationVO> allTableData, currentPageData;
  	
  	//객체를 담을 vo, list
  	ReservationVO vo = new ReservationVO();
  	ArrayList<ReservationVO> list = new ArrayList<>();
	
  	//로그인유저정보 받기
  	ComVO cv = LoginSession.comsession;
  	String user;
  	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_all.getStylesheets().add(getClass().getResource("/tableview.css").toString());

		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			iros = (IReservationService) reg.lookup("reservation");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ObservableList<String> cb_data = FXCollections.observableArrayList(
				"전체보기","대기","승인","반려","노쇼"
				);
		
		combo_select.setItems(cb_data);
		
		col_no.setCellValueFactory(new PropertyValueFactory<>("res_no"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("res_st"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("res_date"));
		col_person.setCellValueFactory(new PropertyValueFactory<>("res_person"));
		col_tel.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
		col_noshow.setCellValueFactory(new PropertyValueFactory<>("no_show"));
		
		allTableData = FXCollections.observableArrayList();
		
		System.out.println("table 테스트");
		
		/**
		 *  예약 리스트 출력하기
		 */
		//로그인유저(업체)정보 받아오기
		user = cv.getCom_id();
		txtf_com_name.setText(cv.getCom_name());
		
		try {
			list = (ArrayList<ReservationVO>) iros.selectReserList(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
				
		allTableData.setAll(list);
		
		tb_all.setItems(allTableData);	
		
		setPagination();		
		
		//조회를 클릭하면
		btn_print.setOnAction(event1->{
			
			if(combo_select.getValue().toString().equals("전체보기")) {
				
				refresh();
			}
			
			if(combo_select.getValue().toString().equals("승인")) {
				
				col_no.setCellValueFactory(new PropertyValueFactory<>("res_no"));
				col_status.setCellValueFactory(new PropertyValueFactory<>("res_st"));
				col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
				col_time.setCellValueFactory(new PropertyValueFactory<>("res_date"));
				col_person.setCellValueFactory(new PropertyValueFactory<>("res_person"));
				col_tel.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
				col_noshow.setCellValueFactory(new PropertyValueFactory<>("no_show"));
				
				allTableData = FXCollections.observableArrayList();
				
				try {
					list = (ArrayList<ReservationVO>) iros.selectReserApprove(user);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				allTableData.setAll(list);
				
				tb_all.setItems(allTableData);	
				
				setPagination();
			}
			
			if(combo_select.getValue().toString().equals("대기")) {
				
				col_no.setCellValueFactory(new PropertyValueFactory<>("res_no"));
				col_status.setCellValueFactory(new PropertyValueFactory<>("res_st"));
				col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
				col_time.setCellValueFactory(new PropertyValueFactory<>("res_date"));
				col_person.setCellValueFactory(new PropertyValueFactory<>("res_person"));
				col_tel.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
				col_noshow.setCellValueFactory(new PropertyValueFactory<>("no_show"));
				
				allTableData = FXCollections.observableArrayList();
				
				try {
					list = (ArrayList<ReservationVO>) iros.selectReserWait(user);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				allTableData.setAll(list);
				
				tb_all.setItems(allTableData);	
				
				setPagination();
			}
			
			if(combo_select.getValue().toString().equals("반려")) {
				
				col_no.setCellValueFactory(new PropertyValueFactory<>("res_no"));
				col_status.setCellValueFactory(new PropertyValueFactory<>("res_st"));
				col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
				col_time.setCellValueFactory(new PropertyValueFactory<>("res_date"));
				col_person.setCellValueFactory(new PropertyValueFactory<>("res_person"));
				col_tel.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
				col_noshow.setCellValueFactory(new PropertyValueFactory<>("no_show"));
				
				allTableData = FXCollections.observableArrayList();
				
				try {
					list = (ArrayList<ReservationVO>) iros.selectReserBack(user);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				allTableData.setAll(list);
				
				tb_all.setItems(allTableData);	
				
				setPagination();
			}
			
			if(combo_select.getValue().toString().equals("노쇼")) {
				
				col_no.setCellValueFactory(new PropertyValueFactory<>("res_no"));
				col_status.setCellValueFactory(new PropertyValueFactory<>("res_st"));
				col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
				col_time.setCellValueFactory(new PropertyValueFactory<>("res_date"));
				col_person.setCellValueFactory(new PropertyValueFactory<>("res_person"));
				col_tel.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
				col_noshow.setCellValueFactory(new PropertyValueFactory<>("no_show"));
				
				allTableData = FXCollections.observableArrayList();
				
				try {
					list = (ArrayList<ReservationVO>) iros.selectReserNoshow(user);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				allTableData.setAll(list);
				
				tb_all.setItems(allTableData);	
				
				setPagination();
			}
			
		});
		
		//승인해주기
		btn_ok.setOnAction(event2->{
			
			ReservationVO app_vo = new ReservationVO();
			
			String sel_resNO = tb_all.getSelectionModel().getSelectedItem().getRes_no();
			
			System.out.println(sel_resNO);
			
			app_vo.setRes_no(sel_resNO);
			
			int ch_ok = 0;
			
			try {
				ch_ok = iros.approveReservation(app_vo);
				
				if(ch_ok > 0) {
					infoMsg("성공!", "예약 승인이 완료되었습니다!");
					refresh();
					
				}else {
					errMsg("실패!", "예약 상태를 다시 한번 더 확인해주세요.");
					return;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
		
		//반려하기
		btn_back.setOnAction(event3->{
			
			ReservationVO back_vo = new ReservationVO();
			
			String sel_resNO = tb_all.getSelectionModel().getSelectedItem().getRes_no();
			
			System.out.println(sel_resNO);
			
			back_vo.setRes_no(sel_resNO);
			
			int ch_ok = 0;
			
			try {
				ch_ok = iros.backReservation(back_vo);
				
				if(ch_ok > 0) {
					infoMsg("성공!", "예약 반려가 완료되었습니다!");
					refresh();
					
				}else {
					errMsg("실패!", "예약 상태를 다시 한번 더 확인해주세요.");
					return;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
		
		//노쇼하기
		btn_noshow.setOnAction(event3->{
			
			ReservationVO noshow_vo = new ReservationVO();
			
			String sel_resNO = tb_all.getSelectionModel().getSelectedItem().getRes_no();
			
			System.out.println(sel_resNO);
			
			noshow_vo.setRes_no(sel_resNO);
			
			int ch_ok = 0;
			
			try {
				ch_ok = iros.noshowReservation(noshow_vo);
				
				if(ch_ok > 0) {
					infoMsg("성공!", "예약 노쇼처리가 완료되었습니다!");
					refresh();
					
				}else {
					errMsg("실패!", "예약 상태를 다시 한번 더 확인해주세요.");
					return;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	//페이지네이션----------------------------------------------------	
	private void setPagination() {

		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_reservation.setPageCount(totPageCount);	
			
				page_reservation.setPageFactory(this::createPage);

	}


	//페이지네이션에 필요한 메서드 - 1
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		tb_all.setItems(getTableViewData(from, to));
		
		System.out.println("페이지 메서드1");
		
		return tb_all;
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
	
	//수정이나, 삭제 후 이전화면에 셋팅해주기
	private void refresh() {
		
		user = cv.getCom_id();
		try {
			list = (ArrayList<ReservationVO>) iros.selectReserList(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_all.setItems(allTableData);
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_reservation.setPageCount(totPageCount);	
	
				page_reservation.setPageFactory(this::createPage);
		
		
	}
}
