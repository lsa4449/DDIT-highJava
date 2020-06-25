package kr.or.ddit.mp.view.eatdealhome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TempMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/wantgo/mypage_wantGo.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("eatdealhome.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		
		
		primaryStage.setTitle("EAT, SHARE, BE HAPPY! 멜론플레이트 :) ");
		primaryStage.setScene(scene);
		primaryStage.show();
		

	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
