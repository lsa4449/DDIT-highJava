package kr.or.ddit.mp.view.comInfoUpdate;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.comInfoUpdate.ComInfoUpdateService;
import kr.or.ddit.mp.vo.comInfoUpdate.MemberVO;

public class InsertPwController implements Initializable {

	@FXML
	TextField txtf_pw;
	@FXML
	Button btn_checkPw;
	@FXML
	Button btn_cancle;
	@FXML
	AnchorPane an_comInfo;
	
	//세션(mem_no)
	//private String session = "50";
	
	private Registry reg;
	private ComInfoUpdateService cif;
	kr.or.ddit.mp.vo.member.MemberVO vo = LoginSession.session;
	String ss = vo.getMem_no();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			cif = (ComInfoUpdateService) reg.lookup("ComInfoUpdateService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
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
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	@FXML
	public void checkPw(ActionEvent event) throws Exception {
		MemberVO mvo = new MemberVO();
		//mvo.setMem_no(session);
		mvo.setMem_no(ss);
		mvo = cif.getPw(mvo);
		String pw = mvo.getMem_pw();
		if (txtf_pw.getText().equals(pw)) {
			//infoMsg("성공", "ㅎㅇㅎㅇ");
			
		Parent root =FXMLLoader.load(getClass().getResource("comInfoUpdate.fxml"));
		an_comInfo.getChildren().clear();
		an_comInfo.getChildren().add(root);
		
		
	

		} else {
			errMsg("실패", "비밀번호를 확인해 주세요");

		}

	}

}
