package pub.j2ee.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;



public class Base64Utils {

	public static String getString(File f) throws IOException{
		FileInputStream input = new FileInputStream(f);
		int filesize = Long.valueOf(f.length()).intValue();
		byte[] b = new byte[filesize];
		input.read(b);
		input.close();
		return new String(Base64.encodeBase64(b));
	}
	
	public static void main(String[] args){
		try {
			System.out.println(Base64Utils.getString(new File("E:\\export_img\\export_img\\认证投资人名片\\5173.com_李伟泽.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
