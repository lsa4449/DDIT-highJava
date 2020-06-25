package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Subject {

	public static void main(String[] args) {

		String filePath = "d:/D_Other/Tulips.jpg";

		String copyFilePath = "d:/D_Other/copy_Tulips.jpg";

		File file1 = new File(filePath);
		File file2 = new File(copyFilePath);
 
		try {

			FileInputStream fis = new FileInputStream(file1);
			FileOutputStream fos = new FileOutputStream(file2);

			int c;

			while ((c = fis.read()) != -1) {
				fos.write(c);
				
			}

			fis.close();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
