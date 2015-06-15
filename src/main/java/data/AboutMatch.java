package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataservice.MatchDataService;
import PO.MatchPO;
import PO.PlayerTechMPO;

public class AboutMatch {
	public static void main(String[] args){
		AboutMatch am = new AboutMatch();
		ArrayList<MatchPO> list = am.getMatch();
		MatchDataService mds = new MatchData();
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		for(int i=0;i<list.size();i++){
			MatchPO po = mds.completeMatch(list.get(i));
			res.add(po);
			ArrayList<PlayerTechMPO> ml = po.playerStatistic;
			for(int j=0;j<ml.size();j++){
				am.modify(ml.get(j));//向playerTechMPO表中增加数据
			}
		}
	}
	public ArrayList<MatchPO> allMatch(){
		AboutMatch am = new AboutMatch();
		ArrayList<MatchPO> list = am.getMatch();
		MatchDataService mds = new MatchData();
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		for(int i=0;i<list.size();i++){
			MatchPO po = mds.completeMatch(list.get(i));
			res.add(po);
		}
		return res;
	}
	public ArrayList<MatchPO> getMatch(){
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		Tools t = new Tools();
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
			Statement statement1 = conn.createStatement();
			Statement statement2 = conn.createStatement();
			// 要执行的SQL语句
			String sql1 = "SELECT * FROM `match`";
			ResultSet rs1 = statement1.executeQuery(sql1);
			while(rs1.next()) {
				MatchPO mpo = new MatchPO();
				mpo.ifRegular=0;
				String regular = new String(rs1.getString("type").getBytes("ISO-8859-1"),"utf-8");
				if(regular.equals("Regular")){
					mpo.ifRegular=1;
				}
				mpo.ifEnd=1;
				mpo.season=new String(rs1.getString("season").getBytes("ISO-8859-1"),"utf-8")+" "+new String(rs1.getString("type").getBytes("ISO-8859-1"),"utf-8");
				mpo.date=new String(rs1.getString("date").getBytes("ISO-8859-1"),"utf-8");
				mpo.homeTeam=new String(rs1.getString("guest").getBytes("ISO-8859-1"),"utf-8");
				mpo.guestTeam=new String(rs1.getString("host").getBytes("ISO-8859-1"),"utf-8");
				mpo.score=new String(rs1.getString("gueTotal").getBytes("ISO-8859-1"),"utf-8")+"-"+new String(rs1.getString("hosTotal").getBytes("ISO-8859-1"),"utf-8");
				mpo.score1=new String(rs1.getString("gue1").getBytes("ISO-8859-1"),"utf-8")+"-"+new String(rs1.getString("hos1").getBytes("ISO-8859-1"),"utf-8");
				mpo.score2=new String(rs1.getString("gue2").getBytes("ISO-8859-1"),"utf-8")+"-"+new String(rs1.getString("hos2").getBytes("ISO-8859-1"),"utf-8");
				mpo.score3=new String(rs1.getString("gue3").getBytes("ISO-8859-1"),"utf-8")+"-"+new String(rs1.getString("hos3").getBytes("ISO-8859-1"),"utf-8");
				mpo.score4=new String(rs1.getString("gue4").getBytes("ISO-8859-1"),"utf-8")+"-"+new String(rs1.getString("hos4").getBytes("ISO-8859-1"),"utf-8");
				int hosExtra = Integer.valueOf(rs1.getString("guestOT1"))+Integer.valueOf(rs1.getString("guestOT2"))+
						Integer.valueOf(rs1.getString("guestOT3"));
				int gueExtra = Integer.valueOf(rs1.getString("hostOT1"))+Integer.valueOf(rs1.getString("hostOT2"))+
						Integer.valueOf(rs1.getString("hostOT3"));
				mpo.scoreExtra=String.valueOf(hosExtra)+gueExtra;
				mpo.playerStatistic = new ArrayList<PlayerTechMPO>();
				String sql2 = "SELECT * FROM detail where (team='"+mpo.homeTeam+"' or team='"+mpo.guestTeam+"') and date='"+mpo.date+"' and season='"+mpo.season+"' and type='"+regular+"'";
				ResultSet rs2 = statement2.executeQuery(sql2);
				int index=0;
				while(rs2.next()){
					PlayerTechMPO ptpo = new PlayerTechMPO();
					index++;
					ptpo.name=new String(rs2.getString("name").getBytes("ISO-8859-1"),"utf-8");
					ptpo.team=new String(rs2.getString("team").getBytes("ISO-8859-1"),"utf-8");
					ptpo.season=mpo.season;
					//ptpo.division 
					ptpo.date=new String(rs2.getString("date").getBytes("ISO-8859-1"),"utf-8");
					ptpo.position=t.getPos(new String(rs2.getString("pos").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.time=Integer.valueOf(new String(rs2.getString("MIN").getBytes("ISO-8859-1"),"utf-8"));
					String FGM_A =new String(rs2.getString("FGM-A").getBytes("ISO-8859-1"),"utf-8");
					if(FGM_A.equals("0")){
						ptpo.shotIn=0;
						ptpo.shot=0;
					}
					else{
						String[] temp = FGM_A.split("-");
						ptpo.shotIn=Integer.valueOf(temp[0]);
						ptpo.shot=Integer.valueOf(temp[1]);
					}
					String threePM_A =new String(rs2.getString("3PM-A").getBytes("ISO-8859-1"),"utf-8");
					if(threePM_A.equals("0")){
						ptpo.threeShotIn=0;
						ptpo.threeShot=0;
					}
					else{
						String[] temp = FGM_A.split("-");
						ptpo.threeShotIn=Integer.valueOf(temp[0]);
						ptpo.threeShot=Integer.valueOf(temp[1]);
					}
					String FTM_A = new String(rs2.getString("FTM-A").getBytes("ISO-8859-1"),"utf-8");
					if(FTM_A.equals("0")){
						ptpo.penaltyShotIn=0;
						ptpo.penaltyShot=0;
					}
					else{
						String[] temp = FGM_A.split("-");
						ptpo.penaltyShotIn=Integer.valueOf(temp[0]);
						ptpo.penaltyShot=Integer.valueOf(temp[1]);
					}
					ptpo.offensiveRebound=Integer.valueOf(new String(rs2.getString("OREB").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.defensiveRebound=Integer.valueOf(new String(rs2.getString("DREB").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.rebound=Integer.valueOf(new String(rs2.getString("REB").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.secondaryAttack=Integer.valueOf(new String(rs2.getString("AST").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.steal=Integer.valueOf(new String(rs2.getString("STL").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.blockShot=Integer.valueOf(new String(rs2.getString("BLK").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.fault=Integer.valueOf(new String(rs2.getString("TO").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.foul=Integer.valueOf(new String(rs2.getString("PF").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.score=Integer.valueOf(new String(rs2.getString("PTS").getBytes("ISO-8859-1"),"utf-8"));
					ptpo.ifFirstLineUp=0;
					if(index<=5){
						ptpo.ifFirstLineUp=1;
					}
					mpo.playerStatistic.add(ptpo);
				}
				rs2.close();
				res.add(mpo);
			}
			rs1.close();
			conn.close();
			return res;
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
		System.out.println("wrong:about Match");
		return null;
	}
	
	public void modify(PlayerTechMPO po){
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
			String sql = "insert into `playerTechMPO` values('"+po.name+"','"+po.team+"','"+po.division+"','"+po.date+"','"+po.position+"','"+po.time+"','"+po.shotIn+"','"+po.shot+"','"+po.threeShotIn+"','"+po.threeShot+"','"+po.penaltyShotIn+"','"+
					po.penaltyShot+"','"+po.offensiveRebound+"','"+po.defensiveRebound+"','"+po.rebound+"','"+po.secondaryAttack+"','"+po.steal+"','"+po.blockShot+"','"+po.fault+"','"+po.foul+"','"+po.score+"','"+
					po.ifFirstLineUp+"','"+po.ifParticipate+"','"+po.teamAllTime+"','"+po.teamOffensiveRebound+"','"+po.teamDefensiveRebound+"','"+po.opponentOffensiveRebound+"','"+po.opponentDefensiveRebound+"','"+po.teamShotIn+"','"+po.opponentTwoShot+"','"+po.teamShot+"','"+po.teamPenaltyShot+"','"+
					po.teamFault+"','"+po.ifDouble+"')";
			statement.executeUpdate(sql);
			conn.close();
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
	}
}
