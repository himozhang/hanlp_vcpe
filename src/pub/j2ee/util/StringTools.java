package pub.j2ee.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {
	
	private static final String regEx_html = "<[^>]+>";
	private static final String regEx_encode = "&[^;]+;";
	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/*
	 * 去除字符串中的html标签
	 */
	public static String removeInTag(String a) {  
		if(a==null){
			return "";
		}
	    StringBuffer aaa = new StringBuffer(a);    
	    int BeginIndex = 0;   
	    while(BeginIndex != -1){  
	        BeginIndex = aaa.indexOf("<",BeginIndex);  
	        int StarIndex = aaa.indexOf("<", BeginIndex);  
	        int EndIndex = aaa.indexOf(">", BeginIndex);
	        if(StarIndex==-1||EndIndex==-1){
	        	break;
	        }
	        if ((EndIndex > StarIndex)){        
	            aaa.replace(StarIndex, EndIndex+1, "");  
	        }
	    }
	    return replaceBlank(aaa.toString());
	}
	

	/*
	 * 去除字符串中的所有标签格式
	 */
	public static String removeTagA(String a) {
//	    String aaa = a;
	    String aaa = a.replaceAll("<(/?a)\\s*?[^<]*?(/?)>","<$1$2>");
//	    String aaa = a.replaceAll("<a.*?href=\"(.*?)\">","<a>");
	    return aaa;
	}
    /*-----------------------------------
	 
	    笨方法：String s = "你要去除的字符串";
	 
	            1.去除空格：s = s.replace('\\s','');
	 
	            2.去除回车：s = s.replace('\n','');
	 
	    这样也可以把空格和回车去掉，其他也可以照这样做。
	 
	    注：\n 回车(\u000a) 
	    \t 水平制表符(\u0009) 
	    \s 空格(\u0008) 
	    \r 换行(\u000d)*/
	
	public static String removeHtmlTag(String htmlStr){
		if(htmlStr==null){
			return "";
		}
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        return m_html.replaceAll(""); // 过滤html标签 
	}
	
	public static String removeHtmlEncode(String htmlStr){
		if(htmlStr==null){
			return "";
		}
		Pattern p_html = Pattern.compile(regEx_encode, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        return m_html.replaceAll(""); // 过滤html标签 
	}
	
	/**
	 * 去掉不符合json规范的字符串
	 * @param s
	 * @return
	 */
	public static String replaceExceptJson(String s){
		if(s==null){
			return "";
		}
		StringBuffer t = new StringBuffer("");
		for(int i=0,j=s.length();i<j;i++){
			char c = s.charAt(i);
			if(c>31 && c!=34 && c!=39 && c!=92){
				t.append(c);
			}
		}
		return t.toString();
	}
	
	/**
	 * 按自左向右返回非空子串
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String ifNullString(String str1,String str2){
		if(str1 != null){
			return replaceExceptJson(str1);
		}else if(str2 != null){
			return replaceExceptJson(str2);
		}else{
			return "";
		}
	}
	
	/**
	 * 去除文件名中的非法字符
	 * @return
	 */
	public static String replaceExceptFileName(String s){
		if(s==null){
			return "";
		}
		StringBuffer t = new StringBuffer("");
		for(int i=0,j=s.length();i<j;i++){
			char c = s.charAt(i);
			if (c > 31 && c != 63 && c != 47 && c != 92 && c != 42 && c != 58
					&& c != 34 && c != 60 && c != 62 && c!=124) {
				t.append(c);
			}
		}
		return t.toString();
	}
	
	/**
	 * 去除简介里的html标签和非法换行
	 * @param s
	 * @return
	 */
	public static String textToDesc(String s){
		if(s==null){
			return "";
		}
		String rs = removeHtmlTag(s);
		rs = removeHtmlEncode(rs);
		rs = replaceExceptJson(rs);
		return rs;
	}
	
	/**
	 * 获取非null字符串拼接
	 * @param s
	 * @return
	 */
	public static String unNullValue(String s){
		if(s==null){
			return "";
		}else{
			return s.trim();
		}
	}
	/**
	 * 获取非null字符串拼接
	 * @param s
	 * @return
	 */
	public static String unNullValue(Integer s){
		if(s==null || s==0){
			return "";
		}else{
			return s.toString();
		}
	}
	/**
	 * 获取非null字符串拼接
	 * @param s
	 * @return
	 */
	public static String unNullValue(Long s){
		if(s==null || s==0){
			return "";
		}else{
			return s.toString();
		}
	}
	
	/**
	 * 截取字符串
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subString(String str,int length){
		if(str.length()<=length){
			return str;
		}else{
			return str.substring(0, length);
		}
	}
	
	/**
	 * 去掉最后一个","号
	 * @param buff
	 * @return
	 */
	public static void removeEndChar(StringBuffer buff){
		char endChar = buff.charAt(buff.length()-1);
		if(endChar==','){
			buff.deleteCharAt(buff.length()-1);
		}
	}
	
	public static void main(String[] args){
		System.out.println(replaceExceptJson("杨飞是国家重点课题\"中国地区发展战略研究\"的主要研究人员之一"));
		System.out.println(replaceExceptFileName("123123\\/:*?\"<>|"));
	}
}
