package pub.j2ee.util;

import java.util.regex.Pattern;

public class RegexUtils {

	/**
	 * 手机号正则
	 */
	private static String mobileReg = "^[0-9]{11}$";
	/**
	 * 邮箱正则
	 */
	private static String emailReg = "^[a-zA-Z0-9\\_\\-\\.]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	
	/**
	 * 验证手机号是否合法
	 * @param mobileStr 手机号
	 * @return
	 */
	public static boolean isMobile(String mobileStr){
		return Pattern.matches(mobileReg,mobileStr);
	}
	/**
	 * 验证邮箱是否合法
	 * @param emailStr 邮箱字符
	 * @return
	 */
	public static boolean isEmail(String emailStr){
		return Pattern.matches(emailReg, emailStr);
	}
	
	public static void main(String [] args){
		System.out.println(isMobile("15120058058"));
		System.out.println(isEmail("chang.ge.ds@cn.pwc.com"));
	}
}
