package pub.j2ee.spider;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import pub.j2ee.io.TxtUtils;

public class IpSearch {
	
	public static void main(String [] args){
		String ipFile = args[0];
		List<String> list = TxtUtils.readList(ipFile);
		for(String s : list){
			try{
				Document doc = Jsoup.connect("http://ip.chinaz.com/"+s)
						.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36 OPR/37.0.2178.43")
						.timeout(5000)
						.get();
				Elements eles = doc.select(".Whwtdhalf");
				if(eles.size()==8){
					System.out.printf("%s\t%s\r\n",eles.get(4).text(),eles.get(7).text());
				}
			}catch(Exception err){}
		}
	}
}
