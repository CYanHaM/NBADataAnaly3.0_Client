package data.teamtech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.TeamTechPO;
import dataservice.TeamTechDataService;
public class TeamTechData implements TeamTechDataService {

	public static void main(String[] args){
		TeamTechData ttd = new TeamTechData();
		ArrayList<TeamTechPO> li = ttd.findSeasonHotTeam("rebound", "2014-15 Regular");
		System.out.println(li.size());
	}
	
	@Override
	public ArrayList<TeamTechPO> list(String season){
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
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
			if(season.trim().split("\\s+")[1].equals("Regular")){
				season = season+" season";
			}
			String sql = "SELECT * FROM `teamtech` where season='"+season+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.shotInRate=  Double.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Double.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Double.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Double.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Double.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Double.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Double.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Double.valueOf(rs.getString("secondaryAttackEfficiency"));
				po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"));
				po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"));
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
		};
		System.out.println("wrong: teamtech.list");
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> Descend(String type,String season) {
		// TODO Auto-generated method stub
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
		String driver = "com.mysql.jdbc.Driver";
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
			switch(type){
			case"shotInNumave":
				type = "shotInNum/gameNum";
				break;
			case "threeShotInNumave":
				type = "threeShotInNum/gameNum";
				break;
			case "shotNumave":
				type = "shotNum/gameNum";
				break;
			case "threeShotNumave":
				type = "threeShotNum/gameNum";
				break;
			case "penaltyShotInNumave":
				type = "penaltyShotInNumv/gameNum";
				break;
			case "penaltyShotNumave":
				type = "spenaltyShotNum/gameNum";
				break;
			case "offensiveReboundave":
				type = "offensiveRebound/gameNum";
				break;
			case "defensiveReboundave":
				type = "defensiveRebound/gameNum";
				break;
			case "reboundave":
				type = "rebound/gameNum";
				break;
			case "secondaryAttackave":
				type = "secondaryAttack/gameNum";
				break;
			case "stealave":
				type = "steal/gameNum";
				break;
			case "blockShotave":
				type = "blockShot/gameNum";
				break;
			case "faultave":
				type = "fault/gameNum";
				break;
			case "foulave":
				type = "foul/gameNum";
				break;
			case "scoreave":
				type = "score/gameNum";
				break;
			}
			String sql = "SELECT * FROM `teamtech` ";
			if(type.equals("season")){
				sql=sql+"order by season desc";
			}
			else{
				if(season.trim().split("\\s+")[1].equals("Regular")){
					season = season+" season";
				}
				sql += " where season='"+season+"' order by "+type+" desc";
			}
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.shotInRate=  Double.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Double.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Double.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Double.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Double.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Double.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Double.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Double.valueOf(rs.getString("secondaryAttackEfficiency"));
				po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"));
				po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"));
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
		};
		System.out.println("wrong: teamTech,desc");
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> Ascend(String type,String season) {
		// TODO Auto-generated method stub
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
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
			switch(type){
			case"shotInNumave":
				type = "shotInNum/gameNum";
				break;
			case "threeShotInNumave":
				type = "threeShotInNum/gameNum";
				break;
			case "shotNumave":
				type = "shotNum/gameNum";
				break;
			case "threeShotNumave":
				type = "threeShotNum/gameNum";
				break;
			case "penaltyShotInNumave":
				type = "penaltyShotInNumv/gameNum";
				break;
			case "penaltyShotNumave":
				type = "spenaltyShotNum/gameNum";
				break;
			case "offensiveReboundave":
				type = "offensiveRebound/gameNum";
				break;
			case "defensiveReboundave":
				type = "defensiveRebound/gameNum";
				break;
			case "reboundave":
				type = "rebound/gameNum";
				break;
			case "secondaryAttackave":
				type = "secondaryAttack/gameNum";
				break;
			case "stealave":
				type = "steal/gameNum";
				break;
			case "blockShotave":
				type = "blockShot/gameNum";
				break;
			case "faultave":
				type = "fault/gameNum";
				break;
			case "foulave":
				type = "foul/gameNum";
				break;
			case "scoreave":
				type = "score/gameNum";
				break;
			}
			String sql = "SELECT * FROM `teamtech` ";
			if(type.equals("season")){
				sql=sql+"order by season";
			}
			else{
				if(season.trim().split("\\s+")[1].equals("Regular")){
					season = season+" season";
				}
				sql += " where season='"+season+"' order by "+type;
			}
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.shotInRate=  Double.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Double.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Double.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Double.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Double.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Double.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Double.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Double.valueOf(rs.getString("secondaryAttackEfficiency"));
				po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"));
				po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"));
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
		};
		System.out.println("wrong: teamTech,desc");
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> findSeasonHotTeam(String type,
			String season) {
		// TODO Auto-generated method stub
		ArrayList<TeamTechPO> list = Descend(type,season);
		ArrayList<TeamTechPO> res = new ArrayList<TeamTechPO>();
		for(int i=0;i<5;i++){
			res.add(list.get(i));
		}
		return res;
	}

	@Override
	public ArrayList<TeamTechPO> all() {
		// TODO Auto-generated method stub
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
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
			String sql = "SELECT * FROM `teamtech`";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.shotInRate=  Double.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Double.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Double.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Double.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Double.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Double.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Double.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Double.valueOf(rs.getString("secondaryAttackEfficiency"));
				po.opponentDefensiveRebound=  Integer.valueOf(rs.getString("opponentDefensiveRebound"));
				po.opponentOffensiveRebound=  Integer.valueOf(rs.getString("opponentOffensiveRebound"));
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
		};
		System.out.println("wrong: teamtech.all");
		return null;
	}

}
