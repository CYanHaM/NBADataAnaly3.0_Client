package bussinesslogic.playerbl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamTechPO;

public class PlayerTech implements StatsInfo{

	@Override
	public TeamTechPO getTeamTech(String teamname, String season, int ifRegular) {
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
						String sql = "SELECT * FROM `teamtech` where name = '"+teamname+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							TeamTechPO po = new TeamTechPO();
							//===============================================biaoming
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
		System.out.println("something wrong in getTeamTech");
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
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "cyanham";
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
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
						String sql = "SELECT * FROM `teamTechPO` where name = '"+team+"' and ifRegular = '1'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							TeamTechPO po = new TeamTechPO();
							//=======================================================
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
       }
		System.out.println("something wrong in getPlayerForYear");
		return null;
	}

	@Override
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,
			String season) {
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "cyanham";
		ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
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
						String sql = "SELECT * FROM `playerTechMPO` where name = '"+player+"' and season = '"+season+"' order by date desc limit 0,20";				
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							PlayerTechMPO mpo = new PlayerTechMPO();
							mpo.name = rs.getString("name");
							mpo.team = rs.getString("team");
							mpo.season = rs.getString("season");
							mpo.division = rs.getString("division");
							mpo.date = rs.getString("date");
							mpo.position = rs.getString("position");
							mpo.time = Integer.valueOf(rs.getString("time"));
							mpo.shotIn =  Integer.valueOf(rs.getString("shotIn"));
							mpo.shot = Integer.valueOf(rs.getString("shot"));
							mpo.threeShotIn =  Integer.valueOf(rs.getString("threeShotIn"));
							mpo.threeShot = Integer.valueOf(rs.getString("threeShot"));
							mpo.penaltyShotIn = Integer.valueOf(rs.getString("penaltyShotIn"));
							mpo.penaltyShot = Integer.valueOf(rs.getString("penaltyShot"));
							mpo.offensiveRebound = Integer.valueOf(rs.getString("offensiveNum"));
							mpo.defensiveRebound = Integer.valueOf(rs.getString("defensiveNum"));
							mpo.rebound = Integer.valueOf(rs.getString("rebound"));
							mpo.secondaryAttack = Integer.valueOf(rs.getString("assist"));
							mpo.steal = Integer.valueOf(rs.getString("steal"));
							mpo.blockShot = Integer.valueOf(rs.getString("blockShot"));
							mpo.fault = Integer.valueOf(rs.getString("fault"));
							mpo.foul = Integer.valueOf(rs.getString("foul"));
							mpo.score =  Integer.valueOf(rs.getString("score"));
							mpo.ifFirstLineUp = Integer.valueOf(rs.getString("ifFirstLineUp"));
							mpo.ifParticipate = Integer.valueOf(rs.getString("ifParticipate"));
							mpo.teamAllTime = Integer.valueOf(rs.getString("teamAllTime"));
							mpo.teamOffensiveRebound = Integer.valueOf(rs.getString("teamOffRebound"));
							mpo.teamDefensiveRebound = Integer.valueOf(rs.getString("teamDefRebound"));
							mpo.opponentOffensiveRebound = Integer.valueOf(rs.getString("opponentOffRebound"));
							mpo.opponentDefensiveRebound = Integer.valueOf(rs.getString("opponentDefRebound"));
							mpo.teamShotIn = Integer.valueOf(rs.getString("teamShotIn"));
							mpo.opponentOffensiveNum = Integer.valueOf(rs.getString("oppOffNum"));
							mpo.opponentTwoShot = Integer.valueOf(rs.getString("oppTwoShot"));
							mpo.teamShot = Integer.valueOf(rs.getString("teamShot"));
							mpo.teamPenaltyShot = Integer.valueOf(rs.getString("teamPenaltyShot"));
							mpo.teamFault = Integer.valueOf(rs.getString("teamFault"));
							mpo.ifDouble = Integer.valueOf(rs.getString("ifDouble"));
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
       }
		System.out.println("something wrong in getPlayerTech");
		return null;
	}

	////常规赛 罚球 失误 上场时间 命中率 犯规 三分
	@Override
	public ArrayList<PlayerTechPO> getPlayerForYear(String name) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "cyanham";
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
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
						String sql = "SELECT * FROM `playerTechPO` where name = '"+name+"' and ifRegular = '1'";						
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
							po.rebound=Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"))/po.gameNum;
							po.time=Integer.valueOf(rs.getString("time"))/po.gameNum;
							po.offensiveNum=Integer.valueOf(rs.getString("offensiveNum"));
							po.defensiveNum=Integer.valueOf(rs.getString("defensiveNum"));
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.shotIn=Integer.valueOf(rs.getString("shotIn"))/po.gameNum;
							po.shot=Integer.valueOf(rs.getString("shot"));
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"))/po.gameNum;
							po.threeShot=Integer.valueOf(rs.getString("threeShot"));
							po.penaltyShotIn=Integer.valueOf(rs.getString("penaltyShotIn"));
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"))/po.gameNum;
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
       }
		System.out.println("something wrong in getPlayerForYear");
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
							po.rebound=Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"))/po.gameNum;
							po.time=Integer.valueOf(rs.getString("time"))/po.gameNum;
							po.offensiveNum=Integer.valueOf(rs.getString("offensiveNum"));
							po.defensiveNum=Integer.valueOf(rs.getString("defensiveNum"));
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.shotIn=Integer.valueOf(rs.getString("shotIn"))/po.gameNum;
							po.shot=Integer.valueOf(rs.getString("shot"));
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"))/po.gameNum;
							po.threeShot=Integer.valueOf(rs.getString("threeShot"));
							po.penaltyShotIn=Integer.valueOf(rs.getString("penaltyShotIn"));
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"))/po.gameNum;
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

	//常规赛 球员属性排名 得分篮板助攻抢断盖帽犯规失误投篮命中率三分命中数罚球出手数上场时间
	@Override
	public PlayerTechPO getPlayerRank(String player, String season,
			int ifRegular) {
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
						String sql = "SELECT * FROM `playerTechPO` where name = '"+player+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						PlayerTechPO po = new PlayerTechPO();
						while(rs.next()) {
							po.name=rs.getString("name");
							po.season=rs.getString("season");
							po.team=rs.getString("team");
							po.ifRegular=Integer.valueOf(rs.getString("ifRegular"));
							po.gameNum=Integer.valueOf(rs.getString("gameNum"));
							po.rebound=Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"))/po.gameNum;
							po.time=Integer.valueOf(rs.getString("time"))/po.gameNum;
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"))/po.gameNum;
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"))/po.gameNum;
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
						}
						//得分篮板助攻抢断盖帽犯规失误 投篮命中率 三分命中数 罚球出手数 上场时间
						Statement state = conn.createStatement();
						String sql1 = "select distict name,gameNum,count(name) from `playerTechPO` where score/gameNum>"+po.score/po.gameNum;
						ResultSet r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.score = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTechPO` where rebound/gameNum>"+po.rebound/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.rebound = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTechPO` where assist/gameNum>"+po.secondaryAttack/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.secondaryAttack = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where steal/gameNum>"+po.steal/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.steal = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where block/gameNum>"+po.blockShot/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.blockShot = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where foul/gameNum>"+po.foul/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.foul = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where fault/gameNum>"+po.fault/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.fault = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where shotInRate>"+po.shotInRate/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.shotInRate = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where threeShotIn/gameNum>"+po.threeShotIn/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.threeShotIn = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where penaltyShot/gameNum>"+po.penaltyShot/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.penaltyShot = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where time/gameNum>"+po.time/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.time = Integer.valueOf(r1.getString(2));
						}
						rs.close();
						conn.close(); 
						return po;
       } catch(ClassNotFoundException e) {   
              System.out.println("Sorry,can`t find the Driver!");   
              e.printStackTrace();   
       } catch(SQLException e) {   
              e.printStackTrace();   
       } catch(Exception e) {   
              e.printStackTrace();   
       }
		System.out.println("something wrong in getPlayerRank");
		return null;
	}

	@Override
	//得分篮板助攻抢断盖帽犯规失误投篮命中率三分命中数罚球出手数上场时间
	public PlayerTechPO getAllPlayerAverage(String season, int ifRegular) {
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
						PlayerTechPO po = new PlayerTechPO();
						//得分篮板助攻抢断盖帽犯规失误 投篮命中率 三分命中数 罚球出手数 上场时间
						Statement state = conn.createStatement();
						String sql = "select avg(score)as a,avg(rebound) as b, avg(assist) as c, avg(steal) as d, avg(block) as e,"
								+ " avg(foul)  as f, avg(fault) as g, avg(shotInRate) as h, avg(threeShotIn) as i,avg(penaltyShot) as j, avg(time) as k"
								+ "from `playerTechPO` where season='"+season+"'";
						ResultSet rs = state.executeQuery(sql);
						while(rs.next()){
							po.score = Integer.valueOf(rs.getString(1));
							po.rebound=Integer.valueOf(rs.getString(2));
							po.secondaryAttack=Integer.valueOf(rs.getString(3));
							po.steal=Integer.valueOf(rs.getString(4));
							po.blockShot=Integer.valueOf(rs.getString(5));
							po.foul=Integer.valueOf(rs.getString(6));
							po.fault=Integer.valueOf(rs.getString(7));
							po.shotInRate=Integer.valueOf(rs.getString(8));
							po.threeShotIn=Integer.valueOf(rs.getString(9));
							po.penaltyShot=Integer.valueOf(rs.getString(10));
							po.time=Integer.valueOf(rs.getString(11));
						}
						rs.close();
						conn.close(); 
						return po;
       } catch(ClassNotFoundException e) {   
              System.out.println("Sorry,can`t find the Driver!");   
              e.printStackTrace();   
       } catch(SQLException e) {   
              e.printStackTrace();   
       } catch(Exception e) {   
              e.printStackTrace();   
       }
		System.out.println("something wrong in getAllPlayerAverage");
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
						PlayerTechPO po = new PlayerTechPO();
						//得分篮板助攻抢断盖帽犯规失误 投篮命中率 三分命中数 罚球出手数 上场时间
						Statement state = conn.createStatement();
						String sql = "select avg(score)as a,avg(rebound) as b, avg(assist) as c, avg(steal) as d, avg(block) as e,"
								+ " avg(foul)  as f, avg(fault) as g, avg(shotInRate) as h, avg(threeShotIn) as i,avg(penaltyShot) as j, avg(time) as k"
								+ "from `playerTechPO` where season='"+season+"' and team in(select abbreviation from teamInfo where division ='"+division+"')";
						ResultSet rs = state.executeQuery(sql);
						while(rs.next()){
							po.score = Integer.valueOf(rs.getString(1));
							po.rebound=Integer.valueOf(rs.getString(2));
							po.secondaryAttack=Integer.valueOf(rs.getString(3));
							po.steal=Integer.valueOf(rs.getString(4));
							po.blockShot=Integer.valueOf(rs.getString(5));
							po.foul=Integer.valueOf(rs.getString(6));
							po.fault=Integer.valueOf(rs.getString(7));
							po.shotInRate=Integer.valueOf(rs.getString(8));
							po.threeShotIn=Integer.valueOf(rs.getString(9));
							po.penaltyShot=Integer.valueOf(rs.getString(10));
							po.time=Integer.valueOf(rs.getString(11));
						}
						rs.close();
						conn.close(); 
						return po;
       } catch(ClassNotFoundException e) {   
              System.out.println("Sorry,can`t find the Driver!");   
              e.printStackTrace();   
       } catch(SQLException e) {   
              e.printStackTrace();   
       } catch(Exception e) {   
              e.printStackTrace();   
       }
		System.out.println("something wrong in getDivPlayerAverage");
		return null;
	}

	@Override
	public ArrayList<String> getTeamSeasonList(String team) {
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
						String sql = "select distinct season from teamtech where name='"+team+"'";
						ResultSet rs = statement.executeQuery(sql);
						ArrayList<String> se = new ArrayList<String>();
						while(rs.next()){
							String[] temp = rs.getString(1).trim().split("\\s+");
							se.add(temp[0]);
						}
						rs.close();
						conn.close();
						return se;
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
	public ArrayList<String> getPlayerSeasonList(String player) {
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
						String sql = "select distinct season from playerTechPO where name='"+player+"'";
						ResultSet rs = statement.executeQuery(sql);
						ArrayList<String> se = new ArrayList<String>();
						while(rs.next()){
							String[] temp = rs.getString(1).trim().split("\\s+");
							se.add(temp[0]);
						}
						rs.close();
						conn.close();
						return se;
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

}
