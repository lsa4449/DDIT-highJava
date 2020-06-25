package kr.or.ddit.mp.view.join;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.mp.service.join.IJoinService;
import kr.or.ddit.mp.vo.zipcode.ZipcodeVO;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Zipcode_Controller implements Initializable {

	@FXML AnchorPane ap_zip;
	@FXML TableView<ZipcodeVO> tb_zipcode;
	@FXML TableColumn<ZipcodeVO, String> col_zipcode;
	@FXML TableColumn<ZipcodeVO, String> col_dong;
	@FXML TableColumn<ZipcodeVO, String> col_si;
	@FXML TableColumn<ZipcodeVO, String> col_gu;
	
	@FXML TextField txtf_dong;
	@FXML Button btn_search;
	@FXML Button btn_zip_ok;
	
	private Registry reg;
	private IJoinService ijoin;
	
	private ObservableList<ZipcodeVO> allTableData;
	ArrayList<ZipcodeVO> zipList = new ArrayList<>();
	private ZipcodeVO zvo = new ZipcodeVO();
	
	public static String zipcode;
	public static String dong1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry("localhost", 8429);
			// 설정한 서버를 찾는 메소드 lookup
			
			ijoin =  (IJoinService) reg.lookup("userjoin");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		assert tb_zipcode != null : "fx:id=\"tb_zipcode\" was not injected : check your FXML file 'zipcode.fxml'.";
		
		col_zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
		col_si.setCellValueFactory(new PropertyValueFactory<>("sido"));
		col_gu.setCellValueFactory(new PropertyValueFactory<>("gugun"));
		col_dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
		
	}



	@FXML public void search() {
		
		ZipcodeVO zvo = new ZipcodeVO();
		allTableData = FXCollections.observableArrayList();
		
		zvo.setDong(txtf_dong.getText());
		
		try {
			zipList = (ArrayList<ZipcodeVO>) ijoin.selectZipcode(zvo);
			
			if (zipList.size() > 0) {
				allTableData.setAll(zipList);
				tb_zipcode.setItems(allTableData);

			} else {
				errMsg("실패", "일치하는 값이 없습니다.");
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}


	@FXML public void zip_ok() {
		
		Stage stage = (Stage) btn_zip_ok.getScene().getWindow();
		stage.close();
		
	}
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

}
