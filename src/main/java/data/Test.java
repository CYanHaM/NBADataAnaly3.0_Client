package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {
	public ArrayList<String>  fake(String team) {
		//����������
			String driver = "com.mysql.jdbc.Driver";
			ArrayList<String> host = new ArrayList<String>();
			//URLָ��Ҫ���ʵ����ݿ���nba
			String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
			// MySQL����ʱ���û���
			String user = "root";
			// Java����MySQL����ʱ������
			String password = "cyanham";
			try {
				// ������������
				Class.forName(driver);
				// �������ݿ�
				Connection conn = DriverManager.getConnection(url, user, password);
				if(!conn.isClosed()){
					System.out.println("Succeeded connecting to the Database!");
				}
				// statement����ִ��SQL���
				Statement statement = (Statement) conn.createStatement();
				
				// Ҫִ�е�SQL���
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
