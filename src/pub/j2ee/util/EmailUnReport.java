package pub.j2ee.util;

import java.util.List;

import pub.j2ee.io.TxtUtils;

/**
 * 邮件去重
 * @author pengyuzhang
 * 2017年11月24日
 */
public class EmailUnReport {

	public static void main(String[] args){
		List<String> list = TxtUtils.readList("E:\\batch_send_email.txt");
		for(String s : list){
			if(RegexUtils.isEmail(s)){
				System.out.println(s);
			}
		}
	}
	
}
