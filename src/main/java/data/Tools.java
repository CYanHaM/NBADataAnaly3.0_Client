package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import PO.PlayerTechPO;


public class Tools {
	
	public static void main(String[] args){
		Tools t = new Tools();
		t.transferScore();
	}
	
	public void transferDetail(){
		Tools t = new Tools();
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
			Statement statement1 = conn.createStatement();
			// 要执锟叫碉拷SQL锟斤拷锟�
			String sql = "SELECT * FROM `match_detail`";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String date="";
				String season="";
				date = new String(rs.getString(17).getBytes("ISO-8859-1"),"utf-8");
				season = new String(rs.getString(18).getBytes("ISO-8859-1"),"utf-8");
				date = t.parseDate(date, season);
				String sql2 = "replace into detail values ('"+rs.getString(1).replaceAll("'", "''")+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+
				rs.getString(6)+"','"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+
				rs.getString(14)+"','"+rs.getString(15)+"','"+rs.getString(16)+"','"+date+"','"+rs.getString(18)+"','"+rs.getString(19)+"','"+rs.getString(20)+"')";
				System.out.println(sql2);
				statement1.executeUpdate(sql2);
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
	}
	public void transferScore(){
		Tools t = new Tools();
		String driver = "com.mysql.jdbc.Driver";
		//URL指锟斤拷要锟斤拷锟绞碉拷锟斤拷菘锟斤拷锟絥ba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL锟斤拷锟斤拷时锟斤拷锟矫伙拷锟斤拷
		String user = "root";
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
			Statement statement1 = conn.createStatement();
			String sql = "SELECT * FROM score";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String date="";
				String season="";
				date = new String(rs.getString(19).getBytes("ISO-8859-1"),"utf-8");
				season = new String(rs.getString(20).getBytes("ISO-8859-1"),"utf-8");
				date = t.parseDate(date, season);
				String sql2 = "replace into `match` values ('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+
				rs.getString(6)+"','"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+
				rs.getString(14)+"','"+rs.getString(15)+"','"+rs.getString(16)+"','"+rs.getString(17)+"','"+rs.getString(18)+"','"+date+"','"+rs.getString(20)+"','"+rs.getString(21)+"')";
				System.out.println(sql2);
				statement1.executeUpdate(sql2);
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
	}
	public String parseDate(String date,String season){
		String result = "";
		String[] temp1 = date.split(",");
		String[] dt = temp1[1].trim().split("\\s+");
		int year = 0;
		String month = "";
		String[] temp2 = season.split("-");
		int year1 = Integer.parseInt(temp2[0]);
		int year2 = year1+1;
		switch(dt[0]){
			case "Jan":
					year = year2;
					month = "01";
				break;
			case "Feb":
				year = year2;
				month = "02";
				break;
			case "Mar":
				year = year2;
				month = "03";
				break;
			case "Apr":
				year = year2;
				month = "04";
				break;
			case "May":
				year = year2;
				month = "05";
				break;
			case "Jun":
				year = year2;
				month = "06";
				break;
			case "Sep":
				year = year1;
				month = "09";
				break;
			case "Oct":
				year = year1;
				month = "10";
				break;
			case "Nov":
				year = year1;
				month = "11";
				break;
			case "Dec":
				year = year1;
				month = "12";
				break;
			default:
				System.out.println("wrong date");
		}
		String day = dt[1];
		if(dt[1].length()==1){
			day = "0"+day;
		}
		result = year+"-"+month+"-"+day;
		return result;
	}
	
	public String getPos(String pos){
						if(pos.equals(" ")){
							return " ";
						}
						String[] temp = pos.trim().split(",");
						return temp[1];
					} 

	public ArrayList<PlayerTechPO> fillPlayerTech(){
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名nba
		String url = "jdbc:mysql://127.0.0.1:3306/NBADataAnaly";
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "cyanham";
		try{
			// 加载驱动程序
			Class.forName(driver);
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			String sql1 = "select * from `player_scoring`";
			ResultSet rs = statement.executeQuery(sql1);
			Statement statement2 = conn.createStatement();
			while(rs.next()){
				PlayerTechPO po = new PlayerTechPO();
				String name = rs.getString("name");
				String season = rs.getString("season");
				String type = rs.getString("type");
				String team = rs.getString("team");
				po.name=name;
				po.season=season;
				po.team=team;
				po.ifRegular=type.equals("Regular")?1:0;
				po.position=getPos(rs.getString("pos"));
				Statement sta = conn.createStatement();
				String s1 = "select * from `teamInfo` where name='"+team+"'";
				ResultSet set1 = sta.executeQuery(s1);
				while(set1.next()){
					po.division=set1.getString("division");
				}
				String sql = "select * from player_scoring where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				ResultSet r = statement.executeQuery(sql);
				while(r.next()){
					po.gameNum=Integer.valueOf(r.getString("GP"));
					po.score=Integer.valueOf(r.getString("PTS"));
					po.penaltyShotInRate=Double.valueOf(r.getString("FT%"));
					po.shotInRate=Double.valueOf(r.getString("FG%"));
					po.threeShotInRate=Double.valueOf(r.getString("3P%"));
				}
				sql = "select * from player_rebounds where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.rebound=Integer.valueOf(r.getString("REB"));
					po.offensiveNum=Integer.valueOf(r.getString("OFF"));
					po.defensiveNum=Integer.valueOf(r.getString("DEF"));
				}
				sql = "select * from player_fieldgoals where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.shotIn=Integer.valueOf(r.getString("FGMtotal"));
					po.shot=Integer.valueOf(r.getString("FGAtotal"));
					
				}
				sql = "select * from player_freethrows where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.penaltyShotIn=Integer.valueOf(r.getString("FTMtotal"));
					po.penaltyShot=Integer.valueOf(r.getString("FTAtotal"));
					
				}
				sql = "select * from player_3points where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.threeShotIn=Integer.valueOf(r.getString("3PMtotal"));
					po.threeShot=Integer.valueOf(r.getString("3PAtotal"));
				}
				sql = "select * from player_assists where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.secondaryAttack=Integer.valueOf(r.getString("AST"));
					po.fault=Integer.valueOf(r.getString("TO"));
				}
				sql = "select * from player_steals where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.steal=Integer.valueOf(r.getString("STL"));
				}
				sql = "select * from player_blocks where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.blockShot=Integer.valueOf(r.getString("BLK"));
				}
				sql = "select * from player_fouls where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.foul=po.fault=Integer.valueOf(r.getString("PF"));
				}
				sql = "select * from player_minutes where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.time=Integer.valueOf(r.getString("MIN"));
				}
				sql = "select * from player_doubles where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				r = statement.executeQuery(sql);
				while(r.next()){
					po.ifDouble=Integer.valueOf(r.getString("DBLDBL"));
				}
				String sql2 = "select * from `player_h` where name='"+name+"' and  team='"+team+"' and year='"+season+" "+type+"'";
				ResultSet set= statement2.executeQuery(sql2);
				while(set.next()){
					po.trueShotInRate=Double.valueOf(set.getString("TS"));
					po.reboundRate=Double.valueOf(set.getString("REBR"));
					po.offensiveReboundRate=Double.valueOf(set.getString("ORR"));
					po.defensiveReboundRate=Double.valueOf(set.getString("DRR"));
					po.secondaryAttackRate=Double.valueOf(set.getString("AST"));
					po.faultRate=Double.valueOf(set.getString("TO"));
					po.usageRate=Double.valueOf(set.getString("USG"));
				}
				po.GmScEfficiency=(double)po.score+0.4*(double)po.shotIn-0.7*(double)po.shot-0.4*((double)po.penaltyShot-(double)po.penaltyShotIn)+0.7*(double)po.offensiveNum+0.3*(double)po.defensiveNum+(double)po.steal+0.7*(double)po.secondaryAttack+0.7*(double)po.blockShot-0.4*(double)po.foul-(double)po.fault;
				if((double)po.shot==0){
					po.shootingEfficiency=0;
				}else{
					po.shootingEfficiency=((double)po.shotIn+0.5*(double)po.threeShotIn)/(double)po.shot;
				}
				list.add(po);
				Statement state = conn.createStatement();
				String str = "replace into `playerTechPO` values('"+po.name.replaceAll("'", "''")+"','"+po.season+"','"+po.team+"','"+po.ifRegular+"','"+po.position+"','"+po.division+"','"+po.gameNum+"','"+po.startingNum+"','"+po.rebound+"','"+po.secondaryAttack+"','"+po.time+"','"+po.offensiveNum+"','"+po.defensiveNum+"','"+po.steal+"','"+po.blockShot+"','"+po.fault+"','"+po.foul+"','"+po.score+"','"+po.shotIn+"','"+po.shot+"','"+
						po.threeShotIn+"','"+po.threeShot+"','"+po.penaltyShotIn+"','"+po.penaltyShot+"','"+po.shotInRate+"','"+po.threeShotInRate+"','"+po.penaltyShotInRate+"','"+po.GmScEfficiency+"','"+po.trueShotInRate+"','"+po.shootingEfficiency+"','"+po.reboundRate+"','"+po.offensiveReboundRate+"','"+po.defensiveReboundRate+"','"+po.secondaryAttackRate+"','"+po.faultRate+"','"+po.usageRate+"','"+po.ifDouble+"')";
				state.executeUpdate(str);
			}
			return list;
		}catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}; 
		System.out.println("wrong:tools.fillPlayerTechPO");
		return null;
}


	public int compareDate(String d1,String d2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(d1);
			Date dt2 = df.parse(d2);
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
		return 0;
	}
	
	public String changeSeason(String str){
		String[] temp = str.split("-");
		String season = temp[0]+"-"+temp[1].charAt(2)+temp[1].charAt(3);
		return season;
	}
	
	public String convertSeason(String str){
		String[] temp = str.split("-");
		String season = temp[0]+"-"+(Integer.valueOf(temp[0])+1);
		return season;
	}
}
