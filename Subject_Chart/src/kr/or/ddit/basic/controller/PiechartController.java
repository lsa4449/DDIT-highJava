package kr.or.ddit.basic.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PiechartController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
		
		int stuInfo = GradeController.data.size();
		
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		
		for(int i = 0; i < stuInfo; i++ ) {
		ser1.setName("국어");
		ser1.getData().add(new XYChart.Data<String, Number>
							(GradeController.data.get(i).getName(), 
									Integer.parseInt(GradeController.data.get(i).getKor())));
		}
		
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		
		for(int i = 0; i < stuInfo; i++ ) {
		ser2.setName("수학");
		ser2.getData().add(new XYChart.Data<String, Number>
							(GradeController.data.get(i).getName(), 
									Integer.parseInt(GradeController.data.get(i).getMath())));
		}
		
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();
		
		for(int i = 0; i < stuInfo; i++ ) {
		ser3.setName("영어");
		ser3.getData().add(new XYChart.Data<String, Number>
							(GradeController.data.get(i).getName(), 
									Integer.parseInt(GradeController.data.get(i).getEng())));
		}
		
		bc.getData().addAll(ser1, ser2, ser3);
		
		Scene scene = new Scene(bc, 800, 600);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Chart");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
