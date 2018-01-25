package pub.j2ee.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于网络文件下载
 * @author pengyuzhang
 * 2016年5月27日
 */
public class DownFileUtil {

	/**
	 * 文件服务器根目录
	 */
	public final static String BASE_PATH = "/zdb/zdbupload/";
	//public final static String BASE_PATH = "E://zdbupload/";
	/**
	 * 图片访问域名
	 */
	public final static String ACCESS_DOMAIN = "http://pic.pedata.cn/";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 下载文件
	 * @param urlString		下载链接
	 * @param targetFolder	相对目录
	 * @param filename		文件名
	 * @return
	 */
	public static String download(String urlString,String targetFolder,String filename){
		if(urlString!=null && urlString.length()>0){
			String urlLink = "";
			// 去除活动行图片logo
			if(urlString.contains("@!wmlogo")){
				urlLink = urlString.replace("@!wmlogo", "");
			}else{
				urlLink = urlString;
			}
			System.out.println("download:"+urlLink);
			InputStream is = null;
			OutputStream os = null;
			try	{
				String dateFolder = sdf.format(new Date());
				// 文件下载路径
				String filePath = BASE_PATH + targetFolder + "/" + dateFolder;
				File fPath = new File(filePath);
				if(!fPath.exists()){	// 检查路径是否存在，不存在则创建相关目录
					fPath.mkdirs();
				}
				// 构造URL  
		        URL url = new URL(urlLink);  
		        // 打开连接  
		        URLConnection con = url.openConnection();  
		        con.setRequestProperty("accept", "*/*");
		        con.setRequestProperty("connection", "keep-alive");
		        con.setRequestProperty("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
		        //设置请求超时为5s  
		        con.setConnectTimeout(5*1000);  
		        con.setReadTimeout(5*1000);
		        // 输入流  
		        is = con.getInputStream();  
		      
		        // 1K的数据缓冲  
		        byte[] bs = new byte[1024];  
		        // 读取到的数据长度  
		        int len;  
		        os = new FileOutputStream(filePath+"/"+filename+".jpg"); 
		        // 开始读取  
		        while ((len = is.read(bs)) != -1) {  
		          os.write(bs, 0, len);  
		        }  
		        // 完毕，关闭所有链接  
		        os.close();  
		        is.close();
		        return ACCESS_DOMAIN + targetFolder + "/" + dateFolder + "/" + filename+".jpg";
			}catch(Exception err){
				err.printStackTrace();
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(os!=null){
					try{
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return "";
			}
		}else{
			return "";
		}
	}
	
	/**
	 * 下载文件服务
	 * @param downFilePath 	文件下载链接
	 * @param savePath		文件保存相对目录
	 * @param fileName		文件名称
	 */
	public static String downloadFile(String downFilePath,String savePath,String fileName){
		InputStream is = null;
		OutputStream os = null;
		try	{
			String dateFolder = sdf.format(new Date());
			// 文件下载路径
			String filePath = BASE_PATH + savePath + "/" + dateFolder;
			File fPath = new File(filePath);
			if(!fPath.exists()){	// 检查路径是否存在，不存在则创建相关目录
				fPath.mkdirs();
			}
			// 构造URL  
	        URL url = new URL(downFilePath);  
	        // 打开连接  
	        URLConnection con = url.openConnection();  
	        con.setRequestProperty("accept", "*/*");
	        con.setRequestProperty("connection", "keep-alive");
	        con.setRequestProperty("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
	        //设置请求超时为5s  
	        con.setConnectTimeout(5*1000);  
	        con.setReadTimeout(5*1000);
	        // 输入流  
	        is = con.getInputStream();  
	      
	        // 1K的数据缓冲  
	        byte[] bs = new byte[1024];  
	        // 读取到的数据长度  
	        int len;  
	        os = new FileOutputStream(filePath+"/"+fileName); 
	        // 开始读取  
	        while ((len = is.read(bs)) != -1) {  
	          os.write(bs, 0, len);  
	        }  
	        // 完毕，关闭所有链接  
	        os.close();  
	        is.close();
	        return filePath+"/"+fileName;
		}catch(Exception err){
			err.printStackTrace();
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os!=null){
				try{
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "";
		}
	}
	
	public static void main(String[] args){
		System.out.println(DownFileUtil.downloadFile("http://imguserradar.analysys.cn/images/item_logo/min/64_64_2028050.png", "qianfan", "111"));
	}
}
