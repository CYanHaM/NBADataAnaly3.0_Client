package bussinesslogic.playerbl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import data.AboutMatch;
import PO.MatchPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamTechPO;

public class PlayerTech implements StatsInfo{

	@Override
	public TeamTechPO getTeamTech(String teamname, String season, int ifRegular) {
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
						String sql = "SELECT * FROM `teamtech` where name = '"+teamname+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						while(rs.next()) {
							TeamTechPO po = new TeamTechPO();
							po.name = rs.getString("name");
							po.ifReagular = Integer.valueOf(rs.getString("ifReagular"));
							po.season =  rs.getString("season");
							po.gameNum = Integer.valueOf(rs.getString("gameNum"));
							po.shotInNum = Integer.valueOf(rs.getString("shotInNum"));
							po.shotNum = Integer.valueOf(rs.getString("shotNum"));
							po.threeShotInNum = Integer.valueOf(rs.getString("threeShotInNum"));
							po.threeShotNum =  Integer.valueOf(rs.getString("threeShotNum"));
							po.penaltyShotInNum =  Integer.valueOf(rs.getString("penaltyShotInNum"));
							po.penaltyShotNum=  Integer.valueOf(rs.getString("penaltyShotNum"));
							po.offensiveRebound=  Integer.valueOf(rs.getString("offensiveRebound"));
							po.defensiveRebound=  Integer.valueOf(rs.getString("defensiveRebound"));
							po.rebound=  Integer.valueOf(rs.getString("rebound"));;
							po.secondaryAttack=  Integer.valueOf(rs.getString("secondaryAttack"));;
							po.steal=  Integer.valueOf(rs.getString("steal"));
							po.blockShot=  Integer.valueOf(rs.getString("blockShot"));
							po.fault=  Integer.valueOf(rs.getString("fault"));
							po.foul=  Integer.valueOf(rs.getString("foul"));
							po.score=  Integer.valueOf(rs.getString("score"));
							po.shotInRate=  Integer.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=  Integer.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=  Integer.valueOf(rs.getString("penaltyShotInRate"));
							po.winningNum=  Integer.valueOf(rs.getString("winningNum"));
							po.winningRate=  Integer.valueOf(rs.getString("winningRate"));
							po.offensiveRound=  Integer.valueOf(rs.getString("offensiveRound"));
							po.offensiveEfficiency=  Integer.valueOf(rs.getString("offensiveEfficiency"));
							po.defensiveEfficiency=  Integer.valueOf(rs.getString("defensiveEfficiency"));
							po.reboundEfficiency=  Integer.valueOf(rs.getString("reboundEfficiency"));
							po.stealEfficiency=  Integer.valueOf(rs.getString("stealEfficiency"));
							po.secondaryAttackEfficiency=  Integer.valueOf(rs.getString("secondaryAttackEfficiency"));
							po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"));
							po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"));
							po.opponentOffensiveRound=  Integer.valueOf(rs.getString("opponentOffensiveRound"));
							po.opponentScore=  Integer.valueOf(rs.getString("opponentScore"));
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
	public ArrayList<MatchPO> getRecentMatch(String team,String season) {
		// TODO Auto-generated method stub
		AboutMatch am = new AboutMatch();
		ArrayList<MatchPO> list = am.allMatch();
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		int size = list.size();
		for(int i=0;i<size;i++){
			if(list.get(i).homeTeam.equals(team)||list.get(i).guestTeam.equals(team)){
				String temp1=season.trim().split("\\s+")[0];
				String temp2=list.get(i).season.trim().split("\\s+")[0];
				if(temp1.equals(temp2)){
					res.add(list.get(i));
				}
			}
		}
		Comparator<MatchPO> comparator = new Comparator<MatchPO>(){  	
			public int compare(MatchPO p2, MatchPO p1) {   
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date dt1 = df.parse(p2.date);
					Date dt2 = df.parse(p1.date);
				if (dt1.getTime() > dt2.getTime()) {
					return 1;
				} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
				} else {
					return 0;
				}
				} catch (Exception exception) {
				exception.printStackTrace();
				}
				System.out.println("wrong in compare");
				return -2;
				}
		}; 
		Collections.sort(res, comparator);
		ArrayList<MatchPO> result = new ArrayList<MatchPO>();
		for(int i=0;i<20;i++){
			result.add(res.get(i));
		}
		return result;
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
							po.shotInNum = Integer.valueOf(rs.getString("shotInNum"));
							po.shotNum = Integer.valueOf(rs.getString("shotNum"));
							po.threeShotInNum = Integer.valueOf(rs.getString("threeShotInNum"));
							po.threeShotNum =  Integer.valueOf(rs.getString("threeShotNum"));
							po.penaltyShotInNum =  Integer.valueOf(rs.getString("penaltyShotInNum"));
							po.penaltyShotNum=  Integer.valueOf(rs.getString("penaltyShotNum"));
							po.offensiveRebound=  Integer.valueOf(rs.getString("offensiveRebound"));
							po.defensiveRebound=  Integer.valueOf(rs.getString("defensiveRebound"));
							po.rebound=  Integer.valueOf(rs.getString("rebound"));;
							po.secondaryAttack=  Integer.valueOf(rs.getString("secondaryAttack"));;
							po.steal=  Integer.valueOf(rs.getString("steal"));
							po.blockShot=  Integer.valueOf(rs.getString("blockShot"));
							po.fault=  Integer.valueOf(rs.getString("fault"));
							po.foul=  Integer.valueOf(rs.getString("foul"));
							po.score=  Integer.valueOf(rs.getString("score"));
							po.shotInRate=  Integer.valueOf(rs.getString("shotInRate"));
							po.threeShotInRate=  Integer.valueOf(rs.getString("threeShotInRate"));
							po.penaltyShotInRate=  Integer.valueOf(rs.getString("penaltyShotInRate"));
							po.winningNum=  Integer.valueOf(rs.getString("winningNum"));
							po.winningRate=  Integer.valueOf(rs.getString("winningRate"));
							po.offensiveRound=  Integer.valueOf(rs.getString("offensiveRound"));
							po.offensiveEfficiency=  Integer.valueOf(rs.getString("offensiveEfficiency"));
							po.defensiveEfficiency=  Integer.valueOf(rs.getString("defensiveEfficiency"));
							po.reboundEfficiency=  Integer.valueOf(rs.getString("reboundEfficiency"));
							po.stealEfficiency=  Integer.valueOf(rs.getString("stealEfficiency"));
							po.secondaryAttackEfficiency=  Integer.valueOf(rs.getString("secondaryAttackEfficiency"));
							po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"));
							po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"));
							po.opponentOffensiveRound=  Integer.valueOf(rs.getString("opponentOffensiveRound"));
							po.opponentScore=  Integer.valueOf(rs.getString("opponentScore"));
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
						Statement statement = conn.createStatement();
						String sql = "SELECT * FROM `teamtech` where name = '"+teamname+"' and season = '"+season+"'";						
						ResultSet rs = statement.executeQuery(sql);
						TeamTechPO po = new TeamTechPO();
						while(rs.next()) {
							po.name=rs.getString("name");
							po.season=rs.getString("season");
							po.gameNum=Integer.valueOf(rs.getString("gameNum"));
							po.rebound=Integer.valueOf(rs.getString("rebound"))/po.gameNum;
							po.secondaryAttack=Integer.valueOf(rs.getString("assist"))/po.gameNum;
							po.steal=Integer.valueOf(rs.getString("steal"))/po.gameNum;
							po.blockShot=Integer.valueOf(rs.getString("blockShot"))/po.gameNum;
							po.fault=Integer.valueOf(rs.getString("fault"))/po.gameNum;
							po.foul=Integer.valueOf(rs.getString("foul"))/po.gameNum;
							po.score=Integer.valueOf(rs.getString("score"))/po.gameNum;
							po.threeShotInNum=Integer.valueOf(rs.getString("threeShotIn"))/po.gameNum;
							po.penaltyShotNum=Integer.valueOf(rs.getString("penaltyShot"))/po.gameNum;
							po.shotInRate=Double.valueOf(rs.getString("shotInRate"));
						}
						
						Statement state = conn.createStatement();
						String sql1 = "select distict name,gameNum,count(name) from `teamtech` where score/gameNum>"+po.score/po.gameNum;
						ResultSet r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.score = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where rebound/gameNum>"+po.rebound/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.rebound = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where secondaryAttack/gameNum>"+po.secondaryAttack/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.secondaryAttack = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where steal/gameNum>"+po.steal/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.steal = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where blockShot/gameNum>"+po.blockShot/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.blockShot = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where foul/gameNum>"+po.foul/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.foul = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where fault/gameNum>"+po.fault/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.fault = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where shotInRate>"+po.shotInRate/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.shotInRate = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where threeShotInNum/gameNum>"+po.threeShotInNum/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.threeShotInNum = Integer.valueOf(r1.getString(2));
						}
						sql1 = "select distict name,gameNum,count(name) from `teamtech` where penaltyShotNum/gameNum>"+po.penaltyShotNum/po.gameNum;
						r1 = state.executeQuery(sql1);
						while(r1.next()){
							po.penaltyShotNum = Integer.valueOf(r1.getString(2));
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
						TeamTechPO po = new TeamTechPO();
						//锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较革拷帽锟斤拷锟斤拷失锟斤拷 投锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟较筹拷时锟斤拷
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

	//锟斤拷锟斤拷锟斤拷 锟斤拷员锟斤拷锟斤拷锟斤拷锟斤拷 锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较革拷帽锟斤拷锟斤拷失锟斤拷投锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较筹拷时锟斤拷
	@Override
	public PlayerTechPO getPlayerRank(String player, String season,
			int ifRegular) {
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
						// statement锟斤拷锟斤拷执锟斤拷SQL锟斤拷锟�
						Statement statement = conn.createStatement();
						// 要执锟叫碉拷SQL锟斤拷锟�
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
						//锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较革拷帽锟斤拷锟斤拷失锟斤拷 投锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟较筹拷时锟斤拷
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
						TeamTechPO po = new TeamTechPO();
						//锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较革拷帽锟斤拷锟斤拷失锟斤拷 投锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟较筹拷时锟斤拷
						Statement state = conn.createStatement();
						String sql = "select avg(score)as a,avg(rebound) as b, avg(secondaryAttack) as c, avg(steal) as d, avg(blockShot) as e,"
								+ " avg(foul)  as f, avg(fault) as g, avg(shotInRate) as h, avg(threeShotInNum) as i,avg(penaltyShotNum) as j"
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
						String sql = "select avg(score)as a,avg(rebound) as b, avg(assist) as c, avg(steal) as d, avg(block) as e,"
								+ " avg(foul)  as f, avg(fault) as g, avg(shotInRate) as h, avg(threeShotIn) as i,avg(penaltyShot) as j, avg(time) as k"
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
						// statement锟斤拷锟斤拷执锟斤拷SQL锟斤拷锟�
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
						// statement锟斤拷锟斤拷执锟斤拷SQL锟斤拷锟�
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
