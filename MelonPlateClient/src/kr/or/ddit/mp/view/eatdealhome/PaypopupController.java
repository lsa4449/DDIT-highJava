package kr.or.ddit.mp.view.eatdealhome;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.model.DataModel;
import kr.or.ddit.mp.service.comeatdeal.IComEatdealService;
import kr.or.ddit.mp.service.paydeal.IPaydealService;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.eatdeal.PayVO;
import kr.or.ddit.mp.vo.eatdeal.RequestVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.MileageVO;

public class PaypopupController implements Initializable{
	
//	public static String lastMil = "";
//	
	@FXML JFXTextField 			txtf_buyprice;
    @FXML JFXComboBox<String> 	com_cardchoice; 
    @FXML JFXTextField   		txtf_cardno1;  
    @FXML JFXTextField   		txtf_cardno2;  
    @FXML JFXTextField   		txtf_cardno3;  
    @FXML JFXTextField   		txtf_cardno4;  
    @FXML JFXTextField   		txtf_cardcvc;  
    @FXML JFXButton   			btn_buyfinish;  
	private Registry reg;
	private IPaydealService paydeal;
	
	//쿠폰 번호
	public static String couponnum = null;
	
	MemberVO mv = LoginSession.session; 
	RequestVO revo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		revo = DataModel.orderInfo.get(DataModel.orderInfo.size()-1);
		DataModel.orderInfo.clear();
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			paydeal = (IPaydealService) reg.lookup("paydealService");
			System.out.println("IPaydealServiceRMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ObservableList<String> cardlist = FXCollections.observableArrayList(
				"국민카드","신한카드","우리카드","농협카드","삼성카드","현대카드","롯데카드","카카오뱅크",
				"케이뱅크","비씨은행","신협카드"
				);
		
		com_cardchoice.setItems(cardlist);
		
		btn_buyfinish.setOnAction(pay->{
			if(
				(txtf_cardno1.getText().equals("") || txtf_cardno1.getText()==null)	||
				(txtf_cardno2.getText().equals("") || txtf_cardno2.getText()==null)	||
				(txtf_cardno3.getText().equals("") || txtf_cardno3.getText()==null)	||
				(txtf_cardno4.getText().equals("") || txtf_cardno4.getText()==null)	||
				(txtf_cardcvc.getText().equals("") || txtf_cardcvc.getText()==null)
					) {
				errMsg("경고!", "빈 항목이 있는지 확인해보세요");
				return;
			}
			
			int cnt_req = 0;
			int cnt_pay = 0;
			int cnt_miluse = 0;
			int cnt_miladd = 0;
			
			String mem_no = mv.getMem_no();
			
			
			ArrayList<RequestVO> relist = null;
			try {
				cnt_req 	= paydeal.requestPayInsert(revo);
				relist		= (ArrayList<RequestVO>) paydeal.requestSelect(mem_no);
			
			} catch (RemoteException e) {
			
				e.printStackTrace();
			}
			
			//Pay에 넣을 VO 셋팅
			PayVO payvo = new PayVO();
			String ord_no = relist.get(0).getOrder_no();
			String pay_com = com_cardchoice.getSelectionModel().getSelectedItem();
			String pay_price = revo.getOrder_price();
			payvo.setOrder_no(ord_no);
			payvo.setPay_com(pay_com);
			payvo.setPay_price(pay_price);
			try {
				cnt_pay		= paydeal.payInsert(payvo);
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("페이브이오까지 됨!!!!");
			
			// 마일리지 vo 셋팅
			int add_mil = (int) (Integer.parseInt(EatdealHomeController.lastPrice) * 0.01);
			int mile = Integer.parseInt(EatdealHomeController.lastMil);
			
			MileageVO milvo = new MileageVO();
			milvo.setMem_no(mem_no);
			milvo.setOrder_no(ord_no);
			milvo.setMel_add(Integer.toString(add_mil));
			milvo.setMel_use(EatdealHomeController.useMil);
			
			
			System.out.println("마일리지브이오까지 됨!!!!");
			
			
			if(EatdealHomeController.useMil.equals("0")) {
				mile = Integer.parseInt(EatdealHomeController.lastMil) + add_mil;
				milvo.setMelonage(Integer.toString(mile));
				try {
					cnt_miladd		= paydeal.mileNotuseInsert(milvo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}else {
				milvo.setMelonage(Integer.toString(mile));
				try {
					cnt_miluse		= paydeal.mileUseInsert(milvo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			
			}
			
			// 가능한 잇딜 개수 셋팅
			int pay_qty = Integer.parseInt(revo.getOrder_qty());
			int ori_qty = EatdealHomeController.get_can_qty;
			int after_qty = ori_qty - pay_qty;
			String atr_afterqty = Integer.toString(after_qty);
			
			EatdealVO eatvo = new EatdealVO();
			eatvo.setEat_no(revo.getEat_no());
			eatvo.setEat_qty(atr_afterqty);
			int eat_cnt = 0;
			try {
				eat_cnt =  paydeal.eatdealQtyDown(eatvo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if(eat_cnt <= 0) {
				errMsg("결제 에러!", "문제가 있어!");
				return;
			}
			
			if(cnt_req != 0||cnt_pay != 0 ||cnt_miluse != 0 ||cnt_miladd != 0) {
				infoMsg("결제 완료!", "문자의 쿠폰 번호를 확인하세요!");
				SendSMS();
				
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("eatdealhome.fxml"));
			Parent pr = null;
			try {
				pr = loader.load();
				EatdealHomeController cont = loader.getController();
				Text txt_qty = (Text) pr.lookup("#txt_qty");
				
				txt_qty.setText(atr_afterqty);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			Stage sta = (Stage) btn_buyfinish.getScene().getWindow();
			sta.close();
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
	
	private void SendSMS() {

        SMS sms = new SMS();

        sms.appversion("TEST/1.0");
        sms.charset("utf8");
        sms.setuser("phy5687", "ddit201!");				// coolsms 계정 입력해주시면되요

        String number[] = new String[1];            // 받을 사람 폰번호
        number[0] = "01030414611";

        //쿠폰번호 난수
        couponnum();
        System.out.println(couponnum);
        for( int i = 0 ; i < number.length ; i ++ ) {
        	
	        SmsMessagePdu pdu = new SmsMessagePdu();
	        pdu.type = "SMS";
	        pdu.destinationAddress = number[i];
	        pdu.scAddress = "01056518002"	;       // 발신자 번호          
	        pdu.text = 
	        		" MelonPlate 쿠폰 발급\n"
	    			+ revo.getEat_name()+"\n"
	    			+ "● 쿠폰 번호 : "+couponnum+"\n";				// 보낼 메세지 내용

	        sms.add(pdu);

	        try {
	        	
	            sms.connect();
	            sms.send();
	            sms.disconnect();

	        } catch (IOException e) {

	            System.out.println(e.toString());
	        }

	        sms.printr();
	        sms.emptyall();
	    }
       
    }
	
	

	public static void couponnum(){
        
		// 실행시 ???개 쿠폰 생성
		int couponSize = 1;

		final char[] possibleCharacters = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };

		final int possibleCharacterCount = possibleCharacters.length;
		String[] arr = new String[couponSize];
		Random rnd = new Random();
		int currentIndex = 0;
		int i = 0;
		while (currentIndex < couponSize) {
			StringBuffer buf = new StringBuffer(16);
			// i는 8자리의 랜덤값을 의미
			for (i = 8; i > 0; i--) {
				buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
			}
			couponnum = buf.toString();
			System.out.println("couponnum==>" + couponnum);
			arr[currentIndex] = couponnum;
			currentIndex++;
		}
	     
	}
}
