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
		ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
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
			String sql = "SELECT * FROM detail where date="+date+" and season=2014-15"+"order by "+keyword+" desc";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				PlayerTechMPO mpo = new PlayerTechMPO();
				mpo.name=new String(rs.getString(1).getBytes("ISO-8859-1"),"utf-8");
				mpo.team=new String(rs.getString(19).getBytes("ISO-8859-1"),"utf-8");
				mpo.date=new String(rs.getString(17).getBytes("ISO-8859-1"),"utf-8");
				mpo.position=tool.getPos(new String(rs.getString(2).getBytes("ISO-8859-1"),"utf-8"));
				mpo.rebound=Integer.valueOf(new String(rs.getString(9).getBytes("ISO-8859-1"),"utf-8"));
				mpo.secondaryAttack=Integer.valueOf(new String(rs.getString(10).getBytes("ISO-8859-1"),"utf-8"));
				mpo.steal=Integer.valueOf(new String(rs.getString(11).getBytes("ISO-8859-1"),"utf-8"));
				mpo.score=Integer.valueOf(new String(rs.getString(16).getBytes("ISO-8859-1"),"utf-8"));
				mpo.blockShot=Integer.valueOf(new String(rs.getString(12).getBytes("ISO-8859-1"),"utf-8"));				
				mpo.ifDouble = Integer.valueOf(new String(rs.getString("doubles").getBytes("ISO-8859-1"),"utf-8"));		
				list.add(mpo);
			}
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
		};
		System.out.println("wrong:findHotPlayerToday");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findSeasonHotPlayer(String keyword,String season) {
		// TODO Auto-generated method stub
		String[] temp=season.trim().split("\\s+");
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
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
			String sql = "";
			String name ="";
			String team = "";
			switch(keyword){
			case "PTS":
				sql ="SELECT  FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "BLK":
				sql ="SELECT * FROM `player_blocks` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "STL":
				sql ="SELECT * FROM `player_steals` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "REB":
				sql ="SELECT * FROM `player_rebounds` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "AST":
				sql ="SELECT * FROM `player_assists` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			/*case "":
				break;
			case "":
				break;
			case "":
				break;
				*/
			default:
				System.out.println("wrong keyword");	
			}
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				PlayerTechPO po = new PlayerTechPO();
				name = rs.getString("name");
				team = rs.getString("team");
				//---------------------------------------------------
				Statement statement1 = conn.createStatement();
				String sql1 ="SELECT * FROM `player_assists` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs1 = statement1.executeQuery(sql1);
				while(rs1.next()){
					po.secondaryAttack = Integer.valueOf(rs1.getString("AST"));
				}
				//-----------------------------------------------------
				Statement statement2 = conn.createStatement();
				String sql2 ="SELECT * FROM `player_scoring` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs2 = statement2.executeQuery(sql2);
				while(rs2.next()){
					po.score = Integer.valueOf(rs2.getString("PTS"));
				}
				//--------------------------------------------------------
				Statement statement3 = conn.createStatement();
				String sql3 ="SELECT * FROM `player_steals` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs3 = statement3.executeQuery(sql3);
				while(rs3.next()){
					po.steal = Integer.valueOf(rs3.getString("STL"));
				}
				//----------------------------------------------------------
				Statement statement4 = conn.createStatement();
				String sql4 ="SELECT * FROM `player_blocks` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs4 = statement4.executeQuery(sql4);
				while(rs4.next()){
					po.blockShot = Integer.valueOf(rs4.getString("BLK"));
				}
				//------------------------------------------------------------------
				Statement statement5 = conn.createStatement();
				String sql5 ="SELECT * FROM `player_rebounds` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs5 = statement5.executeQuery(sql5);
				while(rs5.next()){
					po.rebound = Integer.valueOf(rs5.getString("REB"));
				}
				po.name=name;
				po.team=team;
				po.season=season;
				list.add(po);
			}
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
		};
		System.out.println("wrong:SeasonHotPlayerToday");
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
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
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
			String sql = "";
			String name ="";
			String team = "";
			switch(keyword){
			case "PTS":
				sql ="SELECT  FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "BLK":
				sql ="SELECT * FROM `player_blocks` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "STL":
				sql ="SELECT * FROM `player_steals` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "REB":
				sql ="SELECT * FROM `player_rebounds` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			case "AST":
				sql ="SELECT * FROM `player_assists` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
				break;
			/*case "":
				break;
			case "":
				break;
			case "":
				break;
				*/
			default:
				System.out.println("wrong keyword");	
			}
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				PlayerTechPO po = new PlayerTechPO();
				name = rs.getString("name");
				team = rs.getString("team");
				//---------------------------------------------------
				Statement statement1 = conn.createStatement();
				String sql1 ="SELECT * FROM `player_assists` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs1 = statement1.executeQuery(sql1);
				while(rs1.next()){
					po.secondaryAttack = Integer.valueOf(rs1.getString("AST"));
				}
				//-----------------------------------------------------
				Statement statement2 = conn.createStatement();
				String sql2 ="SELECT * FROM `player_scoring` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs2 = statement2.executeQuery(sql2);
				while(rs2.next()){
					po.score = Integer.valueOf(rs2.getString("PTS"));
				}
				//--------------------------------------------------------
				Statement statement3 = conn.createStatement();
				String sql3 ="SELECT * FROM `player_steals` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs3 = statement3.executeQuery(sql3);
				while(rs3.next()){
					po.steal = Integer.valueOf(rs3.getString("STL"));
				}
				//----------------------------------------------------------
				Statement statement4 = conn.createStatement();
				String sql4 ="SELECT * FROM `player_blocks` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs4 = statement4.executeQuery(sql4);
				while(rs4.next()){
					po.blockShot = Integer.valueOf(rs4.getString("BLK"));
				}
				//------------------------------------------------------------------
				Statement statement5 = conn.createStatement();
				String sql5 ="SELECT * FROM `player_rebounds` where name='"+name+"' team='"+team+"' season='"+temp[0]+"' and type='"+temp[1]+"'";
				ResultSet rs5 = statement5.executeQuery(sql5);
				while(rs5.next()){
					po.rebound = Integer.valueOf(rs5.getString("REB"));
				}
				po.name=name;
				po.team=team;
				po.season=season;
				list.add(po);
			}
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
		};
		System.out.println("wrong:SeasonHotPlayerToday");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findFastImprovingPlayer(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO findPlayerTechByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
