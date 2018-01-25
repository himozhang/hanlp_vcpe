package pub.j2ee.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EPCheckNumber {
	private static Map<String,Integer> CODE_VALUE_MP = new HashMap<String,Integer>();
	
	private static char[] codeArr = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y'};  
	
	static{
		CODE_VALUE_MP.put("0", 0);
		CODE_VALUE_MP.put("1", 1);
		CODE_VALUE_MP.put("2", 2);
		CODE_VALUE_MP.put("3", 3);
		CODE_VALUE_MP.put("4", 4);
		CODE_VALUE_MP.put("5", 5);
		CODE_VALUE_MP.put("6", 6);
		CODE_VALUE_MP.put("7", 7);
		CODE_VALUE_MP.put("8", 8);
		CODE_VALUE_MP.put("9", 9);
		CODE_VALUE_MP.put("A", 10);
		CODE_VALUE_MP.put("B", 11);
		CODE_VALUE_MP.put("C", 12);
		CODE_VALUE_MP.put("D", 13);
		CODE_VALUE_MP.put("E", 14);
		CODE_VALUE_MP.put("F", 15);
		CODE_VALUE_MP.put("G", 16);
		CODE_VALUE_MP.put("H", 17);
		CODE_VALUE_MP.put("J", 18);
		CODE_VALUE_MP.put("K", 19);
		CODE_VALUE_MP.put("L", 20);
		CODE_VALUE_MP.put("M", 21);
		CODE_VALUE_MP.put("N", 22);
		CODE_VALUE_MP.put("P", 23);
		CODE_VALUE_MP.put("Q", 24);
		CODE_VALUE_MP.put("R", 25);
		CODE_VALUE_MP.put("T", 26);
		CODE_VALUE_MP.put("U", 27);
		CODE_VALUE_MP.put("W", 28);
		CODE_VALUE_MP.put("X", 29);
		CODE_VALUE_MP.put("Y", 30);
	}
	
	/**
	 * 加权因子
	 */
	private static int[] w = new int[]{27,1,9,19,16,26,17,20,25,29,13,8,24,10,30,28,3};
	
	/**
	 * 获取拼接值
	 * @param code
	 * @return
	 */
	public static String getCheckCode(String code){
		int total = 0;
		for(int i=0,j=code.length();i<j;i++){
			Character c = code.charAt(i);
			int codeValue = CODE_VALUE_MP.get(c.toString());
			total+=codeValue*w[i];
		}
		int mod = total%31;
		int value = mod==0?31:mod;
		return code+codeArr[31-value];
	}
	
	public static void main(String[] args){
		System.out.println(getCheckCode("CMK"));
		/*String rl = "C1";
		Map<String,Boolean> code = new HashMap<String,Boolean>();
		while(code.entrySet().size()<700){
			Random random = new Random();
			StringBuffer buff = new StringBuffer("");
			buff.append(codeArr[random.nextInt(30)]);
			buff.append(codeArr[random.nextInt(30)]);
			buff.append(codeArr[random.nextInt(30)]);
			String sign = getCheckCode(buff.toString());
			code.put("201712C1"+sign, true);
		}
		for(Map.Entry<String,Boolean> et :code.entrySet()){
			System.out.println(et.getKey());
		}*/
	}
}
