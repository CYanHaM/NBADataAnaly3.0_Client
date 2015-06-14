package data.playertech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import bussinesslogic.playerinfobl.PlayerInfo;
import bussinesslogic.playertechbl.ShowPlayerTech;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.PlayerTechVO;
import VO.PlayerVO;
import VO.ScreeningConditionVO;
import data.Tools;
import dataservice.playertechdataservice.FindDataService;

public class Find implements FindDataService {
	Tools tool = new Tools();
	@Override
	public ArrayList<PlayerTechMPO> findHotPlayerToday(String date,
			String keyword) {
		ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
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
			String sql = "SELECT * FROM detail where date='"+date+"' order by "+keyword+" desc";
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
		System.out.println("wrong: today hot player");
		return null;
	}
	
	@Override
	public ArrayList<PlayerTechPO> findSeasonHotPlayer(String keyword,
			String season) {
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
					String sql = "";;
					switch(keyword){
					case "PTS":
						sql ="SELECT  FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
						break;
					case "BLKPG":
						sql ="SELECT * FROM `player_blocks` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
						break;
					case "STPG":
					sql ="SELECT * FROM `player_steals` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
						break;
					case "RPG":
						sql ="SELECT * FROM `player_rebounds` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
						break;
					case "APG":
						sql ="SELECT * FROM `player_assists` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
					break;
					case "3P%":
						sql ="SELECT * FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
					break;
					case "FG%":
						sql ="SELECT * FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
					break;
					case "FT%":
						sql ="SELECT * FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
					break;
					case "doubles":
						sql ="SELECT * FROM `player_doubles` where season='"+temp[0]+"' and type='"+temp[1]+"' order by DBLDBL desc";
						break;
						//====================================================================
					case "efficiency":
						sql ="SELECT  FROM `player_scoring` where season='"+temp[0]+"' and type='"+temp[1]+"' order by '"+keyword+"' desc";
						break;
						//=====================================================================
					default:
						System.out.println("wrong keyword");	
					}
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) {
						PlayerTechPO po = new PlayerTechPO();
						String name = rs.getString("name");
						String team = rs.getString("team");
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
							po.efficiency=Integer.valueOf(rs2.getString("efficiency"));
							po.threeShotInRate = Double.valueOf(rs2.getString("3P%"));
							po.shotInRate=Double.valueOf(rs2.getString("FG%"));
							po.penaltyShotInRate=Double.valueOf(rs2.getString("FT%"));
							//scoreratio
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
							po.gameNum=Integer.valueOf(rs5.getString("GP"));
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
		ShowPlayerTech sh = new ShowPlayerTech();
		ArrayList<PlayerTechVO> all = sh.showSeasonPlayerData();
		PlayerInfo pi = new PlayerInfo();
		ArrayList<PlayerVO> info = pi.showAllPlayerInfo();
		Iterator<PlayerTechVO> it = all.iterator();
		while(it.hasNext()){
			PlayerTechVO pt = it.next();
			String position = "";
			for(int i=0;i<info.size();i++){
				if(info.get(i).name.equals(pt.name))
					position = info.get(i).position;
			}
			if((position==null)||(!position.equals(list.get(0).position))){
				it.remove();
				continue;
			}
			
			if(!pt.division.equals(list.get(0).division)){
				System.out.println(pt.division+" "+list.get(0).division);
				it.remove(); 
			}
		}
		Iterator<PlayerTechVO> it2 = all.iterator();
		while(it2.hasNext()){
			PlayerTechVO pt = it2.next();
			for(int j=0;j<list.size();j++){
				ScreeningConditionVO vo = list.get(j); 
				if(vo.condition.equals("score")){
					if(vo.comparison.equals(">=")){
						if(pt.score<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.score>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("blockshot")){
					if(vo.comparison.equals(">=")){
						if(pt.blockShot<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.blockShot>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("steal")){
					if(vo.comparison.equals(">=")){
						if(pt.steal<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.steal>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("secondaryattack")){
					if(vo.comparison.equals(">=")){
						if(pt.secondaryAttack<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.secondaryAttack>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("rebound")){
					if(vo.comparison.equals(">=")){
						if(pt.rebound<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.rebound>vo.number){
							it2.remove();
							break;
						}
					}
				}else{
					System.out.println("wrong condition");
				}
			}
		}
		
		return all;

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
					String sql = "select * from detail";
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) {
						if(rs.getString("name").charAt(0)==letter){
							list=tool.getPlayerTech(rs);
						}
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
	public ArrayList<PlayerTechPO> findFastImprovingPlayer(final String keyword) {
		// TODO Auto-generated method stub
		ShowPlayerTech sh = new ShowPlayerTech();
		ArrayList<PlayerTechPO> list = sh.showSeasonPlayerData();
		ArrayList<PlayerTechPO> res = new ArrayList<PlayerTechPO>();
		//降序排列
		Comparator<PlayerTechPO> comparator = new Comparator<PlayerTechPO>(){  
			public int compare(PlayerTechPO p1, PlayerTechPO p2) {   
				//重写比较方法
				switch(keyword){
				case "score":
					return  (p2.scoreImproving>=p1.scoreImproving)?1:-1;
				case "blockshot":
					return p2.blockShotImproving>=p1.blockShotImproving?1:-1;
				case "rebound":
					return p2.reboundImproving>=p1.reboundImproving?1:-1;
				case "secondaryAttack":
					return p2.secondaryAttackImproving>=p1.secondaryAttackImproving?1:-1;
				case "steal":
					return p2.stealImproving>=p1.stealImproving?1:-1;
				default:
					System.out.println("wrong type");
					return 0;
				}
			}  
		}; 
		Collections.sort(list, comparator);
		for(int i=0;i<5;i++){
			res.add(list.get(i));
		}
		return res;
	}

	@Override
	public PlayerTechPO findPlayerTechByName(String name) {
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
			String sql = "select * from detail where name='"+name+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("name").equals(name)){
					list=tool.getPlayerTech(rs);
				}
			}
			rs.close();
			conn.close();
			if(list==null){
				return null;
			}
		return list.get(0);
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

}
