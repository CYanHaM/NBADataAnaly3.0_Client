package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import dataservice.StatsDataService;

public class StatsData implements StatsDataService{

//	public static void main(String args[]){
//		StatsData sd=new StatsData();
//		sd.calculateTDistribution(24, 0.005);
//	}
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
				case "0.3":res=Double.valueOf(str[1]);
				case "0.2":res=Double.valueOf(str[2]);
				case "0.1":res=Double.valueOf(str[3]);
				case "0.05":res=Double.valueOf(str[4]);
				case "0.025":res=Double.valueOf(str[5]);
				case "0.01":res=Double.valueOf(str[6]);
				case "0.005":res=Double.valueOf(str[7]);
				case "0.0005":res=Double.valueOf(str[8]);
				}
			}else if(n>30&&n<=120){
				String[] str=info.get((int)(29+(n-30)/10)).split(" ");
				switch(Double.toString(a)){
				case "0.4":res=Double.valueOf(str[0]);
				case "0.3":res=Double.valueOf(str[1]);
				case "0.2":res=Double.valueOf(str[2]);
				case "0.1":res=Double.valueOf(str[3]);
				case "0.05":res=Double.valueOf(str[4]);
				case "0.025":res=Double.valueOf(str[5]);
				case "0.01":res=Double.valueOf(str[6]);
				case "0.005":res=Double.valueOf(str[7]);
				case "0.0005":res=Double.valueOf(str[8]); 
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
			File file = new File("n_d");
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
				case "0.3":res=Double.valueOf(str[1]);
				case "0.2":res=Double.valueOf(str[2]);
				case "0.1":res=Double.valueOf(str[3]);
				case "0.05":res=Double.valueOf(str[4]);
				case "0.025":res=Double.valueOf(str[5]);
				case "0.01":res=Double.valueOf(str[6]);
				case "0.005":res=Double.valueOf(str[7]);
				case "0.0005":res=Double.valueOf(str[8]);
				}
			}else if(n>30&&n<=120){
				String[] str=info.get((int)(29+(n-30)/10)).split(" ");
				switch(Double.toString(a)){
				case "0.4":res=Double.valueOf(str[0]);
				case "0.3":res=Double.valueOf(str[1]);
				case "0.2":res=Double.valueOf(str[2]);
				case "0.1":res=Double.valueOf(str[3]);
				case "0.05":res=Double.valueOf(str[4]);
				case "0.025":res=Double.valueOf(str[5]);
				case "0.01":res=Double.valueOf(str[6]);
				case "0.005":res=Double.valueOf(str[7]);
				case "0.0005":res=Double.valueOf(str[8]); 
				}
				
			}
			
		}catch(Exception e) {
			System.out.println("操作出错");
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
		
	}
}
