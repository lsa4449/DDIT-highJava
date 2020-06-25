package kr.or.ddit.mp.view.pageadmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MyAdminPageController implements Initializable {

	@FXML Button btn_adm_member;
	@FXML Button btn_adm_join;
	@FXML Button btn_adm_black;
	@FXML Button btn_adm_holic;
	@FXML Button btn_adm_nice;
	@FXML Button btn_adm_eatdeal;
	@FXML BorderPane mainBorder;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btn_adm_member.setOnAction(member->{
			changeScene("/kr/or/ddit/mp/view/admin/adminMain.fxml");
		});
		
		btn_adm_join.setOnAction(join->{
			changeScene("/kr/or/ddit/mp/view/admin/readyCom.fxml");
		});
		
		btn_adm_black.setOnAction(black->{
			changeScene("/kr/or/ddit/mp/view/admin/blackReady.fxml");
		});
		
		btn_adm_holic.setOnAction(holic->{
			changeScene("/kr/or/ddit/mp/view/admin/holicReady.fxml");
		});
		
		btn_adm_nice.setOnAction(nice->{
			changeScene("/kr/or/ddit/mp/view/admin/allGoodCom.fxml");
		});
		
		btn_adm_eatdeal.setOnAction(eatdeal->{
			changeScene("/kr/or/ddit/mp/view/recogeatdeal/recog.fxml");
		});
		
	}
	
	/**
	 * 버튼을 누르면 가운데 화면을 전환해주는 메서드 !! 메인 보더에 이름 줘야함.
	 * 이런식으로쓰세요
	 * top_eat.setOnAction( e1 -> {
			changeScene("../view/goodcom/goodcom_home_final.fxml");
		});
	 * 
	 * 
	 * 
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
