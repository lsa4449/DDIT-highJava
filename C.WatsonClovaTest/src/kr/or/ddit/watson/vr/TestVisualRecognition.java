package kr.or.ddit.watson.vr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class TestVisualRecognition {
	
	public void test(){
		
	   IamOptions options = new IamOptions.Builder().apiKey("bQOhukOrcGunphRsO-LhZVbcGweDeB3gpsBIZUF2R9em").build();
	   
	   VisualRecognition service = new VisualRecognition("2018-03-19", options);
	   
	   InputStream imagesStream;
	   
	   try {
		   
		   //imagesStream = new FileInputStream("src/basic/watson/actrees.jpg");
		   //imagesStream = new FileInputStream("src/kr/or/ddit/watson/visualRecognition/test.jpg");
		   imagesStream = new FileInputStream(new File(getClass().getResource("test.jpg").getPath()));
		   
		   ClassifyOptions classifyOptions
		   	= new ClassifyOptions
		   	.Builder()
		   	.imagesFile(imagesStream)
		   	.imagesFilename("test.jpg")
		   	.build();
		   
		   ClassifiedImages result = service.classify(classifyOptions).execute();
		   System.out.println(result);
		   
	   }catch(FileNotFoundException e) {
		   e.printStackTrace();
	   }
	   
	}
	
	public static void main(String[] args) {
		TestVisualRecognition obj = new TestVisualRecognition();
		obj.test();
	}
}
