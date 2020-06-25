package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T09_ComboBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		HBox hbox = new HBox();
		TextArea txtArea = new TextArea();

		ComboBox<String> combo = new ComboBox<>();
		combo.getItems().addAll("한강", "금강", " 영산강", "낙동강");
		// 보이는 기본 값 설정
		combo.setValue("한강");

		// ComboBox의 값이 변경될 때의 처리 즉, Change이벤트 처리
		combo.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> 
								observable, String oldValue, String newValue) {

				// String temp = combo.getItems().get(0); // 첫 번째 컬럼 가져오기
				txtArea.setText(newValue);

			}
		});
		ObservableList<String> fruitList = 
				FXCollections.observableArrayList("사과", "자몽", "복숭아", "포도", "오렌지");

		// 객체 생성 및 데이터 초기화를 동시에
		ComboBox<String> combo2 = new ComboBox<>();
		//combo2.getItems().addAll(fruitList);
		combo2.setItems(fruitList);
		
		// 데이터를 초기화 후에 추가하기
		combo2.getItems().addAll("호두", "대추");
		combo2.setValue("감");

		Button button = new Button("확인");

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (combo.getValue() != null && combo2.getValue() != null) {
					txtArea.setText(combo.getValue() + "지역에는 " + combo2.getValue() + "가 유명합니다.");

				}
			}
		});

		hbox.setSpacing(10);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(combo, combo2, button);

		root.setTop(hbox);
		root.setCenter(txtArea);

		Scene scene = new Scene(root);
		primaryStage.setTitle("ComboBox  연습");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
