package kr.or.ddit.mp.view.chatbot;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.vo.member.MemberVO;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

public class ChatbotController implements Initializable {

	private static final String URL = "https://gateway.watsonplatform.net/assistant/api";
	public static final String WORKSPACE_ID= "b916acec-9abe-4cb4-8fce-34111c2d4143";
	public static final String API_KEY = "Ko0SxEiGXsRRzo-hrqp-ie6qnO6gWzSRL7smabDbhANH";
	public static final String VERSION = "2019-04-18";

	private Assistant assistant;
	@FXML AnchorPane an_chatmain;
	@FXML TextField txtf_qna;
	@FXML JFXTextArea txta_chatbot;
	@FXML JFXButton btn_send;
	
	MemberVO mv = LoginSession.session;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	      ChatbotService cs = new ChatbotService();
	      
	      cs.HelloMelony();
	      
	      btn_send.setOnAction(e->{
	         cs.message(mv.getMem_nick() ,txtf_qna.getText());
	      });
	      
	      txtf_qna.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    	    @Override
	    	    public void handle(KeyEvent keyEvent) {
	    	        if (keyEvent.getCode() == KeyCode.ENTER)  {
	    	        	cs.message(mv.getMem_nick() ,txtf_qna.getText());
	    	        	txtf_qna.setText("");
	    	        }
	    	    }
	    	});
	    
		
	}
	
	public class ChatbotService {
		
		public ChatbotService()  {
			
			IamOptions options = new IamOptions.Builder()
					.apiKey(API_KEY)
					.build();
			
			assistant = new Assistant(VERSION, options);
			
			assistant.setEndPoint(URL);
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("X-Watson-Learning-Opt-Out", "true");
			assistant.setDefaultHeaders(headers);
			
		}
		
		public void message(String nick, String msg) {
			
			txta_chatbot.appendText( nick + " : " + msg +"\n");
			
			
			InputData input = new InputData.Builder(msg).build();
			
			MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
					.input(input)
					.build();
			
			MessageResponse response = assistant.message(options).execute();
			
			JsonObject jsonObject=new JsonObject();
			JsonParser jsonParser=new JsonParser();
			
			Object obj = jsonParser.parse(response.toString());
			
			jsonObject = (JsonObject) obj;
			
			obj = jsonParser.parse(jsonObject.get("output").toString());
			
			jsonObject = (JsonObject) obj;
			
			JsonArray list = (JsonArray)jsonObject.get("text");
			
			Iterator<JsonElement> it = list.iterator();

			txta_chatbot.appendText("멜로니 : ");
			
			while(it.hasNext()){
				String ans_chat = it.next().toString();
				ans_chat = ans_chat.substring(1, ans_chat.length()-1);
				txta_chatbot.appendText(ans_chat+"\n");
			}
		}
		
		public void HelloMelony() {
									
			InputData input = new InputData.Builder("").build();
			
			MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
					.input(input)
					.build();
			
			MessageResponse response = assistant.message(options).execute();
			
			JsonObject jsonObject=new JsonObject();
			JsonParser jsonParser=new JsonParser();
			
			Object obj = jsonParser.parse(response.toString());
			
			jsonObject = (JsonObject) obj;
			
			obj = jsonParser.parse(jsonObject.get("output").toString());
			
			jsonObject = (JsonObject) obj;
			
			JsonArray list = (JsonArray)jsonObject.get("text");
			
			Iterator<JsonElement> it = list.iterator();
			txta_chatbot.appendText("멜로니 : ");
			
			while(it.hasNext()){
				String ans_chat = it.next().toString();
				ans_chat = ans_chat.substring(1, ans_chat.length()-1);
				txta_chatbot.appendText(ans_chat+"\n");
			}
		}
	}


	
}
