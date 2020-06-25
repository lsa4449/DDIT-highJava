package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class T06_AnchorPaneTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// AnchorPane => 컨트롤들이 놓일 좌표를 설정하여 배치하는 컨테이너
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300, 150);
		
		Label label1 = new Label("아 이 디 : ");
		label1.setLayoutX(62); // x좌표 설정
		label1.setLayoutY(22); // y좌표 설정
	
		Label label2 = new Label("비밀번호 : ");
		label2.setLayoutX(62);
		label2.setLayoutY(68);
	
		TextField textField1 = new TextField();
		textField1.setLayoutX(132);
		textField1.setLayoutY(18);
		
		TextField textField2 = new TextField();
		textField2.setLayoutX(132);
		textField2.setLayoutY(64);
		
		Button btn1 = new Button("로그인");
		btn1.setLayoutX(86);
		btn1.setLayoutY(106);
		
		Button btn2 = new Button("취 소");
		btn2.setLayoutX(160);
		btn2.setLayoutY(106);
		
		root.getChildren().addAll(label1, label2, 
				textField1, textField2, btn1, btn2);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("AnchorPane연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
