package data;

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
	
	public void modifyDataBase(){
		String driver = "com.mysql.jdbc.Driver";
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
				String sql = "select team from playerTechMPO";
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) { 
					String str = rs.getString(1);
					int index = str.indexOf("20");
					String season = str.substring(index, str.length());
					String team = str.substring(0, index-1);
					String sql2 = "Update playerTechMPO set season='"+season+"' where team = '"+str+"'";
					Statement state = conn.createStatement();
					state.executeUpdate(sql2);
					String sql3 = "Update playerTechMPO set newTeam='"+team+"' where team = '"+str+"'";
					state.executeUpdate(sql3);
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
	}
}
