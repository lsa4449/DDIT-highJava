package kr.or.ddit.mp.view.alleatdeal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.alleatdeal.IAllEatdealService;
import kr.or.ddit.mp.view.eatdealhome.EatdealHomeController;
import kr.or.ddit.mp.view.goodcom.GoodcomHomeController;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;
import javafx.scene.layout.AnchorPane;

public class AllEatdealHomeController implements Initializable{
	
	// CHANGELIST
	@FXML Button btn_byzone;
	@FXML Button btn_bynew;
	@FXML Button btn_byqty;
	
	// IMAGE
	@FXML ImageView img_eathome1;
	@FXML ImageView img_eathome2;
	@FXML ImageView img_eathome3;
	@FXML ImageView img_eathome4;
	@FXML ImageView img_eathome5;
	@FXML ImageView img_eathome6;
	@FXML ImageView img_eathome7;
	@FXML ImageView img_eathome8;
	@FXML ImageView img_eathome9;
	@FXML ImageView img_eathome10;
	
	
	// name TEXT
	@FXML Text txt_eatname1;
	@FXML Text txt_eatname2;
	@FXML Text txt_eatname3;
	@FXML Text txt_eatname4;
	@FXML Text txt_eatname5;
	@FXML Text txt_eatname6;
	@FXML Text txt_eatname7;
	@FXML Text txt_eatname8;
	@FXML Text txt_eatname9;
	@FXML Text txt_eatname10;
	
	
	// price Text
	@FXML Text txt_eatprice1;
	@FXML Text txt_eatprice2;
	@FXML Text txt_eatprice3;
	@FXML Text txt_eatprice4;
	@FXML Text txt_eatprice5;
	@FXML Text txt_eatprice6;
	@FXML Text txt_eatprice7;
	@FXML Text txt_eatprice8;
	@FXML Text txt_eatprice9;
	@FXML Text txt_eatprice10;
	
	
	private Registry reg;
	private IAllEatdealService alleat;
	
	ArrayList<ZoneVO> 		zonelist;
	ArrayList<EatdealVO> 	bynewlist;
	ArrayList<EatdealVO> 	byqtylist;
	ArrayList<EatdealVO> 	byzonelist;
	ArrayList<String> 		eatidlist;
	
	MemberVO mv = LoginSession.session;
	String mem_no = mv.getMem_no();
//	String mem_no = "54";
	@FXML AnchorPane an_main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			
			reg 		= LocateRegistry.getRegistry("localhost", 8429);
			alleat 		= (IAllEatdealService) reg.lookup("alleatService");
			zonelist	= (ArrayList<ZoneVO>) alleat.zoneForlist(mem_no);
			ZoneVO zn 	= zonelist.get(0);
			bynewlist	= (ArrayList<EatdealVO>) alleat.eatlistByNew();
			byqtylist	= (ArrayList<EatdealVO>) alleat.eatlistByQty();
			byzonelist	= (ArrayList<EatdealVO>) alleat.eatlistByMyzone(zn);
			
			System.out.println(bynewlist.size());
			System.out.println(byqtylist.size());
			System.out.println(byzonelist.size());
			
			System.out.println("AllEatdealHomeController RMI성공");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ImageView[] arr_img_eat = { 
				img_eathome1,img_eathome2,img_eathome3,img_eathome4,img_eathome5,
				img_eathome6,img_eathome7,img_eathome8,img_eathome9,img_eathome10
		};
		
		Text[] arr_name = {
				txt_eatname1, txt_eatname2, txt_eatname3, txt_eatname4, txt_eatname5,
				txt_eatname6, txt_eatname7, txt_eatname8, txt_eatname9, txt_eatname10
		};
		
		Text[] arr_price = {
				txt_eatprice1, txt_eatprice2, txt_eatprice3, txt_eatprice4, txt_eatprice5,
				txt_eatprice6, txt_eatprice7, txt_eatprice8, txt_eatprice9, txt_eatprice10
		};
		
		
		settingBybutton(arr_img_eat,arr_name,arr_price,bynewlist);
		clickkeySearch(arr_img_eat, bynewlist);
		btn_byzone.setOnAction(byzone->{
			settingBybutton(arr_img_eat,arr_name,arr_price,byzonelist);
			clickkeySearch(arr_img_eat, byzonelist);
		});
		btn_bynew.setOnAction(bynew->{
			settingBybutton(arr_img_eat,arr_name,arr_price,bynewlist);
			clickkeySearch(arr_img_eat, bynewlist);
		});
		btn_byqty.setOnAction(byqty->{
			settingBybutton(arr_img_eat,arr_name,arr_price,byqtylist);
			clickkeySearch(arr_img_eat, byqtylist);
		});
		
		
	}
	
	
	
	
	/**
	 * 
	 * 정렬시켜주는 메서드
	 */
	private void settingBybutton(ImageView[] arr_img_eat,Text[] arr_name,Text[] arr_price, 
			ArrayList<EatdealVO> list) {
		
		// 먼저 지워주기
		for (int i = 0; i < arr_img_eat.length; i++) {
			arr_img_eat[i].setImage(null);
			arr_name[i].setText(null);
			arr_price[i].setText(null);
		}
		
		int int_list = list.size();
		
		if(int_list > 10) {
			int_list = 10;
		}
		
		String eatName 		= "";
		String eatPrice 	= "";
		String eatImgUrl 	= "";
		File eatimgfile		= null;
		Image eatimage 		= null;
		
		for(int i = 0; i < int_list; i++) {
			eatName		= list.get(i).getEat_name();
			eatPrice	= list.get(i).getEat_price();
			arr_name[i].setText(eatName);
			arr_price[i].setText(eatPrice + " 원");
			if(!eatImgUrl.equals("") || eatImgUrl !=null) {
				eatImgUrl 	= list.get(i).getEat_cont2();
				eatimgfile	= new File(eatImgUrl);
				eatimage	= new Image(eatimgfile.toURI().toString());
			}
			arr_img_eat[i].setImage(eatimage);
		}
		
	}	
	
	public void clickkeySearch(ImageView[] arr_img_eat, ArrayList<EatdealVO> list) {
		for(int i = 0; i < arr_img_eat.length; i++) {
			if(i < list.size()) {
				int j = i;
				arr_img_eat[i].setOnMouseClicked(e->gowithComid(list.get(j).getEat_no()));
			}
		}
	}
	public void gowithComid(String keyword) {
		
		EatdealHomeController.get_eat_no = keyword;
		changeScene("/kr/or/ddit/mp/view/eatdealhome/eatdealhome.fxml");

	}
	
	
	/**
	 * 파일의 경로를 넣으면 화면전환
	 * @param fxmlURL
	 * @return loader
	 */
	private FXMLLoader changeScene(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		an_main.getChildren().clear();
		an_main.getChildren().add(parent);
		return loader;
	}

}
