package kr.or.ddit.mp.view.searchgoodcom;

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

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.main.TopMemberController;
import kr.or.ddit.mp.service.searchgoodcom.ISearchGoodcomService;
import kr.or.ddit.mp.service.wantgo.IWantgoService;
import kr.or.ddit.mp.view.goodcom.GoodcomHomeController;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.WantgoVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchHomeController implements Initializable{
	
	private Registry reg;
	private ISearchGoodcomService searchcom;
	
	
	ArrayList<WantgoVO> list = new ArrayList<>();

	
	public static String get_search_word = "";
	
	ArrayList<ComVO> resultList;
	private IWantgoService want;
	
	MemberVO mv = LoginSession.session;
	
	
	ArrayList<ImageView> arr_zzim;
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
	
	@FXML ImageView img_zero;
	
	@FXML Text txt_eatname1;
	@FXML Text txt_eatname2;
	@FXML Text txt_eatname3;
	@FXML Text txt_eatname4;
	@FXML Text txt_eatname5;
	@FXML Text txt_eatprice5;
	@FXML Text txt_eatprice4;
	@FXML Text txt_eatprice3;
	@FXML Text txt_eatprice2;
	@FXML Text txt_eatprice1;
	@FXML Text txt_eatprice6;
	@FXML Text txt_eatprice7;
	@FXML Text txt_eatprice8;
	@FXML Text txt_eatprice9;
	@FXML Text txt_eatprice10;
	@FXML Text txt_eatname10;
	@FXML Text txt_eatname9;
	@FXML Text txt_eatname8;
	@FXML Text txt_eatname7;
	@FXML Text txt_eatname6;
	@FXML AnchorPane an_main;
	@FXML Text txt_searchword;
	

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		mv = LoginSession.session;
			
		///------------------------RMI
		
		String word = get_search_word;
		txt_searchword.setText(" '"+word+"'의 검색결과");
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			searchcom = (ISearchGoodcomService) reg.lookup("searchComService");
			want = (IWantgoService) reg.lookup("wantgoService");
			resultList = (ArrayList<ComVO>) searchcom.getResultGoodCom(word);
			System.out.println("RMI성공");
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
		for (int i = 0; i < arr_img_eat.length; i++) {
			arr_img_eat[i].setImage(null);
			arr_name[i].setText(null);
			arr_price[i].setText(null);
		}
		/**
		 * 검색결과 0일 경우 이미지를 띄움
		 */
		img_zero.setVisible(false);
		if(resultList.size()==0) {
			img_zero.setVisible(true);
			return;
		}
		
		settingBybutton(arr_img_eat,arr_name,arr_price, resultList);
		clickkeySearch(arr_img_eat, resultList);
	}
	
	
	/**
	 * 정렬시켜주는 메서드
	 */
	private void settingBybutton(ImageView[] arr_img_eat,Text[] arr_name,Text[] arr_price, 
			ArrayList<ComVO> list) {
		
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
		File eatimgfile;
		Image eatimage;
		
		for(int i = 0; i < int_list; i++) {
			eatName		= list.get(i).getCom_name();
			
			arr_name[i].setText(eatName);
			arr_price[i].setText("");
			eatImgUrl 	= list.get(i).getCom_photo();
			if(eatImgUrl != null) {
				eatimgfile	= new File(eatImgUrl);
				eatimage	= new Image(eatimgfile.toURI().toString());
				arr_img_eat[i].setImage(eatimage);
			}
		}
		
	}	
	
	public void clickkeySearch(ImageView[] arr_img_eat, ArrayList<ComVO> list) {
		for(int i = 0; i < arr_img_eat.length; i++) {
			int j = i;
			arr_img_eat[i].setOnMouseClicked(e->gowithComid(list.get(j).getCom_id()));
		}
	}
	public void gowithComid(String keyword) {
		
		GoodcomHomeController.get_com_id = keyword;
		changeScene("/kr/or/ddit/mp/view/goodcom/goodcom_home_final.fxml");

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

	
	
	
	
	public void addZzim() throws RemoteException {

		for (int i = 0; i < arr_zzim.size(); i++) {
			int j = i;
			arr_zzim.get(i).setOnMouseClicked(e -> {

				ComVO vo = resultList.get(j);
				WantgoVO wv = new WantgoVO();

				// wv.setMem_no(mv.getMem_no());
				wv.setMem_no("6");

				wv.setCom_id(vo.getCom_id());

				try {
					boolean isEmpty = want.isEmptyWantgo(wv);
					if (!isEmpty) {
						errMsg("실패!", "해당 맛집은 이미 찜되어 있습니다.");
						return;
					} else {

						wv.setCom_name(vo.getCom_name());
						wv.setCom_addr(vo.getCom_addr());
						int cnt = 0;
						cnt = want.insertWantgo(wv);

						if (cnt <= 0) {
							errMsg("실패!", "가고싶은 맛집 기능에 문제가 생겼다!");
							return;
						}

						if (cnt > 0) {
							infoMsg("성공!", "가고싶은 맛집에 꾹~");
							return;
						}

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
		}

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
		errAlert.setTitle("알림");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}
	
	
}
