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

	public static void main(String[] args){
		PlayerTech pt = new PlayerTech();
		ArrayList<MatchPO>li = pt.getRecentMatch("BOS", "2010-11 Regular");
	   System.out.println(li.size());
		for(int i=0;i<li.size();i++){
			System.out.println(li.get(i).date);
		}
		
	}
	@Override
 	public TeamTechPO getTeamTech(String teamname, String season, int ifRegular) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		if(season.trim().split("\\s+")[1].equals("Regular")){
			season = season+" season";
		}
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
						String sql = "SELECT * FROM `teamtech` where name = '"+teamname+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							TeamTechPO po = new TeamTechPO();
							po.name = rs.getString("name");
							po.ifReagular = Integer.valueOf(rs.getString("ifReagular"));
							po.season =  rs.getString("season");
							po.gameNum = Integer.valueOf(rs.getString("gameNum"));
							po.shotInNum = Integer.valueOf(rs.getString("shotInNum"))/po.gameNum;
							po.shotNum = Integer.valueOf(rs.getString("shotNum"))/po.gameNum;
							po.threeShotInNum = Integer.valueOf(rs.getString("threeShotInNum"))/po.gameNum;
							po.threeShotNum =  Integer.valueOf(rs.getString("threeShotNum"))/po.gameNum;
							po.penaltyShotInNum =  Integer.valueOf(rs.getString("penaltyShotInNum"))/po.gameNum;
							po.penaltyShotNum=  Integer.valueOf(rs.getString("penaltyShotNum"))/po.gameNum;
							po.offensiveRebound=  Integer.valueOf(rs.getString("offensiveRebound"))/po.gameNum;
							po.defensiveRebound=  Integer.valueOf(rs.getString("defensiveRebound"))/po.gameNum;
							po.rebound=  Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=  Integer.valueOf(rs.getString("secondaryAttack"))/po.gameNum;
							po.steal=  Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=  Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=  Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=  Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=  Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.shotInRate=  Double.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=  Double.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=  Double.valueOf(rs.getString("penaltyShotInRate"));
							po.offensiveEfficiency=  Double.valueOf(rs.getString("offensiveEfficiency"));
							po.defensiveEfficiency=  Double.valueOf(rs.getString("defensiveEfficiency"));
							po.reboundEfficiency=  Double.valueOf(rs.getString("reboundEfficiency"));
							po.stealEfficiency=  Double.valueOf(rs.getString("stealEfficiency"));
							po.secondaryAttackEfficiency=  Double.valueOf(rs.getString("secondaryAttackEfficiency"));
							po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"))/po.gameNum;
							po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"))/po.gameNum;
							po.opponentScore=  Integer.valueOf(rs.getString("opponentScore"))/po.gameNum;
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
	public PlayerTechPO getPlayerTech(String player, String season, int ifRegular) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
						String sql = "SELECT * FROM `playerTechPO` where name = '"+player+"' and season = '"+season+"'";		
						System.out.println(sql);
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
							po.startingNum=Integer.valueOf(rs.getString("startingNum"))/po.gameNum;
							po.rebound=Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"))/po.gameNum;
							po.time=Integer.valueOf(rs.getString("time"))/po.gameNum;
							po.offensiveNum=Integer.valueOf(rs.getString("offensiveNum"))/po.gameNum;
							po.defensiveNum=Integer.valueOf(rs.getString("defensiveNum"))/po.gameNum;
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("block"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.shotIn=Integer.valueOf(rs.getString("shotIn"))/po.gameNum;
							po.shot=Integer.valueOf(rs.getString("shot"))/po.gameNum;
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"))/po.gameNum;
							po.threeShot=Integer.valueOf(rs.getString("threeShot"))/po.gameNum;
							po.penaltyShotIn=Integer.valueOf(rs.getString("penaltyShotIn"))/po.gameNum;
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"))/po.gameNum;
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=Double.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=Double.valueOf(rs.getString("penaltyShotInRate"));
							po.GmScEfficiency=Double.valueOf(rs.getString("GmSc"));
							po.trueShotInRate=Double.valueOf(rs.getString("trueShotInRate"));
							po.shootingEfficiency=Double.valueOf(rs.getString("ShootingEffi"));
							po.reboundRate=Double.valueOf(rs.getString("reboundRate"));
							po.offensiveReboundRate=Double.valueOf(rs.getString("offReboundRate"));
							po.defensiveReboundRate=Double.valueOf(rs.getString("defReboundRate"));
							po.secondaryAttackRate=Double.valueOf(rs.getString("assistRate"));
							po.faultRate=Double.valueOf(rs.getString("faultRate"));
							po.usageRate=Double.valueOf(rs.getString("usageRate"));
							po.ifDouble=Integer.parseInt(rs.getString("ifDouble"));
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
	public ArrayList<MatchPO> getRecentMatch(String team,String season) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		ArrayList<MatchPO> list = new ArrayList<MatchPO>();
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
						String pre = season.trim().split("\\s+")[0];
						String sql = "SELECT * FROM `MatchPO` where guestTeam= '"+team+"' and (season = '"+pre+" "+"Regular' or season='"+pre+" Postseason') order by `date` desc limit 0,20";	
						System.out.println(sql);
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							MatchPO po = new MatchPO();
							po.ifRegular = Integer.parseInt(rs.getString("ifRegular"));
							po.season = rs.getString("season");
							po.date = rs.getString("date");
							po.homeTeam = rs.getString("homeTeam");
							po.guestTeam = rs.getString("guestTeam");
							po.score = rs.getString("score");
							po.score1 = rs.getString( "score1");
							po.score2 = rs.getString("score2");
							po.score3 = rs.getString("score3");
							po.score4 = rs.getString("score4");
							po.scoreExtra = rs.getString("scoreExtra");
							po.scoringChampion = rs.getString("scoringChampion");
							po.reboundChampion = rs.getString("reboundChampion");
							po.assistChampion = rs.getString("assistChampion");
							po.ifHomeTeamWin = Integer.parseInt(rs.getString("ifHomeTeamWin"));
							po.ifGuestTeamWin = Integer.parseInt(rs.getString("ifGuestTeamWin"));
							po.homeTeamDeffensiveRebound = Integer.parseInt(rs.getString("homeTeamDeffensiveRebound"));
							po.guestTeamDeffensiveRebound = Integer.parseInt(rs.getString("guestTeamDeffensiveRebound"));
							po.homeTeamOffensiveRebound = Integer.parseInt(rs.getString("homeTeamOffensiveRebound"));
							po.guestTeamOffensiveRebound = Integer.parseInt(rs.getString("guestTeamOffensiveRebound"));
							po.homeTeamFoul = Integer.parseInt(rs.getString("homeTeamFoul"));
							po.guestTeamFoul = Integer.parseInt(rs.getString("guestTeamFoul"));
							po.homeTeamSecondaryAttack = Integer.parseInt(rs.getString("homeTeamSecondaryAttack"));
							po.guestTeamSecondaryAttack = Integer.parseInt(rs.getString("guestTeamSecondaryAttack"));
							po.homeTeamBlockShot = Integer.parseInt(rs.getString("homeTeamBlockShot"));
							po.guestTeamBlockShot = Integer.parseInt(rs.getString("guestTeamBlockShot"));
							po.homeScore = Integer.parseInt(rs.getString("homeScore"));
							po.guestScore = Integer.parseInt(rs.getString("guestScore"));
							po.homeAllTime = Integer.parseInt(rs.getString("homeAllTime"));
							po.guestAllTime = Integer.parseInt(rs.getString("guestAllTime"));
							po.homeShotIn = Integer.parseInt(rs.getString("homeShotIn"));
							po.guestShotIn = Integer.parseInt(rs.getString("guestShotIn"));
							po.homeShot = Integer.parseInt(rs.getString("homeShot"));
							po.guestShot = Integer.parseInt( rs.getString("guestShot"));
							po.homeTwoShot = Integer.parseInt(rs.getString("homeTwoShot"));
							po.homeTwoShotIn = Integer.parseInt(rs.getString("homeTwoShotIn"));
							po.guestTwoShot = Integer.parseInt(rs.getString("guestTwoShot"));
							po.guestTwoShotIn = Integer.parseInt(rs.getString("guestTwoShotIn"));
							po.homeThreeShot = Integer.parseInt(rs.getString("homeThreeShot"));
							po.guestThreeShot = Integer.parseInt(rs.getString("guestThreeShot"));
							po.homeThreeShotIn = Integer.parseInt(rs.getString("homeThreeShotIn"));
							po.guestThreeShotIn = Integer.parseInt(rs.getString("guestThreeShotIn"));
							po.homePenaltyShot = Integer.parseInt(rs.getString("homePenaltyShot"));
							po.guestPenaltyShot = Integer.parseInt(rs.getString("guestPenaltyShot"));
							po.homePenaltyShotIn = Integer.parseInt(rs.getString("homePenaltyShotIn"));
							po.guestPenaltyShotIn = Integer.parseInt(rs.getString("guestPenaltyShotIn"));
							po.homeFault = Integer.parseInt(rs.getString("homeFault"));
							po.guestFault = Integer.parseInt(rs.getString("guestFault"));
							String sql2 = "select * from `playerTechPO` where date = '"+po.date+"' and (team ='"+po.homeTeam+"' or team = '"+po.guestTeam+"')";
							ArrayList<PlayerTechMPO> mpo = new ArrayList<PlayerTechMPO>();
							Statement state = conn.createStatement();
							ResultSet r = state.executeQuery(sql2);
							while(r.next()){
								PlayerTechMPO p = new PlayerTechMPO();
								p.name = rs.getString("name");
								p.team = rs.getString("team");
								p.season = rs.getString("season");
								p.division = rs.getString("division");
								p.date = rs.getString("date");
								p.position = rs.getString("position");
								p.time = Integer.valueOf(rs.getString("time"));
								p.shotIn =  Integer.valueOf(rs.getString("shotIn"));
								p.shot = Integer.valueOf(rs.getString("shot"));
								p.threeShot = Integer.valueOf(rs.getString("threeShot"));
								p.penaltyShotIn = Integer.valueOf(rs.getString("penaltyShotIn"));
								p.penaltyShot = Integer.valueOf(rs.getString("penaltyShot"));
								p.offensiveRebound = Integer.valueOf(rs.getString("offensiveNum"));
								p.defensiveRebound = Integer.valueOf(rs.getString("defensiveNum"));
								p.rebound = Integer.valueOf(rs.getString("rebound"));
								p.secondaryAttack = Integer.valueOf(rs.getString("assist"));
								p.steal = Integer.valueOf(rs.getString("steal"));
								p.blockShot = Integer.valueOf(rs.getString("blockShot"));
								p.fault = Integer.valueOf(rs.getString("fault"));
								p.foul = Integer.valueOf(rs.getString("foul"));
								p.score =  Integer.valueOf(rs.getString("score"));
								p.ifFirstLineUp = Integer.valueOf(rs.getString("ifFirstLineUp"));
								p.ifParticipate = Integer.valueOf(rs.getString("ifParticipate"));
								p.teamAllTime = Integer.valueOf(rs.getString("teamAllTime"));
								p.teamOffensiveRebound = Integer.valueOf(rs.getString("teamOffRebound"));
								p.teamDefensiveRebound = Integer.valueOf(rs.getString("teamDefRebound"));
								p.opponentOffensiveRebound = Integer.valueOf(rs.getString("opponentOffRebound"));
								p.opponentDefensiveRebound = Integer.valueOf(rs.getString("opponentDefRebound"));
								p.teamShotIn = Integer.valueOf(rs.getString("teamShotIn"));
								p.opponentOffensiveNum = Integer.valueOf(rs.getString("oppOffNum"));
								p.opponentTwoShot = Integer.valueOf(rs.getString("oppTwoShot"));
								p.teamShot = Integer.valueOf(rs.getString("teamShot"));
								p.teamPenaltyShot = Integer.valueOf(rs.getString("teamPenaltyShot"));
								p.teamFault = Integer.valueOf(rs.getString("teamFault"));
								p.ifDouble = Integer.valueOf(rs.getString("ifDouble"));
								mpo.add(p);
							}
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
		System.out.println("something wrong in getRecentPlayerTech");
		return null;
	}

	@Override
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,
			String season) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
						String pre = season.trim().split("\\s+")[0];
						String sql = "SELECT * FROM `playerTechMPO` where name = '"+player+"' and (season = '"+pre+" "+"Regular' or season='"+pre+" Postseason') order by date desc limit 0,20";	
						System.out.println(sql);
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
		System.out.println("something wrong in getRecentPlayerTech");
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> getMatchForYear(String team) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
						String sql = "SELECT * FROM `teamtech` where name = '"+team+"' and ifRegular = '1'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							TeamTechPO po = new TeamTechPO();
							po.name = rs.getString("name");
							po.ifReagular = Integer.valueOf(rs.getString("ifReagular"));
							po.season =  rs.getString("season");
							po.gameNum = Integer.valueOf(rs.getString("gameNum"));
							po.shotInNum = Integer.valueOf(rs.getString("shotInNum"))/po.gameNum;
							po.shotNum = Integer.valueOf(rs.getString("shotNum"))/po.gameNum;
							po.threeShotInNum = Integer.valueOf(rs.getString("threeShotInNum"))/po.gameNum;
							po.threeShotNum =  Integer.valueOf(rs.getString("threeShotNum"))/po.gameNum;
							po.penaltyShotInNum =  Integer.valueOf(rs.getString("penaltyShotInNum"))/po.gameNum;
							po.penaltyShotNum=  Integer.valueOf(rs.getString("penaltyShotNum"))/po.gameNum;
							po.offensiveRebound=  Integer.valueOf(rs.getString("offensiveRebound"))/po.gameNum;
							po.defensiveRebound=  Integer.valueOf(rs.getString("defensiveRebound"))/po.gameNum;
							po.rebound=  Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=  Integer.valueOf(rs.getString("secondaryAttack"))/po.gameNum;
							po.steal=  Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=  Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=  Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=  Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=  Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.shotInRate=  Double.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=  Double.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=  Double.valueOf(rs.getString("penaltyShotInRate"));
							po.offensiveEfficiency=  Double.valueOf(rs.getString("offensiveEfficiency"));
							po.defensiveEfficiency=  Double.valueOf(rs.getString("defensiveEfficiency"));
							po.reboundEfficiency=  Double.valueOf(rs.getString("reboundEfficiency"));
							po.stealEfficiency=  Double.valueOf(rs.getString("stealEfficiency"));
							po.secondaryAttackEfficiency=  Double.valueOf(rs.getString("secondaryAttackEfficiency"));
							po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"))/po.gameNum;
							po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"))/po.gameNum;
							po.opponentScore=  Integer.valueOf(rs.getString("opponentScore"))/po.gameNum;
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
		System.out.println("something wrong in getMatchForYear");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> getPlayerForYear(String name) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
						String sql = "SELECT * FROM `playerTechPO` where name = '"+name+"' and ifRegular = '1'";		
						System.out.println(sql);
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
							po.offensiveNum=Integer.valueOf(rs.getString("offensiveNum"))/po.gameNum;
							po.defensiveNum=Integer.valueOf(rs.getString("defensiveNum"))/po.gameNum;
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("block"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.shotIn=Integer.valueOf(rs.getString("shotIn"))/po.gameNum;
							po.shot=Integer.valueOf(rs.getString("shot"))/po.gameNum;
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"))/po.gameNum;
							po.threeShot=Integer.valueOf(rs.getString("threeShot"))/po.gameNum;
							po.penaltyShotIn=Integer.valueOf(rs.getString("penaltyShotIn"))/po.gameNum;
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"))/po.gameNum;
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=Double.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=Double.valueOf(rs.getString("penaltyShotInRate"));
							po.GmScEfficiency=Double.valueOf(rs.getString("GmSc"));
							po.trueShotInRate=Double.valueOf(rs.getString("trueShotInRate"));
							po.shootingEfficiency=Double.valueOf(rs.getString("ShootingEffi"));
							po.reboundRate=Double.valueOf(rs.getString("reboundRate"));
							po.offensiveReboundRate=Double.valueOf(rs.getString("offReboundRate"));
							po.defensiveReboundRate=Double.valueOf(rs.getString("defReboundRate"));
							po.secondaryAttackRate=Double.valueOf(rs.getString("assistRate"));
							po.faultRate=Double.valueOf(rs.getString("faultRate"));
							po.usageRate=Double.valueOf(rs.getString("usageRate"));
							po.ifDouble=Integer.parseInt(rs.getString("ifDouble"));
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
	public TeamTechPO getTeamRank(String teamname, String season, int ifRegular) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						if(season.trim().split("\\s+")[1].equals("Regular")){
							season = season+" season";
						}
						Statement statement = conn.createStatement();
						String sql = "SELECT * FROM `teamtech` where name = '"+teamname+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						TeamTechPO po = new TeamTechPO();
						while(rs.next()) {
							po.name=rs.getString("name");
							po.season=rs.getString("season");
							po.gameNum=Integer.valueOf(rs.getString("gameNum"));
							po.rebound=Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=Integer.valueOf(rs.getString("secondaryAttack"))/po.gameNum;
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.threeShotInNum=Integer.valueOf(rs.getString("threeShotInNum"))/po.gameNum;
							po.penaltyShotNum=Integer.valueOf(rs.getString("penaltyShotNum"))/po.gameNum;
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
						}
						
						Statement state = conn.createStatement();
						String sql1 = "select name,gameNum,count(name),score/gameNum from `teamtech` where score/gameNum>"+po.score/po.gameNum;
						ResultSet r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.score = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),rebound/gameNum from `teamtech` where rebound/gameNum>"+po.rebound/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.rebound = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),secondaryAttack/gameNum from `teamtech` where secondaryAttack/gameNum>"+po.secondaryAttack/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.secondaryAttack = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),steal/gameNum from `teamtech` where steal/gameNum>"+po.steal/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.steal = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),blockShot/gameNum from `teamtech` where blockShot/gameNum>"+po.blockShot/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.blockShot = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),foul/gameNum from `teamtech` where foul/gameNum>"+po.foul/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.foul = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),fault/gameNum from `teamtech` where fault/gameNum>"+po.fault/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.fault = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name) from `teamtech` where shotInRate>"+po.shotInRate;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.shotInRate = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name), threeShotInNum/gameNum from `teamtech` where threeShotInNum/gameNum>"+po.threeShotInNum/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.threeShotInNum = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),penaltyShotNum/gameNum from `teamtech` where penaltyShotNum/gameNum>"+po.penaltyShotNum/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.penaltyShotNum = Integer.valueOf(r1.getString(3));
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
	public TeamTechPO getAllTeamAverage(String season, int ifRegular) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						TeamTechPO po = new TeamTechPO();
						if(season.trim().split("\\s+")[1].equals("Regular")){
							season = season+" season";
						}
						Statement state = conn.createStatement();
						String sql = "select avg(score)as a,avg(rebound) as b, avg(secondaryAttack) as c, avg(steal) as d, avg(blockShot) as e,"
								+ " avg(foul)  as f, avg(fault) as g, avg(shotInRate) as h, avg(threeShotInNum) as i,avg(penaltyShotNum) as j"
								+ "from `teamtech` where season='"+season+"'";
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
							po.threeShotInNum=Integer.valueOf(rs.getString(9));
							po.penaltyShotNum=Integer.valueOf(rs.getString(10));
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
	public PlayerTechPO getPlayerRank(String player, String season,
			int ifRegular) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						Statement statement = conn.createStatement();
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
						Statement state = conn.createStatement();
						String sql1 = "select distict name,gameNum,count(name),score/gameNum from `playerTechPO` where score/gameNum>"+po.score/po.gameNum;
						ResultSet r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.score = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),rebound/gameNum from `playerTechPO` where rebound/gameNum>"+po.rebound/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.rebound = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),assist/gameNum from `playerTechPO` where assist/gameNum>"+po.secondaryAttack/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.secondaryAttack = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),steal/gameNum from `playerTech` where steal/gameNum>"+po.steal/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.steal = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),block/gameNum from `playerTech` where block/gameNum>"+po.blockShot/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.blockShot = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),foul/gameNum from `playerTech` where foul/gameNum>"+po.foul/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.foul = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),fault/gameNum from `playerTech` where fault/gameNum>"+po.fault/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.fault = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name) from `playerTech` where shotInRate>"+po.shotInRate;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.shotInRate = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name), threeShotIn/gameNum from `playerTech` where threeShotIn/gameNum>"+po.threeShotIn/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.threeShotIn = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),penaltyShot/gameNum from `playerTech` where penaltyShot/gameNum>"+po.penaltyShot/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.penaltyShot = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select distict name,gameNum,count(name),time/gameNum from `playerTech` where time/gameNum>"+po.time/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.time = Integer.valueOf(r1.getString(3));
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
	//锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较革拷帽锟斤拷锟斤拷失锟斤拷投锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较筹拷时锟斤拷
	public PlayerTechPO getAllPlayerAverage(String season, int ifRegular) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		//URL指锟斤拷要锟斤拷锟绞碉拷锟斤拷菘锟斤拷锟絥ba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL锟斤拷锟斤拷时锟斤拷锟矫伙拷锟斤拷
		String user = "root";
		// Java锟斤拷锟斤拷MySQL锟斤拷锟斤拷时锟斤拷锟斤拷锟斤拷
		String password = "cyanham";
		try {
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟�
						Class.forName(driver);
						// 锟斤拷锟斤拷锟斤拷菘锟�
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						PlayerTechPO po = new PlayerTechPO();
						//锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较革拷帽锟斤拷锟斤拷失锟斤拷 投锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟较筹拷时锟斤拷
						Statement state = conn.createStatement();
						String sql = "select avg(score/gameNum)as a,avg(rebound/gameNum) as b, avg(assist/gameNum) as c, avg(steal/gameNum) as d, avg(block/gameNum) as e,"
								+ " avg(foul/gameNum)  as f, avg(fault/gameNum) as g, avg(shotInRate) as h, avg(threeShotIn/gameNum) as i,avg(penaltyShot/gameNum) as j, avg(time/gameNum) as k"
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
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						TeamTechPO po = new TeamTechPO();
						Statement state = conn.createStatement();
						String sql = "select avg(score/gameNum)as a,avg(rebound/gameNum) as b, avg(secondaryAttack/gameNum) as c, avg(steal/gameNum) as d, avg(blockShot/gameNum) as e,"
								+ " avg(foul/gameNum)  as f, avg(fault/gameNum) as g, avg(shotInRate) as h, avg(threeShotInNum/gameNum) as i,avg(penaltyShotNum/gameNum) as j"
								+ "from `playerTechPO` where season='"+season+"' and team in(select abbreviation from teamInfo where division ='"+division.toUpperCase()+"')";
						System.out.println(sql);
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
							po.threeShotInNum=Integer.valueOf(rs.getString(9));
							po.penaltyShotNum=Integer.valueOf(rs.getString(10));
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
	public PlayerTechPO getDivPlayerAverage(String season, int ifRegular,
			String division) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
						PlayerTechPO po = new PlayerTechPO();
						
						Statement state = conn.createStatement();
						String sql = "select avg(score/gameNum)as a,avg(rebound/gameNum) as b, avg(assist/gameNum) as c, avg(steal/gameNum) as d, avg(block/gameNum) as e,"
								+ " avg(foul/gameNum)  as f, avg(fault/gameNum) as g, avg(shotInRate) as h, avg(threeShotIn/gameNum) as i,avg(penaltyShot/gameNum) as j, avg(time/gameNum) as k"
								+ "from `playerTechPO` where season='"+season+"' and team in(select abbreviation from teamInfo where division ='"+division.toUpperCase()+"')";
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
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
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
		System.out.println("wrong:getTeamSeasonList");
		return null;
	}

	@Override
	public ArrayList<String> getPlayerSeasonList(String player) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		String user = "root";
		String password = "cyanham";
		try {
						Class.forName(driver);
						Connection conn = DriverManager.getConnection(url, user, password);
						if(!conn.isClosed()){
							System.out.println("Succeeded connecting to the Database!");
						}
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
		System.out.println("wrong: getPlayerSeasonList");
		return null;
	}

}
