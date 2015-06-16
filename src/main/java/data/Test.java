package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {
	public ArrayList<String>  fake(String team) {
		//驱动程序名
			String driver = "com.mysql.jdbc.Driver";
			ArrayList<String> host = new ArrayList<String>();
			//URL指向要访问的数据库名nba
			String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
			// MySQL配置时的用户名
			String user = "root";
			// Java连接MySQL配置时的密码
			String password = "cyanham";
			try {
				// 加载驱动程序
				Class.forName(driver);
				// 连续数据库
				Connection conn = DriverManager.getConnection(url, user, password);
				if(!conn.isClosed()){
					System.out.println("Succeeded connecting to the Database!");
				}
				// statement用来执行SQL语句
				Statement statement = (Statement) conn.createStatement();
				
				// 要执行的SQL语句
					String sql = "select date from`match` where guest ='"+team+"'";
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) { 
						host.add(rs.getString("date"));
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
	       }
			return host;
	}
	

}
