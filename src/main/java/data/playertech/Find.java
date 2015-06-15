package data.playertech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import PO.PlayerPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.ScreeningConditionVO;
import data.info.PlayerInfo;
import dataservice.playertechdataservice.FindDataService;

public class Find implements FindDataService {
	
	@Override
	public ArrayList<PlayerTechMPO> findHotPlayerToday(String date,
			String keyword) {
		ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
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
			String sql = "SELECT * FROM `playerTechMPO` where date='"+date+"' order by "+keyword+" desc";
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
		};
		System.out.println("wrong: today hot player");
		return null;
	}
	
	@Override
	public ArrayList<PlayerTechPO> sift(ArrayList<ScreeningConditionVO> list,String season) {
		// TODO Auto-generated method stub
		Show sh = new Show();
		ArrayList<PlayerTechPO> all = sh.showSeasonPlayerData(season);
		PlayerInfo pi = new PlayerInfo();
		ArrayList<PlayerPO> info = pi.findAll(0);
		Iterator<PlayerTechPO> it = all.iterator();
		while(it.hasNext()){
			PlayerTechPO pt = it.next();
			String position = "";
			for(int i=0;i<info.size();i++){
				if(info.get(i).name.equals(pt.name))
					position = info.get(i).position;
			}
			if((position==null)||(!position.equals(list.get(0).position))){
				it.remove();
				continue;
			}
			
			if(!pt.division.equals(list.get(0).division)){
				System.out.println(pt.division+" "+list.get(0).division);
				it.remove(); 
			}
		}
		Iterator<PlayerTechPO> it2 = all.iterator();
		while(it2.hasNext()){
			PlayerTechPO pt = it2.next();
			for(int j=0;j<list.size();j++){
				ScreeningConditionVO vo = list.get(j); 
				if(vo.condition.equals("score")){
					if(vo.comparison.equals(">=")){
						if(pt.score<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.score>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("blockshot")){
					if(vo.comparison.equals(">=")){
						if(pt.blockShot<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.blockShot>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("steal")){
					if(vo.comparison.equals(">=")){
						if(pt.steal<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.steal>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("secondaryattack")){
					if(vo.comparison.equals(">=")){
						if(pt.secondaryAttack<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.secondaryAttack>vo.number){
							it2.remove();
							break;
						}
					}
				}else if(vo.condition.equals("rebound")){
					if(vo.comparison.equals(">=")){
						if(pt.rebound<vo.number){
							it2.remove();
							break;
						}
					}else{
						if(pt.rebound>vo.number){
							it2.remove();
							break;
						}
					}
				}else{
					System.out.println("wrong condition");
				}
			}
		}
		
		return all;
	}

	@Override
	public ArrayList<PlayerTechPO> findPlayerByletter(char letter,String season) {
		// TODO Auto-generated method stub
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
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
