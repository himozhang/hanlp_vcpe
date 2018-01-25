package pub.j2ee.ct.chapter3;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import pub.j2ee.ct.MyMath;
import pub.j2ee.io.TxtUtils;

/**
 * 分级聚类算法
 * @author pengyuzhang
 * 2017年9月12日
 */
public class TestHcluster1 {
	
	public static double distance(double[] v1,double[] v2){
		//return 1.0 - MyMath.pearson(v1,v2);	// 针对矩阵数值位真实值的情况
		return 1.0 - MyMath.tanimoto(v1,v2); // 矩阵内容为1、0时使用
	}
	
	public static BiCluster1 hcluster(double[][]rows){
		Map<String,Double> distances = new HashMap<String,Double>();
		int currentclustid = -1;
		
		// 最开始的聚类就是数据集中的行
		List<BiCluster1> clust = new ArrayList<BiCluster1>();
		for(int i=0,j=rows.length;i<j;i++){
			clust.add(new BiCluster1(null, rows[i], i, 0.0));
		}
		
		while(clust.size()>1){
			int[] lowestpair = {0,1};
			Double closest = distance(clust.get(0).vec, clust.get(1).vec);
			List<Integer> clsList = new ArrayList<Integer>();
			// 遍历每个配对，寻找最小距离
			for(int i=0,j=clust.size();i<j;i++){
				for(int m=i+1;m<j;m++){
					Double d = distances.get(clust.get(i).id+","+clust.get(m).id);
					if(d==null){
						d = distance(clust.get(i).vec,clust.get(m).vec);
						distances.put(clust.get(i).id+","+clust.get(m).id, d);
					}
					if(d<closest){
						closest = d;
						lowestpair[0] = i;
						lowestpair[1] = m;
					}
				}
			}
			
			clsList.add(lowestpair[0]);
			clsList.add(lowestpair[1]);
			
			for(int i=0,j=clust.size();i<j;i++){
				if(i!=lowestpair[0]&&i!=lowestpair[1]){
					Double d = distances.get(clust.get(i).id+","+clust.get(lowestpair[0]).id);
					if(d==null){
						d = distance(clust.get(i).vec,clust.get(lowestpair[0]).vec);
						distances.put(clust.get(i).id+","+clust.get(lowestpair[0]).id, d);
					}
					if(d==closest){
						clsList.add(i);
					}
				}
			}
			
			// 计算两个聚类的平均值
			double[] mergevec = MyMath.avgArr(clust.get(lowestpair[0]).vec, clust.get(lowestpair[1]).vec);
			
			List<BiCluster1> list = new ArrayList<BiCluster1>();
			for(Integer i : clsList){
				list.add(clust.get(i));
			}
			// 建立新的聚类
			BiCluster1 newcluster = new BiCluster1((BiCluster1[])(list.toArray(new BiCluster1[list.size()])), mergevec, currentclustid, closest);
			
			// 不在原始集合中的聚类，其id为负数
			currentclustid-=1;
			Collections.sort(clsList,new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			});
			for(int j=clsList.size();j>=0;j++){
				clust.remove(clsList.get(j-1));
			}
			clust.add(newcluster);
		}
		return clust.get(0);
	}
	
	public static void printclust(BiCluster1 clust,String[] labels,String [] columns,int n){
		for(int i=0;i<n;i++){
			System.out.print(" ");
		}
		if(clust.id<0){
			if(n==0){
				System.out.print("◆");
			}else{
				System.out.print("|");
				System.out.print("【");
				for(int m=0,u=clust.vec.length;m<u;m++){
					if(clust.vec[m]>0){
						System.out.print(columns[m]);
						System.out.print(",");
					}
				}
				System.out.print("】");
			}
		}else{
			System.out.print("-");
			System.out.print(labels[clust.id]);
			System.out.print("【");
			for(int m=0,u=clust.vec.length;m<u;m++){
				if(clust.vec[m]>0){
					System.out.print(columns[m]);
					System.out.print(",");
				}
			}
			System.out.print("】");
		}
		System.out.println();
		if(clust!=null&&clust.cls.length>0){
			for(BiCluster1 b : clust.cls){
				printclust(b, labels,columns,n+1);
			}
		}
	}
	
	public static void printRadialInfo(BiCluster1 clust,String[] labels,String [] columns,String parent){
		if(clust.id<0){
			if(parent==null){
				parent="汽车交通,交通出行";
				System.out.print("");
			}else{
				parent=parent+".g"+clust.id;
				System.out.printf("%s,",parent);
			}
		}else{
			parent=parent+"."+labels[clust.id].replace(".", "。");
			System.out.printf("%s,%d",parent,clust.id);
		}
		System.out.println();
		if(clust!=null&&clust.cls.length>0){
			for(BiCluster1 b : clust.cls){
				printRadialInfo(b, labels,columns,parent);
			}
		}
	}
	
	public static void main(String[] args){
		List<String> list = TxtUtils.readList(URLDecoder.decode(TestHcluster1.class.getResource("/").getPath())+"/pub/j2ee/ct/chapter3/汽车交通,交通出行.txt");
		String[] columns = null;
		String[] rownames = new String[list.size()-1];
		double[][] data = new double[list.size()-1][];
		for(int i=0,j=list.size();i<j;i++){
			String s = list.get(i);
			String[] temp = s.split("\t");
			if(i==0){
				columns = Arrays.copyOfRange(temp,1,temp.length);
			}else{
				rownames[i-1] = temp[0];
				double[] rdata = new double[temp.length-1];
				for(int m=1,n=temp.length;m<n;m++)
					rdata[m-1]=Integer.valueOf(temp[m]);
				data[i-1] = rdata;
			}
		}
		//System.out.println(Arrays.toString(columns));
		//System.out.println(Arrays.toString(rownames));
		//System.out.println(JSONObject.toJSONString(hcluster(data)));
		//printclust(hcluster(data),rownames,columns,0);
		long start = System.currentTimeMillis();
		//printRadialInfo(hcluster(data),rownames,columns,null);
		printclust(hcluster(data),rownames,columns,1);
		System.out.println(System.currentTimeMillis() - start);
	}
}
