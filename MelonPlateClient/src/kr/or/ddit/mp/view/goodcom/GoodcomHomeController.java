package kr.or.ddit.mp.view.goodcom;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ibatis.common.resources.Resources;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.main.TopMemberController;
import kr.or.ddit.mp.service.goodcom.IGoodcomService;
import kr.or.ddit.mp.service.wantgo.IWantgoService;
import kr.or.ddit.mp.view.reservation.ReservationUserController;
import kr.or.ddit.mp.view.review.ReviewHomeController;
import kr.or.ddit.mp.view.searchgoodcom.SearchHomeController;
import kr.or.ddit.mp.vo.eatdeal.EatqaVO;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.WantgoVO;
import javafx.scene.input.MouseEvent;


public class GoodcomHomeController implements Initializable{

	public static String get_com_id = "";
	MemberVO mv = LoginSession.session;
	String mem_no = mv.getMem_no();
	
	@FXML AnchorPane goodcom_anchor;
	@FXML AnchorPane anpane_map;
	@FXML ImageView imgHome;
	@FXML JFXButton btn_howWay;
	@FXML JFXButton btn_reserv;
	@FXML JFXButton btn_wishCom;
	@FXML Text txt_comName;
	@FXML ImageView imgHome1;
	@FXML ImageView imgHome2;
	@FXML ImageView imgHome3;
	@FXML Text txt_searchWord1;
	@FXML Text txt_searchWord2;
	@FXML Text txt_searchWord3;
	@FXML Text txt_searchWord4;
	@FXML Text txt_searchWord5;
	@FXML JFXTextField txtf_comName;
	@FXML JFXTextField txtf_comAddr1;
	@FXML JFXTextField txtf_comAddr2;
	@FXML JFXTextField txtf_comTel;
	@FXML JFXTextField txtf_comCate;
	@FXML JFXTextField txtf_comParking;
	@FXML JFXTextField txtf_comHour;
	@FXML JFXTextField txtf_comPickMenu;
	@FXML ImageView imgWeather;
	@FXML Text txt_c;	
	@FXML ImageView imgMise;
	@FXML ImageView img_miniCrown;
	@FXML Button btn_goreview;

	////////////////////////////////////////
	private Registry reg;
	private IGoodcomService co;
	private IWantgoService want;
	
	private String[] arr_sido 	= {	"서울특별시",	"부산광역시",	"대구광역시",	"인천광역시",	"광주광역시",	
									"대전광역시",	"울산광역시",	"세종특별자치시","경기도",		"강원도",	
									"충청북도",	"충청남도",	"전라북도",	"전라남도",	"경상북도",
									"경상남도","제주특별자치도"};
	private String[] arr_x 		= { "60","98","89","55","58",
									"67","102","66","60","73",
									"69","68","63","51","89",
									"91","52"};
	private String[] arr_y 		= {	"127","76","90","124","74",
									"100","84","103","120","134",
									"107","100","89","67","91",
									"77","38"};
	private String[] arr_loc = { 	"종로구", "덕포동", "수창동", "동춘", "서석동", 
									"둔산동", "삼산동", "신흥동", "수내동", "석사동", 
									"보은읍", "도고면", "삼천동", "담양읍",	"중방동", 
									"동상동", "성산읍" };
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			co = (IGoodcomService) reg.lookup("goodcomService");
			want = (IWantgoService) reg.lookup("wantgoService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	
		
		ComVO cv = new ComVO(); 
		
		cv.setCom_id(get_com_id);
		System.out.println(get_com_id);
		ArrayList<ComVO> list = new ArrayList<>();
		ArrayList<MemberVO> isvip = new ArrayList<>();
		try {
			list = (ArrayList<ComVO>) co.getSearchGoodCom(cv);
			isvip = (ArrayList<MemberVO>) co.isVipCom(get_com_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		/**
		 * 우수맛집일 경우 인증 사진 나타나게하는 뭐리
		 */
		
		if(isvip.size()>0) {
			img_miniCrown.setVisible(true);
		}else {
			img_miniCrown.setVisible(false);
		}
		

		txt_comName.setText(list.get(0).getCom_name());
		txtf_comName.setText(list.get(0).getCom_name());
		
		String[] arr_addr = list.get(0).getCom_addr().split(" ");
		String adrr1 ="";
		String adrr2 ="";
		
		for(int i = 0; i < arr_addr.length; i++) {
			if(i <= 1) {
				adrr1 += arr_addr[i]+" ";
			}else {
				adrr2 += arr_addr[i]+" ";
			}
		}
		
		txtf_comAddr1.setText(adrr1);
		txtf_comAddr2.setText(adrr2);
		txtf_comTel.setText(list.get(0).getCom_tel());
		txtf_comCate.setText(list.get(0).getCom_cat());
		String parking = list.get(0).getCom_parking();
		if(parking.equals("y")) {
			parking = "주차가능";
		}else {
			parking = "주차불가";
		}
		txtf_comParking.setText(parking);
		
		String str_open = "";
		String str_close = "";
		
		int int_open =  Integer.parseInt(list.get(0).getCom_opentime());
		int int_close =  Integer.parseInt(list.get(0).getCom_closetime());
		
		if( 0 < int_open && int_open <13) {
			str_open = "오전 " + list.get(0).getCom_opentime();
		}else {
			str_open = "오후 " + list.get(0).getCom_opentime();
		}
		if( 0 < int_close && int_close < 13) {
			str_close = "오전 " + list.get(0).getCom_closetime();
		}else {
			str_close = "오후 " + list.get(0).getCom_closetime();
		}
		txtf_comHour.setText(str_open + " ~ " + str_close);
		txtf_comPickMenu.setText(list.get(0).getCom_menu() + " (" + list.get(0).getCom_foodprice() + "원) ");
		
		
		txtf_comName.setEditable(false);
		txtf_comAddr1.setEditable(false);
		txtf_comAddr2.setEditable(false);
		txtf_comTel.setEditable(false);
		txtf_comCate.setEditable(false);
		txtf_comParking.setEditable(false);
		txtf_comHour.setEditable(false);
		txtf_comPickMenu.setEditable(false);
		
//		System.out.println(list.get(0).getCom_key());
		String keywords = list.get(0).getCom_key();
		String[] arr_keywords = keywords.split(",");
		String[] arr_temp = new String[]{"","","","",""};
		
		System.out.println("keywords:" + keywords);
		System.out.println("arr_keywords:" + arr_keywords[0]);
		System.out.println("arr_keywords 길이:" + arr_keywords.length);
		
		for (int i = 0; i < arr_temp.length; i++) {
			if(i <= arr_keywords.length-1) {
				arr_temp[i] = arr_keywords[i];
				System.out.println("for문돌아가는 중");
			}
		}
		System.out.println(Arrays.toString(arr_temp));
		if(arr_temp[0] != "") {
			txt_searchWord1.setText("#" + arr_keywords[0]);
		}
		if(arr_temp[1] != "") {
			txt_searchWord2.setText("#" + arr_keywords[1]);
		}
		if(arr_temp[2] != "") {
			txt_searchWord3.setText("#" + arr_keywords[2]);
		}
		if(arr_temp[3] != "") {
			txt_searchWord4.setText("#" + arr_keywords[3]);
		}
		if(arr_temp[4] != "") {
			txt_searchWord5.setText("#" + arr_keywords[4]);
		}
		
		//날씨정보 조회
		
		//1. 위도경도 가져오기
		String sido = arr_addr[0].substring(0, 1);
		int x = 0;
		int y = 0;
		String loca = ""; // 미세먼지 조회에 쓰일 지역
		for (int i = 0; i < arr_sido.length; i++) {
			if(arr_sido[i].contains(sido)) {
				x = Integer.parseInt((arr_x[i]));
				y = Integer.parseInt((arr_y[i]));
				loca = arr_loc[i];
			}
		}
		
		/**
		 * 돈네예보 얻어오는 API 실행 부분
		 */
		
	    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
	    SimpleDateFormat format2 = new SimpleDateFormat ( "HH");  // 시간
	    
	    Date time = new Date();
	    String day = format1.format(time);
	    String hh = format2.format(time);
	    
	    String baseDate = day;
	    String baseTime = "";
	  
	    int int_hh = Integer.parseInt(hh);
	
	    String[] arr_hh = {"0200", "0500", "0800", "1100", "1400", "2000", "2300"};
	    
	    if(int_hh < 2 || int_hh == 23) {
	    	
	    	baseTime = arr_hh[6];
	    	
	        Calendar cal = new GregorianCalendar(Locale.KOREA);
	        cal.setTime(time);
	        cal.add(Calendar.DAY_OF_YEAR, -1); 
	      
	        String strDate = format1.format(cal.getTime());
	        baseDate = strDate;
	    	
	    }else if( 2 <= int_hh && int_hh < 5) {
	    	baseTime = arr_hh[0];
	    }else if( 5 <= int_hh && int_hh < 8) {
	    	baseTime = arr_hh[1];
	    }else if( 8 <= int_hh && int_hh < 11) {
	    	baseTime = arr_hh[2];
	    }else if( 11 <= int_hh && int_hh < 14) {
	    	baseTime = arr_hh[3];
	    }else if( 14 <= int_hh && int_hh < 20) {
	    	baseTime = arr_hh[4];
	    }else if( 20 <= int_hh && int_hh < 23) {
	    	baseTime = arr_hh[5];
	    }

//		String baseTime = "2300"; 
		//0200, 0500, 0800, 1100, 1400, 2000, 2300
	   
	    VillageWeatherJSON vwJson = new VillageWeatherJSON();
	   
	    VillageWeatherVO vw = vwJson.getVillageWeather(baseDate, baseTime, x, y);
	   
	    String sky = vw.getSky();
	    String rain = vw.getPty();
	    String cel = vw.getT3h();

	    String file_url = "";
	    if(!rain.equals("0")) {
	    	if(rain.equals("1")) {
	    		file_url = "img/comHome/mel_rain.png";
	    	}
	    	if(rain.equals("2")) {
	    		file_url = "img/comHome/mel_sleet.png";
	    	}
	    	if(rain.equals("3")) {
	    		file_url = "img/comHome/mel_snowy.png";
	    	}
	    	
	    }else {
	    	if(sky.equals("1")) {
	    		file_url = "img/comHome/mel_sun.png";
	    	}
	    	if(sky.equals("2")) {
	    		file_url = "img/comHome/mel_cloud.png";
	    	}
	    	if(sky.equals("3")) {
	    		file_url = "img/comHome/mel_cloudymany.png";
	    	}
	    	if(sky.equals("4")) {
	    		file_url = "img/comHome/mel_h.png";
	    	}
	    	
	    }
	    
	    File file = new File(file_url);
	    Image image = new Image(file.toURI().toString());
	    imgWeather.setImage(image);
	    txt_c.setText(vw.getT3h() + "°C");
	    System.out.println(sky + rain +cel);
		
	    /**
	     * 미세먼지 정보 조회
	     */
	    MiseApi mi = new MiseApi();
		MiseVO mise = new MiseVO();
		mise = mi.getMise(loca);
		
		String mise_url = "";
	    if(sky.equals("1")) {
	    	mise_url = "img/comHome/mise_good.png";
	    }
	    if(sky.equals("2")) {
	    	mise_url = "img/comHome/mise_soso.png";
	    }
	    if(sky.equals("3")) {
	    	mise_url = "img/comHome/mise_bad.png";
	    }
	    if(sky.equals("4")) {
	    	mise_url = "img/comHome/mise_devil.png";
	    }
	    
	    File mise_file = new File(mise_url);
	    Image mise_image = new Image(mise_file.toURI().toString());
	    imgMise.setImage(mise_image);
	    
	    
	    /**
	     * 맛집 사진 출력
	     */
	    
		String comImgUrl = "";
		File comimgfile;
		Image comimage;
		
		comImgUrl 	= list.get(0).getCom_photo();
		comimgfile	= new File(comImgUrl);
		comimage	= new Image(comimgfile.toURI().toString());
		imgHome2.setImage(comimage);
	    
	   
		/**
		 * 지도 출력
		 */
		WebView webView = new WebView();

		webView.setMinSize(345, 280);
		webView.setMaxSize(345, 280);
		webView.setStyle("-fx-border-color: #000000");

		WebEngine webEngine = webView.getEngine();
	
        String url = "https://m.map.kakao.com/actions/detailMapView?id=1905828907&refService=place";
        if(txt_comName.getText().equals("버터핑거")) {
        	url = "https://m.map.kakao.com/actions/detailMapView?id=13304294&refService=place";
        }
        	
        webEngine.load(url);
        anpane_map.getChildren().add(webView);


		
		
		/**
		 * 키워드 클릭시 검색창 이동
		 */
	    Text[] arr_txtSearch = {
	    		txt_searchWord1, txt_searchWord2, txt_searchWord3, txt_searchWord4, txt_searchWord5
	    };
	    
	    clickkeySearch(arr_txtSearch);
	    
	}
	
	
	/**
	 * 키워드 누르면 서치화면으로 넘어간다.
	 * @param arr_txtSearch
	 */
	
	public void clickkeySearch(Text[] arr_txtSearch) {
		for(int i = 0; i < arr_txtSearch.length; i++) {
			int j = i;
			arr_txtSearch[i].setOnMouseClicked(e->gowithComid(arr_txtSearch[j].getText()));
		}
	}
	public void gowithComid(String keyword) {
		String okkey = keyword.substring(1, keyword.length());
		SearchHomeController.get_search_word = okkey;
		changeScene("/kr/or/ddit/mp/view/searchgoodcom/searchhome.fxml");
	}
	
	
	/**
	 * 파일의 경로를 넣으면 센터에 넣어주고 로더를 반환하는 메서드
	 * @param fxmlURL
	 * @return loader
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		
		try {
			System.out.println(loader.toString());
			parent = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		goodcom_anchor.getChildren().clear();
		goodcom_anchor.getChildren().add(parent);
		
		return loader;
	}
	
	/**
	 * 가는길 찾는 메서드
	 * @param event
	 */
	@FXML public void ac_howWay(ActionEvent event) {
		
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_howWay.getScene().getWindow());
		dialog.setTitle("맛집까지 가는 길 찾기");
		
		Parent parent = null;

		try {
           parent = FXMLLoader.load(getClass().getResource("anchor_popup.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
		AnchorPane anc_howWay = (AnchorPane) parent.lookup("#anc_howWay");
		
		WebView webView = new WebView();
		webView.setMinSize(600, 600);
		webView.setMaxSize(600, 600);
		webView.setStyle("-fx-border-color: #000000");

		WebEngine webEngine = webView.getEngine();
	
		
        String url = "https://m.map.kakao.com/actions/routeView?exEnc=MVRTOO&eyEnc=OWSRSM&endLoc=%EC%86%8C%EC%9A%B8%EC%B9%B4%EC%B8%A0&ids=%2CP1905828907";
        if(txt_comName.getText().equals("버터핑거")) {
        	url = "https://m.map.kakao.com/actions/routeView?ex=505646&ey=1110952&endLoc=%EB%B2%84%ED%84%B0%ED%95%91%EA%B1%B0%ED%8C%AC%EC%BC%80%EC%9D%B4%ED%81%AC%20%EA%B0%95%EB%82%A8%EC%A0%90&ids=%2CP13304294";
        }
        webEngine.load(url);
        anc_howWay.getChildren().add(webView);
      
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(false); 
        dialog.show();
		
	}
	
	/**
	 * 리뷰화면을 팝업으로 띄워주는 메서드
	 * @param event
	 */
	@FXML public void gogoreview(ActionEvent event) 	{
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_howWay.getScene().getWindow());
		dialog.setTitle("멜론플레이트 :) _______"+txt_comName.getText() + " 생생리뷰보기");
		
		Parent parent = null;

		try {
			ReviewHomeController.get_rev_com_id= get_com_id;
			parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/review/Review_home.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
      
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(false); 
        dialog.show();	
	}
	
	/**
	 * 예약하기
	 * @param event
	 */
	@FXML public void ac_reserv(ActionEvent event) {
		
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_howWay.getScene().getWindow());
		dialog.setTitle("멜론플레이트 :) _______"+txt_comName.getText() + " 예약하기");
		
		Parent parent = null;

		try {
			ReservationUserController.get_resv_com_id = get_com_id;
			parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/reservation/Reservation_request.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
      
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(false); 
        dialog.show();
		
	}
	
	/**
	 * 가고싶은 식당
	 * @param event
	 * @throws RemoteException
	 */
	@FXML public void ac_wishCom(ActionEvent event) throws RemoteException {
		
		WantgoVO wv = new WantgoVO();
		
		wv.setMem_no(mem_no);
		wv.setCom_id(get_com_id);
		boolean isEmpty = want.isEmptyWantgo(wv);
		if(!isEmpty) {
			errMsg("실패!", "해당 맛집은 이미 찜되어 있습니다.");
			return;
		}else {
			
			wv.setCom_name(txtf_comName.getText());
			wv.setCom_addr(txtf_comAddr1.getText() + txtf_comAddr2.getText());
			int cnt = 0;
			cnt = want.insertWantgo(wv);
			
			if(cnt <= 0) {
				errMsg("실패!", "가고싶은 맛집 기능에 문제가 생겼다!");
				return;
			}
			
			if(cnt > 0) {
				infoMsg("성공!", "가고싶은 맛집에 꾹~");
				return;
			}
			
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
