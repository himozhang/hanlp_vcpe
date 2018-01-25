package pub.j2ee.spider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pub.j2ee.io.TxtUtils;

public class IpGet {

	public static void main(String[] args) {
		/*List<String> list = TxtUtils.readList("C:\\cygwin64\\pedata");
		Set<String> st = new HashSet<String>();
		for(String s : list){
			String [] temp = s.split("\t");
			st.add(temp[1]);
		}
		for(String s : st){
			System.out.println(s);
		}*/
		
		List<String> sn = TxtUtils.readList("C:\\cygwin64\\ip_n.txt");
		List<String> sa = TxtUtils.readList("C:\\cygwin64\\ip_a.txt");
		Set<String> n = new HashSet<String>();
		Set<String> a = new HashSet<String>();
		for(String t : sn){
			n.add(t);
		}
		for(String t : sa){
			a.add(t);
		}
		
		n.removeAll(a);
		for(String t : n){
			System.out.println(t);
		}
	}

}
