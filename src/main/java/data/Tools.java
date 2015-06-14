package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Tools {

	public static void main(String[] args){
		Tools t = new Tools();
	}
	public String parseDate(String date,String season){
		String result = "";
		String[] temp1 = date.split(",");
		String[] dt = temp1[1].split("\\s+");
		int year = 0;
		String month = "";
		String[] temp2 = season.split("-");
		int year1 = Integer.parseInt(temp2[0]);
		int year2 = year1+1;
		switch(dt[0]){
			case "Jan":
					year = year2;
					month = "01";
				break;
			case "Feb":
				year = year2;
				month = "02";
				break;
			case "Mar":
				year = year2;
				month = "03";
				break;
			case "Apr":
				year = year2;
				month = "04";
				break;
			case "May":
				year = year2;
				month = "05";
				break;
			case "Jun":
				year = year2;
				month = "06";
				break;
			case "Sep":
				year = year1;
				month = "09";
				break;
			case "Oct":
				year = year1;
				month = "10";
				break;
			case "Nov":
				year = year1;
				month = "11";
				break;
			case "Dec":
				year = year1;
				month = "12";
				break;
			default:
				System.out.println("wrong date");
		}
		String day = dt[1];
		if(dt[1].length()==1){
			day = "0"+day;
		}
		result = year+month+day;
		return result;
	}
	
	public String getPos(String pos){
						String[] temp = pos.split(",");
						// Êä³ö
						System.out.println(temp[1]);  
						return temp[1];
					} 
}
