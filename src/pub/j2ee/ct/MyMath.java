package pub.j2ee.ct;

public class MyMath {

	// 简单求和
	public static double sum(double [] values){
		double total = 0;
		for(double t : values){
			total+=t;
		}
		return total;
	}
	
	// 平方和
	public static double sumSq(double [] values){
		double total = 0;
		for(double t : values){
			total+=(t*t);
		}
		return total;
	}
	
	// 求乘积之和
	public static double sumMult(double [] values0,double [] values1){
		double total = 0;
		for(int i=0,j=values0.length;i<j;i++){
			total+=(values0[i]*values1[i]);
		}
		return total;
	};
	
	public static double[] avgArr(double[] v1,double[] v2){
		double[] t = new double[v1.length];
		for(int i=0,j=v1.length;i<j;i++){
			t[i] = (v1[i]+v2[i])/2;
		}
		return t;
	}
	
	public static double[] avgArrP(double[] v1,double[] v2){
		double[] t = new double[v1.length];
		for(int i=0,j=v1.length;i<j;i++){
			t[i] = ((v1[i]+v2[i])/2)>0?1:0;
		}
		return t;
	}
	
	/**
	 * 皮尔逊相关系数
	 * @param x
	 * @param y
	 * @return
	 */
	public static double pearson(double[] x,double[] y){
		int n = x.length;
		// 简单求和
		double sumx = MyMath.sum(x);
		double sumy = MyMath.sum(y);
		// 求平方和
		double sumxSq = MyMath.sumSq(x);
		double sumySq = MyMath.sumSq(y);
		// 求乘积之和
		double pSum = MyMath.sumMult(x, y);
		
		// 计算皮尔逊评价值
		double num = pSum - (sumx*sumy/n);
		double den = Math.sqrt((sumxSq - Math.pow(sumx, 2)/n)*(sumySq - Math.pow(sumy, 2)/n));
		if(den==0){
			return 1;
		}
		double r = num/den;
		return r;
	}
	
	/**
	 * Tanimoto系数
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double tanimoto(double[] v1,double[] v2){
		double c1=0,c2=0,shr=0;
		for(int i=0,j=v1.length;i<j;i++){
			if(v1[i]!=0)
				c1+=1;
			if(v2[i]!=0)
				c2+=1;
			if(v1[i]!=0 && v2[i]!=0)
				shr+=1;
		}
		return shr/(c1+c2-shr);
	}
	
	public static void main(String[] args){
		double[] a = {0,2,3,4};
		double[] b = {0,1,3,5};
		double[] c = {0,1,1,0};
		double[] d = {0,1,1,0};
		System.out.println(pearson(a,b));
		System.out.println(pearson(c,d));
		System.out.println(tanimoto(c,d));
	}
}
