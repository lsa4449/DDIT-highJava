package kr.or.ddit.mp.view.memInfoUpdate;

import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.model.DataModel;
import kr.or.ddit.mp.service.memInfoUpdate.MemInfoUpdateService;
import kr.or.ddit.mp.vo.memInfoUpdate.MemberVO;
import kr.or.ddit.mp.vo.memInfoUpdate.ZiptbVO;
import javafx.scene.layout.AnchorPane;

public class MemInfoUpdateController implements Initializable {

	@FXML
	TextField txtf_insertId;
	@FXML
	PasswordField txtf_insertPw;
	@FXML
	PasswordField txtf_insertPwCheck;
	@FXML
	TextField txtf_insertName;
	@FXML
	TextField txtf_insertBirth;
	@FXML
	TextField txtf_insertHp;
	@FXML
	TextField txtf_insertNick;
	@FXML
	TextField txtf_insertZipCode;
	@FXML
	TextField txtf_insertAddr;
	@FXML
	Button btn_nickCheck;
	@FXML
	Button btn_serchAddr;
	@FXML
	Button btn_updateComplete;
	@FXML
	Button btn_updateCancle;
	@FXML
	Button btn_withDrawal;
	@FXML
	AnchorPane an_insertPw;

	private Registry reg;
	private MemInfoUpdateService mif;
	// 세션(mem_no)
	// private String session = "35";

	kr.or.ddit.mp.vo.member.MemberVO vo = LoginSession.session;
	String ss = vo.getMem_no();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			mif = (MemInfoUpdateService) reg.lookup("MemInfoUpdateService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		MemberVO mvo = new MemberVO();
		// mvo.setMem_no(session);
		mvo.setMem_no(ss);
		try {
			mvo = mif.getDefaultInfo(mvo);
			txtf_insertId.setText(mvo.getMem_id());
			txtf_insertName.setText(mvo.getMem_name());
			txtf_insertBirth.setText(mvo.getMem_birth());
			txtf_insertHp.setText(mvo.getMem_hp());
			txtf_insertNick.setText(mvo.getMem_nick());
			txtf_insertZipCode.setText(mvo.getMem_zipcode());
			txtf_insertAddr.setText(mvo.getMem_addr());

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		txtf_insertId.setDisable(true);
		txtf_insertName.setDisable(true);
		txtf_insertBirth.setDisable(true);
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
	public void serchAddr(ActionEvent event) throws Exception {

		Parent parent = null;

		try {
			parent = FXMLLoader.load(getClass().getResource("zipCodeSearch.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(parent);
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setTitle("우편번호 검색");
		dialog.setScene(scene);
		dialog.show();

		TableView<ZiptbVO> view = (TableView<ZiptbVO>) parent.lookup("#tableview");

		view.setOnMouseClicked(e -> {
			String zip = view.getSelectionModel().getSelectedItem().getZipcode();
			String gungu = view.getSelectionModel().getSelectedItem().getGugun();
			String sido = view.getSelectionModel().getSelectedItem().getSido();
			String dong = view.getSelectionModel().getSelectedItem().getDong();
			txtf_insertZipCode.setText(zip);
			txtf_insertAddr.setText(gungu + " " + sido + " " + dong);
			dialog.close();
		});

	}

	@FXML
	public void updateComplete(ActionEvent event) throws Exception {
		int cnt = 0;
		MemberVO mvo = new MemberVO();
		// mvo.setMem_no(session);
		mvo.setMem_no(ss);

		mvo.setMem_pw(txtf_insertPw.getText());
		mvo.setMem_hp(txtf_insertHp.getText());
		mvo.setMem_nick(txtf_insertNick.getText());
		mvo.setMem_zipcode(txtf_insertZipCode.getText());
		mvo.setMem_addr(txtf_insertAddr.getText());

		if (txtf_insertPw.getText().length() < 1 || txtf_insertPwCheck.getText().length() < 1
				|| txtf_insertHp.getText().length() < 1 || txtf_insertNick.getText().length() < 1
				|| txtf_insertZipCode.getText().length() < 1 || txtf_insertAddr.getText().length() < 1) {
			errMsg("실패", "정보를 모두 입력해 주세요");
		} else if (!txtf_insertPw.getText().equals(txtf_insertPwCheck.getText())) {
			errMsg("실패", "비밀번호가 다릅니다. 다시 확인해 주세요");
		} else if (vo.getMem_nick().equals(txtf_insertNick.getText())) {
			cnt = mif.updateMember(mvo);
			try {
				if (cnt > 0) {
					infoMsg("성공", "내 정보가 수정 되었습니다.");
					Parent root = FXMLLoader.load(getClass().getResource("insertPw.fxml"));
					an_insertPw.getChildren().clear();
					an_insertPw.getChildren().add(root);
				}
			} catch (Exception e) {
				errMsg("실패", "오류!!!");
			}
		} else if (mif.getNickCheck(txtf_insertNick.getText()) != null) {
			errMsg("실패", "닉네임을 확인해 주세요");
		} else {
			cnt = mif.updateMember(mvo);
			try {
				if (cnt > 0) {
					infoMsg("성공", "내 정보가 수정 되었습니다.");
					Parent root = FXMLLoader.load(getClass().getResource("insertPw.fxml"));
					an_insertPw.getChildren().clear();
					an_insertPw.getChildren().add(root);
					vo.setMem_nick(txtf_insertNick.getText());
				}
			} catch (Exception e) {
				errMsg("실패", "오류!!!");
			}
		}

	}

	@FXML
	public void updateCancle(ActionEvent event) throws Exception {
		MemberVO mvo = new MemberVO();
		// mvo.setMem_no(session);
		mvo.setMem_no(ss);
		infoMsg("알림", "이전 페이지로 돌아갑니다.");
		Parent root = FXMLLoader.load(getClass().getResource("insertPw.fxml"));
		an_insertPw.getChildren().clear();
		an_insertPw.getChildren().add(root);

	}

	@FXML
	public void withDrawal(ActionEvent event) throws Exception {
		MemberVO mvo = new MemberVO();
		// mvo.setMem_no(session);
		mvo.setMem_no(ss);

		int cnt = mif.deleteMember(mvo);

		try {
			if (cnt > 0) {
				infoMsg("알림", "탈퇴완료");
			
				LoginSession.ch_down 		= 0;
				LoginSession.ch_up 			= 0;
				LoginSession.comsession 	= null;
				LoginSession.session 		= null;
				DataModel.isFirstLogin 		= true;
				DataModel.isFirstZone 		= true;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/mp/view/login/login.fxml"));
				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				Stage main1 = (Stage) btn_withDrawal.getScene().getWindow();
				main1.setScene(scene);
				
			}
		} catch (Exception e) {
			errMsg("실패", "오류!!!");
		}

	}

	@FXML
	public void nickCheck(ActionEvent event) throws Exception {

		if (vo.getMem_nick().equals(txtf_insertNick.getText())) {
			infoMsg("성공", "현재 닉네임 입니다!!");
		} else if (mif.getNickCheck(txtf_insertNick.getText()) != null) {
			errMsg("실패", "닉네임이 중복됩니다!!");
		} else if (txtf_insertNick.getText().length() < 1) {
			errMsg("실패", "닉네임을 입력해 주세요!!");
		} else {
			infoMsg("성공", "사용가능한 닉네임 입니다!!");
		}

	}

}
