package kr.or.ddit.basic;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Control_Subject extends Application {
	ToggleGroup group;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);

		VBox vbox = new VBox();
		vbox.setPrefSize(700, 500);

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);

		Label label1 = new Label("이 름 :");
		Label label2 = new Label("성 별 :");
		Label label3 = new Label("취 미 :");

		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10));
		hbox2.setSpacing(10);

		group = new ToggleGroup();

		RadioButton rd = new RadioButton("남");
		rd.setToggleGroup(group);
		RadioButton rd1 = new RadioButton("여");
		rd1.setToggleGroup(group);

		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(10));
		hbox3.setSpacing(10);

		CheckBox cbox = new CheckBox("여행");
		CheckBox cbox2 = new CheckBox("등산");
		CheckBox cbox3 = new CheckBox("독서");
		CheckBox cbox4 = new CheckBox("바둑");
		CheckBox cbox5 = new CheckBox("장기");
		CheckBox cbox6 = new CheckBox("게임");
		CheckBox cbox7 = new CheckBox("테니스");
		CheckBox cbox8 = new CheckBox("배드민턴");

		TextField tf = new TextField();
		tf.setPrefWidth(200);

		Button btnDan = new Button("보기");

		TextArea txtResult = new TextArea();
		txtResult.setPrefSize(150, 330);

		hbox.getChildren().addAll(label1, label3, tf, label2);
		hbox2.getChildren().addAll(label2, rd, rd1);
		hbox3.getChildren().addAll(label3, cbox, cbox2, cbox3, cbox4, cbox5, cbox6, cbox7, cbox8);
		vbox.getChildren().addAll(hbox, hbox2, hbox3, btnDan, txtResult);

		btnDan.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				ArrayList<String> list = new ArrayList<String>();

				if (cbox.isSelected()) {
					list.add(cbox.getText());
				}

				if (cbox2.isSelected()) {
					list.add(cbox2.getText());
				}

				if (cbox3.isSelected()) {
					list.add(cbox3.getText());
				}

				if (cbox4.isSelected()) {
					list.add(cbox4.getText());
				}

				if (cbox5.isSelected()) {
					list.add(cbox5.getText());
				}

				if (cbox6.isSelected()) {
					list.add(cbox6.getText());
				}

				if (cbox7.isSelected()) {
					list.add(cbox7.getText());
				}

				if (cbox8.isSelected()) {
					list.add(cbox8.getText());
				}
				
				if (tf.getText() != null && hbox2 != null && hbox3 != null) {
					txtResult.setText(tf.getText() + "님의 성별은 " + ((RadioButton) group.getSelectedToggle()).getText()
							+ "자 입니다  " + "\n그리고 취미는... ");
					for (String select : list) {
						txtResult.appendText(select + ", ");
					}
					txtResult.appendText("이네요!!!");
				}
			}
		});

		Scene scene = new Scene(vbox);

		primaryStage.setTitle("컨트롤 객체 연습");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}