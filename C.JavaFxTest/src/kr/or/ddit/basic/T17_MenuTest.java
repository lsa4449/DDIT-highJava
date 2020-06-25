package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T17_MenuTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		
		MenuBar menuBar = new MenuBar();
		root.setTop(menuBar);
		
		// File Menu 만들어 보기 => new, save, exit
		Menu fileMenu = new Menu("File"); // 주메뉴
		
		MenuItem newMenuItem = new MenuItem("New"); // 부메뉴
		MenuItem saveMenuItem = new MenuItem("Save"); // 부메뉴
		MenuItem exitMenuItem = new MenuItem("Exit"); // 부메뉴
		
		// 메뉴 아이템에 이벤트 설정
		exitMenuItem.setOnAction(e ->{
			// 해당 메뉴를 클릭 했을 때 처리할 내용 기술
			Platform.exit();
		});
		
		// 주메뉴에 부메뉴들 추가
		fileMenu.getItems().addAll(newMenuItem, saveMenuItem, exitMenuItem);
		
		//---------------------------------------------------------------------
		Menu webMenu = new Menu("WEB");
		
		CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
		htmlMenuItem.setSelected(false);
		
		CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
		cssMenuItem.setSelected(true);
		
		webMenu.getItems().addAll(htmlMenuItem, cssMenuItem);
		
		//----------------------------------------------------------------------
		Menu sqlMenu = new Menu("SQL");
		ToggleGroup tGroup = new ToggleGroup();
		
		RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
		mysqlItem.setToggleGroup(tGroup);
		mysqlItem.setSelected(true);
		
		RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
		oracleItem.setToggleGroup(tGroup);
		
		RadioMenuItem msSqlItem = new RadioMenuItem("Ms-SQL");
		msSqlItem.setToggleGroup(tGroup);
		
		sqlMenu.getItems().addAll(mysqlItem, oracleItem, msSqlItem);
		
		// 부메뉴의 부메뉴 구성하기
		Menu tutorialMenu = new Menu("Tutorial");
		tutorialMenu.getItems().addAll(
				new CheckMenuItem("Java"),
				new CheckMenuItem("JavaFX"),
				new CheckMenuItem("Java Swing")
			);		
		
		sqlMenu.getItems().addAll(new SeparatorMenuItem(), tutorialMenu);
		
		// 주 메뉴들을 MenuBar에 추가하기
		menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
		
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setTitle("메뉴연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
