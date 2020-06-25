package kr.or.ddit.mp.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.model.DataModel;
import kr.or.ddit.mp.service.chat.ChatClientImpl;
import kr.or.ddit.mp.service.chat.ChatController;
import kr.or.ddit.mp.service.eatdealhome.IEatdealHomeService;
import kr.or.ddit.mp.service.goodcom.IGoodcomService;
import kr.or.ddit.mp.service.home.IHomeService;
import kr.or.ddit.mp.service.zone.IZoneService;
import kr.or.ddit.mp.view.eatdealQA.EatdealQAController;
import kr.or.ddit.mp.view.eatdealhome.EatdealHomeController;
import kr.or.ddit.mp.view.goodcom.GoodcomHomeController;
import kr.or.ddit.mp.view.review.ReviewHomeController;
import kr.or.ddit.mp.view.searchgoodcom.SearchHomeController;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;

public class TopMemberController implements Initializable{

	@FXML JFXButton top_eat;
	@FXML BorderPane mainBorder;
	@FXML JFXButton top_cs;
	@FXML JFXButton top_mypage;
	@FXML JFXButton top_logout;
	@FXML JFXButton top_setzone;
	@FXML JFXButton top_melony;
	
	//초기 중앙화면
	@FXML JFXButton btn_searchmain;
	@FXML JFXTextField txtf_searchmain;
	@FXML Text txt_top1;
	@FXML Text txt_top2;
	@FXML Text txt_top3;
	@FXML Text txt_top4;
	@FXML Text txt_top5;
	@FXML Text txt_top6;
	@FXML Text txt_top7;
	@FXML Text txt_top8;
	@FXML Text txt_top9;
	@FXML Text txt_top10;
	@FXML AnchorPane an_main_eat1;
	@FXML AnchorPane an_main_eat2;
	@FXML AnchorPane an_main_eat3;
	@FXML AnchorPane an_main_eat4;
	@FXML AnchorPane an_main_eat5;
	@FXML AnchorPane an_main_food1;
	@FXML AnchorPane an_main_food2;
	@FXML AnchorPane an_main_food3;
	@FXML AnchorPane an_main_food4;
	@FXML AnchorPane an_main_food5;
	@FXML Button btn_goeatmain;
	@FXML Button btn_gofoodmain;
	
	@FXML Text txt_eatname1;
	@FXML Text txt_eatname2;
	@FXML Text txt_eatname3;
	@FXML Text txt_eatname4;
	@FXML Text txt_eatname5;
	@FXML Text txt_comename1;
	@FXML Text txt_comename2;
	@FXML Text txt_comename3;
	@FXML Text txt_comename4;
	@FXML Text txt_comename5;
	@FXML ImageView img_besteat1;
	@FXML ImageView img_besteat2;
	@FXML ImageView img_besteat3;
	@FXML ImageView img_besteat4;
	@FXML ImageView img_besteat5;
	
	@FXML ImageView img_bestcom1;
	@FXML ImageView img_bestcom2;
	@FXML ImageView img_bestcom3;
	@FXML ImageView img_bestcom4;
	@FXML ImageView img_bestcom5;
	
	private Registry reg;
	private IHomeService home;
	
	//로그인 유저의 관심지역 설정하기
	private IZoneService izs;
	
	MemberVO mv = LoginSession.session;
	String user = null;
	ZoneVO zvo = new ZoneVO();
	ArrayList<ZoneVO> zlist = new ArrayList<>();
	
	ArrayList<MemberVO> bestCom10;
	ArrayList<ZoneVO> myZone;
	ArrayList<EatdealVO> bestEat5;
	ArrayList<ComVO> bestCom5;
	
	public static String click_com_id = "";
	public static String click_eatdeal_id = "";
	
	
	@FXML JFXButton top_gohome;
	@FXML AnchorPane centerborder;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/**
		 * Rmi
		 */
		try {
			reg 		= LocateRegistry.getRegistry("localhost", 8429);
			home 		= (IHomeService) reg.lookup("homeService");
			izs			= (IZoneService) reg.lookup("zone");
			System.out.println("Home RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		/**
		 * 최초로그인시 관심지역 설정창
		 */
		
		user = mv.getMem_no();
		zvo.setMem_no(user);
		
//		try {
//			zlist = (ArrayList<ZoneVO>) izs.selectZoneChoice(zvo);
//			System.out.println(zlist.size());
//			if(zlist.size()==0) {
//				top_cs.setVisible(false);
//				top_mypage.setVisible(false);
//				top_gohome.setVisible(false);
//				top_setzone.setVisible(false);
//				top_eat.setVisible(false);
//				changeScene("/kr/or/ddit/mp/view/zone/Zone_option.fxml");
//				return;
//				
//			}
//		} catch (RemoteException e4) {
//			
//			e4.printStackTrace();
//		}
		
		/**
		 * 관심지역이 설정된 회원이 관리자일 경우 관심지역 설정 창 뜨지 않음
		 */
		String str_grade = mv.getMem_grade();
		if(str_grade.equals("관리자")) {
			top_setzone.setVisible(false);
			if(DataModel.isFirstLogin) {
				changeScene("../view/pageadmin/My_AdminPage.fxml");
				DataModel.isFirstLogin = false;
			}
		}
		/**
		 * 관심지역 설정 버튼 누르면 관심지역 설정화면이 뜸
		 */
		top_setzone.setOnAction(zone_set->{
			
			Stage dialog = new Stage(StageStyle.UTILITY);
        	
        	dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(top_setzone.getScene().getWindow());
        	
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/zone/Zone_option.fxml"));
	           
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false); 
			dialog.show();
			
		});
		
		/**
		 * 화면 세팅을 위한 아이바티스 실행부분
		 */
		try {
			bestCom10 	= (ArrayList<MemberVO>) home.homeBestTop10();
			myZone		= (ArrayList<ZoneVO>) home.myZoneSelect(mv.getMem_no());
			bestEat5	= (ArrayList<EatdealVO>) home.homeEatBest5();
			bestCom5	= (ArrayList<ComVO>) home.homeComeBest5(myZone.get(0));
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		/**
		 * 화면 나타내는 부분 코드
		 */
		int bestCom10_size = bestCom10.size();
		if(bestCom10_size > 10) {
			bestCom10_size = 10;
		}
		
		
		String top10name = "";
		
		Text[] arr_eatname = {
				txt_eatname1, txt_eatname2, txt_eatname3, txt_eatname4, txt_eatname5
		};
		Text[] arr_comname = {
				txt_comename1, txt_comename2, txt_comename3, txt_comename4, txt_comename5
		};
		
		ImageView[] img_besteat = {
				img_besteat1, img_besteat2, img_besteat3, img_besteat4, img_besteat5
		};
		
		ImageView[] img_bestcom = {
				img_bestcom1, img_bestcom2, img_bestcom3, img_bestcom4, img_bestcom5
		};
		
		Text[] arr_toptext = {
				txt_top1, txt_top2, txt_top3, txt_top4, txt_top5,
				txt_top6, txt_top7, txt_top8, txt_top9, txt_top10
		};
		
		for(int j = 0; j < arr_toptext.length; j ++) {
			if( j < 5) {
				arr_eatname[j].setText(null);
				arr_comname[j].setText(null);
				img_besteat[j].setImage(null);
				img_bestcom[j].setImage(null);

				arr_toptext[j].setText(null);
			}else {
				arr_toptext[j].setText(null);
			}
			
		}
		
		for(int i =0; i < bestCom10_size; i++) {
			
			top10name = bestCom10.get(i).getCom_name();
			System.out.println(top10name);
			arr_toptext[i].setText(top10name);
		}

		
		int bestEat5_size = bestEat5.size();
		int bestCom5_size = bestCom5.size();
		
		if(bestEat5_size > 5) {
			bestEat5_size = 5;
		}
		if(bestCom5_size > 5) {
			bestCom5_size = 5;
		}
		
		String bestEatname = "";
		String eatImgUrl = "";
		File eatimgfile;
		Image eatimage;
		
		for(int i =0; i < bestEat5_size; i++) {
			
			bestEatname = bestEat5.get(i).getEat_name();
			arr_eatname[i].setText(bestEatname);
			eatImgUrl 	= bestEat5.get(i).getEat_cont2();
			if(eatImgUrl != null) {
				eatimgfile	= new File(eatImgUrl);
				eatimage	= new Image(eatimgfile.toURI().toString());
				img_besteat[i].setImage(eatimage);
			}
		}
		
		
		String bestComname = "";
		String comImgUrl = "";
		File comimgfile;
		Image comimage;
		
		for(int i =0; i < bestCom5_size; i++) {
			
			bestComname = bestCom5.get(i).getCom_name();
			arr_comname[i].setText(bestComname);
			comImgUrl 	= bestCom5.get(i).getCom_photo();
			
			if(comImgUrl != null) {
				comimgfile	= new File(comImgUrl);
				comimage	= new Image(comimgfile.toURI().toString());
				img_bestcom[i].setImage(comimage);
			}
		}
		
		/**
		 * 누르면 이동하는 코드
		 */
		
		img_besteat[0].setOnMouseClicked(e->{
			EatdealHomeController.get_eat_no = bestEat5.get(0).getEat_no();
			changeScene("../view/eatdealhome/eatdealhome.fxml");
		});
		img_besteat[1].setOnMouseClicked(e->{
			EatdealHomeController.get_eat_no = bestEat5.get(1).getEat_no();
			changeScene("../view/eatdealhome/eatdealhome.fxml");
		});
		img_besteat[2].setOnMouseClicked(e->{
			EatdealHomeController.get_eat_no = bestEat5.get(2).getEat_no();
			changeScene("../view/eatdealhome/eatdealhome.fxml");
		});
		img_besteat[3].setOnMouseClicked(e->{
			EatdealHomeController.get_eat_no = bestEat5.get(3).getEat_no();
			changeScene("../view/eatdealhome/eatdealhome.fxml");
		});
		img_besteat[4].setOnMouseClicked(e->{
			EatdealHomeController.get_eat_no = bestEat5.get(4).getEat_no();
			changeScene("../view/eatdealhome/eatdealhome.fxml");
		});
		
		// com
		img_bestcom[0].setOnMouseClicked(e->{
			GoodcomHomeController.get_com_id = bestCom5.get(0).getCom_id();
			changeScene("../view/goodcom/goodcom_home_final.fxml");
		});
		img_bestcom[1].setOnMouseClicked(e->{
			GoodcomHomeController.get_com_id = bestCom5.get(1).getCom_id();
			changeScene("../view/goodcom/goodcom_home_final.fxml");
		});
		img_bestcom[2].setOnMouseClicked(e->{
			GoodcomHomeController.get_com_id = bestCom5.get(2).getCom_id();
			changeScene("../view/goodcom/goodcom_home_final.fxml");
		});
		img_bestcom[3].setOnMouseClicked(e->{
			GoodcomHomeController.get_com_id = bestCom5.get(3).getCom_id();
			changeScene("../view/goodcom/goodcom_home_final.fxml");
		});
		img_bestcom[4].setOnMouseClicked(e->{
			GoodcomHomeController.get_com_id = bestCom5.get(4).getCom_id();
			changeScene("../view/goodcom/goodcom_home_final.fxml");
		});
		
		clickTextGoComhome(arr_toptext, bestCom10);


		
		/**
		 * 보더패인 TOP부분 버튼 제어 부분
		 */
		top_eat.setOnAction( e1 -> {
			
			changeScene("/kr/or/ddit/mp/view/alleatdeal/alleatdeal_main.fxml");

		});
		
		top_cs.setOnAction( chat -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/mp/service/chat/chat.fxml"));
		      Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		      ChatController controller = loader.getController();
		      Stage primaryStage = new Stage();
		      String nin = "";
		      nin = mv.getMem_nick();
		      if(str_grade.equals("관리자")) {
		    	  nin = "멜로니";
		      }
		      controller.setNickName(nin);
		      ChatClientImpl chatClientImpl = null;
			try {
				chatClientImpl = new ChatClientImpl(nin, controller, primaryStage);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		      try {
				chatClientImpl.connect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		      
		      Scene scene = new Scene(root);
		      primaryStage.setScene(scene);
		      primaryStage.show();
			
			
		});		
		
		/**
		 * 잇딜 더보기 클릭시 잇딜 메인으로 이동
		 */
		btn_goeatmain.setOnAction(gogoeat ->{
			changeScene("/kr/or/ddit/mp/view/alleatdeal/alleatdeal_main.fxml");
		});
	}
	
	/**
	 * 검색창 나타내는 코드
	 * @param event
	 */
	@FXML public void SearchClick(ActionEvent event) {
		
		String key = txtf_searchmain.getText();
		if(key.equals("") || key == null) {
			errMsg("검색 결과", "검색어를 입력해주세요 :) ");
			return;
		}
		SearchHomeController.get_search_word = key;
		changeScene("/kr/or/ddit/mp/view/searchgoodcom/searchhome.fxml");
		
	}
	
	/**
	 * 화면전환을 위한 메서드
	 * @param arr_toptext
	 * @param list
	 */
	private void clickTextGoComhome(Text[] arr_toptext, ArrayList<MemberVO> list) {
		for(int i = 0; i < arr_toptext.length; i++) {
			int j = i;
			arr_toptext[i].setOnMouseClicked(e->gowithComid(list.get(j).getCom_id()));
		}
	}
	private void gowithComid(String com_id) {
		GoodcomHomeController.get_com_id = com_id;
		changeScene("../view/goodcom/goodcom_home_final.fxml");
	}
	
	/**
	 * 메시지 에러창
	 * @param headerText
	 * @param msg
	 */
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("알림");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}	
	

	/**
	 * 파일의 경로를 넣으면 센터에 넣어주고 로더를 반환하는 메서드
	 * @param fxmlURL
	 * @return loader
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		mainBorder.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainBorder.setCenter(parent);
		return loader;
	}
	public void changeScene(String fxmlURL1, String fxmlURL2) {
		mainBorder.setCenter(null);
		mainBorder.setBottom(null);
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource(fxmlURL1));
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource(fxmlURL2));
		Parent parent1 = null;
		Parent parent2 = null;
		try {
			parent1 = loader1.load();
			parent2 = loader2.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainBorder.setCenter(parent1);
		mainBorder.setBottom(parent2);
		
	}

	@FXML public void ClickHome(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../main/top_member.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		Stage main1 = (Stage) top_gohome.getScene().getWindow();
		main1.setScene(scene);

	}

	@FXML public void logout(ActionEvent event) {
		
		LoginSession.ch_down 		= 0;
		LoginSession.ch_up 			= 0;
		LoginSession.comsession 	= null;
		LoginSession.session 		= null;
		DataModel.isFirstLogin 		= true;
		DataModel.isFirstZone 		= true;
		DataModel.orderInfo.clear();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/mp/view/login/login.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		Stage main1 = (Stage) top_gohome.getScene().getWindow();
		main1.setScene(scene);

	}	
	
	
	@FXML public void goMypage(ActionEvent event) {
		String str_grade = mv.getMem_grade();
//		String str_grade = "일반";
		if(str_grade.equals("일반")) {
			changeScene("../view/pagemem/mypage_Member.fxml");
		}else if(str_grade.equals("업체")) {
			changeScene("../view/pagecom/My_ComPage.fxml");
		}else if(str_grade.equals("관리자")) {
			changeScene("../view/pageadmin/My_AdminPage.fxml");
		}
		
		
		
	}
	
	/**
	 * 챗봇 진입 버튼
	 * @param event
	 */

	@FXML public void GoChatBot(ActionEvent event) {
		Stage dialog = new Stage(StageStyle.UTILITY);
    	
    	dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(top_melony.getScene().getWindow());
    	
		Parent parent = null;

		try {
           parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/chatbot/chat_pop.fxml"));
           
        }catch(IOException ex) {
           ex.printStackTrace();
        }
		
		Scene scene = new Scene(parent);
		
		dialog.setScene(scene);
		dialog.setResizable(false); 
		dialog.show();
	}
	
	
}
