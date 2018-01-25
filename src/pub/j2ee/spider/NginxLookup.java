package pub.j2ee.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NginxLookup {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String filePath = "";
		String url = "";
		if(args!=null){
			url = args[0];
			filePath = args[1];
		}
		BufferedWriter output = null;
		try{
			File file = new File(filePath);
			output = new BufferedWriter(new FileWriter(file,true));
			while(true){ 
				try{
					Document doc = Jsoup.connect(url)
							.get();
					StringBuffer buff = new StringBuffer();
					buff.append(sdf.format(new Date()));
					buff.append("\t");
					buff.append(doc.text().split(" ")[2]);
					System.out.println(buff);
					output.write(buff.toString());
					output.write("\r\n");
					output.flush();
					Thread.sleep(30000);
				}catch(Exception err){}
			}
		}catch(Exception err){
			err.printStackTrace();
		}finally{
			if(output!=null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
