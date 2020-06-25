package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class T18_DialogTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		HBox root = new HBox(10);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		// 파일 열기 창
		Button btnFileOpen = new Button("Open FileChooser 실행");
		btnFileOpen.setOnAction(e ->{
			FileChooser fileChooser = new FileChooser();
			
			// 확장자별로 파일 구분하는 필터 등록하기
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", 
											"*.png", "*.jpg", "*.gjf"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3"),
					new ExtensionFilter("All Files", "*.*")
			);

			// Dialog창에서 '열기' 버튼을 누르면 선택한 파일 정보가
			// 반환되고 '취소'버튼을 누르면 null이 반환된다.
			File selectFile = fileChooser.showOpenDialog(primaryStage);
			if(selectFile != null) {
				// 이 영역에서 파일내용을 읽어오는 작업을 수행한다.
				System.out.println("OPEN : " + selectFile.getPath());
			
			}
		
		});

		Button btnFileSave = new Button("SAVE FileChooser 실행");
		btnFileSave.setOnAction(e ->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("All Files", "*.*")
			);
			
			File selFile = fileChooser.showOpenDialog(primaryStage);
			if(selFile != null) {
				// 이 곳에서 선택한 파일을 이용한 저장 작업을 수행한다.
				System.out.println("SAVE : " + selFile.getPath());
			}
		});
		
		// 폴더(디렉토리)만 선택하는 Dialog창
		Button btnDirectory = new Button("DirectoryChooser 실행");
		btnDirectory.setOnAction(e -> {
			DirectoryChooser dirChooser = new DirectoryChooser();
			File selDir = dirChooser.showDialog(primaryStage);
			if(selDir != null) {
				System.out.println("Directory : " + selDir);
			}
		});
		
		Button btnPopup = new Button("Popup 실행");
		btnPopup.setOnAction(e -> {
			//popup창에 나타낼 컨트롤들 구성 시작..
			HBox popRoot = new HBox();
			
			ImageView imgView = new ImageView();
			imgView.setImage(new Image(
					getClass().getResource("./images/ok.png")
					.toString()));
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);

			Label lblMsg = new Label("메세지가 왔습니다.");
			HBox.setMargin(lblMsg, new Insets(0, 5, 0, 5));
			
			popRoot.getChildren().addAll(imgView, lblMsg);
			// 구성 끝
			
			// Popup객체 생성 후 위에서 구성한 컨트롤들 추가 후 보이기
			Popup popop = new Popup();
			popop.setX(1000);
			popop.setY(400);
			popop.getContent().add(popRoot);
			popop.setAutoHide(true);
			popop.show(primaryStage);
			
		});
		
		// 사용자가 만든 임의의 창 나타내기
		Button btnCustom = new Button("사용자 정의 창 실행");
		btnCustom.setOnAction(e ->{
			// 새 창 띄우기
			
			// 1. Stage객체 생성
			Stage dialog = new Stage(StageStyle.UTILITY);
			
			// 2. 모달창 여부 설정(다른창 접근 금지)
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);
			
			// 3. 부모창 지정
			dialog.initOwner(primaryStage);
			
			dialog.setTitle("사용자 정의 창");
			
			// 4. 자식창에 나타날 컨테이너 객체 생성
			Parent parent = null;
			
			try {
				parent = FXMLLoader.load(getClass().getResource("myDialog.fxml"));

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		
			// 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
			TextField txtName = (TextField) parent.lookup("#txtName");
			PasswordField pass = (PasswordField) parent.lookup("#pass");

			Button btnOk = (Button) parent.lookup("#btnOk");
			btnOk.setOnAction(e2 -> {
				System.out.println("이      름 : " + txtName.getText());
				System.out.println("비밀번호 : " + pass.getText());
			});

			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction(e2 -> {
				dialog.close();
			});

			// 5. Scene객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.show();

		});

		root.getChildren().addAll(btnFileOpen, btnFileSave
									, btnDirectory, btnPopup, btnCustom);
		
		Scene scene = new Scene(root, 800, 100);
		primaryStage.setTitle("Dialog창 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
