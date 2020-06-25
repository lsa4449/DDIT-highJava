package kr.or.ddit.basic.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.basic.vo.VO;
import javafx.scene.control.TableColumn;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class GradeController implements Initializable {

	@FXML
	TableView tv;
	@FXML
	TableColumn name;
	@FXML
	TableColumn kor;
	@FXML
	TableColumn eng;
	@FXML
	TableColumn math;
	@FXML
	Button add_btn;
	@FXML
	Button graph4student;

	public static ObservableList<VO> data = FXCollections.observableArrayList
		   (new VO("홍길동A", "80", "50", "70"),
			new VO("홍길동B", "70", "40", "90"), 
			new VO("홍길동C", "80", "90", "40"));

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		kor.setCellValueFactory(new PropertyValueFactory<>("kor"));
		eng.setCellValueFactory(new PropertyValueFactory<>("eng"));
		math.setCellValueFactory(new PropertyValueFactory<>("math"));

		tv.setItems(data);
		name.setStyle("-fx-alignment: center;");
		kor.setStyle("-fx-alignment: center;");
		eng.setStyle("-fx-alignment: center;");
		math.setStyle("-fx-alignment: center;");

		add_btn.setOnAction(e -> {

			// 1.Stage객체 생성
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("추가");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("../fxml/add.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.show();
		});

		graph4student.setOnAction(e -> {

			// 1.Stage객체 생성
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("추가");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("../fxml/piechart.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

		});
	}

}
