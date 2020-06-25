package kr.or.ddit.basic.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.or.ddit.basic.vo.VO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class addController implements Initializable {

	@FXML
	TextField tfname;
	@FXML
	TextField tfkorean;
	@FXML
	TextField tfmath;
	@FXML
	TextField tfenglish;
	@FXML
	Button add2;
	@FXML
	Button cancel2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cancel2.setOnAction(e -> {
			Stage stage = (Stage) cancel2.getScene().getWindow();
			stage.close();

		});
		
		add2.setOnAction(e -> {
			
			VO vo = new VO(tfname.getText(), tfkorean.getText(), tfmath.getText(), tfenglish.getText());
			GradeController.data.add(vo);
			
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("저장 완료");
			alertInfo.setContentText("저장 완료되었습니다!");
			alertInfo.showAndWait(); 
			
			Stage stage = (Stage) add2.getScene().getWindow();
			stage.close();

		});
	}

}
