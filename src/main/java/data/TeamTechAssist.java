package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PO.MatchPO;
import PO.TeamTechPO;
public class TeamTechAssist {
	
	public static void main(String args[]){
		TeamTechAssist tta = new TeamTechAssist();
		tta.transferDetail();
	}
	
	public void transferDetail(){
		Tools to = new Tools();
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

			// 要执行的SQL语句
			

			
			String sql1 = "SELECT * FROM team_comparison";
			ResultSet rs1 = statement1.executeQuery(sql1);
			while(rs1.next()) {
				TeamTechPO ttpo = new TeamTechPO();
				ttpo.ifReagular = 0;
				String regular = new String(rs1.getString("year").getBytes("ISO-8859-1"),"utf-8");
				String[] ifre = regular.split(" ");
				String reg = ifre[0];
				String gular = ifre[1];
				if(gular.equals("Regular")){
					ttpo.ifReagular = 1;
				}
				
				String name = new String(rs1.getString("team").getBytes("ISO-8859-1"),"utf-8");
     		    ttpo.name = nameTrans(name);
				ttpo.season = reg;
				ttpo.gameNum = 82;
				ttpo.score = Integer.valueOf(new String(rs1.getString("OWN_Poi").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.shotInRate = Double.valueOf(new String(rs1.getString("OWN_FG").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.threeShotInRate = Double.valueOf(new String(rs1.getString("OWN_3P").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.penaltyShotInRate = Double.valueOf(new String(rs1.getString("FT").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.opponentScore = Integer.valueOf(new String(rs1.getString("OPP_Poi").getBytes("ISO-8859-1"),"utf-8"));
				
				Statement statement2 = conn.createStatement();
				String sql2 = "SELECT * FROM high where (team='"+name+"') and year='"+regular+"'";
				System.out.println(sql2);
				ResultSet rs2 = statement2.executeQuery(sql2);
				while(rs2.next()){
					ttpo.secondaryAttackEfficiency = Double.valueOf(new String(rs2.getString("ast")));
					ttpo.reboundEfficiency = Double.valueOf(new String(rs2.getString("rebr")));
					ttpo.offensiveEfficiency = Double.valueOf(new String(rs2.getString("off_EFF")));
					ttpo.defensiveEfficiency =Double.valueOf( new String(rs2.getString("def_EFF")));
				}
				rs2.close();
				Statement statement3 = conn.createStatement();
				String sql3 = "SELECT * FROM rebound where (team='"+name+"') and year='"+regular+"'";
				ResultSet rs3 = statement3.executeQuery(sql3);
				while(rs3.next()){
					ttpo.offensiveRebound = Integer.valueOf(new String(rs3.getString("OffenOWN")));
					ttpo.defensiveRebound = Integer.valueOf(new String(rs3.getString("DefenOWN")));
					ttpo.opponentOffensiveRebound = Integer.valueOf(new String(rs3.getString("OffenOPP")));
					ttpo.opponentDefensiveRebound = Integer.valueOf(new String(rs3.getString("DefenOPP")));
					ttpo.rebound = Integer.valueOf(new String(rs3.getString("TOTOWN")));
				}
				rs3.close();
				Statement statement4 = conn.createStatement();
				String sql4 = "SELECT * FROM offense where (team='"+name+"') and year='"+regular+"'";
				ResultSet rs4 = statement4.executeQuery(sql4);
				while(rs4.next()){
					ttpo.shotNum = Integer.valueOf(new String(rs4.getString("FGA")));
					ttpo.shotInNum = Integer.valueOf(new String(rs4.getString("FGM")));
					ttpo.threeShotNum = Integer.valueOf(new String(rs4.getString("TPA")));
					ttpo.threeShotInNum = Integer.valueOf(new String(rs4.getString("TPM")));
					ttpo.penaltyShotNum = Integer.valueOf(new String(rs4.getString("FTA")));
					ttpo.penaltyShotInNum = Integer.valueOf(new String(rs4.getString("FTM")));				
				}
				rs4.close();
				Statement statement5 = conn.createStatement();
				String sql5 = "SELECT * FROM miscell where (team='"+name+"') and year='"+regular+"'";
				ResultSet rs5 = statement5.executeQuery(sql5);
				while(rs5.next()){
					ttpo.blockShot = Integer.valueOf(new String(rs5.getString("BLO_OWN")));
					ttpo.steal = Integer.valueOf(new String(rs5.getString("STE_OWN")));
					ttpo.secondaryAttack = Integer.valueOf(new String(rs5.getString("ASS_OWN")));
					ttpo.foul = Integer.valueOf(new String(rs5.getString("TUR_TECH")));
					ttpo.fault = Integer.valueOf(new String(rs5.getString("TUR_OWN")));
				}
				
				AboutMatch am = new AboutMatch();
				ArrayList<MatchPO> list = am.allMatch();
				for(MatchPO m:list){
					if(m.season.equals(regular)){
					if(m.homeTeam.equals(ttpo.name)){
						if(m.ifHomeTeamWin==1){
							ttpo.winningNum++;
						}
						ttpo.offensiveRound = m.homeTeamOffensiveRound;
					}else if(m.guestTeam.equals(ttpo.name)){
						if(m.ifGuestTeamWin==1){
							ttpo.winningNum++;
						}
						ttpo.offensiveRound = m.guestTeamOffensiveRound;
					}}
				}
				ttpo.winningRate = ttpo.winningNum/82;	
				rs5.close();
				Statement state = conn.createStatement();
				String str = "replace into `teamtech` values('"+ttpo.ifReagular+"','"+ttpo.name+"','"+ttpo.season+"','"+ttpo.gameNum+"','"+ttpo.shotInNum+"','"+ttpo.shotNum+"','"+ttpo.threeShotInNum+"','"+ttpo.threeShotNum+"','"+ttpo.penaltyShotInNum+"','"+ttpo.penaltyShotNum+"','"+ttpo.offensiveRebound+"','"+ttpo.defensiveRebound+"','"+ttpo.rebound+"','"+ttpo.secondaryAttack+"','"+ttpo.steal+"','"+ttpo.blockShot+"','"+ttpo.fault+"','"+ttpo.foul+"','"+ttpo.score+"','"+ttpo.shotInRate+"','"+
						ttpo.threeShotInRate+"','"+ttpo.penaltyShotInRate+"','"+ttpo.winningRate+"','"+ttpo.winningNum+"','"+ttpo.offensiveRound+"','"+ttpo.offensiveEfficiency+"','"+ttpo.defensiveEfficiency+"','"+ttpo.reboundEfficiency+"','"+ttpo.stealEfficiency+"','"+ttpo.secondaryAttackEfficiency+"','"+ttpo.opponentDefensiveRebound+"','"+ttpo.opponentOffensiveRebound+"','"+ttpo.opponentOffensiveRound+"','"+ttpo.opponentScore+"'";
				System.out.println(str);
				state.executeUpdate(str);
			}
			rs1.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		};
		System.out.println("wrong:about Match");
	}
	
	public String nameTrans(String totrans){
		Map m = new HashMap();
		m.put("Atlanta", "ATL");
		m.put("Boston", "BOS");
		m.put("Brooklyn", "BNK");
		m.put("Charlotte", "CHA");
		m.put("Chicago", "CHI");
		m.put("Cleveland", "CLE");
		m.put("Dallas", "DAL");
		m.put("Denver", "DEN");
		m.put("Detroit", "DET");
		m.put("Golden State", "GS");
		m.put("Houston", "HOU");
		m.put("Indiana", "IND");
		m.put("LA Clippers", "LAC");
		m.put("LA Lakers", "LAL");
		m.put("Memphis", "Mem");
		m.put("Miami", "MIA");
		m.put("Milwaukee", "MIL");
		m.put("Minnesota", "MIN");
		m.put("New Orleans","NO");
		m.put("New York", "NY");
		m.put("Oklahoma City", "OKC");
		m.put("Orlando", "ORL");
		m.put("Philadelphia", "PHI");
		m.put("Phoenix", "PHX");
		m.put("Portlan", "POR");
		m.put("Sacramento", "SAC");
		m.put("San Antonio", "SEA");
		m.put("Toronto","TOR");
		m.put("Utah", "UTAH");
		m.put("Washington", "WSH");
		return m.get(totrans).toString();
	}
}
