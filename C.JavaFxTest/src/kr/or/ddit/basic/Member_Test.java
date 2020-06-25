package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.basic.T15_TableViewTest.Member;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Member_Test implements Initializable {

	private ObservableList<MemVO> data;

	@FXML
	TextField tid;
	@FXML
	TextField tname;
	@FXML
	TextField ttel;
	@FXML
	TextField taddr;
	@FXML
	Button btnAdd;
	@FXML
	Button btnModify;
	@FXML
	Button btnOk;
	@FXML
	Button btnDelete;
	@FXML
	Button btnCancel;
	@FXML
	TableView<MemVO> tv;
	@FXML
	TableColumn<MemVO, String> table_id;
	@FXML
	TableColumn<MemVO, String> table_name;
	@FXML
	TableColumn<MemVO, String> table_tel;
	@FXML
	TableColumn<MemVO, String> table_addr;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		table_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		table_addr.setCellValueFactory(new PropertyValueFactory<>("addr"));

		data = FXCollections.observableArrayList(new MemVO("a001", "수아", "01042275995", "대전광역시"));
		tv.setItems(data);
		
		abuttonOff();
		
		// 추가
		btnAdd.setOnAction(e -> {
			if (tid.getText().isEmpty() || tname.getText().isEmpty() || ttel.getText().isEmpty()
					|| taddr.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			buttonOff();
            abuttonOn();

			data.add(
					new MemVO(tid.getText(), tname.getText(), 
								taddr.getText(), ttel.getText()));

			infoMsg("작업결과 >>", tname.getText() + "님의 정보 추가 완료!");
			
			tid.clear();
			tname.clear();
			ttel.clear();
			taddr.clear();
			
		    buttonOn();
            abuttonOff();

		});
		
		// 수정
		btnModify.setOnAction(e -> {
			if (tid.getText().isEmpty() || tname.getText().isEmpty() || ttel.getText().isEmpty()
					|| taddr.getText().isEmpty()) {
				
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			 buttonOff();
	         abuttonOn();
			
			data.set(
					tv.getSelectionModel().getSelectedIndex(),
					new MemVO(tid.getText(), tname.getText(), 
								taddr.getText(), ttel.getText()));
		
			infoMsg("작업결과 >>", tname.getText() + "님의 정보 수정 완료!");
	
			tid.clear();
			tname.clear();
			ttel.clear();
			taddr.clear();
			
			buttonOn();
            abuttonOff();
			
		});
	
		// 삭제
		btnDelete.setOnAction(e -> {
			if (tv.getSelectionModel().isEmpty()) {			
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			buttonOff();
            abuttonOn();
            
			data.remove(tv.getSelectionModel().getSelectedIndex());
			
			infoMsg("작업결과 >>", tname.getText() + "님의 정보 삭제 완료!");

			tid.clear();
			tname.clear();
			ttel.clear();
			taddr.clear();
			
			 buttonOn();
             abuttonOff();
			
		});
		
		tv.setOnMouseClicked(e -> {

			MemVO mem = tv.getSelectionModel().getSelectedItem();
			
			tid.setText(mem.getId());
			tname.setText(mem.getName());
			ttel.setText(mem.getTel());
			taddr.setText(mem.getAddr());
		
		});
	}
	
	private void abuttonOff() {
		btnOk.setDisable(true);
		btnCancel.setDisable(true);
	}

	private void abuttonOn() {
		btnOk.setDisable(false);
		btnCancel.setDisable(false);
	}

	private void buttonOff() {
		btnAdd.setDisable(true);
		btnModify.setDisable(true);
		btnDelete.setDisable(true);
	}

	private void buttonOn() {
		btnAdd.setDisable(false);
		btnModify.setDisable(false);
		btnDelete.setDisable(false);
	}
	    
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인!!");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();

	}
}