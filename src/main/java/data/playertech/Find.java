package data.playertech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.ScreeningConditionVO;
import data.Tools;
import dataservice.playertechdataservice.FindDataService;

public class Find implements FindDataService {
	Tools tool = new Tools();
	@Override
	public ArrayList<PlayerTechMPO> findHotPlayerToday(String date,
			String keyword) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
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
			Statement statement = conn.createStatement();
			Statement statement1 = conn.createStatement();
			// 要执行的SQL语句
			String sql = "SELECT * FROM match_detail";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String date="";
				String season="";
				date = new String(rs.getString(17).getBytes("ISO-8859-1"),"utf-8");
				season = new String(rs.getString(18).getBytes("ISO-8859-1"),"utf-8");
				System.out.println(date);
				System.out.println(season);
				date = t.parseDate(date, season);
				String sql2 = "insert into detail values ('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+
				rs.getString(6)+"','"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+
				rs.getString(14)+"','"+rs.getString(15)+"','"+rs.getString(16)+"','"+date+"','"+rs.getString(18)+"','"+rs.getString(19)+"','"+rs.getString(20)+"')";
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
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findSeasonHotPlayer(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> sift(ArrayList<ScreeningConditionVO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findPlayerByletter(char letter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findPlayerByLetter(char letter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findFastImprovingPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
