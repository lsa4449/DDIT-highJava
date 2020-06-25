package kr.or.ddit.mp.view.reservation;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.reservation.IReservationService;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.reservation.ReserOptionVO;
import kr.or.ddit.mp.vo.reservation.ReservationVO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class ReserOptionController2 implements Initializable{

	@FXML AnchorPane ap_write;
    @FXML DatePicker date_sel;
    
    @FXML Text txt_comName;
    @FXML JFXTextField txtf_etc;
   
    @FXML JFXComboBox cb_person;
    @FXML JFXComboBox cb_openTime;
    @FXML JFXComboBox cb_closeTime;
    
    @FXML JFXButton btn_ok;
    
    //예약 서비스 
  	private Registry reg;
  	private IReservationService iros;
  	
  	//업체 예약정보 셋팅해주는 vo
  	ReserOptionVO vo = new ReserOptionVO();
  	ArrayList<ReserOptionVO> list = new ArrayList<>();
  	ArrayList<ComVO> clist = new ArrayList<>();
  	
  	//로그인유저정보 받기
  	ComVO cv = LoginSession.comsession;
  	String user;
  	String comName;
  	
  	//토글 키 사용하기
  	boolean flag_toggle = true;
  	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			iros = (IReservationService) reg.lookup("reservation");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		user 	= cv.getCom_id();
		comName = cv.getCom_name();
		
		txt_comName.setText(comName);
		
		try {
			clist  = (ArrayList<ComVO>) iros.selectComFirstOption(user);
			
			if(clist.size()!=0) {
				String com_open = clist.get(0).getCom_opentime();
				String com_close = clist.get(0).getCom_closetime();
				
				cb_person.setPromptText("인원수 선택하기");
			    cb_openTime.setValue(com_open);
			    cb_closeTime.setValue(com_close);
				
				//인원설정
				ObservableList<String> cb_data1 = FXCollections.observableArrayList(
						"2","3","4","5","6"
						);
				cb_person.setItems(cb_data1);				
				
				ObservableList<String> cb_data2 = FXCollections.observableArrayList(
						"9","10","11","12","13","14","15","16","17","18","19","20","21","22"
						);
				cb_openTime.setItems(cb_data2);
				
				ObservableList<String> cb_data3 = FXCollections.observableArrayList(
						"11","12","13","14","15","16","17","18","19","20","21","22","23","24"
						);
				cb_closeTime.setItems(cb_data3);
				
			}else {
				cb_person.setPromptText("인원수 선택하기");
			    cb_openTime.setPromptText("오픈시간 선택하기");
			    cb_closeTime.setPromptText("마감시간 선택하기");
				
				//인원설정
				ObservableList<String> cb_data1 = FXCollections.observableArrayList(
						"2","3","4","5","6"
						);
				
			    cb_person.setItems(cb_data1);
				
			    //오픈시간
			    ObservableList<String> cb_data2 = FXCollections.observableArrayList(
			    		"11","12","13","14","15","16","17","18","19","20","21","22"
						);
			    
			    cb_openTime.setItems(cb_data2);
			    
			    //마감시간
			    ObservableList<String> cb_data3 = FXCollections.observableArrayList(
			    		"11","12","13","14","15","16","17","18","19","20","21","22","23","24"
						);
			    
			    cb_closeTime.setItems(cb_data3);
			}
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		
	    
	    
	    
	    /**
		 *  예약 옵션 데이터 출력하기
		 */
		//로그인유저(업체)정보 받아오기
		user = cv.getCom_id();
		
		try {
			list = (ArrayList<ReserOptionVO>) iros.selectComOption(user);
			
			if(list.size()!=0) {
			    cb_person.setValue(list.get(0).getOp_person().toString());
			    cb_openTime.setValue(list.get(0).getOp_time().toString());
			    cb_closeTime.setValue(list.get(0).getCl_time().toString());	
			    
			    if(list.get(0).getEtc_option()!=null) {
			    	txtf_etc.setText(list.get(0).getEtc_option().toString());
			    }
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	    
	    String pattern = "yyyy-MM-dd";
	    
	    date_sel.setPromptText(pattern.toLowerCase());
	    	    
	    date_sel.setOnAction(event -> {
	    	
	        LocalDate date = date_sel.getValue();
	        
	        vo.setOp_breakday(date_sel.getValue().toString());
	    });
	   
//		vo.setOp_breakday(op_breakday);	//휴일
//		vo.setEtc_option(etc_option);	//기타 안내멘트
	    
	    btn_ok.setOnAction(option->{
	    	
	    		//vo셋팅 값 : 사업자번호, 오픈시간, 마감시간, 인원설정, 기타 
	    	    vo.setCom_id(user);//업체사업자번호
	    	    try {
					clist  = (ArrayList<ComVO>) iros.selectComFirstOption(user);
				} catch (RemoteException e1) {
					
					e1.printStackTrace();
				}
	    	    if(cb_person.getValue()!=null) {
	    	    	vo.setOp_person(cb_person.getValue().toString());	
	    	    }else {
	    	    	errMsg("실패!", "인원수를 설정해주세요");
					return;
	    	    }
	    	    
	    	    if(cb_openTime.getValue()!=null) {
	    	    	vo.setOp_time(cb_openTime.getValue().toString());	
	    	    }else {
	    	    	errMsg("실패!", "오픈시간 설정해주세요");
					return;
	    	    }
	    	    
	    	    if(cb_closeTime.getValue()!=null) {
	    	    	vo.setCl_time(cb_closeTime.getValue().toString());	
	    	    }else {
	    	    	errMsg("실패!", "마감시간 설정해주세요");
					return;
	    	    }
	    	    
	    	    if(txtf_etc.getText()!=null) {
	    	    	vo.setEtc_option(txtf_etc.getText().toString());
	    	    }
	    		
	    		int ch_in = 0; 
			    
			    try {
					ch_in = iros.insertReserOption(vo);
					
					if(ch_in > 0) {
						infoMsg("성공!", "예약 옵션이 설정되었습니다!");
						
					}else {
						errMsg("실패!", "오류를 확인해주세요.");
						return;
					}
					
				} catch (RemoteException e) {
				
					e.printStackTrace();
				}
	    		
	    	
	    });
	    
		
		
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
}


