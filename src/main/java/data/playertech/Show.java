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
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
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
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
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
