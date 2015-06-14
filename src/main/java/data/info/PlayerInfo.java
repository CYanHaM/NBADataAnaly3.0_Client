package data.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.PlayerPO;
import data.Tools;
import dataservice.playerinfodataservice.PlayerInfoDataService;

public class PlayerInfo implements PlayerInfoDataService {

	Tools tool = new Tools();
	@Override
	public ArrayList<PlayerPO> findAll() {
		//����������
		String driver = "com.mysql.jdbc.Driver";

		//URLָ��Ҫ���ʵ����ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>();
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
						String sql = "SELECT * FROM playerInfo";
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							PlayerPO po = new PlayerPO();
							po.name = rs.getString(2);
							po.uniformNum = Integer.valueOf(rs.getString(3));
							// ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
							// Ȼ��ʹ���ַ�������ָ�����ֽ�����
							po.position = new String(rs.getString(4).getBytes("ISO-8859-1"),"utf-8");
							po.height = new String(rs.getString(5).getBytes("ISO-8859-1"),"utf-8");
							po.weight = rs.getDouble(6);
							po.birth = new String(rs.getString(7).getBytes("ISO-8859-1"),"utf-8");
							po.age = rs.getInt(8);
							po.exp = rs.getInt(9);
							po.school = new String(rs.getString(10).getBytes("ISO-8859-1"),"utf-8");
							po.team = new String(rs.getString(11).getBytes("ISO-8859-1"),"utf-8");
							po.season = new String(rs.getString(12).getBytes("ISO-8859-1"),"utf-8");
							list.add(po);
						}
						System.out.println("find all player");
						rs.close();
						conn.close(); 
						return list;
       } catch(ClassNotFoundException e) {   
              System.out.println("Sorry,can`t find the Driver!");   
              e.printStackTrace();   
       } catch(SQLException e) {   
              e.printStackTrace();   
       } catch(Exception e) {   
              e.printStackTrace();   
       }
		System.out.println("something wrong in find all player");
		return null;
	}

	@Override
	public PlayerPO findOne(String name) {
		// TODO Auto-generated method stub
				//���������ݿ�
				//����������
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
					String sql = "SELECT * FROM playerInfo where name ="+name;
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) { 
						PlayerPO po = new PlayerPO();
						po.name = rs.getString(2);
						po.uniformNum = Integer.valueOf(rs.getString(3));
						// ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
						// Ȼ��ʹ���ַ�������ָ�����ֽ�����
						po.position = new String(rs.getString(4).getBytes("ISO-8859-1"),"utf-8");
						po.height = new String(rs.getString(5).getBytes("ISO-8859-1"),"utf-8");
						po.weight = rs.getDouble(6);
						po.birth = new String(rs.getString(7).getBytes("ISO-8859-1"),"utf-8");
						po.age = rs.getInt(8);
						po.exp = rs.getInt(9);
						po.school = new String(rs.getString(10).getBytes("ISO-8859-1"),"utf-8");
						po.team = new String(rs.getString(11).getBytes("ISO-8859-1"),"utf-8");
						po.season = new String(rs.getString(12).getBytes("ISO-8859-1"),"utf-8");
						return po;
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
				System.out.println("something wrong in playerinfo.findone");
				return null;
	}

	@Override
	public ArrayList<PlayerPO> findByTeam(String team) {
		// TODO Auto-generated method stub
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>();
		//���������ݿ�
		//����������
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
			String sql = "SELECT * FROM playerInfo where team = "+team;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {     
				PlayerPO po = new PlayerPO();
				po.name = rs.getString(2);
				po.uniformNum = Integer.valueOf(rs.getString(3));
				// ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
				// Ȼ��ʹ���ַ�������ָ�����ֽ�����
				po.position = new String(rs.getString(4).getBytes("ISO-8859-1"),"utf-8");
				po.height = new String(rs.getString(5).getBytes("ISO-8859-1"),"utf-8");
				po.weight = rs.getDouble(6);
				po.birth = new String(rs.getString(7).getBytes("ISO-8859-1"),"utf-8");
				po.age = rs.getInt(8);
				po.exp = rs.getInt(9);
				po.school = new String(rs.getString(10).getBytes("ISO-8859-1"),"utf-8");
				po.team = new String(rs.getString(11).getBytes("ISO-8859-1"),"utf-8");
				po.season = new String(rs.getString(12).getBytes("ISO-8859-1"),"utf-8");
				list.add(po);
			}
			System.out.println("����team����player");
			rs.close();
			conn.close();
			return list;
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("wrong:player.findByTeam");
		return null;
	}

	@Override
	public ArrayList<PlayerPO> findByLetter(char letter) {
		// TODO Auto-generated method stub
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>();
		//���������ݿ�
		//����������
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
			String sql = "SELECT * FROM playerInfo";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {     
				PlayerPO po = new PlayerPO();
				po.name = rs.getString(2);
				if(po.name.charAt(0)!=letter){
					break;
				}
				po.uniformNum = Integer.valueOf(rs.getString(3));
				// ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
				// Ȼ��ʹ���ַ�������ָ�����ֽ�����
				po.position = new String(rs.getString(4).getBytes("ISO-8859-1"),"utf-8");
				po.height = new String(rs.getString(5).getBytes("ISO-8859-1"),"utf-8");
				po.weight = rs.getDouble(6);
				po.birth = new String(rs.getString(7).getBytes("ISO-8859-1"),"utf-8");
				po.age = rs.getInt(8);
				po.exp = rs.getInt(9);
				po.school = new String(rs.getString(10).getBytes("ISO-8859-1"),"utf-8");
				po.team = new String(rs.getString(11).getBytes("ISO-8859-1"),"utf-8");
				po.season = new String(rs.getString(12).getBytes("ISO-8859-1"),"utf-8");
				list.add(po);
			}
			System.out.println("����letter����player");
			rs.close();
			conn.close();
			return list;
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("wrong:player.findByLetter");
		return null;
	}

}
