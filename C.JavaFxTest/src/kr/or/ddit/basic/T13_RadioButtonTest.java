package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T13_RadioButtonTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// 라디오 버튼을 묶음으로 처리할 객체 생성
		ToggleGroup group = new ToggleGroup();
		ImageView icon = new ImageView();
		
		RadioButton rb1 = new RadioButton("HOME");
		rb1.setToggleGroup(group); // 라디오 버튼을 그룹에 추가	
		rb1.setUserData("HOME"); // 선택 했을 때의 값을 나타내기 위한 데이터
		
		RadioButton rb2 = new RadioButton("CALENDAR"); 
		rb2.setToggleGroup(group);
		rb2.setUserData("Calendar");
		
		RadioButton rb3 = new RadioButton("CONTACTS");
		rb3.setToggleGroup(group);
		rb3.setUserData("Contacts");
		
		// 그룹 내에서 RadioButton 중 하나가 선택되었을 때 처리하기
		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> 
								observable, Toggle oldValue, Toggle newValue) {

				//if(group.getSelectedToggle().getUserData() != null) {
				if((newValue.getUserData() != null)) {
					String url = group.getSelectedToggle().
										getUserData().toString();
					
					Image image = new Image(getClass().
										getResourceAsStream("images/" + url + ".jpg"));
					icon.setImage(image);
				}
			}
		});
		
		rb1.setSelected(true);	// 선택된 상태로 설정
		
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		
		vbox.getChildren().addAll(rb1, rb2, rb3);
		vbox.setSpacing(10);
		
		hbox.getChildren().addAll(vbox, icon);
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(10));
		
		Scene scene = new Scene(hbox, 250, 150);
		
		primaryStage.setTitle("라디오 버튼 예제");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
