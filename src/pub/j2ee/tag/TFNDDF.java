package pub.j2ee.tag;

public class TFNDDF {

	// 函数参数
	private static Double o = 0.0001;
	private static Integer u = 500;
	public static Double nddf(Double x){
		Double p1 = Math.pow(Math.E, -1*Math.pow(x-u, 2)/2*Math.pow(o, 2));
		Double p2 = Math.sqrt(2*Math.PI)*o;
		return p1/p2;
	}
	
	public static void main(String[] args){
		for(int i=0;i<1000;i++){
			System.out.printf("%d\t%f\r\n",i,nddf(Double.valueOf(i)));
		}
	}
}
