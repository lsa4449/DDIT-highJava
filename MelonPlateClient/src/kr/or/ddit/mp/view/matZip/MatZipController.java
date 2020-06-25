package kr.or.ddit.mp.view.matZip;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.matZip.MatZipService;
import kr.or.ddit.mp.vo.matZip.ReviewVO;
import kr.or.ddit.mp.vo.member.ComVO;

public class MatZipController implements Initializable {

	@FXML
	AnchorPane ap_write;
	@FXML
	Label lab_percent;
	@FXML
	Label lab_comName;
	@FXML
	ImageView img_gage;
	@FXML
	ImageView mel_home;
	@FXML
	Label lab_re_num;
	@FXML
	Label lab_good_num;
	@FXML
	Label lab_re_per;
	@FXML
	Label lab_good_per;
	@FXML
	Label lab_str1;
	@FXML
	Label lab_str2;
	@FXML
	Label lab_totalPer;
	@FXML
	Button btn_pdf;

	// 맛집 서비스
	private Registry reg;
	private MatZipService mzs;

	// 글 전체 정보를 담고 있는 리스트

	// 테스트용 vo ---> 로그인한 사용자 정보 받아오기
	ReviewVO rvo = new ReviewVO();
	// String session = "35";

	// kr.or.ddit.mp.vo.member.MemberVO vo = LoginSession.session;
	ComVO cvo = LoginSession.comsession;
	String ss = cvo.getMem_no();
	ArrayList<ReviewVO> list = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btn_pdf.setDisable(true);

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
		try {

			list = (ArrayList<ReviewVO>) mzs.getGoodCom(rvo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		lab_comName.setText(cvo.getCom_name());

		if (list.size() != 0) {

			// lab_comName.setText(list.get(0).getCom_name());

			int goodCom_gage_re_no = 0;
			if (Integer.parseInt(list.get(0).getRe_no()) >= 0 && Integer.parseInt(list.get(0).getRe_no()) < 4) {
				goodCom_gage_re_no = 0;
			} else if (Integer.parseInt(list.get(0).getRe_no()) >= 4 && Integer.parseInt(list.get(0).getRe_no()) < 8) {
				goodCom_gage_re_no = 10;
			} else if (Integer.parseInt(list.get(0).getRe_no()) >= 8 && Integer.parseInt(list.get(0).getRe_no()) < 12) {
				goodCom_gage_re_no = 20;
			} else if (Integer.parseInt(list.get(0).getRe_no()) >= 12
					&& Integer.parseInt(list.get(0).getRe_no()) < 16) {
				goodCom_gage_re_no = 30;
			} else if (Integer.parseInt(list.get(0).getRe_no()) >= 16
					&& Integer.parseInt(list.get(0).getRe_no()) < 20) {
				goodCom_gage_re_no = 40;
			} else if (Integer.parseInt(list.get(0).getRe_no()) >= 20) {
				goodCom_gage_re_no = 50;
			} else {
				goodCom_gage_re_no = 0;
			}

			int goodCom_gage_re_score = 0;
			if (Double.parseDouble(list.get(0).getRe_score()) >= 0
					&& Double.parseDouble(list.get(0).getRe_score()) < 0.48) {
				goodCom_gage_re_score = 0;
			} else if (Double.parseDouble(list.get(0).getRe_score()) >= 0.48
					&& Double.parseDouble(list.get(0).getRe_score()) < 0.96) {
				goodCom_gage_re_score = 10;
			} else if (Double.parseDouble(list.get(0).getRe_score()) >= 0.96
					&& Double.parseDouble(list.get(0).getRe_score()) < 1.44) {
				goodCom_gage_re_score = 20;
			} else if (Double.parseDouble(list.get(0).getRe_score()) >= 1.44
					&& Double.parseDouble(list.get(0).getRe_score()) < 1.92) {
				goodCom_gage_re_score = 30;
			} else if (Double.parseDouble(list.get(0).getRe_score()) >= 1.92
					&& Double.parseDouble(list.get(0).getRe_score()) < 2.4) {
				goodCom_gage_re_score = 40;
			} else if (Double.parseDouble(list.get(0).getRe_score()) >= 2.4) {
				goodCom_gage_re_score = 50;
			} else {
				goodCom_gage_re_score = 0;
			}

			lab_re_num.setText(list.get(0).getRe_no() + "개");
			lab_re_per.setText(goodCom_gage_re_no * 2 + "% 달성");

			lab_good_num.setText(list.get(0).getRe_score() + "점");
			lab_good_per.setText(goodCom_gage_re_score * 2 + "% 달성");

			lab_percent.setText(100 - (goodCom_gage_re_no + goodCom_gage_re_score) + "%");
			lab_totalPer.setText(goodCom_gage_re_no + goodCom_gage_re_score + "%");

			// 확인하기 System.out.println(goodCom_gage);

			if (goodCom_gage_re_no + goodCom_gage_re_score == 90) {
				img_gage.setImage(new Image(getClass().getResource("90.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 80) {
				img_gage.setImage(new Image(getClass().getResource("80.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 70) {
				img_gage.setImage(new Image(getClass().getResource("70.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 60) {
				img_gage.setImage(new Image(getClass().getResource("60.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 50) {
				img_gage.setImage(new Image(getClass().getResource("50.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 40) {
				img_gage.setImage(new Image(getClass().getResource("40.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 30) {
				img_gage.setImage(new Image(getClass().getResource("30.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 20) {
				img_gage.setImage(new Image(getClass().getResource("20.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 10) {
				img_gage.setImage(new Image(getClass().getResource("10.png").toString()));
			} else if (goodCom_gage_re_no + goodCom_gage_re_score == 100) {
				btn_pdf.setDisable(false);
				img_gage.setImage(new Image(getClass().getResource("100.png").toString()));
				lab_str1.setText("우수맛집");
				lab_percent.setText("달성");
				lab_str2.setText("을 축하드립니다!!");
			}
		} else {

			img_gage.setImage(new Image(getClass().getResource("0.png").toString()));

			lab_re_num.setText("0 개");
			lab_re_per.setText("0 % 달성");

			lab_good_num.setText("0 점");
			lab_good_per.setText("0 % 달성");

			lab_percent.setText("100%");
			lab_totalPer.setText("0 %");

		}
	}

	@FXML
	public void storedPdf(ActionEvent event) throws Exception {

		Parent parent = null;

		try {
			parent = FXMLLoader.load(getClass().getResource("pdf.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(parent);
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setTitle("우수 맛집 증서 발급");
		dialog.setScene(scene);
		dialog.show();

	}

}
