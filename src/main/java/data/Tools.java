package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Tools {
	
	public static void main(String[] args){
		Tools t = new Tools();
	//	String res=t.parseDate("Fri,May 9", "2012-13");
	//	System.out.println(res);
		t.transferScore();
	}
	
	public void transferDetail(){
		Tools t = new Tools();
		String driver = "com.mysql.jdbc.Driver";
		//URLָ��Ҫ���ʵ���ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ���������
			Class.forName(driver);
			// ������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			Statement statement1 = conn.createStatement();
			// Ҫִ�е�SQL���
			String sql = "SELECT * FROM `match_detail`";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String date="";
				String season="";
				date = new String(rs.getString(17).getBytes("ISO-8859-1"),"utf-8");
				season = new String(rs.getString(18).getBytes("ISO-8859-1"),"utf-8");
				date = t.parseDate(date, season);
				String sql2 = "replace into detail values ('"+rs.getString(1).replaceAll("'", "''")+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+
				rs.getString(6)+"','"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+
				rs.getString(14)+"','"+rs.getString(15)+"','"+rs.getString(16)+"','"+date+"','"+rs.getString(18)+"','"+rs.getString(19)+"','"+rs.getString(20)+"')";
				System.out.println(sql2);
				statement1.executeUpdate(sql2);
			}
			rs.close();
			conn.close();
		
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
	}
	public void transferScore(){
		Tools t = new Tools();
		String driver = "com.mysql.jdbc.Driver";
		//URLָ��Ҫ���ʵ���ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ���������
			Class.forName(driver);
			// ������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			Statement statement1 = conn.createStatement();
			// Ҫִ�е�SQL���
			String sql = "SELECT * FROM score";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String date="";
				String season="";
				date = new String(rs.getString(19).getBytes("ISO-8859-1"),"utf-8");
				season = new String(rs.getString(20).getBytes("ISO-8859-1"),"utf-8");
				date = t.parseDate(date, season);
				String sql2 = "replace into `match` values ('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+
				rs.getString(6)+"','"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+
				rs.getString(14)+"','"+rs.getString(15)+"','"+rs.getString(16)+"','"+rs.getString(17)+"','"+rs.getString(18)+"','"+date+"','"+rs.getString(20)+"','"+rs.getString(21)+"')";
				System.out.println(sql2);
				statement1.executeUpdate(sql2);
			}
			rs.close();
			conn.close();
		
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
	}
	public String parseDate(String date,String season){
		String result = "";
		String[] temp1 = date.split(",");
		String[] dt = temp1[1].trim().split("\\s+");
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
						// ���
						System.out.println(temp[1]);  
						return temp[1];
					} 
}
