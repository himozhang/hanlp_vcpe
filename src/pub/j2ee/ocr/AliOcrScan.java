package pub.j2ee.ocr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pub.j2ee.util.Base64Utils;
import pub.j2ee.util.HttpUtils;

public class AliOcrScan {
	
	public static void post(File file){
		String host = "https://dm-57.data.aliyun.com";
	    String path = "/rest/160601/ocr/ocr_business_card.json";
	    String method = "POST";
	    String appcode = "43cd4d0808e84543a003a9b497c5117a";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/json; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
	    String bodys = null;
		try {
			bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""+Base64Utils.getString(file)+"\"}}]}";
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		System.out.println(bodys);
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//	    	System.out.println(response.toString());
	    	//获取response的body
	    	String result = EntityUtils.toString(response.getEntity());
	    	if(StringUtils.isNotBlank(result)&&result.startsWith("{")){
	    		JSONObject rs = JSONObject.parseObject(result);
	    		JSONArray jA = rs.getJSONArray("outputs");
	    		if(jA!=null && jA.size()>0){
	    			JSONObject r1 = jA.getJSONObject(0);
	    			JSONObject outputValue = r1.getJSONObject("outputValue");
	    			if(outputValue!=null){
	    				System.out.println("Success:"+file.getName());
	    				JSONObject temp = JSONObject.parseObject(outputValue.getString("dataValue"));
	    				if(temp.getBooleanValue("success")){
	    					if(temp.get("name")!=null)
	    					System.out.println("姓名："+temp.get("name"));
	    					if(temp.get("company")!=null)
		    				System.out.println("公司："+temp.get("company"));
	    					if(temp.get("department")!=null)
		    				System.out.println("部门："+temp.get("department"));
	    					if(temp.get("title")!=null)
		    				System.out.println("职位："+temp.get("title"));
	    					if(temp.get("tel_cell")!=null)
		    				System.out.println("手机："+temp.get("tel_cell"));
	    					if(temp.get("tel_work")!=null)
		    				System.out.println("座机："+temp.get("tel_work"));
	    					if(temp.get("addr")!=null)
		    				System.out.println("地址："+temp.get("addr"));
	    					if(temp.get("email")!=null)
		    				System.out.println("邮箱："+temp.get("email"));
	    					
	    					System.out.println();
	    					System.out.println();
	    				}
	    			}
	    		}
	    	}
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		File root = new File("E:\\export_img\\export_img\\认证投资人名片");
		for(File file : root.listFiles()){
			try {
				post(file);
			} catch (Exception e) {
			}
		}
	}

}
