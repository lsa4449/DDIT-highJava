package kr.or.ddit.mp.view.eatdealhome;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.main.TopMemberController;
import kr.or.ddit.mp.model.DataModel;
import kr.or.ddit.mp.service.eatdealhome.IEatdealHomeService;
import kr.or.ddit.mp.service.goodcom.IGoodcomService;
import kr.or.ddit.mp.view.eatdealQA.EatdealQAController;
import kr.or.ddit.mp.view.goodcom.GoodcomHomeController;
import kr.or.ddit.mp.view.reservation.ReservationUserController;
import kr.or.ddit.mp.vo.eatdeal.CartVO;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.eatdeal.RequestVO;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.MileageVO;

public class EatdealHomeController implements Initializable{
	public static String lastPrice = "";
	public static String lastMil = "";
	public static String useMil = "";
	public static String get_eat_no = "";
	public static int get_can_qty = 0;
	
	@FXML AnchorPane goodcom_anchor;
	@FXML ImageView imgDeal;
	@FXML Text txt_name;
	@FXML Text txt_con1;
	@FXML Text txt_qty;
	@FXML JFXTextField txtf_comName; 
	@FXML JFXTextField txtf_price; 
	@FXML JFXButton btn_cart;
	@FXML JFXButton btn_buy;
	@FXML JFXComboBox<String> com_qty;
	@FXML Button btn_goeatqa;
	
	ArrayList<EatdealVO> eatlist;
	ArrayList<ComVO> comList;
	ArrayList<MileageVO> milList;

	String eat_no = "9";
//	String mem_no = "15";
	
	private Registry reg;
	private IEatdealHomeService eatHomeSer;
	private IGoodcomService comSer;
	
	MemberVO mv = LoginSession.session;
	String mem_no = mv.getMem_no();
	
	private String total_p;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			eatHomeSer 	= (IEatdealHomeService) reg.lookup("eatdealHomeService");
			comSer		= (IGoodcomService) reg.lookup("goodcomService");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		
		try {
			eatlist = (ArrayList<EatdealVO>) eatHomeSer.getSelectEatdeal(get_eat_no);
			milList = (ArrayList<MileageVO>) eatHomeSer.getMileageLast(mem_no);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String eattitle 	=eatlist.get(0).getEat_name();
		String eatcont 		=eatlist.get(0).getEat_cont1();
		String price 		=eatlist.get(0).getEat_price();
		String qty 			=eatlist.get(0).getEat_qty();
		get_can_qty			=Integer.parseInt(qty);
		String img_url 		=eatlist.get(0).getEat_cont2();
		String com_id		=eatlist.get(0).getCom_id();
		System.out.println(eattitle);
		ComVO comvo = new ComVO();
		comvo.setCom_id(com_id);
		
		try {
			comList = (ArrayList<ComVO>) comSer.getSearchGoodCom(comvo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String com_name 	=comList.get(0).getCom_name();
		
		txt_name.setText(eattitle);
		txt_con1.setText(eatcont);
		txt_qty.setText(qty);
		txtf_comName.setText(com_name);
		txtf_comName.setEditable(false);
		txtf_price.setText(price);
		txtf_price.setEditable(false);
		
		File file = new File(img_url);
	    Image image = new Image(file.toURI().toString());
		imgDeal.setImage(image);
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		for(int i = 1; i <= Integer.parseInt(qty);i++) {
			String str_i = Integer.toString(i);
			list.add(str_i);
		}
	
		com_qty.setItems(list);
		
		btn_buy.setOnAction(event->eatbuy_popup(event));

		
		
	}
	
	private String howMany(){
		String cart_qty = com_qty.getSelectionModel().getSelectedItem();
		String eat_price = txtf_price.getText();
		int int_cart_price = Integer.parseInt(cart_qty) * Integer.parseInt(eat_price); 
		
		return Integer.toString(int_cart_price);
	}
	/**
	 * 잇딜 큐에이 보기
	 * @param event
	 */
	@FXML public void gogoeatqa(ActionEvent event) {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_goeatqa.getScene().getWindow());
		dialog.setTitle("멜론플레이트 :) _______ EAT딜 궁금증 해결소");
		
		Parent parent = null;

		try {
			EatdealQAController.get_eat_no = get_eat_no;
			parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/eatdealQA/EATDeal_QA.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
      
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(false); 
        dialog.show();
		
	}
	@FXML public void addCart(ActionEvent event) throws RemoteException {
		
		if(com_qty.getSelectionModel().getSelectedItem()==null) {
			errMsg("경고!", "수량을 선택하세요!");
			return;
		}
		
		String cart_price =  howMany();

		CartVO cartvo = new CartVO();
		cartvo.setEat_no(get_eat_no);
		cartvo.setCart_price(cart_price);
		cartvo.setCart_qty(com_qty.getSelectionModel().getSelectedItem());
		String mem_no = mv.getMem_no();
		cartvo.setMem_no(mem_no);
		cartvo.setEatdeal_name(txt_name.getText());
		
		int cnt = 0;
		cnt = eatHomeSer.insertCartEatdeal(cartvo);
		
		if(cnt == 0) {
			errMsg("실패!", "장바구니 추가에 문제가 있습니다.");
			return;
		}
		if(cnt > 0) {
			infoMsg("성공!", "장바구니 추가 꾹~");
			return;
		}
	}
	
	
	public void eatbuy_popup(ActionEvent e) {

		if (com_qty.getSelectionModel().getSelectedItem() == null) {
			errMsg("경고!", "수량을 선택하세요!");
			return;
		}

		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_buy.getScene().getWindow());
		dialog.setTitle("멜론플레이트 :) ______ EAT딜 구매");

		Parent parent = null;

		try {
			parent = FXMLLoader.load(getClass().getResource("buy_popup.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Text popbuyer = (Text) parent.lookup("#popbuyer");
		Text popph = (Text) parent.lookup("#popph");
		Text popcomName = (Text) parent.lookup("#popcomName");
		Text popdealname = (Text) parent.lookup("#popdealname");
		Text popbuyqty = (Text) parent.lookup("#popbuyqty");
		Text popbefmil = (Text) parent.lookup("#popbefmil");
		JFXTextField popbefprice = (JFXTextField) parent.lookup("#popbefprice");
		JFXTextField popafterpri = (JFXTextField) parent.lookup("#popafterpri");
		JFXTextField popusemile = (JFXTextField) parent.lookup("#popusemile");
		JFXButton btn_nnext = (JFXButton) parent.lookup("#btn_nnext");
		JFXButton btn_miluse = (JFXButton) parent.lookup("#btn_miluse");
		ImageView popimg = (ImageView) parent.lookup("#popimg");

		// 로그인 했다는 가정 하에 쓸 것
		mv.getMem_name();
		mv.getMem_hp();
		mv.getMem_id();
		
		popph.setText(mv.getMem_hp());
		popbuyer.setText(mv.getMem_name());
		popcomName.setText(txtf_comName.getText());
		popdealname.setText(txt_name.getText());
		popbuyqty.setText(com_qty.getSelectionModel().getSelectedItem() + " 개");
		if(milList.size() > 0) {
			popbefmil.setText(milList.get(0).getMelonage());
		}else {
			popbefmil.setText("0");
		}
		popusemile.setText("0");
		popbefprice.setText(howMany());
		popbefprice.setEditable(false);
		popafterpri.setText(howMany());
		popafterpri.setEditable(false);

		File pop_file = new File(eatlist.get(0).getEat_cont2());
		Image pop_image = new Image(pop_file.toURI().toString());

		System.out.println(pop_file.toURI().toString());

		popimg.setImage(pop_image);
		
		

		btn_miluse.setOnAction(use -> {
			int int_usemil = Integer.parseInt(popusemile.getText());
			int int_befmil = 0;
			if(milList.size() > 0) {
				int_befmil = Integer.parseInt(milList.get(0).getMelonage());
			}
			String str_popuse = popusemile.getText();
			
			if(str_popuse.equals("0")) {
				errMsg("경고!", "멜로니지는 0점 이상을 입력하세요!");
				return;
			}
			if(int_usemil % 100 != 0) {
				errMsg("경고!", "멜로니지는 100점 단위로 사용할 수 있습니다.");
				return;
			}
			if(int_usemil > int_befmil) {
				errMsg("경고!", "사용할 멜로니지가 보유한 멜로니지보다 많습니다.");
				return;
			}
			
			int beforeprice 	= Integer.parseInt(howMany());
			int totalprice 		= beforeprice - int_usemil;
			
			if(totalprice < 0) {
				errMsg("경고!", "전체금액을 초과할 수 없습니다.");
				return;
			}
			
			popbefmil.setText(Integer.toString(int_befmil - int_usemil));
			popafterpri.setText(Integer.toString(totalprice));

		});
		
		btn_nnext.setOnAction(next ->{
			
			String eattitle 	=eatlist.get(0).getEat_name();
			String eatcont 		=eatlist.get(0).getEat_cont1();
			
			
			RequestVO revo = new RequestVO();
			revo.setEat_name(eattitle);
			revo.setEat_cont(eatcont);
			revo.setOrder_qty(com_qty.getSelectionModel().getSelectedItem());
			revo.setOrder_price(popafterpri.getText());
			revo.setMelonage(popusemile.getText());
			revo.setMem_no(mv.getMem_no()); 
			revo.setEat_no(get_eat_no);
			
			DataModel.orderInfo.add(revo);
			
			lastPrice 	= popafterpri.getText();
			lastMil 	= popbefmil.getText();
			useMil 		= popusemile.getText();
			
			
			
			dialog.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("pay_popup.fxml"));
			Parent pay_popup = null;
			PaypopupController PaypopupController = null;
			
			try {
				pay_popup = loader.load();
				PaypopupController = loader.getController(); // init 실행부분
					
				PaypopupController.txtf_buyprice.setText(popafterpri.getText());
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Scene scene1 = new Scene(pay_popup);
			dialog.setScene(scene1);
			dialog.show();
			
		});

		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.show();

	}
	
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("알림");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}
	
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
}
