package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class T02_StageSceneTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox(); // 컨트롤을 세로로 배치해 주는 컨테이너
		root.setPrefWidth(650); // VBox의 너비
		root.setPrefHeight(150); // VBox의 높이
		root.setAlignment(Pos.CENTER); // 컨트롤들을 가운데 정령
		root.setSpacing(20); // 컨트롤과 컨트롤 사이의 간격

		Label label = new Label(); // Label 객체 생성
		label.setText("안녕하세요 자바 에프엑스입니다");
		label.setFont(new Font(50)); // Font객체를 이용하여 글자크기 설정

		Button button = new Button();
		button.setText("확 인");

		// 버튼에 대한 클릭 이벤트 처리
		// 방법1 -> 람다식을 사용하지 않은 경우
//		button.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//
//				// 처리할 내용을 기술하는 영역
//				Platform.exit(); // 프로그램 종료
//			}
//		});
//		
		// 방법2 -> 람다식을 사용하는 경우
		button.setOnAction(e -> {
			Platform.exit(); // 프로그램 종료
		});

		// VBox에 컨트롤들 추가하기
		// root.getChildren().add(label);
		// root.getChildren().add(button);
		root.getChildren().addAll(label, button);
		
		// VBox를 루트 컨테이너로 하는 Scene객체 생성
		Scene scene = new Scene(root);

		primaryStage.setTitle("Stage와 Scene 연습"); // 창 제목
		primaryStage.setScene(scene); // Stage에 Scene설정
		primaryStage.show(); // 창(Stage)보이기

	}

	public static void main(String[] args) {
		launch(args);
	}
}
