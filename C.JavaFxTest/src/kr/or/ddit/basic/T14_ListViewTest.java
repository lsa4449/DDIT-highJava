package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class T14_ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		ListView<String> list = new ListView<>();

		Label label = new Label();
		label.setFont(new Font(20));

		// ListView에 들어갈 데이터 구성하기
		ObservableList<String> data = FXCollections.observableArrayList(
				"lightgreen", "gold", "pink", "purple", "black",
					"orange", "lightblue", "gray", "red", "blue");
	
		List<String> strList = new ArrayList<String>();
		strList.add("lightgreen");
		strList.add("pink");
		strList.add("red");
		
		ObservableList<String> data2 = 
				FXCollections.observableArrayList(strList);
		
		list.setItems(data);

		// ListView에서 값이 선택되었을 때 처리
		list.getSelectionModel().selectedItemProperty().
							addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> 
							observable, String oldValue, String newValue) {
				
				label.setText(newValue);
				label.setTextFill(Color.web(newValue));

			}
		});
		
		// ListView의 내용은 변경되지 않고 화면에 보이는 부분만 변경하기
		list.setCellFactory(
				new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {

				return new ListCell<String>() {
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
						// 변경되는 데이터가 문자열이면  setText()
						// 변경되는 데이터가 그래픽이면 setGraphic()
						
						Rectangle rect = new Rectangle(100, 20);
						if(item != null) { // 또는  empty
							rect.setFill(Color.web(item)); // 4각형 색칠하기
							
							Label lblTxt = new Label(item);
							lblTxt.setTextFill(Color.web(item));
							
							HBox hbox2 = new HBox(10);
							hbox2.getChildren().addAll(rect, lblTxt,
									new ImageView(new Image(getClass()
											.getResourceAsStream("images/Security.png"))));
							setGraphic(hbox2);
						}
					}
				};
			}
		});
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(list, label);
		
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("ListView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
