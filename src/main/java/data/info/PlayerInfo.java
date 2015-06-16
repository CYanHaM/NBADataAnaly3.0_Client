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

	public static void main(String[] args){
		PlayerInfo pi = new PlayerInfo();
		ArrayList<PlayerPO> all = pi.findAll(0);
		System.out.println(all.size());
	}
	Tools tool = new Tools();
	@Override
	public ArrayList<PlayerPO> findAll(int active) {
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
						String sql = "SELECT * FROM playerInfo ";
						if(active==1){
							sql = sql+"where season = '2014-15'";
						}
						if(active==2){
							sql = sql+"where season <> '2014-15'";
						}
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							PlayerPO po = new PlayerPO();
							po.name = rs.getString("name");
							if(rs.getString("No").equals("")){
								po.uniformNum=2;
							}else{
								po.uniformNum = Integer.valueOf(rs.getString("No"));
							}
							po.position = new String(rs.getString("Pos").getBytes("ISO-8859-1"),"utf-8");
							po.height = new String(rs.getString("Height").getBytes("ISO-8859-1"),"utf-8");
							po.weight = Double.parseDouble(rs.getString("Weight"));
							po.birth = new String(rs.getString("BirthDate").getBytes("ISO-8859-1"),"utf-8");
							if(rs.getString("Age").equals("")){
								po.age=26;
							}else{
								po.age = Integer.parseInt(rs.getString("Age"));
							}
							if(rs.getString("Exp").equals("R")){
								po.exp=2;
							}
							else{
								po.exp = Integer.parseInt(rs.getString("Exp"));
							}
							po.school = new String(rs.getString("From").getBytes("ISO-8859-1"),"utf-8");
							po.team = new String(rs.getString("team").getBytes("ISO-8859-1"),"utf-8");
							po.season = new String(rs.getString("season").getBytes("ISO-8859-1"),"utf-8");
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
	public PlayerPO findOne(String name,int active) {
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
					String sql = "SELECT * FROM playerInfo where name ='"+name+"'";
					if(active==1){
						sql = sql+" and season = '2014-15'";
					}
					if(active==2){
						sql = sql+" and season <> '2014-15'";
					}
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) { 
						PlayerPO po = new PlayerPO();
						po.name = rs.getString(2);
						po.uniformNum = Integer.valueOf(rs.getString(3));
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
	public ArrayList<PlayerPO> findByTeam(String team,int active) {
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
			String sql = "SELECT * FROM playerInfo where team = '"+team+"'";
			if(active==1){
				sql = sql+" and season = '2014-15'";
			}
			if(active==2){
				sql = sql+" and season <> '2014-15'";
			}
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
	public ArrayList<PlayerPO> findByLetter(char letter,int active) {
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
			if(active==1){
				sql = sql+" where season = '2014-15'";
			}
			if(active==2){
				sql = sql+" where season <> '2014-15'";
			}
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
