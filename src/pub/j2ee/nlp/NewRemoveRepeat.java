package pub.j2ee.nlp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.formula.HammingDistance;
import pub.j2ee.nlp.util.DelStopWordTool;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class NewRemoveRepeat {
	
	public static byte[] toByteArray(int hashcode){
		byte[] t = new byte[32];
		for(int i=0;i<32;i++){
			t[i]=-1;
		}
		String str = Integer.toBinaryString(hashcode);
		for(int i=0,j=str.length();i<j;i++){
			if(str.charAt(i)=='1'){
				t[32-str.length()+i]=1;
			}
		}
		return t;
	}
	
	public static String getSign(String str){
		byte[] total = new byte[32];
		for(int i=0;i<32;i++){
			total[i]=0;
		}
		List<Term> termList1 = HanLP.segment(str);
		//termList1 = DelStopWordTool.removeStopWord(termList1);
		for(Term t : termList1){
			//System.out.printf("%s/%s/%d\r\n",t.word,t.nature.toString(),t.getFrequency());
			//System.out.println(Integer.toBinaryString(t.word.hashCode()));
			byte[] bt = toByteArray(t.word.hashCode());
			for(int i=0;i<32;i++){
				total[i]= (byte)(total[i] + bt[i]);
			}
		}
		StringBuffer buff = new StringBuffer("");
		for(byte b : total){
			if(b>0){
				buff.append("1");
			}else{
				buff.append("0");
			}
		}
		return buff.toString();
	}
	
	public static void genSign(){
		long t = 0;
		try{
			FileInputStream fis = new FileInputStream("E:\\ep\\公司全称.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\ep\\公司全称_签名.txt"), "UTF-8"));
			String s = null;
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					String sign = getSign(s);
					wit.write(s+":"+sign);
					wit.write("\r\n");
					t++;
				}
				if(t%100==0){
					System.out.println(t);
				}
			}
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public static List<String> loadGen(){
		List<String> sList = new ArrayList<String>();
		long t = 0;
		try{
			FileInputStream fis = new FileInputStream("E:\\ep\\公司全称_签名.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			String s = null;
			while((s=red.readLine())!=null){
				sList.add(s.split(":")[1]);
				t++;
				if(t%100==0){
					System.out.println(t);
				}
			}
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
		return sList;
	}
	
	public static void main(String[] args){
		String sign = getSign("杭州沃土教育科技股份有限公司");
		String sign1 = getSign("广州景恩电子有限公司");
		System.out.println(sign);
		System.out.println(sign1);
		System.out.println(HammingDistance.getDistance(sign, sign1));
		
		//genSign();
		/*
		List<String> list = loadGen();
		System.out.println("加载完成");
		long start = System.currentTimeMillis();
		int sf = 0;
		for(int i=0,j=list.size();i<j;i++){
			int t = HammingDistance.getDistance(sign,list.get(i));
			if(t<2){
				sf++;
				System.out.println("坐标："+list.get(i));
			}
		}
		System.out.println(System.currentTimeMillis()-start);
		System.out.println("重复企业数："+sf);*/
	}
}
