package kr.or.ddit.mp.view.pagecom;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import kr.or.ddit.mp.service.eatdealhome.IEatdealHomeService;
import kr.or.ddit.mp.service.goodcom.IGoodcomService;
import javafx.scene.layout.BorderPane;

public class MyComPageController implements Initializable {

	@FXML Button btn_com_info;
	@FXML Button btn_com_eatdeal;
	@FXML Button btn_com_eatQA;
	@FXML Button btn_com_resOp;
	@FXML Button btn_com_resManage;
	@FXML Button btn_com_nice;
	@FXML Button btn_com_my;
	@FXML Button btn_com_eatsales;

	@FXML BorderPane mainBorder;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btn_com_info.setOnAction(comInfo->{ // 업체회원 개인정보수정
			changeScene("/kr/or/ddit/mp/view/memInfoUpdate/insertPw.fxml");
			
		});
		btn_com_my.setOnAction(comInfo->{ // 업체회원 맛집 정보수정
			
			changeScene("/kr/or/ddit/mp/view/comInfoUpdate/insertPw.fxml");
		});
		btn_com_eatQA.setOnAction(eatqa->{
			
			changeScene("/kr/or/ddit/mp/view/qnacomeatdeal/com_eat_qna.fxml");
		});
		btn_com_eatdeal.setOnAction(eatdeal->{
			
			changeScene("/kr/or/ddit/mp/view/comeatdeal/compage_eatdeal_list.fxml");
		});
		
		btn_com_resOp.setOnAction(res_op->{
			
			changeScene("/kr/or/ddit/mp/view/reservation/Reservation_option2.fxml");
		});
		
		btn_com_resManage.setOnAction(res_table->{
			
			changeScene("/kr/or/ddit/mp/view/reservation/Reservation_comTable.fxml");
		});
		
		btn_com_nice.setOnAction(com_nice->{
			
			changeScene("/kr/or/ddit/mp/view/matZip/matZip.fxml");
		});
		
		btn_com_eatsales.setOnAction(com_sales->{
			
			changeScene("/kr/or/ddit/mp/view/salescom/sales_com_list.fxml");
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
