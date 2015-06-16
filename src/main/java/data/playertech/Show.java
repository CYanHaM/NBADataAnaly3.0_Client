package data.playertech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.PlayerTechPO;
import dataservice.playertechdataservice.ShowDataService;

public class Show implements ShowDataService{

	@Override
	public ArrayList<PlayerTechPO> showSeasonPlayerData(String season) {
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
			String sql = "SELECT * FROM `playerTechPO` where season='"+season+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.blockShot=Integer.valueOf(rs.getString("block"));
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
		};
		System.out.println("wrong: show playerTech");
		return null;
	}

	@Override
	public PlayerTechPO showKeyData(String name, String team) {
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
			String sql = "SELECT * FROM `playerTechPO` where name='"+name+"' and team='"+team+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.blockShot=Integer.valueOf(rs.getString("block"));
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
		};
		System.out.println("wrong: showKeyData");
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		showSeasonPlayerData("2014-15");
	}

	@Override
	public ArrayList<PlayerTechPO> ascend(String type,String season) {
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
			switch(type){
			case "reboundave":
				type = "rebound/gameNum";
				break;
			case "secondaryattackave":
				type = "assist/gameNum";
				break;
			case "timeave":
				type = "time/gameNum";
				break;
			case "offensivenumave":
				type = "offensiveNum/gameNum";
				break;
			case "defensivenumave":
				type = "defensiveNum/gameNum";
				break;
			case "blockshotave":
				type = "block/gameNum";
				break;
			case "faultave":
				type = "fault/gameNum";
				break;
			case "foulave":
				type = "foul/gameNum";
				break;
			case "stealave":
				type = "steal/gameNum";
				break;
			case "scoreave":
				type = "score/gameNum";
				break;
			}
			String sql = "SELECT * FROM `playerTechPO` where season='"+season+"' order by "+type;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.blockShot=Integer.valueOf(rs.getString("block"));
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
		};
		System.out.println("wrong: show playerTech");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> descend(String type,String season) {
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
			switch(type){
			case "reboundave":
				type = "rebound/gameNum";
				break;
			case "secondaryattackave":
				type = "assist/gameNum";
				break;
			case "timeave":
				type = "time/gameNum";
				break;
			case "offensivenumave":
				type = "offensiveNum/gameNum";
				break;
			case "defensivenumave":
				type = "defensiveNum/gameNum";
				break;
			case "blockshotave":
				type = "block/gameNum";
				break;
			case "faultave":
				type = "fault/gameNum";
				break;
			case "foulave":
				type = "foul/gameNum";
				break;
			case "stealave":
				type = "steal/gameNum";
				break;
			case "scoreave":
				type = "score/gameNum";
				break;
			}
			String sql = "SELECT * FROM `playerTechPO` where season='"+season+"' order by "+type+"desc";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.blockShot=Integer.valueOf(rs.getString("block"));
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
		};
		System.out.println("wrong: show playerTech");
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> all() {
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
			String sql = "SELECT * FROM `playerTechPO`";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
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
				po.blockShot=Integer.valueOf(rs.getString("block"));
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
		};
		System.out.println("wrong: show playerTech");
		return null;
	}

}
