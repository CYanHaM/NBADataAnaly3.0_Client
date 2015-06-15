package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import dataservice.StatsDataService;

public class StatsData implements StatsDataService{

	public static void main(String args[]){
		StatsData sd=new StatsData();
		sd.calculateNormalDistribution(3.41);
	}
	public double calculateTDistribution(double n,double a){
		double res=0;
		try {
			File file = new File("t_d");
			ArrayList<String>info=new ArrayList<String>();					
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = bufferedReader.readLine())!=null) {
				info.add(line);
				System.out.println(line);
				
			}
			if(n>0&&n<=30){
				String[] str=info.get((int)n).split(" ");
				switch(Double.toString(a)){
				case "0.4":res=Double.valueOf(str[0]);
				break;
				case "0.3":res=Double.valueOf(str[1]);
				break;
				case "0.2":res=Double.valueOf(str[2]);
				break;
				case "0.1":res=Double.valueOf(str[3]);
				break;
				case "0.05":res=Double.valueOf(str[4]);
				break;
				case "0.025":res=Double.valueOf(str[5]);
				break;
				case "0.01":res=Double.valueOf(str[6]);
				break;
				case "0.005":res=Double.valueOf(str[7]);
				break;
				case "0.0005":res=Double.valueOf(str[8]);
				break;
				}
			}else if(n>30&&n<=120){
				String[] str=info.get((int)(29+(n-30)/10)).split(" ");
				switch(Double.toString(a)){
				case "0.4":res=Double.valueOf(str[0]);
				break;
				case "0.3":res=Double.valueOf(str[1]);
				break;
				case "0.2":res=Double.valueOf(str[2]);
				break;
				case "0.1":res=Double.valueOf(str[3]);
				break;
				case "0.05":res=Double.valueOf(str[4]);
				break;
				case "0.025":res=Double.valueOf(str[5]);
				break;
				case "0.01":res=Double.valueOf(str[6]);
				break;
				case "0.005":res=Double.valueOf(str[7]);
				break;
				case "0.0005":res=Double.valueOf(str[8]); 
				break;
				}
				
			}
			
		}catch(Exception e) {
			System.out.println("操作出错");
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
		
	}
	
	public double calculateNormalDistribution(double n){
		double res=0;
		try {
			File file = new File("normal_d");
			ArrayList<String>info=new ArrayList<String>();					
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = bufferedReader.readLine())!=null) {
				info.add(line);
				System.out.println(line);
				
			}
			int row=(int)(n/0.1);
			int column=(int)((n-row*0.1)/0.01);
//			System.out.println(row+"-_____-___-----"+(n-row*0.1)/0.01);
			if(row>=0&&row<=27){
				String[] str=info.get(row+1).split(" ");
				res=Double.valueOf(str[column]);
			}else if(row>=28&&row<=49){
				String[] str=info.get(row+1).split("  ");
				res=Double.valueOf(str[column+1].substring(0, 4))*Math.pow(0.1, Integer.valueOf(str[column+1].substring(6, 8)));		
			}
			
		}catch(Exception e) {
			System.out.println("操作出错");
			e.printStackTrace();
		}
		System.out.println(res);
		return 1-res;
		
	}
}
