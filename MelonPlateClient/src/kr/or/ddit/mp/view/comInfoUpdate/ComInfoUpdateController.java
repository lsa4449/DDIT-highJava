package kr.or.ddit.mp.view.comInfoUpdate;

import java.io.File;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.comInfoUpdate.ComInfoUpdateService;
import kr.or.ddit.mp.vo.comInfoUpdate.ComVO;
import kr.or.ddit.mp.vo.comInfoUpdate.ZiptbVO;
import kr.or.ddit.mp.vo.member.MemberVO;

public class ComInfoUpdateController implements Initializable {

	@FXML
	TextField txtf_insertComName;
	@FXML
	TextField txtf_insertMenu;
	@FXML
	TextField txtf_insertComTel;
	@FXML
	TextField txtf_insertComZipCode;
	@FXML
	TextField txtf_insertComAddr;
	@FXML
	TextField txtf_category;
	@FXML
	TextField txtf_insertKeyWord;
	@FXML
	Button btn_serchAddr;
	@FXML
	Button btn_updateComplete;
	@FXML
	Button btn_updateCancle;
	@FXML
	Button btn_updatePhoto;
	@FXML
	ComboBox<String> com_open;
	@FXML
	ComboBox<String> com_close;
	@FXML
	ComboBox<String> com_parking;
	@FXML
	AnchorPane an_comInfo;
	
	private Registry reg;
	private ComInfoUpdateService cif;
	
	// 세션(mem_no)
	//private String session = "50";
	MemberVO vo = LoginSession.session;
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

		ComVO cvo = new ComVO();
		//cvo.setMem_no(session);
		cvo.setMem_no(ss);
		try {
			cvo = cif.getDefaultInfo(cvo);
			txtf_insertComName.setText(cvo.getCom_name());
			txtf_insertMenu.setText(cvo.getCom_menu());       
			txtf_insertComTel.setText(cvo.getCom_tel());     
			txtf_insertComZipCode.setText(cvo.getCom_zipcode()); 
			txtf_insertComAddr.setText(cvo.getCom_addr());   
			txtf_category.setText(cvo.getCom_cat());         
			txtf_insertKeyWord.setText(cvo.getCom_key()); 
			com_open.setValue(cvo.getCom_opentime());
			com_close.setValue(cvo.getCom_closetime());
			com_parking.setValue(cvo.getCom_parking());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		txtf_insertComName.setDisable(true);
        
		com_open.getItems().addAll("9","10","11","12","13","14","15","16","17","18","19");
		com_close.getItems().addAll("14","15","16","17","18","19","20","21","22","23","24");
		com_parking.getItems().addAll("Y","N");
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
		//주소 추가부분 참고하셈
		
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
	      
	      view.setOnMouseClicked(e->{
	         String zip = view.getSelectionModel().getSelectedItem().getZipcode();
	         String gungu = view.getSelectionModel().getSelectedItem().getGugun();
	         String sido = view.getSelectionModel().getSelectedItem().getSido();
	         String dong = view.getSelectionModel().getSelectedItem().getDong();
	         txtf_insertComZipCode.setText(zip);
	         txtf_insertComAddr.setText(gungu+" "+sido+" "+dong);
	         dialog.close();
	      });

		
	}

	@FXML
	public void updateComplete(ActionEvent event) throws Exception {

		ComVO cvo = new ComVO();
		//cvo.setMem_no(session);
		cvo.setMem_no(ss);
		
		cvo.setCom_menu(txtf_insertMenu.getText());
		cvo.setCom_tel(txtf_insertComTel.getText());
		cvo.setCom_addr(txtf_insertComAddr.getText());
		cvo.setCom_zipcode(txtf_insertComZipCode.getText());
		cvo.setCom_addr(txtf_insertComAddr.getText());
		cvo.setCom_cat(txtf_category.getText());
		cvo.setCom_key(txtf_insertKeyWord.getText());
		cvo.setCom_parking(com_parking.getValue());
		cvo.setCom_opentime(com_open.getValue());
		cvo.setCom_closetime(com_close.getValue());
		
		if (txtf_insertMenu.getText().length()<1 || txtf_insertComTel.getText().length()<1 || txtf_insertComAddr.getText().length()<1 || txtf_insertComZipCode.getText().length()<1
				||txtf_category.getText().length()<1|| txtf_insertComAddr.getText().length()<1|| txtf_insertKeyWord.getText().length()<1|| com_parking.getValue().length()<1) {
			errMsg("실패", "정보를 모두 입력해 주세요");
		}else if(Integer.parseInt(cvo.getCom_opentime())>Integer.parseInt(cvo.getCom_closetime())) {
			errMsg("실패", "오픈시간이 마감시간보다 늦을 수 없습니다.");
		}
		else {

			int cnt = cif.updateCom(cvo);
			try {
				if (cnt > 0) {
					infoMsg("성공", "내 가게 정보가 수정 되었습니다.");
				}

			} catch (Exception e) {
				errMsg("실패", "오류!!!");
			}
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("insertPw.fxml"));
		an_comInfo.getChildren().clear();
		an_comInfo.getChildren().add(root);
		
		
	}

	@FXML
	public void updateCancle(ActionEvent event) throws Exception {
		ComVO cvo = new ComVO();
		//cvo.setMem_no(session);
		cvo.setMem_no(ss);
		
		infoMsg("알림", "이전 페이지로 돌아갑니다.");
		Parent root = FXMLLoader.load(getClass().getResource("insertPw.fxml"));
		an_comInfo.getChildren().clear();
		an_comInfo.getChildren().add(root);

	}
	@FXML
	public void updatePhoto(ActionEvent event) throws Exception {
		ComVO cvo = new ComVO();
		//cvo.setMem_no(session);
		cvo.setMem_no(ss);
		
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(
	               new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		
		File selectFile = chooser.showOpenDialog(null);
		
		cvo.setCom_photo(selectFile.getPath());
		
		int cnt = cif.updatePhoto(cvo);
		
		if(cnt>0) {
			infoMsg("성공", cnt+"장의 사진이 업데이트 되었습니다.");
		}else {
			errMsg("실패", "오류!!!");
		}
		
		//System.out.println(selectFile.getPath());
		
	}


}
