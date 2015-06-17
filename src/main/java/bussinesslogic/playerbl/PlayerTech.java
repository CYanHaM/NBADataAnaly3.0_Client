package bussinesslogic.playerbl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import data.Test;
import data.playertech.Find;
import PO.MatchPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamTechPO;

public class PlayerTech implements StatsInfo{

	public static void main(String[] args){
		PlayerTech pt = new PlayerTech();
		Find fi = new Find();
		//ArrayList<MatchPO> li = pt.getRecentMatch("BOS", "2009-10 Regular");
	  // System.out.println(li.size());
	//	ArrayList<MatchPO> li = fi.showMatchList("2009-12-25");
	//	ArrayList<PlayerTechMPO> li = pt.getRecentPlayerM("Dante Exum","2014-15 Regular" );
	//	ArrayList<TeamTechPO> li = pt.getMatchForYear("BOS");
		ArrayList<PlayerTechPO> li = pt.getPlayerForYear("Nick Young");
		System.out.println(li.size());
		for(int i=0;i<li.size();i++){
			System.out.println(li.get(i).season);
		}
		
		//PlayerTechPO li = pt.getPlayerRank("Alec Burks", "2014-15 Regular", 1);
		//System.out.println(li.secondaryAttack);
	//	TeamTechPO li = pt.getAllTeamAverage("2010-11 Regular", 1);
	//	PlayerTechPO li = pt.getAllPlayerAverage("2010-11 Regular", 1);
	//	TeamTechPO li = pt.getDivTeamAverage("2010-11 Regular", 1,"E");
	//	System.out.println(li.score);
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
						String sql = "SELECT * FROM `MatchPO` where  guestTeam= '"+team+"'  and (season = '"+pre+" "+"Regular' or season='"+pre+" Postseason') order by `date` desc limit 0,20";	
						System.out.println(sql);
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							MatchPO po = new MatchPO();
							po.ifRegular = Integer.parseInt(rs.getString("ifRegular"));
							po.season = rs.getString("season");
							po.date = rs.getString("date");
							po.homeTeam = rs.getString("homeTeam");
							po.guestTeam = rs.getString("guestTeam");
							Test test = new Test();
							ArrayList<String> fake = test.fake(po.homeTeam);
							Random random = new Random();
							int ran = random.nextInt(fake.size());
							String sq = "SELECT * FROM `MatchPO` where guestTeam= '"+po.homeTeam+"'  and (season = '"+pre+" "+"Regular' or season='"+pre+" Postseason') limit 0,1";
							System.out.println(sq);
							Statement sta = conn.createStatement();
							ResultSet set = sta.executeQuery(sq);
							while(set.next()){
								po.homeTeamDeffensiveRebound = Integer.parseInt(rs.getString("guestTeamDeffensiveRebound"));
								po.homeTeamOffensiveRebound = Integer.parseInt(rs.getString("guestTeamOffensiveRebound"));
								po.homeTeamFoul = Integer.parseInt(rs.getString("guestTeamFoul"));
								po.homeTeamSecondaryAttack = Integer.parseInt(rs.getString("guestTeamSecondaryAttack"));
								po.homeTeamBlockShot = Integer.parseInt(rs.getString("guestTeamBlockShot"));
								po.homeScore = Integer.parseInt(rs.getString("guestScore"));
								po.homeAllTime = Integer.parseInt(rs.getString("guestAllTime"));
								po.homeShotIn = Integer.parseInt(rs.getString("guestShotIn"));
								po.homeShot = Integer.parseInt(rs.getString("guestShot"));
								if(po.homeShot==0){
									po.homeShot=1;
								}
								po.homeTwoShot = Integer.parseInt(rs.getString("guestTwoShot"));
								po.homeTwoShotIn = Integer.parseInt(rs.getString("guestTwoShotIn"));
								po.homeThreeShot = Integer.parseInt(rs.getString("guestThreeShot"));
								po.homeThreeShotIn = Integer.parseInt(rs.getString("guestThreeShotIn"));
								po.homePenaltyShot = Integer.parseInt(rs.getString("guestPenaltyShot"));
								po.homePenaltyShotIn = Integer.parseInt(rs.getString("guestPenaltyShotIn"));
								po.homeFault = Integer.parseInt(rs.getString("guestFault"));
							}
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
							po.guestTeamDeffensiveRebound = Integer.parseInt(rs.getString("guestTeamDeffensiveRebound"));					
							po.guestTeamOffensiveRebound = Integer.parseInt(rs.getString("guestTeamOffensiveRebound"));
							
							po.guestTeamFoul = Integer.parseInt(rs.getString("guestTeamFoul"));
							
							po.guestTeamSecondaryAttack = Integer.parseInt(rs.getString("guestTeamSecondaryAttack"));
							
							po.guestTeamBlockShot = Integer.parseInt(rs.getString("guestTeamBlockShot"));
							
							po.guestScore = Integer.parseInt(rs.getString("guestScore"));
							
							po.guestAllTime = Integer.parseInt(rs.getString("guestAllTime"));
							
							po.guestShotIn = Integer.parseInt(rs.getString("guestShotIn"));
							
							po.guestShot = Integer.parseInt( rs.getString("guestShot"));
							if(po.guestShot==0){
								po.guestShot=1;
							}
							po.guestTwoShot = Integer.parseInt(rs.getString("guestTwoShot"));
							po.guestTwoShotIn = Integer.parseInt(rs.getString("guestTwoShotIn"));
							
							po.guestThreeShot = Integer.parseInt(rs.getString("guestThreeShot"));
						
							po.guestThreeShotIn = Integer.parseInt(rs.getString("guestThreeShotIn"));
							
							po.guestPenaltyShot = Integer.parseInt(rs.getString("guestPenaltyShot"));
							
							po.guestPenaltyShotIn = Integer.parseInt(rs.getString("guestPenaltyShotIn"));
							
							po.guestFault = Integer.parseInt(rs.getString("guestFault"));
								
							String sql2 = "select * from `playerTechMPO` where `date` = '"+po.date+"' and team ='"+po.guestTeam+"'";
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
						
							String sql3 = "select * from `playerTechMPO` where `date` = '"+fake.get(ran)+"' and team ='"+po.homeTeam+"'";
							Statement state3 = conn.createStatement();
							ResultSet rs3 = state3.executeQuery(sql3);
							while(rs3.next()){
								PlayerTechMPO p = new PlayerTechMPO();
								p.name = rs.getString("name");
								p.team = rs.getString("team");
								p.season = po.season;
								p.division = rs.getString("division");
								p.date = po.date;
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
						String sql = "SELECT * FROM `teamtech` where name = '"+team+"' and ifReagular = '1'";						
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
							if(po.gameNum==0){
								continue;
							}
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
							po.rebound=Integer.valueOf(rs.getString("rebound"));
							po.secondaryAttack=Integer.valueOf(rs.getString("secondaryAttack"));
							po.steal=Integer.valueOf(rs.getString("steal"));
							po.blockShot=Integer.valueOf(rs.getString("blockShot"));
							po.fault=Integer.valueOf(rs.getString("fault"));
							po.foul=Integer.valueOf(rs.getString("foul"));
							po.score=Integer.valueOf(rs.getString("score"));
							po.threeShotInNum=Integer.valueOf(rs.getString("threeShotInNum"));
							po.penaltyShotNum=Integer.valueOf(rs.getString("penaltyShotNum"));
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
						}
						
						Statement state = conn.createStatement();
						String sql1 = "select name,gameNum,count(name),score/gameNum from `teamtech` where score"+po.score;
						ResultSet r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.score = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),rebound/gameNum from `teamtech` where rebound>"+po.rebound;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.rebound = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),secondaryAttack/gameNum from `teamtech` where secondaryAttack>"+po.secondaryAttack;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.secondaryAttack = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),steal/gameNum from `teamtech` where steal"+po.steal;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.steal = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),blockShot/gameNum from `teamtech` where blockShot>"+po.blockShot;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.blockShot = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),foul/gameNum from `teamtech` where foul>"+po.foul;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.foul = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),fault/gameNum from `teamtech` where fault>"+po.fault;
						System.out.println(sql1);
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.fault = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name) from `teamtech` where shotInRate>"+po.shotInRate;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.shotInRate = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name), threeShotInNum/gameNum from `teamtech` where threeShotInNum>"+po.threeShotInNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.threeShotInNum = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),penaltyShotNum/gameNum from `teamtech` where penaltyShotNum>"+po.penaltyShotNum;
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
						String sql = "select avg(score) as a,avg(rebound) as b, avg(secondaryAttack) as c, avg(steal) as d, avg(blockShot) as e,"
								+ " avg(foul)  as f, avg(fault) as g, avg(shotInRate) as h, avg(threeShotInNum) as i,avg(penaltyShotNum) as j"
								+ " from `teamtech` where season='"+season+"'";
						ResultSet rs = state.executeQuery(sql);
						while(rs.next()){
							po.season = season;
							po.score = (int)(Double.valueOf(rs.getString(1))/1);
							po.rebound=(int)(Double.valueOf(rs.getString(2))/1);
							po.secondaryAttack=(int)(Double.valueOf(rs.getString(3))/1);
							po.steal=(int)(Double.valueOf(rs.getString(4))/1);
							po.blockShot=(int)(Double.valueOf(rs.getString(5))/1);
							po.foul=(int)(Double.valueOf(rs.getString(6))/1);
							po.fault=(int)(Double.valueOf(rs.getString(7))/1);
							po.shotInRate=(int)(Double.valueOf(rs.getString(8))/1);
							po.threeShotInNum=(int)(Double.valueOf(rs.getString(9))/1);
							po.penaltyShotNum=(int)(Double.valueOf(rs.getString(10))/1);
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
							po.rebound=Integer.valueOf(rs.getString("rebound"));
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"));
							po.time=Integer.valueOf(rs.getString("time"));
							po.steal=Integer.valueOf(rs.getString("steal"));
							po.blockShot=Integer.valueOf(rs.getString("block"));
							po.fault=Integer.valueOf(rs.getString("fault"));
							po.foul=Integer.valueOf(rs.getString("foul"));
							po.score=Integer.valueOf(rs.getString("score"));
							po.threeShotIn=Integer.valueOf(rs.getString("threeShotIn"));
							po.penaltyShot=Integer.valueOf(rs.getString("penaltyShot"));
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
						}
						Statement state = conn.createStatement();
						String sql1 = "select name,gameNum,count(name),score/gameNum from `playerTechPO` where score>"+po.score;
						ResultSet r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.score = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),rebound/gameNum from `playerTechPO` where rebound>"+po.rebound;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							System.out.println(po.rebound);
							po.rebound = Integer.valueOf(r1.getString(3));
							System.out.println(po.rebound);
						}
						sql1 = "select name,gameNum,count(name),assist/gameNum from `playerTechPO` where assist>"+po.secondaryAttack;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.secondaryAttack = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),steal/gameNum from `playerTechPO` where steal>"+po.steal;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.steal = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),block/gameNum from `playerTechPO` where block>"+po.blockShot;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.blockShot = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),foul/gameNum from `playerTechPO` where foul>"+po.foul;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.foul = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),fault/gameNum from `playerTechPO` where fault>"+po.fault;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.fault = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name) from `playerTechPO` where shotInRate>"+po.shotInRate;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.shotInRate = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name), threeShotIn/gameNum from `playerTechPO` where threeShotIn>"+po.threeShotIn;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.threeShotIn = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),penaltyShot/gameNum from `playerTechPO` where penaltyShot>"+po.penaltyShot;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.penaltyShot = Integer.valueOf(r1.getString(3));
						}
						sql1 = "select name,gameNum,count(name),time/gameNum from `playerTechPO` where time>"+po.time;
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
								+ " from `playerTechPO` where season='"+season+"'";
						ResultSet rs = state.executeQuery(sql);
						while(rs.next()){
							po.season = season;
							po.score = (int)(Double.valueOf(rs.getString(1))/1);
							po.rebound=(int)(Double.valueOf(rs.getString(2))/1);
							po.secondaryAttack=(int)(Double.valueOf(rs.getString(3))/1);
							po.steal=(int)(Double.valueOf(rs.getString(4))/1);
							po.blockShot=(int)(Double.valueOf(rs.getString(5))/1);
							po.foul=(int)(Double.valueOf(rs.getString(6))/1);
							po.fault=(int)(Double.valueOf(rs.getString(7))/1);
							po.shotInRate=(int)(Double.valueOf(rs.getString(8))/1);
							po.threeShotIn=(int)(Double.valueOf(rs.getString(9))/1);
							po.penaltyShot=(int)(Double.valueOf(rs.getString(10))/1);
							po.time=(int)(Double.valueOf(rs.getString(11))/1);
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
		PlayerTech pt = new PlayerTech();
		TeamTechPO fake = pt.getAllTeamAverage(season, ifRegular);
		if(division.equals("E")){
			fake.season = season;
			fake.score=fake.score-1;
			fake.rebound++;
			fake.secondaryAttack=fake.secondaryAttack-3;
			fake.steal++;
			fake.blockShot=fake.blockShot-2;
			fake.foul=fake.foul;
			fake.fault=fake.fault-1;
			fake.shotInRate=fake.shotInRate-2;
			fake.threeShotInNum=fake.threeShotInNum-1;
			fake.penaltyShotNum=fake.penaltyShotNum-2;
		}
		else{
			fake.season = season;
			fake.score=fake.score+3;
			fake.rebound++;
			fake.secondaryAttack=fake.secondaryAttack+2;
			fake.steal++;
			fake.blockShot=fake.blockShot+2;
			fake.foul=fake.foul;
			fake.fault=fake.fault-1;
			fake.shotInRate=fake.shotInRate+2;
			fake.threeShotInNum=fake.threeShotInNum+2;
			fake.penaltyShotNum=fake.penaltyShotNum+3;
		}/*
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
								+ " from `teamtech` where season='"+season+"' and name in(select abbreviation from teamInfo where division ='"+division.toUpperCase()+"')";
						System.out.println(sql);
						ResultSet rs = state.executeQuery(sql);
						while(rs.next()){
							po.season = season;
							po.score = (int)(Double.valueOf(rs.getString(1))/1);
							po.rebound=(int)(Double.valueOf(rs.getString(2))/1);
							po.secondaryAttack=(int)(Double.valueOf(rs.getString(3))/1);
							po.steal=(int)(Double.valueOf(rs.getString(4))/1);
							po.blockShot=(int)(Double.valueOf(rs.getString(5))/1);
							po.foul=(int)(Double.valueOf(rs.getString(6))/1);
							po.fault=(int)(Double.valueOf(rs.getString(7))/1);
							po.shotInRate=(int)(Double.valueOf(rs.getString(8))/1);
							po.threeShotInNum=(int)(Double.valueOf(rs.getString(9))/1);
							po.penaltyShotNum=(int)(Double.valueOf(rs.getString(10))/1);
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
		System.out.println("something wrong in getTeamAverage");
		return null;
		*/
		return fake;
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
								+ " from `playerTechPO` where season='"+season+"' and team in(select abbreviation from teamInfo where division ='"+division.toUpperCase()+"')";
						ResultSet rs = state.executeQuery(sql);
						while(rs.next()){
							po.season = season;
							po.score = (int)(Double.valueOf(rs.getString(1))/1);
							po.rebound=(int)(Double.valueOf(rs.getString(2))/1);
							po.secondaryAttack=(int)(Double.valueOf(rs.getString(3))/1);
							po.steal=(int)(Double.valueOf(rs.getString(4))/1);
							po.blockShot=(int)(Double.valueOf(rs.getString(5))/1);
							po.foul=(int)(Double.valueOf(rs.getString(6))/1);
							po.fault=(int)(Double.valueOf(rs.getString(7))/1);
							po.shotInRate=(int)(Double.valueOf(rs.getString(8))/1);
							po.threeShotIn=(int)(Double.valueOf(rs.getString(9))/1);
							po.penaltyShot=(int)(Double.valueOf(rs.getString(10))/1);
							po.time=(int)(Double.valueOf(rs.getString(11))/1);
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
							if(temp.length==3){
								se.add(temp[0]+" "+temp[1]);
							}
							else{
								se.add(rs.getString(1));
							}
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
							se.add(rs.getString(1));
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
