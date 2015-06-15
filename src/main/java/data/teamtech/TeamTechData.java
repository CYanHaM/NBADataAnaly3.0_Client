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
		};
		System.out.println("wrong: teamtech.list");
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> Descend(String DataType,String season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> Ascend(String DataType,String season) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> findSeasonHotTeam(String DataType,
			String season) {
		// TODO Auto-generated method stub
		return null;
	}

}
