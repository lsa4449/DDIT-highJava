package kr.or.ddit.mp.view.memInfoUpdate;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kr.or.ddit.mp.service.memInfoUpdate.MemInfoUpdateService;
import kr.or.ddit.mp.vo.memInfoUpdate.ZiptbVO;

public class AddrSearchController implements Initializable {

	@FXML
	TableView<ZiptbVO> tableview;
	@FXML
	TableColumn<ZiptbVO, String> col_zipcode;
	@FXML
	TableColumn<ZiptbVO, String> col_sido;
	@FXML
	TableColumn<ZiptbVO, String> col_gugun;
	@FXML
	TableColumn<ZiptbVO, String> col_dong;
	@FXML
	TextField txtf_serchAddr;
	@FXML
	Button btn_check;
	@FXML
	Button btn_search;

	@FXML
	Pagination page_addrSearch;

	private Registry reg;
	private MemInfoUpdateService mif;
	private ObservableList<ZiptbVO> allTableData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);

			mif = (MemInfoUpdateService) reg.lookup("MemInfoUpdateService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		assert tableview != null : "fx:id=\"tableview\" was not injected : check your FXML file 'AdminMain.fxml'.";
		assert page_addrSearch != null : "fx:id=\"page_addrSearch\" was not injected : check your FXML file 'BoardMain.fxml'.";

		col_zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
		col_sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
		col_gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
		col_dong.setCellValueFactory(new PropertyValueFactory<>("dong"));

	}




	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	@FXML
	public void check(ActionEvent event) throws Exception {
		
		Stage stage = (Stage) btn_check.getScene().getWindow();
		stage.close();

	}

	@FXML
	public void search(ActionEvent event) throws Exception {

		ZiptbVO zvo = new ZiptbVO();

		allTableData = FXCollections.observableArrayList();
		ArrayList<ZiptbVO> list = new ArrayList<>();

		zvo.setDong(txtf_serchAddr.getText());


		list =  (ArrayList<ZiptbVO>) mif.getSearchZipCode(zvo);
		if (list.size() > 0) {
			allTableData.setAll(list);
			tableview.setItems(allTableData);

		} else {
			errMsg("실패", "일치하는 값이 없습니다.");
		}

	}

}
