package data.info;

import PO.TeamPO;
import dataservice.TeamDataService;

public class TeamInfo implements TeamDataService {

	@Override
	public TeamPO find(String team) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String name = tpo.abbreviation;
				//���������ݿ�
				//����������
				String driver = "com.mysql.jdbc.Driver";
				//URLָ��Ҫ���ʵ����ݿ���nba
				String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
				// MySQL����ʱ���û���
				String user = "root";
				// Java����MySQL����ʱ������
				String password = "";
				try {
					// ������������
					Class.forName(driver);
					// �������ݿ�
					Connection conn = DriverManager.getConnection(url, user, password);
					if(!conn.isClosed()){
						System.out.println("Succeeded connecting to the Database!");
					}
					// statement����ִ��SQL���
					Statement statement = conn.createStatement();
					// Ҫִ�е�SQL���
					String sql = "SELECT * FROM t_team WHERE abbreviation = '"+name+"'";
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) {
						tpo.fullName = new String(rs.getString(1).getBytes("ISO-8859-1"),"utf-8");
						tpo.location = new String(rs.getString(3).getBytes("ISO-8859-1"),"utf-8");
						tpo.division = new String(rs.getString(4).getBytes("ISO-8859-1"),"utf-8");
						tpo.partition = new String(rs.getString(5).getBytes("ISO-8859-1"),"utf-8");
						tpo.homeCourt = new String(rs.getString(6).getBytes("ISO-8859-1"),"utf-8");
						tpo.time = new String(rs.getString(7).getBytes("ISO-8859-1"),"utf-8");
					}
					System.out.println("find team");
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
				return tpo;
	}

	@Override
	public void WriteIn() {
		// TODO Auto-generated method stub
		
	}

}
