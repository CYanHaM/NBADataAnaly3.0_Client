package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import PO.TeamTechPO;
public class TeamTechAssist {
	
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
				regular = ifre[1];
				if(regular.equals("Regular")){
					ttpo.ifReagular = 1;
				}
				String name = new String(rs1.getString("team").getBytes("ISO-8859-1"),"utf-8");
//				ttpo.name = to.;
				ttpo.season = regular;
				ttpo.gameNum = 82;
				ttpo.score = Integer.valueOf(new String(rs1.getString("OWN_Poi").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.shotInRate = Integer.valueOf(new String(rs1.getString("OWN_FG").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.threeShotInRate = Integer.valueOf(new String(rs1.getString("OWN_3P").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.penaltyShotInRate = Integer.valueOf(new String(rs1.getString("FT").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.rebound = Integer.valueOf(new String(rs1.getString("TOT_re").getBytes("ISO-8859-1"),"utf-8"));
				ttpo.opponentScore = Integer.valueOf(new String(rs1.getString("OPP_Poi").getBytes("ISO-8859-1"),"utf-8"));
				
				Statement statement2 = conn.createStatement();
				String sql2 = "SELECT * FROM high where (team='"+name+"') and season='"+regular;
				ResultSet rs2 = statement2.executeQuery(sql2);
				while(rs2.next()){
					ttpo.secondaryAttackEfficiency = Double.valueOf(new String(rs2.getString("ast")));
					ttpo.reboundEfficiency = Double.valueOf(new String(rs2.getString("rebr")));
					ttpo.offensiveEfficiency = Double.valueOf(new String(rs2.getString("off_EFF")));
					ttpo.defensiveEfficiency =Double.valueOf( new String(rs2.getString("def_EFF")));
				}
				
				Statement statement3 = conn.createStatement();
				String sql3 = "SELECT * FROM rebound where (team='"+name+"') and season='"+regular;
				ResultSet rs3 = statement3.executeQuery(sql3);
				while(rs3.next()){
					ttpo.offensiveRebound = Integer.valueOf(new String(rs3.getString("OffenOWN")));
					ttpo.defensiveRebound = Integer.valueOf(new String(rs3.getString("DefenOWN")));
					ttpo.opponentOffensiveRebound = Integer.valueOf(new String(rs3.getString("OffenOPP")));
					ttpo.opponentDefensiveRebound = Integer.valueOf(new String(rs3.getString("DefenOPP")));
				}
				
				Statement statement4 = conn.createStatement();
				String sql4 = "SELECT * FROM offense where (team='"+name+"') and season='"+regular;
				ResultSet rs4 = statement4.executeQuery(sql4);
				while(rs4.next()){
					ttpo.shotNum = Integer.valueOf(new String(rs4.getString("FGA")));
					ttpo.shotInNum = Integer.valueOf(new String(rs4.getString("FGM")));
					ttpo.threeShotNum = Integer.valueOf(new String(rs4.getString("TPA")));
					ttpo.threeShotInNum = Integer.valueOf(new String(rs4.getString("TPM")));
					ttpo.penaltyShotNum = Integer.valueOf(new String(rs4.getString("FTA")));
					ttpo.penaltyShotInNum = Integer.valueOf(new String(rs4.getString("FTM")));				
				}
				
				Statement statement5 = conn.createStatement();
				String sql5 = "SELECT * FROM offense where (team='"+name+"') and season='"+regular;
				ResultSet rs5 = statement5.executeQuery(sql5);
				while(rs5.next()){
					ttpo.blockShot = Integer.valueOf(new String(rs5.getString("BLO_OWN")));
					ttpo.steal = Integer.valueOf(new String(rs5.getString("STE_OWN")));
					ttpo.secondaryAttack = Integer.valueOf(new String(rs5.getString("ASS_OWN")));
					ttpo.foul = Integer.valueOf(new String(rs5.getString("TUR_TECH")));
					ttpo.fault = Integer.valueOf(new String(rs5.getString("TUR_OWN")));
				}
			}
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
}
