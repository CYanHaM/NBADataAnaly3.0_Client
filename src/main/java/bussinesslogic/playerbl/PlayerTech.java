package bussinesslogic.playerbl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamTechPO;

public class PlayerTech implements StatsInfo{

	@Override
	public TeamTechPO getTeamTech(String teamname, String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MatchPO> getRecentMatch(String team,String season) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<TeamTechPO> getMatchForYear(String team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,
			String season) {
		// TODO Auto-generated method stub
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
						String sql = "SELECT * FROM `playerTechPO` where name = '"+player+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							PlayerTechPO po = new PlayerTechPO();
							po.name=rs.getString("name");
							po.season=rs.getString("season");
							po.team=rs.getString("team");
							po.ifRegular=Integer.valueOf(rs.getString("ifRegular"));
							po.position=rs.getString("pos");
							po.division=rs.getString("division");
							po.gameNum=Integer.valueOf(rs.getString("gameNum"));
							po.startingNum=Integer.valueOf(rs.getString("startingNum"));
							po.rebound=Integer.valueOf(rs.getString("rebound"));
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"));
							po.time=Integer.valueOf(rs.getString("time"));
							po.offensiveNum=Integer.valueOf(rs.getString("offensiveNum"));
							po.defensiveNum=Integer.valueOf(rs.getString("defensiveNum"));
							po.steal=Integer.valueOf(rs.getString("steal"));
							po.blockShot=Integer.valueOf(rs.getString("blockShot"));
							po.fault=Integer.valueOf(rs.getString("fault"));
							po.foul=Integer.valueOf(rs.getString("foul"));
							po.score=Integer.valueOf(rs.getString("score"));
							po.shotIn=Integer.valueOf(rs.getString("shotIn"));
							po.shot=Integer.valueOf(rs.getString("shot"));
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"));
							po.threeShot=Integer.valueOf(rs.getString("threeShot"));
							po.penaltyShotIn=Integer.valueOf(rs.getString("penaltyShotIn"));
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"));
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=Double.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=Double.valueOf(rs.getString("penaltyShotInRate"));
							po.GmScEfficiency=Double.valueOf(rs.getString("GmSc"));
							po.trueShotInRate=Double.valueOf(rs.getString("trueShotInRate"));
							po.shootingEfficiency=Double.valueOf(rs.getString("shootingEfficiency"));
							po.reboundRate=Double.valueOf(rs.getString("reboundRate"));
							po.offensiveReboundRate=Double.valueOf(rs.getString("offReboundRate"));
							po.defensiveReboundRate=Double.valueOf(rs.getString("defReboundRate"));
							po.secondaryAttackRate=Double.valueOf(rs.getString("assistRate"));
							po.faultRate=Double.valueOf(rs.getString("faultRate"));
							po.usageRate=Double.valueOf(rs.getString("usageRate"));
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
		System.out.println("something wrong in getPlayerTech");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> getPlayerForYear(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getPlayerTech(String player, String season, int ifRegular) {
		// TODO Auto-generated method stub
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
						String sql = "SELECT * FROM `playerTechPO` where name = '"+player+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							PlayerTechPO po = new PlayerTechPO();
							po.name=rs.getString("name");
							po.season=rs.getString("season");
							po.team=rs.getString("team");
							po.ifRegular=Integer.valueOf(rs.getString("ifRegular"));
							po.position=rs.getString("pos");
							po.division=rs.getString("division");
							po.gameNum=Integer.valueOf(rs.getString("gameNum"));
							po.startingNum=Integer.valueOf(rs.getString("startingNum"));
							po.rebound=Integer.valueOf(rs.getString("rebound"));
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"));
							po.time=Integer.valueOf(rs.getString("time"));
							po.offensiveNum=Integer.valueOf(rs.getString("offensiveNum"));
							po.defensiveNum=Integer.valueOf(rs.getString("defensiveNum"));
							po.steal=Integer.valueOf(rs.getString("steal"));
							po.blockShot=Integer.valueOf(rs.getString("blockShot"));
							po.fault=Integer.valueOf(rs.getString("fault"));
							po.foul=Integer.valueOf(rs.getString("foul"));
							po.score=Integer.valueOf(rs.getString("score"));
							po.shotIn=Integer.valueOf(rs.getString("shotIn"));
							po.shot=Integer.valueOf(rs.getString("shot"));
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"));
							po.threeShot=Integer.valueOf(rs.getString("threeShot"));
							po.penaltyShotIn=Integer.valueOf(rs.getString("penaltyShotIn"));
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"));
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=Double.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=Double.valueOf(rs.getString("penaltyShotInRate"));
							po.GmScEfficiency=Double.valueOf(rs.getString("GmSc"));
							po.trueShotInRate=Double.valueOf(rs.getString("trueShotInRate"));
							po.shootingEfficiency=Double.valueOf(rs.getString("shootingEfficiency"));
							po.reboundRate=Double.valueOf(rs.getString("reboundRate"));
							po.offensiveReboundRate=Double.valueOf(rs.getString("offReboundRate"));
							po.defensiveReboundRate=Double.valueOf(rs.getString("defReboundRate"));
							po.secondaryAttackRate=Double.valueOf(rs.getString("assistRate"));
							po.faultRate=Double.valueOf(rs.getString("faultRate"));
							po.usageRate=Double.valueOf(rs.getString("usageRate"));
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
		System.out.println("something wrong in getPlayerTech");
		return null;
	}

	@Override
	public TeamTechPO getTeamRank(String teamname, String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamTechPO getAllTeamAverage(String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getPlayerRank(String player, String season,
			int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getAllPlayerAverage(String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamTechPO getDivTeamAverage(String season, int ifRegular,
			String division) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getDivPlayerAverage(String season, int ifRegular,
			String division) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTeamSeasonList(String team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayerSeasonList(String player) {
		// TODO Auto-generated method stub
		return null;
	}

}
