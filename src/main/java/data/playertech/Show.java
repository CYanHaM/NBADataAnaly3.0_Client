package data.playertech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.PlayerTechPO;
import data.Tools;
import dataservice.playertechdataservice.ShowDataService;

public class Show implements ShowDataService{

	@Override
	public ArrayList<PlayerTechPO> showSeasonPlayerData(String season) {
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
			// 要执行的SQL语句
			String sql = "SELECT * FROM detail where season='"+season+"'";
			ResultSet rs = statement.executeQuery(sql);
			Tools tool = new Tools();
			ArrayList<PlayerTechPO> res= tool.getPlayerTech(rs);
			rs.close();
			conn.close();
		return res;
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
		System.out.println("wrong: show playerTech");
		return null;
	}

	@Override
	public PlayerTechPO showKeyData(String name, String team) {
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
			// 要执行的SQL语句
			String sql = "SELECT * FROM detail where name='"+name+"' and team='"+team+"'";
			ResultSet rs = statement.executeQuery(sql);
			Tools tool = new Tools();
			ArrayList<PlayerTechPO> res= tool.getPlayerTech(rs);
			rs.close();
			conn.close();
			if(res==null||res.size()==0){
				return null;
			}
		return res.get(0);
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
		System.out.println("wrong: show playerTech");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> ascend(final String type) {
		// TODO Auto-generated method stub
		ArrayList<PlayerTechPO> list = showSeasonPlayerData("2014-15");		
		return list;
	}

	@Override
	public ArrayList<PlayerTechPO> descend(String type) {
		// TODO Auto-generated method stub
		ArrayList<PlayerTechPO> list = showSeasonPlayerData("2014-15");		
		return list;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		showSeasonPlayerData("2014-15");
	}

}
