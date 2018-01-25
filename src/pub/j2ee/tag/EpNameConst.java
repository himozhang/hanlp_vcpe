package pub.j2ee.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import pub.j2ee.io.TxtUtils;

public class EpNameConst {
	public static void main(String[] args){
		List<String> list = TxtUtils.readList("E:\\企业全称.txt");
		Map<String,Integer> m = new HashMap<String,Integer>();
		long idx = 0;
		StringBuffer b = new StringBuffer();
		for(String s : list){
			List<Term> wl = HanLP.segment(s);
			for(Term w : wl){
				b.append(w.nature.name()+"_");
			}
			b.deleteCharAt(b.length()-1);
			Integer i = m.get(b.toString());
			if(i==null){
				m.put(b.toString(), 1);
			}else{
				m.put(b.toString(), ++i);
			}
			b.setLength(0);
			idx++;
			if(idx%10000==0){
				System.out.println(idx);
			}
		}
		System.out.println(m.entrySet().size());
	}
}
