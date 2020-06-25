package kr.or.ddit.mp.view.matZip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.matZip.MatZipService;
import kr.or.ddit.mp.vo.matZip.ReviewVO;
import kr.or.ddit.mp.vo.member.ComVO;

public class PdfController implements Initializable {
	@FXML
	AnchorPane ap_write;
	@FXML
	AnchorPane ap_write1;
	@FXML
	ImageView mel_home;
	@FXML
	Button btn_dwn;
	@FXML
	Button btn_reNameDwn;

	// 맛집 서비스
	private Registry reg;
	private MatZipService mzs;

	// 테스트용 vo ---> 로그인한 사용자 정보 받아오기
	ReviewVO rvo = new ReviewVO();

	// String session = "35";

	kr.or.ddit.mp.vo.member.MemberVO vo = LoginSession.session;
	ComVO cvo = LoginSession.comsession;
	String ss = vo.getMem_no();
	ArrayList<ReviewVO> list = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			mzs = (MatZipService) reg.lookup("MatZipService");
			System.out.println("rmi 성!공!");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		// rvo.setMem_no(session);
		rvo.setMem_no(ss);

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	@FXML
	public void dwn(ActionEvent event) throws Exception {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd ", Locale.KOREA);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("d:/우수맛집인증서.pdf"));
		try {

			doc.open();

			BaseFont korean = BaseFont.createFont("HYSMyeongJo-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);

			Font fontKorean = new Font(korean, 20, Font.BOLD);

			Image imageTop = Image.getInstance(this.getClass().getResource("matzip_top.png").getPath());
			imageTop.scaleAbsolute(500, 320);
			imageTop.setRotation(0);
			doc.add(imageTop);

			Paragraph name = new Paragraph(cvo.getCom_name(), fontKorean);
			name.setIndentationLeft(210);
			doc.add(name);

			Paragraph date = new Paragraph(mTime, fontKorean);
			date.setIndentationLeft(195);
			doc.add(date);

			Image imageBottom = Image.getInstance(this.getClass().getResource("matzip_bottom.png").getPath());
			imageBottom.scaleAbsolute(500, 320);
			imageBottom.setRotation(0);
			doc.add(imageBottom);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			doc.close();
		}

		infoMsg("알림", "D드라이브에 우수맛집인증서가 저장되었습니다.");

		Stage stage = (Stage) btn_dwn.getScene().getWindow();
		stage.close();

		System.out.println("출력완료 파일을 확인하세요");

	}

	@FXML
	public void reNameDwn(ActionEvent event) throws Exception {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd ", Locale.KOREA);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", ".pdf"));

		File selFile = fileChooser.showSaveDialog(null);

		if (selFile != null) {
			Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(selFile.getPath()));
			try {

				doc.open();

				BaseFont korean = BaseFont.createFont("HYSMyeongJo-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);

				Font fontKorean = new Font(korean, 20, Font.BOLD);

				Image imageTop = Image.getInstance(this.getClass().getResource("matzip_top.png").getPath());
				imageTop.scaleAbsolute(500, 320);
				imageTop.setRotation(0);
				doc.add(imageTop);

				Paragraph name = new Paragraph(cvo.getCom_name(), fontKorean);
				name.setIndentationLeft(210);
				doc.add(name);

				Paragraph date = new Paragraph(mTime, fontKorean);
				date.setIndentationLeft(195);
				doc.add(date);

				Image imageBottom = Image.getInstance(this.getClass().getResource("matzip_bottom.png").getPath());
				imageBottom.scaleAbsolute(500, 320);
				imageBottom.setRotation(0);
				doc.add(imageBottom);

			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (DocumentException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				doc.close();
			}

			infoMsg("알림", selFile.getPath() + "가 저장되었습니다.");

			Stage stage = (Stage) btn_dwn.getScene().getWindow();
			stage.close();

			System.out.println("출력완료 파일을 확인하세요");
			System.out.println("SAVE : " + selFile.getPath());
		}

	}

}
