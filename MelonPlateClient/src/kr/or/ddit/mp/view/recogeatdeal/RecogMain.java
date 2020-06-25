package kr.or.ddit.mp.view.recogeatdeal;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecogMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("recog.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);

		primaryStage.setTitle("관리자 eat딜 승인 반려");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
