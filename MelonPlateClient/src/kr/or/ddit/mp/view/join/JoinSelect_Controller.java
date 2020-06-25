package kr.or.ddit.mp.view.join;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class JoinSelect_Controller implements Initializable {

	@FXML ImageView img_member;
	@FXML ImageView img_com;
	@FXML Button btn_home;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		img_member.setOnMouseClicked(e -> {
			try {
				    Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/join/member_join_form.fxml"));
	                Scene scene = new Scene(root);
	                Stage primaryStage =(Stage)img_member.getScene().getWindow();
	                primaryStage.setScene(scene);
	                } catch (IOException e1) {
	                e1.printStackTrace();
	            }
		});
		
		img_com.setOnMouseClicked(e->{
			try {
			    Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/join/comjoin.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage =(Stage)img_member.getScene().getWindow();
                primaryStage.setScene(scene);
                } catch (IOException e1) {
                e1.printStackTrace();
            }
		});
		
		btn_home.setOnAction(e->{
			Stage stage = (Stage) btn_home.getScene().getWindow();
			 stage.close();
		});
		
		
		
	}

}
