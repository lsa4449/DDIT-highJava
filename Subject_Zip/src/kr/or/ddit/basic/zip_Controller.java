package kr.or.ddit.basic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.basic.service.zipServiceImpl;
import kr.or.ddit.basic.vo.zipVO;

public class zip_Controller implements Initializable {

	@FXML
	ComboBox<String> areaSelect;
	@FXML
	TextField areaName;
	@FXML
	Button btn;
	@FXML
	TableView<zipVO> tv;
	@FXML
	TableColumn<zipVO, String> tv_zipNum;
	@FXML
	TableColumn<zipVO, String> tv_si;
	@FXML
	TableColumn<zipVO, String> tv_gu;
	@FXML
	TableColumn<zipVO, String> tv_dong;
	@FXML
	TableColumn<zipVO, String> tv_addr;

	private zipServiceImpl service = zipServiceImpl.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tv_zipNum.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
		tv_si.setCellValueFactory(new PropertyValueFactory<>("sido"));
		tv_gu.setCellValueFactory(new PropertyValueFactory<>("gugun"));
		tv_dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
		tv_addr.setCellValueFactory(new PropertyValueFactory<>("bunji"));

		ObservableList<String> cb = FXCollections.observableArrayList("동이름", "우편번호");
		areaSelect.setItems(cb);
		areaSelect.setValue(cb.get(0));

		btn.setOnAction(e -> {
			ObservableList<zipVO> data = FXCollections.observableArrayList();
			ArrayList<zipVO> list = new ArrayList<zipVO>();

			if (areaSelect.getValue().equals("동이름")) {
				String dong = "%" + areaName.getText() + "%";
				list = (ArrayList<zipVO>)service.selectDong(dong);
				
				data = FXCollections.observableArrayList(list);
				tv.setItems(data);

			} else {
				list = (ArrayList<zipVO>)service.selectCode(areaName.getText());
				data = FXCollections.observableArrayList(list);
				tv.setItems(data);
			}
		});
	}
}
