package kr.or.ddit.mp.view.pagemem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MypageMemberController implements Initializable {

	@FXML AnchorPane an_memleft;
	@FXML Button btn_memInfo;
	@FXML Button btn_memmelon;
	@FXML Button btn_memholic;
	@FXML Button btn_memzzim;
	@FXML Button btn_memcart;
	@FXML Button btn_membuylist;
	@FXML Button btn_memreserv;
	@FXML Button btn_memreview;
	@FXML BorderPane mainBorder;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_memmelon.setOnAction(memmelon->{
			changeScene("/kr/or/ddit/mp/view/melonageCheck/melonageCheck.fxml");
		});
		btn_memInfo.setOnAction(memmelon->{
			changeScene("/kr/or/ddit/mp/view/memInfoUpdate/insertPw.fxml");
		});
		
		btn_membuylist.setOnAction(memmelon->{
			changeScene("/kr/or/ddit/mp/view/buylist/buyList.fxml");
		});
		
		btn_memcart.setOnAction(memmelon->{
			changeScene("/kr/or/ddit/mp/view/cart/eatdealcart.fxml");
		});
		
		btn_memreserv.setOnAction(reser->{
			changeScene("/kr/or/ddit/mp/view/reservation/Reservation_userTable.fxml");
			
		});
		
		btn_memreview.setOnAction(rev->{
			changeScene("/kr/or/ddit/mp/view/review/Review_my.fxml");
			
		});
		
		btn_memholic.setOnAction(rev->{
			changeScene("/kr/or/ddit/mp/view/review/Review_holic.fxml");
			
		});
		
		btn_memzzim.setOnAction(zzim ->{
			changeScene("/kr/or/ddit/mp/view/wantgo/mypage_wantGo.fxml");
			
		});
		
	}
	
	
	
	
	
	/**
	 * 버튼을 누르면 가운데 화면을 전환해주는 메서드 !! 메인 보더에 이름 줘야함.
	 * @param fxmlURL
	 * @return FXMLLoader
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
	

}
