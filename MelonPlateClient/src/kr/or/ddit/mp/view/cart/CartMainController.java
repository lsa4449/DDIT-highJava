package kr.or.ddit.mp.view.cart;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.cart.ICartService;
import kr.or.ddit.mp.view.cart.SMS;
import kr.or.ddit.mp.view.cart.SmsMessagePdu;
import kr.or.ddit.mp.vo.cart.CartVO;
import kr.or.ddit.mp.vo.cart.MileageVO;
import kr.or.ddit.mp.vo.cart.PayVO;
import kr.or.ddit.mp.vo.cart.RequestVO;
import kr.or.ddit.mp.vo.cart.EatdealVO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class CartMainController implements Initializable {

	@FXML Pagination page_cart;
	@FXML TableView<CartVO> tb_cartlist;
	@FXML TableColumn<CartVO, String> col_cartno;
	@FXML TableColumn<CartVO, String> col_dealname;
	@FXML TableColumn<CartVO, String> col_cartdate;
	@FXML Label lab_cart;
	@FXML Button btn_dealdel;
	@FXML Button btn_dealbuy;
	
	String smsNumber = null;
	
	private Registry reg;
	private ICartService icart;
	
	private int from, to, itemsForPage;
	private ObservableList<CartVO> allTableData ,currentPageData;
	
	private CartVO cvo = new CartVO();
	private MileageVO mvo = new MileageVO();
	private RequestVO revo = new RequestVO();
	private PayVO pvo = new PayVO();
	private EatdealVO eatvo = new EatdealVO();
	
	private int use_mile = 0;
	
	ArrayList<CartVO> list = new ArrayList<>();
	ArrayList<EatdealVO> eatInfoList = new ArrayList<>(); 
	ArrayList<MileageVO> mList = new ArrayList<>();
	boolean mileuse = false; // 마일리지 사용 여부를 체크 할 변수 조회하기를 누를때마다 false로, 사용하기 버튼 누를때마다 ture로 변함
	public static String couponnum = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_cartlist.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry("localhost", 8429);
			// 설정한 서버를 찾는 메소드 lookup
			
			icart = (ICartService) reg.lookup("cart");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		col_cartno.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_dealname.setCellValueFactory(new PropertyValueFactory<>("eatdeal_name"));
		col_cartdate.setCellValueFactory(new PropertyValueFactory<>("in_date"));
		//-----------------------------------------------------
		
		// 전체 정보 가져오기
		allTableData = FXCollections.observableArrayList();
		String loginid = LoginSession.session.getMem_no(); // 로그인 세션에 담긴 정보 
		cvo.setMem_no(loginid);
		mvo.setMem_no(loginid);
		
		try {
			list = (ArrayList<CartVO>) icart.getAllCartList(cvo); // 로그인 멤버의 장바구니 목록 가져옴
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

		allTableData.setAll(list);
		tb_cartlist.setItems(allTableData);
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_cart.setPageCount(totPageCount);	
		page_cart.setPageFactory(this::createPage);
		
		// 리스트에서 구매하기 버튼 클릭 시 팝업 창 호출
		btn_dealbuy.setOnAction( e -> {
			
			if(tb_cartlist.getSelectionModel().isEmpty()) {
		           errMsg("작업 오류", "구매 할 자료를 선택한 후 클릭하세요.");
		           return;
		        }
			// 구매하기 팝업 창 띄우기
			Stage dialog = new Stage(StageStyle.UTILITY);
    		dialog.initModality(Modality.APPLICATION_MODAL);
    		dialog.initOwner(btn_dealdel.getScene().getWindow());
    		
    		Parent parent = null;

    		try {
               parent = FXMLLoader.load(getClass().getResource("buyeatdeal.fxml"));
            }catch(IOException ex) {
               ex.printStackTrace();
            }
    		Button btn_select = (Button) parent.lookup("#btn_select"); // 메로니지 조회 버튼
    		Button btn_use = (Button) parent.lookup("#btn_use"); // 메로니지 사용 버튼
    		Button btn_buy = (Button) parent.lookup("#btn_buy"); // 구매하기 버튼
    		Button btn_can = (Button) parent.lookup("#btn_can"); // 취소하기 버튼
    		Button btn_up = (Button) parent.lookup("#btn_up"); // 수량증가 버튼
    		Button btn_down = (Button) parent.lookup("#btn_down"); // 수량감소 버튼
    		TextField txtf_eadname = (TextField) parent.lookup("#txtf_eadname"); // eat딜 이름 텍스트 필드
    		TextField txtf_mileage = (TextField) parent.lookup("#txtf_mileage"); // 마일리지 텍스트 필드
    		TextField txtf_cnt = (TextField) parent.lookup("#txtf_cnt"); // 수량 텍스트 필드
    		TextField txtf_price = (TextField) parent.lookup("#txtf_price"); // 금액 텍스트 필드
    		TextField txtf_num = (TextField) parent.lookup("#txtf_num"); // 전화번호 입력 텍스트 필드
    		
    		
    		CartVO cart = tb_cartlist.getSelectionModel().getSelectedItem(); // 이름 번호 가격 ~! 
    		cvo.setEatdeal_name(cart.getEatdeal_name()); // geteatdealinfo에 쓸 cvo에 eat딜 이름 넣어줌
//    		System.out.println(cvo.getEatdeal_name() + "여기!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    		// 여기서 번호 넣어줘 제발 cart_no 하면 되는데 어디다가?? 어디가가 셋 할꺼야? revo
    		revo.setCart_id(cart.getCart_no());
    		
    		
    		// eat딜 이름 불러오고 수정 불가능하게 하는거 
		    txtf_eadname.setText(cart.getEatdeal_name());
    		txtf_eadname.setEditable(false);
    		txtf_cnt.setText("1");
    		txtf_cnt.setEditable(false);
    		revo.setOrder_qty("1"); // 수량 증감 버튼을 하나도 안눌렀을 경우에 기본적으로 1개로 셋팅
     		txtf_mileage.setText("0");
    		
    		
    		
    		// 수량이랑 금액을 곱해서 결제 금액에 먼저 띄워주자
    		
    		for (int i = 0; i < list.size(); i++) {
    			String eat_name = list.get(i).getEatdeal_name();
    			
    			if(eat_name.equals(txtf_eadname.getText())) {
    				txtf_price.setText(list.get(i).getCart_price()); // 이름에 해당하는 eat딜 가격을 가져옴 
    				// 여기서 기본적으로 eat딜 가격 먼저 셋팅을 해주고 버튼 증감할때마다 가격이 달라지게 해서 셋팅해줘야함
    			}
    		}
    		txtf_price.setEditable(false); // 가격 마음대로 수정 못하게 만들기
    		
    		// 수량 증가 버튼 구현 cart_no(eat딜의 최대 수량)보다 넘어가면 증가 X
    		btn_up.setOnAction(e1 -> {
    			int cnt = Integer.parseInt(txtf_cnt.getText());
    			
    			int cart_no = 0;
    			cnt ++;
    			int eat_price = 0;
    			for (int i = 0; i < list.size(); i++) {
        			String eat_name = list.get(i).getEatdeal_name(); 
        			
        			if(eat_name.equals(txtf_eadname.getText())) { 
        				cart_no = Integer.parseInt(list.get(i).getCart_qty()); // 이름에 해당하는 eat딜 수량 가져옴
        				eat_price = Integer.parseInt(list.get(i).getCart_price()); // 이름에 해당하는 eat딜 가격 가져옴
        			}
        		}
    			
    			if(cnt > cart_no ) {
    				cnt = cart_no;
    				
    			} 
    			txtf_cnt.setText(Integer.toString(cnt));
    			revo.setOrder_qty(Integer.toString(cnt)); // 주문 수량이 증가할때마다 새로 셋팅해줌
    			int buy_pirce = (eat_price*cnt);
    			txtf_price.setText(Integer.toString(buy_pirce));

    		});
    		// 수량 감소 버튼 구현 1보다 감소하면 1로 셋팅
    		btn_down.setOnAction(e1 -> {
    			int cnt = Integer.parseInt(txtf_cnt.getText());
    			cnt --;
    			int eat_price = 0;
    			for (int i = 0; i < list.size(); i++) {
        			String eat_name = list.get(i).getEatdeal_name();
        			
        			if(eat_name.equals(txtf_eadname.getText())) {
        				eat_price = Integer.parseInt(list.get(i).getCart_price()); // 이름에 해당하는 eat딜 가격 가져옴
        				
        			}
        		}
    			if(cnt < 1) {
    				cnt = 1;
    				
    			}
    			txtf_cnt.setText(Integer.toString(cnt));
    			revo.setOrder_qty(Integer.toString(cnt)); // 주문 수량이 감소할때마다 새로 셋팅해줌
    			int buy_pirce = (eat_price*cnt);
    			txtf_price.setText(Integer.toString(buy_pirce));
    		});
    		
    		
    		
    		// 메로니지 조회하기 버튼 구현
    		btn_select.setOnAction(e1 -> {
    			mileuse = false; // 
    			MileageVO milevo = new MileageVO();
    			try {
    				mList = (ArrayList<MileageVO>) icart.getMileage(mvo);
    			}catch (RemoteException e3) {
    				e3.printStackTrace();
    			}
    			milevo = mList.get(0);
    			txtf_mileage.setText(milevo.getMelonage());
    			revo.setMelonage("0"); // revo 미사용 경우 0으로 셋팅
    			
    		}); // 메로니지 조회하기 버튼 구현 끝
   
    		// 메로니지 사용하기 버튼 구현 끝
    		btn_use.setOnAction(e2 -> {
    			
//    			txtf_mileage.getText();
    			
    			mileuse = true; // 마일리지 사용 했기 때문에 바꾼거
    			
    			MileageVO milevo = new MileageVO();
    			
        		if(mileuse) { // 회원의 마일리지를 조회해서 뿌려주고 가격도 조회해서 뿌려주면 될듯 getText안하고
        			int cnt 	= Integer.parseInt(txtf_cnt.getText());
        			
        			try {
        				mList = (ArrayList<MileageVO>) icart.getMileage(mvo);
        			}catch (RemoteException e3) {
        				e3.printStackTrace();
        			}
        			if(mList.size() != 0) {
        			milevo.setMelonage(mList.get(0).getMelonage());
        			}
        			int mileage = Integer.parseInt(milevo.getMelonage());
        			int use_mileage = 0;
        			if(txtf_mileage.getText().equals("")) {
        				errMsg("마일리지 입력해주세용", "사용하려는 마일리지 값을 입력해주세요!");
        				return;
        			} else {
        				use_mileage = Integer.parseInt(txtf_mileage.getText());
        			}
        			int	price = 0;
        			for (int i = 0; i < list.size(); i++) {
            			String eat_name = list.get(i).getEatdeal_name(); 
            			
            			if(eat_name.equals(txtf_eadname.getText())) {
            			price = Integer.parseInt(list.get(i).getCart_price()); // 이름에 해당하는 eat딜 가격 가져옴
            			}
            		}
        			// 조회해서 나온 값이랑 gettext해서 받아온 값이랑 다르면 gettext값 사용하고 
        			int real_mile = 0;
        			
        			if(mileage == use_mileage) {
        				real_mile = use_mileage;
        			
        			} if(use_mileage > mileage) {
        				errMsg("작업 오류", "사용하려는 마일리지가 보유 마일리지 보다 큽니다.");
        				return;
        			} if(use_mileage%100 != 0) {
        				errMsg("작업 오류", "마일리지는 100원 단위로 사용 가능합니다!");
        				return;
        			} if(use_mileage < mileage && use_mileage%100 == 0 ) {
        				real_mile = use_mileage;
        			} if(use_mileage > Integer.parseInt(txtf_price.getText())) {
        				errMsg("마일리지 이상해용", "사용하려는 마일리지가 결제금액보다 큽니다!");
        				return;
        			} if(use_mileage < 0) {
        				errMsg("마일리지 이상해용", "마일리지는 마이너스 값이 될 수 없어요!!");
        				return;
        			}
        			
        			String buy_price = Integer.toString(((price * cnt) - real_mile));
        			txtf_price.setText(buy_price);
        			revo.setMelonage(Integer.toString(real_mile)); // revo 사용 마일리지 셋팅
        			use_mile = real_mile; // 밑에 마일브이오에 추가해 줄 마일리지 사용값
        			revo.setOrder_price(buy_price); // 마일리지 사용해서 차감된 금액 셋팅
        		}
    		}); // 메로니지 사용하기 버튼 끝
    		
    		// 구매취소버튼
    		btn_can.setOnAction(event -> dialog.close());
    		
    		// 결제기능 완성되면 전화번호랑 같이 구현하면 되겠당
    		
    		btn_buy.setOnAction(e3 -> {
    			String price = txtf_price.getText();
    			smsNumber = txtf_num.getText();
    			
    			try {
    				eatInfoList = (ArrayList<EatdealVO>) icart.getEatdealInfo(cvo);
				} catch (RemoteException e4) {
					e4.printStackTrace();
				}    			
    			revo.setEat_name(eatInfoList.get(0).getEat_name());
    			revo.setEat_cont(eatInfoList.get(0).getEat_cont1());
    			revo.setEat_no(eatInfoList.get(0).getEat_no());
    			revo.setOrder_price(price);
    			revo.setMem_no(loginid);
//    			System.out.println(eatInfoList.get(0).getEat_name()); 정보 들어오는거 확인했음
    			
    			Stage dialog1 = new Stage(StageStyle.UTILITY);
        		dialog1.initModality(Modality.APPLICATION_MODAL);
        		dialog1.initOwner(btn_dealdel.getScene().getWindow());
        		
        		Parent parent1 = null;

        		try {
                   parent1 = FXMLLoader.load(getClass().getResource("pay_popup.fxml"));
                }catch(IOException ex) {
                   ex.printStackTrace();
                }
    			
    			JFXComboBox com_cardchoice = (JFXComboBox) parent1.lookup("#com_cardchoice"); //
    			JFXTextField txtf_buyprice = (JFXTextField) parent1.lookup("#txtf_buyprice");
    			JFXButton btn_buyfinish = (JFXButton) parent1.lookup("#btn_buyfinish");
    			JFXTextField txtf_cardno1 = (JFXTextField) parent1.lookup("#txtf_cardno1");
    			JFXTextField txtf_cardno2 = (JFXTextField) parent1.lookup("#txtf_cardno2");
    			JFXTextField txtf_cardno3 = (JFXTextField) parent1.lookup("#txtf_cardno3");
    			JFXTextField txtf_cardno4 = (JFXTextField) parent1.lookup("#txtf_cardno4");
    			JFXTextField txtf_cardcvc = (JFXTextField) parent1.lookup("#txtf_cardcvc");
    			
    			txtf_buyprice.setText(price);
    			
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
    			
    			ArrayList<RequestVO> relist = null;
    			
    			int cnt_req = 0;
    			int cnt_pay = 0;
    			int cnt_miluse = 0;
    			int cnt_miladd = 0;
    			
    			try {
    				cnt_req = icart.requestPayInsert(revo);
    				relist = (ArrayList<RequestVO>) icart.requestSelect(loginid);
				} catch (RemoteException e4) {
					e4.printStackTrace();
				}		
    			
    			// payVO 데이터 셋팅
    			PayVO pvo = new PayVO();
    			String ord_no = relist.get(0).getOrder_no();
    			String pay_com = (String) com_cardchoice.getSelectionModel().getSelectedItem();
    			String pay_price = revo.getOrder_price();
    			pvo.setOrder_no(ord_no);
    			pvo.setPay_com(pay_com);
    			pvo.setPay_price(pay_price);
    			
    			try {
					icart.payInsert(pvo);
				} catch (RemoteException e4) {
					e4.printStackTrace();
				}
    			
    			System.out.println("페이브이오까지 됨!!!!");
    			
    			// 마지막 마일리지 가져와서 적립마일리지랑 더해줘야함
    			int addmileprice = Integer.parseInt(price); // 마일리지 계산 해 줄 결제 가격
    			int addmile = (int) (addmileprice*0.01); // 결제 가격의 1%로 계산한 적합할 마일리지
    			int lastMile = 0;
    			if(mList.size() != 0) {
    				// mList.size가 지금 0이래 왜인지 찾아야해
    				lastMile = Integer.parseInt(mList.get(0).getMelonage());
    			} else {
    				System.out.println("니가 생각한 부분에서 오류가 난게 맞아");
    			}
    			
    			System.out.println(use_mile + "이거다!!!!!!!!!!");
    			String minusmile = Integer.toString(lastMile - use_mile);
    			String mel_use = Integer.toString(use_mile);
    			System.out.println(mel_use + "여기야~!@~!@~!@~!@~!@~!@~");
    			
    			// 마일vo셋팅
    			MileageVO milevo = new MileageVO();
    			milevo.setMem_no(loginid);
    			milevo.setOrder_no(ord_no);
    			
    			
    			if(mileuse) {
    				System.out.println("여기들어가진거니?????");
    				milevo.setMel_use(mel_use); // 사용한 마일리지
    				milevo.setMelonage(minusmile); // 현재 마일리지에서 사용한 마일리지 차감된거
    				try {
						cnt_miluse = icart.mileUseInsert(milevo);
					} catch (RemoteException e4) {
						e4.printStackTrace();
					}
    				
    			} else {
    				String mile = Integer.toString(lastMile + addmile);
    				milevo.setMelonage(mile);
    				milevo.setMel_add(Integer.toString(addmile));
    				try {
						cnt_miladd = icart.mileNotuseInsert(milevo);
					} catch (RemoteException e4) {
						e4.printStackTrace();
					}
    			}
    			System.out.println("마일리지 인서트 성공함!!!!!!!!!!!!!!!");
    			
    			// eat딜 테이블의 결제 가능 수량 감소해주자
    			int pay_qty = Integer.parseInt(revo.getOrder_qty()); // eat딜 결제 수량
    			int ori_qty = Integer.parseInt(eatInfoList.get(0).getEat_qty()); // 해당 eat딜의 판매가능 수량
    			int after_qty = ori_qty - pay_qty;
    			String deal_afterqty = Integer.toString(after_qty);
    			
    			EatdealVO downQtyparam = new EatdealVO();
    			downQtyparam.setEat_qty(deal_afterqty);
//    			downQtyparam.setEat_no(revo.getEat_no());
    			int eat_cnt = 0;
    			int tb_index = page_cart.getCurrentPageIndex() * itemsForPage +
    					tb_cartlist.getSelectionModel().getSelectedIndex();
    			downQtyparam.setEat_no(list.get(tb_index).getEat_no());
    			try {
					eat_cnt =  icart.eatdealQtyDown(downQtyparam);
				} catch (RemoteException e4) {
					e4.printStackTrace();
				}
    			
    			if(eat_cnt <= 0) {
    				errMsg("결제 에러!", "문제 있다고 합니다");
    				return;
    			}
    			
    			if(cnt_req != 0||cnt_pay != 0 ||cnt_miluse != 0 ||cnt_miladd != 0) {
    				infoMsg("결제 완료!", "문자의 쿠폰 번호를 확인하세요!");
    				dialog1.close();
    				dialog.close();
    				SendSMS();
    			}
    			
    		});
    			Scene scene = new Scene(parent1);
	            
	            dialog1.setScene(scene);
	            dialog1.setResizable(false);
	            dialog1.show();
	            
    		});
    		
    		
    		Scene scene = new Scene(parent);
            
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
            
		}); // 리스트 구매하기 버튼 메서드 끝
		
		
		
	    // 리스트에서 데이터 클릭 후 삭제하기 버튼 눌렀을때
		btn_dealdel.setOnAction( e -> {
	        if(tb_cartlist.getSelectionModel().isEmpty()) {
	           errMsg("작업 오류", "삭제 할 자료를 선택한 후 삭제하세요.");
	           return;
	        }
	       
	       
	    		
	    		Stage dialog = new Stage(StageStyle.UTILITY);
	    		dialog.initModality(Modality.APPLICATION_MODAL);
	    		dialog.initOwner(btn_dealdel.getScene().getWindow());
	    		
	    		Parent parent = null;

	    		try {
	               parent = FXMLLoader.load(getClass().getResource("realdel.fxml"));
	            }catch(IOException ex) {
	               ex.printStackTrace();
	            }
	    		Button btn_realdel = (Button) parent.lookup("#btn_realdel");
	            Button btn_nodel = (Button) parent.lookup("#btn_nodel");
	    		// 삭제 버튼 클릭 시 뜨는 팝업 창에서 진짜로 삭제 버튼
	            btn_realdel.setOnAction( e1 -> {
	    	        CartVO cvo = tb_cartlist.getSelectionModel().getSelectedItem();
	    	        
	    	        int cnt = 0;
	    	        try {
	    				cnt = icart.deleteCart(cvo);
	    			} catch (RemoteException e2) {
	    				e2.printStackTrace();
	    				System.out.println("나냐?");
	    			}
	    	        
	    	        if(cnt != 0) {
	    	        	allTableData.remove(tb_cartlist.getSelectionModel().getSelectedIndex());
	    	        	
	    	        	try {
	    	    			list = (ArrayList<CartVO>) icart.getAllCartList(cvo); // 전체 정보 가져온거다
	    	    		} catch (RemoteException e3) {
	    	    			e3.printStackTrace();
	    	    		}

	    	    		allTableData.setAll(list);
	    	    		tb_cartlist.setItems(allTableData);
	    	        	
	    	        	dialog.close();
	    	        }
	            });
	            // 삭제 버튼 클릭 시 뜨는 팝업 창에서 돌아가기 버튼 클릭
	            btn_nodel.setOnAction(event -> dialog.close());
	            
	            // 팝업창 넣어주기
	    		Scene scene = new Scene(parent);
	            
	            dialog.setScene(scene);
	            dialog.setResizable(false);
	            dialog.show();
	            
	        
	     });
	
	}

	
	 /**
	    * 오류 메시지 Alert창
	    * @param headerText
	    * @param msg
	    */
	   private void errMsg(String headerText, String msg) {
	      Alert errAlert = new Alert(AlertType.ERROR);
	      errAlert.setTitle("오류");
	      errAlert.setHeaderText(headerText);
	      errAlert.setContentText(msg);
	      errAlert.showAndWait();
	   }
	   
	   private void infoMsg(String headerText, String msg) {
		      Alert errAlert = new Alert(AlertType.INFORMATION);
		      errAlert.setTitle("정보 확인");
		      errAlert.setHeaderText(headerText);
		      errAlert.setContentText(msg);
		      errAlert.showAndWait();
		   }

	   private ObservableList<CartVO> getTableViewData(int from, int to){
			
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
			tb_cartlist.setItems(getTableViewData(from, to));
			
			return tb_cartlist;
		}
		
		
		private void SendSMS() {

	        SMS sms = new SMS();

	        sms.appversion("TEST/1.0");
	        sms.charset("utf8");
	        sms.setuser("phy5687", "ddit201!");				// coolsms 계정 입력해주시면되요

	        String number[] = new String[1];            // 받을 사람 폰번호
	        number[0] = smsNumber;

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
