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
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";

		//URL指向要访问的数据库名nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "cyanham";
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>();
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
				//连接至数据库
				//驱动程序名
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
		//连接至数据库
		//驱动程序名
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
				// 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
				// 然后使用字符集解码指定的字节数组
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
			System.out.println("根据team查找player");
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
		//连接至数据库
		//驱动程序名
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
				// 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
				// 然后使用字符集解码指定的字节数组
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
			System.out.println("根据letter查找player");
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
