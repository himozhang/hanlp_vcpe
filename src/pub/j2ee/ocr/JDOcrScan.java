package pub.j2ee.ocr;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JDOcrScan {
	
	public static void post(String filePath,String imgType) throws ClientProtocolException, IOException {
		String appKey = "0b70b26d25344e6f8b0cfb77839d28f0";
		String appName = "scan_img";
		long timestamp = System.currentTimeMillis();
		int rand = getRandNum();
		String bucketDomain = "ossfile.oss.cn-north-1.jcloudcs.com";
		StringBuffer buff = new StringBuffer("http://cognitive-console.jcloud.com/service-ai/ocr/detect-text?");
		buff.append("appKey=").append(appKey);
		buff.append("&appName=").append(appName);
		buff.append("&timestamp=").append(timestamp);
		buff.append("&rand=").append(rand);
		buff.append("&bucketDomain=").append(bucketDomain);
		String url = buff.toString();
		
		HttpClient httpclient = new DefaultHttpClient();

		// 请求路径
		HttpPost post = new HttpPost(url);
		// 添加header头信息
		post.setHeader("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;)");
		// 注 这里一定不能添加 content-Type:multipart/form-data 属性
		// 因为这里面有个boundary参数属性是不可控的。这个值是由浏览器生成的。如果强行指明和可能
		// 导致边界值不一致 就会请求失败 详细参见
		// http://blog.csdn.net/xiaojianpitt/article/details/6856536

		post.setHeader("Authorization", generateSign(appKey, appName, timestamp, rand));
		post.setHeader("Host", "cognitive-console.jcloud.com");
		post.setHeader("Accept-Encoding", "gzip");
		post.setHeader("charset", "utf-8");
		FileBody fileBody = new FileBody(new File(filePath), imgType,
				"utf-8");
		MultipartEntity entity = new MultipartEntity();
		// 添加消息体信息
		entity.addPart("bucketDomain", new StringBody("ossfile.oss.cn-north-1.jcloudcs.com", Charset.forName("utf-8")));
		entity.addPart("file", fileBody);
		post.setEntity(entity);
		HttpResponse response = httpclient.execute(post);
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {

			HttpEntity entitys = response.getEntity();
			if (entity != null) {
				String jsonResult = EntityUtils.toString(entitys);
				StringUtils.isNotBlank(jsonResult);
				JSONObject rs = JSONObject.parseObject(jsonResult);
				if(rs.getIntValue("httpCode")==200&&rs.getString("msg").equals("OK")){
					JSONArray jA = rs.getJSONArray("recognizeResult");
					if(jA!=null && jA.size()>0){
						JSONObject texts = jA.getJSONObject(0).getJSONObject("texts");
						JSONArray textsA = texts.getJSONArray("texts");
						if(textsA!=null && textsA.size()>0){
							System.out.println("Success:"+filePath);
							for(Object obj : textsA){
								JSONObject tt = (JSONObject)obj;
								System.out.println(tt.getString("text"));
							}
						}
					}
				}else{
					System.out.println("Error:"+filePath);
					System.out.println(EntityUtils.toString(entitys));
				}
			}
		}else{
			System.out.println(response);
		}
		httpclient.getConnectionManager().shutdown();
		System.out.println();
		System.out.println();
	}
	
	private static String generateSign(String appKey,String appName,long timestamp,int rand) {
		// 拼接字符串

		String str = "appKey=" + appKey + "&appName=" + appName + "&timestamp=" +

		timestamp + "&rand=" + rand;

		String appSecret = "yVaFuz8Y8q8QU6LafuOFka==";// appSecret,用户可在控制台点击‘查看密匙’获取

		try {

			byte[] strBytes = str.getBytes("UTF-8");// 字符串转为byte[]

			byte[] appSecretBytes = appSecret.getBytes("UTF-8");// secret转为byte[]

			byte[] signBytes = encodeHmacSHA512(strBytes, appSecretBytes);// 生成签名byte[]

			String sign = new String(new Base64().encode(signBytes), "UTF-8");// 生成签名

			return sign;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return "";
	}

	private static byte[] encodeHmacSHA512(byte[] data, byte[] key)
			throws Exception {

		SecretKey secretKey = new SecretKeySpec(key, "HmacSHA512");

		Mac mac = Mac.getInstance(secretKey.getAlgorithm());

		mac.init(secretKey);

		return mac.doFinal(data);

	}

	private static int getRandNum() {

		Random random = new Random();

		return random.nextInt(10000);

	}

	
	public static void main(String[] args){
		File root = new File("E:\\export_img\\export_img\\认证投资人名片");
		for(File file : root.listFiles()){
			try {
				post(file.getAbsolutePath(),file.getName().contains(".png")?"image/png":"image/jpeg");
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
