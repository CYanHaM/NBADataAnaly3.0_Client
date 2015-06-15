package data.playertech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.ScreeningConditionVO;
import dataservice.playertechdataservice.FindDataService;

public class Find implements FindDataService {
	@Override
	public ArrayList<PlayerTechMPO> findHotPlayerToday(String date,
			String keyword) {
		ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		//URLָ��Ҫ���ʵ����ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ������������
			Class.forName(driver);
			// �������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
			String sql = "SELECT * FROM `playerTechMPO` where date='"+date+"' order by "+keyword+" desc";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
						PlayerTechMPO mpo = new PlayerTechMPO();
						//daibuchong----------------------------------------------------------
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
	public ArrayList<PlayerTechPO> sift(ArrayList<ScreeningConditionVO> list,String season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechPO> findPlayerByletter(char letter,String season) {
		// TODO Auto-generated method stub
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
				String driver = "com.mysql.jdbc.Driver";
			//URLָ��Ҫ���ʵ����ݿ���nba
				String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
				// MySQL����ʱ���û���
				String user = "root";
				// Java����MySQL����ʱ������
				String password = "cyanham";
				try {
					// ������������
					Class.forName(driver);
				// �������ݿ�
					Connection conn = DriverManager.getConnection(url, user, password);
					if(!conn.isClosed()){
						System.out.println("Succeeded connecting to the Database!");
					}
					// statement����ִ��SQL���
					Statement statement = conn.createStatement();
					// Ҫִ�е�SQL���
					String sql = "select * from playerTechPO where season='"+season+"'";
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) {
						if(rs.getString("name").charAt(0)==letter){
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
							list.add(po);
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
		return null;
	}

	@Override
	public PlayerTechPO findPlayerTechByName(String name,String season) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
	//URLָ��Ҫ���ʵ����ݿ���nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL����ʱ���û���
		String user = "root";
		// Java����MySQL����ʱ������
		String password = "cyanham";
		try {
			// ������������
			Class.forName(driver);
		// �������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement����ִ��SQL���
			Statement statement = conn.createStatement();
			// Ҫִ�е�SQL���
			String sql = "select * from playerTechPO where name='"+name+"' and season='"+season+"'";
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
		};
		System.out.println("wrong:SeasonPlayerStatsByName");
 		return null;
	}

}
