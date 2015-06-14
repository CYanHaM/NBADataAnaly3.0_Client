package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;


public class Tools {
	
	public static void main(String[] args){
		Tools t = new Tools();
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
						String[] temp = pos.split(",");
						// 锟斤拷锟�
						System.out.println(temp[1]);  
						return temp[1];
					} 

	public ArrayList<PlayerTechPO> getPlayerTech(ResultSet rs){
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
				String sql = "select * from player_scoring where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				ResultSet r = statement.executeQuery(sql);
				while(r.next()){
					po.gameNum=Integer.valueOf(r.getString("GP"));
					po.score=po.fault=Integer.valueOf(r.getString("PTS"));
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
					Integer.valueOf(r.getString("AST"));
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
				String sql2 = "select * from detail where name='"+name+"' and season='"+season+"' and type='"+type+"' and team='"+team+"'";
				ResultSet set= statement2.executeQuery(sql2);
				while(set.next()){
					po.teamAllTime = Integer.valueOf(set.getString("teamAllTime"));
					po.teamOffensiveRebound = Integer.valueOf(set.getString("teamOffensiveRebound"));
					po.teamDefensiveRebound = Integer.valueOf(set.getString("teamDefensiveRebound"));
					po.opponentOffensiveRebound = Integer.valueOf(set.getString("opponentOffensiveRebound"));
					po.opponentDefensiveRebound = Integer.valueOf(set.getString("opponentDefensiveRebound"));
					po.teamShotIn = Integer.valueOf(set.getString("teamShotIn"));
					po.opponentOffensiveNum = Integer.valueOf(set.getString("opponentOffensiveNum"));
					po.opponentTwoShot = Integer.valueOf(set.getString("opponentTwoShot"));
					po.teamShot = Integer.valueOf(set.getString("Integer.valueOf(eamShot"));
					po.teamPenaltyShot = Integer.valueOf(set.getString("teamPenaltyShot"));
					po.teamFault = Integer.valueOf(set.getString("teamFault"));
				}

				//根据公式计算
				//除数不能为0
				if(po.threeShot==0){					
					po.threeShotInRate=0;
					}else{
						po.threeShotInRate=(double)po.threeShotIn/(double)po.threeShot;
					}
				po.penaltyShot=po.penaltyShot;
				po.penaltyShotIn=po.penaltyShotIn;
				if(po.penaltyShot==0){					
					po.penaltyShotInRate=0;
					}else{
						po.penaltyShotInRate=(double)po.penaltyShotIn/(double)po.penaltyShot;
					}
				po.efficiency=(po.score+po.rebound+po.secondaryAttack+po.steal+po.blockShot)-(po.shot-po.shotIn)-(po.penaltyShot-po.penaltyShotIn)-po.fault;
				po.GmScEfficiency=(double)po.score+0.4*(double)po.shotIn-0.7*(double)po.shot-0.4*((double)po.penaltyShot-(double)po.penaltyShotIn)+0.7*(double)po.offensiveNum+0.3*(double)po.defensiveNum+(double)po.steal+0.7*(double)po.secondaryAttack+0.7*(double)po.blockShot-0.4*(double)po.foul-(double)po.fault;
				if(2*((double)po.shot+0.44*(double)po.penaltyShot)==0){
					po.trueShotInRate=0;
					}else{
						po.trueShotInRate=(double)po.score/(2*((double)po.shot+0.44*(double)po.penaltyShot));
						}
				if((double)po.shot==0){
					po.shootingEfficiency=0;
				}else{
					po.shootingEfficiency=((double)po.shotIn+0.5*(double)po.threeShotIn)/(double)po.shot;
				}
				if(po.time==0){
					po.reboundRate=0;
				}else{
					po.reboundRate=(double)po.rebound*((double)po.teamAllTime/5)/(double)po.time/((double)po.teamDefensiveRebound+(double)po.teamOffensiveRebound+(double)po.opponentDefensiveRebound+(double)po.opponentOffensiveRebound);
				}
				if((double)po.time==0){
					po.offensiveReboundRate=0;
				}else{
					po.offensiveReboundRate=(double)po.offensiveNum*((double)po.teamAllTime/5)/(double)po.time/((double)po.teamOffensiveRebound+(double)po.opponentOffensiveRebound);
				}
				if((double)po.time==0){
					po.defensiveReboundRate=0;
				}else{
					po.defensiveReboundRate=(double)po.defensiveNum*((double)po.teamAllTime/5)/(double)po.time/((double)po.teamDefensiveRebound+(double)po.opponentDefensiveRebound);
				}
				if((double)po.time==0){
					po.secondaryAttackRate=0;
				}else{
					po.secondaryAttackRate=(double)po.secondaryAttack/((double)po.time/((double)po.teamAllTime/5)*(double)po.teamShotIn-(double)po.shotIn);
				}
				if((double)po.time==0){
					po.stealRate=0;
				}else{
					po.stealRate=(double)po.steal*((double)po.teamAllTime/5)/(double)po.time/(double)po.opponentOffensiveNum;
				}
				
				if(po.time==0){
					po.blockShotRate=0;
				}else{    
					po.blockShotRate=(double)po.blockShot*((double)po.teamAllTime/5)/(double)po.time/(double)po.opponentTwoShot;
				}
				if(((double)po.shot==0)){
					po.faultRate=0;
					}else{
						po.faultRate=(double)po.fault/((double)po.shot-(double)po.threeShot+0.44*(double)po.penaltyShot+(double)po.fault);
				}
				if((double)po.time==0){
					po.usageRate=0;
				}else{
					po.usageRate=((double)po.shot+0.44*(double)po.penaltyShot+(double)po.fault)*((double)po.teamAllTime/5)/(double)po.time/((double)po.teamShot+0.44*(double)po.teamPenaltyShot+(double)po.teamFault);
				}
				list.add(po);
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
		System.out.println("wrong:tools.getPlayerTech");
		return null;
	}

	/*
	public ArrayList<PlayerTechPO> calculateImproving(ArrayList<PlayerTechPO> poList){
		 //计算按时间排序、按姓名分类的技术统计
		 ArrayList<ArrayList<PlayerTechMPO>> li = readDiv();
			int size = li.size();
		
			Iterator<ArrayList<PlayerTechMPO>> it = li.iterator();
			while(it.hasNext()){
				if(it.next().size()<=5){
					it.remove();
				}
			}
			for(int i=0;i<size;i++){
				ArrayList<PlayerTechMPO> list = li.get(i);
				//进行排序
				Comparator<PlayerTechMPO> comparator = new Comparator<PlayerTechMPO>(){  
					public int compare(PlayerTechMPO p1, PlayerTechMPO p2) {   
						//重写比较方法,降序比较日期
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd",Locale.CHINA);
						 Date dt1;
						 Date dt2;
						try {
							dt1 = sdf.parse(p1.date);
							dt2 = sdf.parse(p2.date);
							 if(dt2.getTime()>dt1.getTime())
								 return 1;
							 else
								 return -1;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return 0;
					}
				}; 
				Collections.sort(list, comparator);
			}
		 
		 int amount = li.size();
		 for(int j=0;j<amount;j++){
			 ArrayList<PlayerTechMPO> list= li.get(j);
			 //测使用
		//	 System.out.println(list.size());
			 String name = list.get(0).name;
			 //开始计算每一个球员的提升率
			//最近5场比赛数据
			 ArrayList<PlayerTechMPO> latest = new ArrayList<PlayerTechMPO>();
			 //list为之前数据
			int num=0;
			while(num<5){     
				latest.add(list.get(0));
				list.remove(0);
				num++;
			}
			
			int latestScore = 0;
			int latestSteal=0;
			int latestBlockShot=0;
			int latestSecondaryAttack=0;
			int latestRebound=0;
			int score = 0;
			int steal=0;
			int blockShot=0;
			int secondaryAttack=0;
			int rebound=0;
			
			for(int i=0;i<5;i++){
				PlayerTechMPO mp =  latest.get(i);
				latestScore += mp.score;
				latestSteal += mp.steal;
				latestBlockShot += mp.blockShot;
				latestSecondaryAttack +=mp.secondaryAttack;
				latestRebound += mp.rebound;
			}
			
			int listSize = list.size();
			for(int i=0;i<listSize;i++){
				PlayerTechMPO mp =  list.get(i);
				score = mp.score;
				steal=mp.steal;
				blockShot=mp.blockShot;
				secondaryAttack=mp.secondaryAttack;
				rebound=mp.rebound;
			}
			
			double scoreImproving=(score/listSize)==0?0:(((latestScore/5)-score/listSize)/(score/listSize));
		    double stealImproving=(steal/listSize)==0?0:(((latestSteal/5)-steal/listSize)/(steal/listSize));
			double blockShotImproving=(blockShot/listSize)==0?0:(((latestBlockShot/5)-blockShot/listSize)/(blockShot/listSize));
			double secondaryAttackImproving=(secondaryAttack/listSize)==0?0:(((latestSecondaryAttack/5)-secondaryAttack/listSize)/(secondaryAttack/listSize));
			double reboundImproving=(rebound/listSize)==0?0:(((rebound/5)-rebound/listSize)/(rebound/listSize));
			
			int poSize = list.size();
			for(int m=0;m<poSize;m++){
				PlayerTechPO po = poList.get(m); 
				if(po.name.equals(name)){
					po.scoreImproving = scoreImproving;
					po.stealImproving = stealImproving;
					po.blockShotImproving = blockShotImproving;
					po.secondaryAttackImproving = secondaryAttackImproving;
					po.reboundImproving = reboundImproving;
				}
			}
		 }
		 return poList;
	}*/

}
