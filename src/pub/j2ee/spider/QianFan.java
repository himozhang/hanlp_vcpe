/**
 * 
 */
package pub.j2ee.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;




import pub.j2ee.io.DownFileUtil;
import pub.j2ee.io.TxtUtils;
import pub.j2ee.util.UUIDGenerator;

/**
 * @author pengyuzhang
 * 2017年11月8日
 */
public class QianFan {

	public static void main(String[] args){
		Map<String,String> mg = new HashMap<String,String>(); 
		for(int i=1;i<10;i++){
			List<String> list = TxtUtils.readList("E:\\applogo.txt");
			for(String s : list){
				String[] temp = s.split("####");
				String name = temp[0];
				String img = temp[1];
				if(StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(img)&&!"/images/common/default_logo.png".equals(img)){
					mg.put(name, img);
				}
			}
		}
		for(Map.Entry<String, String> et : mg.entrySet()){
			String dPath = DownFileUtil.downloadFile(et.getValue(), "pedata-app/logo/app", UUIDGenerator.getUUID()+et.getValue().substring(et.getValue().length()-4));
			if(StringUtils.isNotBlank(dPath)){
				System.out.printf("%s\t%s\r\n",et.getKey(),dPath.substring(15));
			}
		}
	}
}
