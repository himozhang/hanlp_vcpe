package pub.j2ee.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.j2ee.io.TxtUtils;

public class StatMain {

	public static void main(String[] args) {
		List<String> list = TxtUtils.readList("E:\\access_logs\\2017-10-12（异常ip）.log");
		Map<String,Integer> m = new HashMap<String,Integer>();
		for(String s : list){
			String[] t = s.split("\t");
//			System.out.println(t[5]);
			String login = null;
			if(!t[3].equals("null")){
				login = t[3];
			}else if(!t[5].equals("null")){
				if(t[5].contains("$")){
					String st = t[5].substring(0, t[5].indexOf("$"));
					login = st.substring(st.lastIndexOf("\"")+1);
				}
			}
			if(login!=null){
				Integer i = m.get(login);
				if(i!=null){
					m.put(login, i+1);
				}else{
					m.put(login, 1);
				}
			}
		}
		
		for(Map.Entry<String, Integer> ent : m.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
	}

}
