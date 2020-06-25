package kr.or.ddit.mp.view.reservation;

import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.reservation.IReservationService;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.reservation.ReserOptionVO;
import kr.or.ddit.mp.vo.reservation.ReservationVO;

public class ReservationUserController implements Initializable{
	
	@FXML AnchorPane ap_write;
	
	@FXML JFXTextField txtf_name;	// 예약자명
	@FXML JFXTextField txtf_tel;	// 예약자 연락처
	
	@FXML JFXButton btn_cancel;// 신청취소 버튼
	@FXML JFXButton btn_request;// 신청완료 버튼
	
	@FXML DatePicker date_sel;
	
	@FXML JFXComboBox cb_q_time;
	@FXML JFXComboBox cb_q_person;
	
	@FXML Text txt_breakday; //업체 쉬는 날
	@FXML Text txt_etc; //업체 기타 정보
	
	//예약서비스
	private Registry reg;
  	private IReservationService iros;
  	
  	//업체 예약정보 셋팅해주는 vo
  	ReservationVO vo = new ReservationVO();
  	ReserOptionVO opvo = new ReserOptionVO();
  	ArrayList<ReserOptionVO> list = new ArrayList<>();
  	ArrayList<ComVO> clist = new ArrayList<>(); 
  	
  	//예약번호 자동생성하기
  	String cre_resNo = null;
  	String openTime  = null;
  	
  	MemberVO mv = LoginSession.session;
  	
  	public static String get_resv_com_id = "";
  	
  	
  	//예약옵션 설정하기
  	int person_from = 0;
	int person_to 	= 0;
	int time_from 	= 0;
	int time_to 	= 0;
	ArrayList<String> set_time 	 = new ArrayList<>();
	ArrayList<String> set_person = new ArrayList<>();
  	
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
		
		cb_q_time.setPromptText("예약시간 선택하기");
		cb_q_person.setPromptText("인원수 선택하기");
		
		try {
			list  = (ArrayList<ReserOptionVO>) iros.selectComOption(get_resv_com_id);
			clist = (ArrayList<ComVO>) iros.chiceComResOption(get_resv_com_id);
			if(list.size()!=0) {
				cb_q_time.setValue(list.get(0).getOp_time().toString());
				cb_q_person.setValue(list.get(0).getOp_person().toString());
				txt_breakday.setText("휴무일 "+list.get(0).getOp_breakday().toString()+"제외하고 선택해주세요 :)");
				
				if(list.get(0).getEtc_option()!=null) {
					txt_etc.setText(list.get(0).getEtc_option().toString());	
				}else {
					txt_etc.setText("");
				}
				
				person_from = 2;
				person_to 	= Integer.parseInt(list.get(0).getOp_person());
				time_from 	= Integer.parseInt(list.get(0).getOp_time());
				time_to 	= Integer.parseInt(list.get(0).getCl_time());
				
				for(int i=person_from; i<=person_to; i++) {
					set_person.add(String.valueOf(i));
				}
				
				for(int i=time_from; i<=time_to; i++) {
					set_time.add(String.valueOf(i));
				}
				
				//인원설정
				ObservableList<String> cb_data1 = FXCollections.observableArrayList(
					set_person
				);
				cb_q_person.setItems(cb_data1);
				
				
			    //시간대
			    ObservableList<String> cb_data2 = FXCollections.observableArrayList(
			    	set_time
				);
			    cb_q_time.setItems(cb_data2);
			    
			    
			    
			}else {
				txt_etc.setText("");
				txt_breakday.setText("*날짜를 선택해주세요");
				time_from 	= Integer.parseInt(clist.get(0).getCom_opentime()); 
				time_to		= Integer.parseInt(clist.get(0).getCom_closetime());
				
				for(int i=time_from; i<time_to; i++) {
					set_time.add(String.valueOf(i));
				}
				
				//인원설정
				ObservableList<String> cb_data1 = FXCollections.observableArrayList(
						"2","3","4","5","6"
						);
				
				cb_q_person.setItems(cb_data1);
				
				//업체 운영시간
			    ObservableList<String> cb_data2 = FXCollections.observableArrayList(
			    		set_time
						);
			    
			    cb_q_time.setItems(cb_data2);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String pattern = "yyyy-MM-dd";
		
		date_sel.setPromptText(pattern.toLowerCase());
		date_sel.setOnAction(event -> {
	    	
	        LocalDate date = date_sel.getValue();
	        
	        
	    });
		
		/* 로그인시 회원 mem_no 받아오기 */
		vo.setMem_no(mv.getMem_no());
		
		/* 예약신청시 업체 사업자번호 com_id 받아오기 */
		vo.setCom_id(get_resv_com_id);
		
		//예약번호 자동생성하기
		String random_res1 = String.valueOf((int) (Math.random()*5000));
		String random_res2 = String.valueOf((int) (Math.random()*5000));
		
		String[] comId = get_resv_com_id.split("-");
		System.out.println(comId.toString());
		
		cre_resNo = random_res1+random_res2;
		
		
		btn_request.setOnAction(event->{
			
			int ch_in = 0; 
		    
			try {
				vo.setRes_date(date_sel.getValue().toString());
				vo.setMem_name(txtf_name.getText());
				vo.setMem_hp(txtf_tel.getText());
				vo.setRes_no(cre_resNo);
				vo.setRes_person(cb_q_person.getSelectionModel().getSelectedItem().toString());	
				vo.setRes_time(cb_q_time.getSelectionModel().getSelectedItem().toString());	
				
				ch_in = iros.insertReservation(vo);
				
				if(ch_in > 0) {
					infoMsg("성공!", "예약신청이 완료되었습니다!");
					Stage stage = (Stage) btn_request.getScene().getWindow();
				    stage.close();
				    
				}else {
					errMsg("실패!", "오류를 확인해주세요.");
					return;
				}
			} catch (RemoteException e) {

				e.printStackTrace();
			}
			
		});
		
		btn_cancel.setOnAction(close->{
			
			Stage stage = (Stage) btn_cancel.getScene().getWindow();
		    stage.close();
			
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
