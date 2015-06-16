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

	@Override
	public ArrayList<TeamTechPO> list(String season){
		ArrayList<TeamTechPO> list = new ArrayList<TeamTechPO>();
		String driver = "com.mysql.jdbc.Driver";
		//URLָ��Ҫ���ʵ���ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ���������
			Class.forName(driver);
			// ������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
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
				po.shotInRate=  Integer.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Integer.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Integer.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Integer.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Integer.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Integer.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Integer.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Integer.valueOf(rs.getString("secondaryAttackEfficiency"));
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
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ���������
			Class.forName(driver);
			// ������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
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
			String sql = "SELECT * FROM `teamtech` where season='"+season+"' order by "+type+"desc";
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
				po.shotInRate=  Integer.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Integer.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Integer.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Integer.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Integer.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Integer.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Integer.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Integer.valueOf(rs.getString("secondaryAttackEfficiency"));
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
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ���������
			Class.forName(driver);
			// ������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
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
			String sql = "SELECT * FROM `teamtech` where season='"+season+"' order by "+type;
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
				po.shotInRate=  Integer.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Integer.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Integer.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Integer.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Integer.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Integer.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Integer.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Integer.valueOf(rs.getString("secondaryAttackEfficiency"));
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
		//URLָ��Ҫ���ʵ���ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ���������
			Class.forName(driver);
			// ������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
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
				po.shotInRate=  Integer.valueOf(rs.getString("shotInRate"));
				po.threeShotInRate=  Integer.valueOf(rs.getString("threeShotInRate"));
				po.penaltyShotInRate=  Integer.valueOf(rs.getString("penaltyShotInRate"));
				po.offensiveEfficiency=  Integer.valueOf(rs.getString("offensiveEfficiency"));
				po.defensiveEfficiency=  Integer.valueOf(rs.getString("defensiveEfficiency"));
				po.reboundEfficiency=  Integer.valueOf(rs.getString("reboundEfficiency"));
				po.stealEfficiency=  Integer.valueOf(rs.getString("stealEfficiency"));
				po.secondaryAttackEfficiency=  Integer.valueOf(rs.getString("secondaryAttackEfficiency"));
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
