package presentation.statui;

import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import PO.MatchPO;
import PO.PlayerStatsPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamStatsPO;
import PO.TeamTechPO;
import blservice.statsblservice.StatsBLService;
import bussinesslogic.playerbl.PlayerTech;
import bussinesslogic.playerbl.StatsInfo;
import bussinesslogic.statsbl.Stats;

public class ImportData {

	StatsInfo statsinfo;
	StatsBLService statsbl;
	
	Object [][]dataAll=new Object[7][4];
	Object [][]dataOff=new Object[5][4];
	Object [][]dataDeff=new Object[4][4];
	TeamTechPO tp=new TeamTechPO();
	TeamTechPO tr=new TeamTechPO();
	TeamTechPO ata=new TeamTechPO();
	TeamTechPO ataw=new TeamTechPO();
	TeamTechPO atae=new TeamTechPO();
	TeamStatsPO tsp=new TeamStatsPO();
	 ArrayList<TeamTechPO>tlist=new ArrayList<TeamTechPO>();
	 ArrayList<PlayerTechPO>plist=new ArrayList<PlayerTechPO>();
	 ArrayList<MatchPO>mlist=new ArrayList<MatchPO>();
	 ArrayList<PlayerTechMPO>pmlist=new ArrayList<PlayerTechMPO>();

	Object [][]dataAllP=new Object[9][4];
	Object [][]dataOffP=new Object[5][4];
	Object [][]dataDeffP=new Object[4][4];
	PlayerTechPO pp=new PlayerTechPO();
	PlayerTechPO pr=new PlayerTechPO();
	PlayerTechPO apa=new PlayerTechPO();
	PlayerTechPO apaw=new PlayerTechPO();
	PlayerTechPO apae=new PlayerTechPO();
	PlayerStatsPO psp=new PlayerStatsPO();

	 
	public ImportData(String team,String season,double a,String player){
		statsinfo=new PlayerTech();
		statsbl=new Stats();
//		tp=statsinfo.getTeamTech(team, season, 1);
//		tr=statsinfo.getTeamRank(team, season, 1);
//		ata=statsinfo.getAllTeamAverage(season, 1);
//		ataw=statsinfo.getDivTeamAverage(season, 1, "w");
//		atae=statsinfo.getDivTeamAverage(season, 1, "e");
//		tsp=statsbl.getTeamtechInterval(team, a, season);
//		
//		pp=statsinfo.getPlayerTech(player, season, 1);
//		pr=statsinfo.getPlayerRank(player, season, 1);
//		apa=statsinfo.getAllPlayerAverage(season, 1);
//		apaw=statsinfo.getDivPlayerAverage(season, 1, "w");
//		apae=statsinfo.getDivPlayerAverage(season, 1, "e");
//		psp=statsbl.getPlayerTechInterval(player, a, season);
//		
//		tlist=statsinfo.getMatchForYear(team);
//		plist=statsinfo.getPlayerForYear(player);
//	    mlist=statsinfo.getRecentMatch(team, season);
//	    pmlist=statsinfo.getRecentPlayerM(player, season);
	}
	
	
	private void testallteam(){
		tp.score=(int) (97+Math.random()*5);
		tp.rebound=(int) (40+Math.random()*5);
		tp.secondaryAttack=(int) (20+Math.random()*5);
		tp.steal=(int) (12+Math.random()*5);
		tp.blockShot=(int) (9+Math.random()*5);
		tp.fault=(int) (21+Math.random()*5);
		tp.foul=(int) (5+Math.random()*5);
				
		tr.score=(int) (94+Math.random()*5);
		tr.rebound=(int) (20+Math.random()*5);
		tr.secondaryAttack=(int) (45+Math.random()*5);
		tr.steal=(int) (16+Math.random()*5);
		tr.blockShot=(int) (17+Math.random()*5);
		tr.fault=(int) (29+Math.random()*5);
		tr.foul=(int) (10+Math.random()*5);
		
		ata.score=(int) (97+Math.random()*5);
		ata.rebound=(int) (40+Math.random()*5);
		ata.secondaryAttack=(int) (20+Math.random()*5);
		ata.steal=(int) (12+Math.random()*5);
		ata.blockShot=(int) (9+Math.random()*5);
		ata.fault=(int) (21+Math.random()*5);
		ata.foul=(int) (5+Math.random()*5);
		
		tsp.score[0]=87.0+Math.random()*5;
		tsp.rebound[0]=37.3+Math.random()*5;
		tsp.assist[0]=18.8+Math.random()*5;
		tsp.steal[0]=12.0+Math.random()*5;
		tsp.block[0]=11.0+Math.random()*5;
		tsp.turnover[0]=15+Math.random()*5;
		tsp.foul[0]=9+Math.random()*5;
		
		tsp.score[1]=85.0+Math.random()*5;
		tsp.rebound[1]=24.2+Math.random()*5;
		tsp.assist[1]=19.3+Math.random()*5;
		tsp.steal[1]=17+Math.random()*5;
		tsp.block[1]=14.0+Math.random()*5;
		tsp.turnover[1]=24+Math.random()*5;
		tsp.foul[1]=19+Math.random()*5;
	}
	
	private void testoffenseteam(){
		tp.score=97;
		tp.secondaryAttack=20;
		tp.shotInRate=12;
		tp.penaltyShotNum=9;
		tp.threeShotInNum=21;
				
		tr.score=97;
		tr.secondaryAttack=20;
		tr.shotInRate=12;
		tr.penaltyShotNum=9;
		tr.threeShotInNum=21;
		
		ata.score=97;
		ata.secondaryAttack=20;
		ata.shotInRate=12;
		ata.penaltyShotNum=9;
		ata.threeShotInNum=21;
		
		tsp.score[0]=87.0;
		tsp.assist[0]=18.8;
		tsp.fg[0]=0.87;
		tsp.penaltyshot[0]=15;
		tsp.threeshot[0]=9;
		
		tsp.score[1]=85.0;
		tsp.assist[1]=19.3;
		tsp.fg[1]=0.73;
		tsp.penaltyshot[1]=14.0;
		tsp.threeshot[1]=24;
	}
	
	private void testdefenseteam(){
		tp.rebound=97;
		tp.steal=20;
		tp.blockShot=12;
		tp.fault=9;
				
		tr.rebound=95;
		tr.steal=23;
		tr.blockShot=18;
		tr.fault=12;
		
		ata.rebound=104;
		ata.steal=40;
		ata.blockShot=16;
		ata.fault=11;
		
		tsp.rebound[0]=98.0;
		tsp.steal[0]=23.8;
		tsp.block[0]=12;
		tsp.turnover[0]=17;
		
		tsp.rebound[1]=87.0;
		tsp.steal[1]=18.8;
		tsp.block[1]=15;
		tsp.turnover[1]=15;
	}
	
	
	public Object[][] getallTeam(){
		testallteam();
		dataAll[0][0]=tp.score;
		dataAll[1][0]=tp.rebound;
		dataAll[2][0]=tp.secondaryAttack;
		dataAll[3][0]=tp.steal;
		dataAll[4][0]=tp.blockShot;
		dataAll[5][0]=tp.fault;
		dataAll[6][0]=tp.foul;
		dataAll[0][1]=tr.score;
		dataAll[1][1]=tr.rebound;
		dataAll[2][1]=tr.secondaryAttack;
		dataAll[3][1]=tr.steal;
		dataAll[4][1]=tr.blockShot;
		dataAll[5][1]=tr.fault;
		dataAll[6][1]=tr.foul;
		dataAll[0][2]=ata.score;
		dataAll[1][2]=ata.rebound;
		dataAll[2][2]=ata.secondaryAttack;
		dataAll[3][2]=ata.steal;
		dataAll[4][2]=ata.blockShot;
		dataAll[5][2]=ata.fault;
		dataAll[6][2]=ata.foul;
		dataAll[0][3]=Double.toString(tsp.score[0])+" ~ "+Double.toString(tsp.score[1]);
		dataAll[1][3]=Double.toString(tsp.rebound[0])+" ~ "+Double.toString(tsp.rebound[1]);
		dataAll[2][3]=Double.toString(tsp.assist[0])+" ~ "+Double.toString(tsp.assist[1]);
		dataAll[3][3]=Double.toString(tsp.steal[0])+" ~ "+Double.toString(tsp.steal[1]);
		dataAll[4][3]=Double.toString(tsp.block[0])+" ~ "+Double.toString(tsp.block[1]);
		dataAll[5][3]=Double.toString(tsp.turnover[0])+" ~ "+Double.toString(tsp.turnover[1]);
		dataAll[6][3]=Double.toString(tsp.foul[0])+" ~ "+Double.toString(tsp.foul[1]);
		
	return dataAll;
	}
	
	public Object[][] getoffenseTeam(){
		testoffenseteam();
		dataOff[0][0]=tp.score;
		dataOff[1][0]=tp.secondaryAttack;
		dataOff[2][0]=tp.shotInRate;
		dataOff[3][0]=tp.penaltyShotNum;
		dataOff[4][0]=tp.threeShotInNum;
		dataOff[0][1]=tr.score;
		dataOff[1][1]=tr.secondaryAttack;
		dataOff[2][1]=tr.shotInRate;
		dataOff[3][1]=tr.penaltyShotNum;
		dataOff[4][1]=tr.threeShotInNum;
		dataOff[0][2]=ata.score;
		dataOff[1][2]=ata.secondaryAttack;
		dataOff[2][2]=ata.shotInRate;
		dataOff[3][2]=ata.penaltyShotNum;
		dataOff[4][2]=ata.threeShotInNum;
		dataOff[0][3]=Double.toString(tsp.score[0])+" ~ "+Double.toString(tsp.score[1]);
		dataOff[1][3]=Double.toString(tsp.assist[0])+" ~ "+Double.toString(tsp.assist[1]);
		dataOff[2][3]=Double.toString(tsp.fg[0])+" ~ "+Double.toString(tsp.fg[1]);
		dataOff[3][3]=Double.toString(tsp.penaltyshot[0])+" ~ "+Double.toString(tsp.penaltyshot[1]);
		dataOff[4][3]=Double.toString(tsp.threeshot[0])+" ~ "+Double.toString(tsp.threeshot[1]);
		
	return dataOff;
	}
	
	public Object[][] getdefenseTeam(){
		testdefenseteam();
		dataDeff[0][0]=tp.rebound;
		dataDeff[1][0]=tp.steal;
		dataDeff[2][0]=tp.blockShot;
		dataDeff[3][0]=tp.fault;
		dataDeff[0][1]=tr.rebound;
		dataDeff[1][1]=tr.steal;
		dataDeff[2][1]=tr.blockShot;
		dataDeff[3][1]=tr.fault;
		dataDeff[0][2]=ata.rebound;
		dataDeff[1][2]=ata.steal;
		dataDeff[2][2]=ata.blockShot;
		dataDeff[3][2]=ata.fault;
		dataDeff[0][3]=Double.toString(tsp.rebound[0])+" ~ "+Double.toString(tsp.rebound[1]);
		dataDeff[1][3]=Double.toString(tsp.steal[0])+" ~ "+Double.toString(tsp.steal[1]);
		dataDeff[2][3]=Double.toString(tsp.block[0])+" ~ "+Double.toString(tsp.block[1]);
		dataDeff[3][3]=Double.toString(tsp.turnover[0])+" ~ "+Double.toString(tsp.turnover[1]);
		
	return dataDeff;
	}
	
	
	private void testallplayer(){
		pp.score=(int) (37+Math.random()*5);
		pp.rebound=(int) (9+Math.random()*5);
		pp.secondaryAttack=(int) (12+Math.random()*5);
		pp.blockShot=(int) (3+Math.random()*5);
		pp.steal=(int) (8+Math.random()*5);
		pp.penaltyShot=(int) (15+Math.random()*5);
		pp.fault=(int) (10+Math.random()*5);
		pp.foul=(int) (6+Math.random()*5);
		pp.time=(int) (35+Math.random()*5);
		
		pr.score=(int) (21+Math.random()*5);
		pr.rebound=(int) (77+Math.random()*5);
		pr.secondaryAttack=(int) (53+Math.random()*5);
		pr.blockShot=(int) (62+Math.random()*5);
		pr.steal=(int) (45+Math.random()*5);
		pr.penaltyShot=(int) (35+Math.random()*5);
		pr.fault=(int) (53+Math.random()*5);
		pr.foul=(int) (11+Math.random()*5);
		pr.time=(int) (12+Math.random()*5);
		
		apa.score=(int) (13+Math.random()*5);
		apa.rebound=(int) (8+Math.random()*5);
		apa.secondaryAttack=(int) (7+Math.random()*5);
		apa.blockShot=(int) (2+Math.random()*5);
		apa.steal=(int) (5+Math.random()*5);
		apa.penaltyShot=(int) (7+Math.random()*5);
		apa.fault=(int) (3+Math.random()*5);
		apa.foul=(int) (4+Math.random()*5);
		apa.time=(int) (21+Math.random()*5);
		
		psp.score[0]=35.7+Math.random()*5;
		psp.rebound[0]=7.8+Math.random()*5;
		psp.assist[0]=10.3+Math.random()*5;
		psp.block[0]=1.2+Math.random()*5;
		psp.steal[0]=6.7+Math.random()*5;
		psp.penaltyshot[0]=13.1+Math.random()*5;
		psp.turnover[0]=8.1+Math.random()*5;
		psp.foul[0]=3.1+Math.random()*5;
		psp.time[0]=29.7+Math.random()*5;
		
		
		psp.score[1]=39.4+Math.random()*5;
		psp.rebound[1]=10.2+Math.random()*5;
		psp.assist[1]=15.2+Math.random()*5;
		psp.block[1]=4.1+Math.random()*5;
		psp.steal[1]=9.2+Math.random()*5;
		psp.penaltyshot[1]=17.2+Math.random()*5;
		psp.turnover[1]=12.1+Math.random()*5;
		psp.foul[1]=5.2+Math.random()*5;
		psp.time[1]=37.1+Math.random()*5;
	}
	
	private void testoffenseplayer(){
		pp.score=37;
		pp.secondaryAttack=12;
		pp.shotInRate=0.43;
		pp.penaltyShot=15;
		pp.threeShotIn=4;
		
		
		pr.score=21;
		pr.secondaryAttack=53;
		pr.shotInRate=77;
		pr.penaltyShot=35;
		pr.threeShotIn=36;
		
		
		apa.score=32;
		apa.secondaryAttack=7;
		apa.shotInRate=0.38;
		apa.penaltyShot=30;
		apa.threeShotIn=31;
		
		
		psp.score[0]=35.7;
		psp.assist[0]=10.2;
		psp.fg[0]=0.37;
		psp.penaltyshot[0]=12;
		psp.threeshot[0]=2;
		
		psp.score[1]=38.2;
		psp.assist[1]=14.2;
		psp.fg[1]=0.65;
		psp.penaltyshot[1]=16;
		psp.threeshot[1]=7;
		
	}
	
	private void testdefenseplayer(){
		pp.rebound=9;
		pp.blockShot=3;
		pp.steal=8;
		pp.fault=10;
		
		pr.rebound=77;
		pr.blockShot=62;
		pr.steal=45;
		pr.fault=53;
		
		apa.rebound=8;
		apa.blockShot=2;
		apa.steal=5;
		apa.fault=3;
		
		psp.rebound[0]=7.8;
		psp.block[0]=1.2;
		psp.steal[0]=6.7;
		psp.turnover[0]=8.1;
		
		psp.rebound[1]=10.2;
		psp.block[1]=4.1;
		psp.steal[1]=9.2;
		psp.turnover[1]=12.1;
		
	}
	
	public Object[][] getallPlayer(){
		testallplayer();
		dataAllP[0][0]=pp.score;
		dataAllP[1][0]=pp.rebound;
		dataAllP[2][0]=pp.secondaryAttack;
		dataAllP[3][0]=pp.blockShot;
		dataAllP[4][0]=pp.steal;
		dataAllP[5][0]=pp.penaltyShot;
		dataAllP[6][0]=pp.fault;
		dataAllP[7][0]=pp.foul;
		dataAllP[8][0]=pp.time;
		dataAllP[0][1]=pr.score;
		dataAllP[1][1]=pr.rebound;
		dataAllP[2][1]=pr.secondaryAttack;
		dataAllP[3][1]=pr.blockShot;
		dataAllP[4][1]=pr.steal;
		dataAllP[5][1]=pr.penaltyShot;
		dataAllP[6][1]=pr.fault;
		dataAllP[7][1]=pr.foul;
		dataAllP[8][1]=pr.time;
		dataAllP[0][2]=apa.score;
		dataAllP[1][2]=apa.rebound;
		dataAllP[2][2]=apa.secondaryAttack;
		dataAllP[3][2]=apa.blockShot;
		dataAllP[4][2]=apa.steal;
		dataAllP[5][2]=apa.penaltyShot;
		dataAllP[6][2]=apa.fault;
		dataAllP[7][2]=apa.foul;
		dataAllP[8][2]=apa.time;
		dataAllP[0][3]=Double.toString(psp.score[0])+" ~ "+Double.toString(psp.score[1]);
		dataAllP[1][3]=Double.toString(psp.rebound[0])+" ~ "+Double.toString(psp.rebound[1]);
		dataAllP[2][3]=Double.toString(psp.assist[0])+" ~ "+Double.toString(psp.assist[1]);
		dataAllP[3][3]=Double.toString(psp.block[0])+" ~ "+Double.toString(psp.block[1]);
		dataAllP[4][3]=Double.toString(psp.steal[0])+" ~ "+Double.toString(psp.steal[1]);
		dataAllP[5][3]=Double.toString(psp.penaltyshot[0])+" ~ "+Double.toString(psp.penaltyshot[1]);
		dataAllP[6][3]=Double.toString(psp.turnover[0])+" ~ "+Double.toString(psp.turnover[1]);
		dataAllP[7][3]=Double.toString(psp.foul[0])+" ~ "+Double.toString(psp.foul[1]);
		dataAllP[8][3]=Double.toString(psp.time[0])+" ~ "+Double.toString(psp.time[1]);
		
	return dataAllP;
	}
	
	public Object[][] getoffensePlayer(){
		testoffenseplayer();
		dataOffP[0][0]=pp.score;
		dataOffP[1][0]=pp.secondaryAttack;
		dataOffP[2][0]=pp.shotInRate;
		dataOffP[3][0]=pp.penaltyShot;
		dataOffP[4][0]=pp.threeShotIn;
		dataOffP[0][1]=pr.score;
		dataOffP[1][1]=pr.secondaryAttack;
		dataOffP[2][1]=pr.shotInRate;
		dataOffP[3][1]=pr.penaltyShot;
		dataOffP[4][1]=pr.threeShotIn;
		dataOffP[0][2]=apa.score;
		dataOffP[1][2]=apa.secondaryAttack;
		dataOffP[2][2]=apa.shotInRate;
		dataOffP[3][2]=apa.penaltyShot;
		dataOffP[4][2]=apa.threeShotIn;
		dataOffP[0][3]=Double.toString(psp.score[0])+" ~ "+Double.toString(psp.score[1]);
		dataOffP[1][3]=Double.toString(psp.assist[0])+" ~ "+Double.toString(psp.assist[1]);
		dataOffP[2][3]=Double.toString(psp.fg[0])+" ~ "+Double.toString(psp.fg[1]);
		dataOffP[3][3]=Double.toString(psp.penaltyshot[0])+" ~ "+Double.toString(psp.penaltyshot[1]);
		dataOffP[4][3]=Double.toString(psp.threeshot[0])+" ~ "+Double.toString(psp.threeshot[1]);
		
	return dataOffP;
	}
	
	public Object[][] getdefensePlayer(){
		testdefenseplayer();
		dataDeffP[0][0]=pp.rebound;
		dataDeffP[1][0]=pp.steal;
		dataDeffP[2][0]=pp.blockShot;
		dataDeffP[3][0]=pp.fault;
		dataDeffP[0][1]=pr.rebound;
		dataDeffP[1][1]=pr.steal;
		dataDeffP[2][1]=pr.blockShot;
		dataDeffP[3][1]=pr.fault;
		dataDeffP[0][2]=apa.rebound;
		dataDeffP[1][2]=apa.steal;
		dataDeffP[2][2]=apa.blockShot;
		dataDeffP[3][2]=apa.fault;
		dataDeffP[0][3]=Double.toString(psp.rebound[0])+" ~ "+Double.toString(psp.rebound[1]);
		dataDeffP[1][3]=Double.toString(psp.steal[0])+" ~ "+Double.toString(psp.steal[1]);
		dataDeffP[2][3]=Double.toString(psp.block[0])+" ~ "+Double.toString(psp.block[1]);
		dataDeffP[3][3]=Double.toString(psp.turnover[0])+" ~ "+Double.toString(psp.turnover[1]);
		
	return dataDeffP;
	}
	
	
	 public  CategoryDataset getBarDataset(String teamname,String linename) {

		  String series1 = "1";
		  String category1 = "场均";
		  String category2 = "总体";
		  String category3 = "东部联盟";
		  String category4 = "西部联盟";
		  double num1=0;
		  double num2=0;
		  double num3=0;
		  double num4=0;
		  switch (linename) {
		case "得分":
//			num1=(double)tp.score;
//			num2=(double)ata.score;
//			num3=(double)atae.score;
//			num4=(double)ataw.score;
			num1=85;
			num2=87;
			num3=92;
			num4=73;
			break;
		case "篮板":
			num1=35;
			num2=37;
			num3=38;
			num4=32;
			break;
		case "助攻":
			num1=31;
			num2=35;
			num3=38;
			num4=30;
			break;

		case "抢断":
			num1=27;
			num2=29;
			num3=31;
			num4=26;
			break;

		case "盖帽":
			num1=5;
			num2=5;
			num3=4;
			num4=6;
			break;

		case "失误":
			num1=18;
			num2=22;
			num3=20;
			num4=24;
			break;

		case "犯规":
			num1=19;
			num2=22;
			num3=23;
			num4=18;
			break;

		case "命中率":
			num1=45;
			num2=47;
			num3=43;
			num4=49;
			break;

		case "罚球":
			num1=42;
			num2=38;
			num3=43;
			num4=36;
			break;

		case "三分":
			num1=15;
			num2=13;
			num3=17;
			num4=12;
			break;


		default:
			break;
		}


		  // 创建数据源
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		  // 放入数据
		  dataset.addValue(num1, series1, category1);
		  dataset.addValue(num2, series1, category2);
		  dataset.addValue(num3, series1, category3);
		  dataset.addValue(num4, series1, category4);


		  return dataset;
		 }
	 
	//球队折线的参数 
	 public  CategoryDataset getLineDataset(String season,String team,String linename){
		 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
//		 if(season.equals("Regular")){
			 double []num=new double[13];
			 switch (linename) {
			 case "得分":
				 num[0]=(int)(Math.random()*4)+80;
				 num[1]=(int)(Math.random()*4)+70;
				 num[2]=(int)(Math.random()*4)+80;
				 num[3]=(int)(Math.random()*4)+79;
				 num[4]=(int)(Math.random()*4)+93;
				 num[5]=(int)(Math.random()*4)+80;
				 num[6]=(int)(Math.random()*4)+76;
				 break;
			 case "篮板":
				 num[0]=(int)(Math.random()*4)+30;
				 num[1]=(int)(Math.random()*4)+27;
				 num[2]=(int)(Math.random()*4)+33;
				 num[3]=(int)(Math.random()*4)+25;
				 num[4]=(int)(Math.random()*4)+28;
				 num[5]=(int)(Math.random()*4)+29;
				 num[6]=(int)(Math.random()*4)+20;
				 break;
			 case "助攻":
				 num[0]=(int)(Math.random()*4)+21;
				 num[1]=(int)(Math.random()*4)+25;
				 num[2]=(int)(Math.random()*4)+22;
				 num[3]=(int)(Math.random()*4)+27;
				 num[4]=(int)(Math.random()*4)+23;
				 num[5]=(int)(Math.random()*4)+20;
				 num[6]=(int)(Math.random()*4)+24;
				 break;
			 case "抢断":
				 num[0]=(int)(Math.random()*4)+18;
				 num[1]=(int)(Math.random()*4)+22;
				 num[2]=(int)(Math.random()*4)+19;
				 num[3]=(int)(Math.random()*4)+24;
				 num[4]=(int)(Math.random()*4)+20;
				 num[5]=(int)(Math.random()*4)+17;
				 num[6]=(int)(Math.random()*4)+21;
				 break;
				 
			 case "盖帽":
				 num[0]=(int)(Math.random()*4)+5;
				 num[1]=(int)(Math.random()*4)+7;
				 num[2]=(int)(Math.random()*4)+6;
				 num[3]=(int)(Math.random()*4)+11;
				 num[4]=(int)(Math.random()*4)+7;
				 num[5]=(int)(Math.random()*4)+4;
				 num[6]=(int)(Math.random()*4)+8;
				 break;
				 
			 default:
				 break;
			 }
			 
			 
			 dataset.addValue(num[0], "First", "2009");  
			 dataset.addValue(num[1], "First", "2010");  
			 dataset.addValue(num[2], "First", "2011");
			 dataset.addValue(num[3], "First", "2012");  
			 dataset.addValue(num[4], "First", "2013");  
			 dataset.addValue(num[5], "First", "2014");  
			 dataset.addValue(num[6], "First", "2015");  
//		 } 
//		 
//		 else{
//			 double []num=new double[10];
//			 double average = 0;
//			 switch (linename) {
//			 case "得分":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)mlist.get(i).homeScore;
//				 }
//				 average=(double)tp.score;
//				 break;
//			 case "篮板":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)mlist.get(i).homeTeamDeffensiveRebound+(double)mlist.get(i).homeTeamOffensiveRebound;
//				 }
//				 average=(double)tp.rebound;
//				 break;
//			 case "助攻":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)mlist.get(i).homeTeamSecondaryAttack;
//				 }
//				 average=(double)tp.secondaryAttack;
//				 break;
//				 
//			 case "抢断":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)mlist.get(i).homeTeamSteal;
//				 }
//				 average=(double)tp.steal;
//				 break;
//				 
//			 case "盖帽":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)mlist.get(i).homeTeamBlockShot;
//				 }
//				 average=(double)tp.blockShot;
//				 break;
//				 
//			 default:
//				 break;
//			 }
//			 
//			 dataset.addValue(num[0], "First", "1");  
//			 dataset.addValue(num[1], "First", "2");  
//			 dataset.addValue(num[2], "First", "3");  
//			 dataset.addValue(num[3], "First", "4");  
//			 dataset.addValue(num[4], "First", "5");  
//			 dataset.addValue(num[5], "First", "6");  
//			 dataset.addValue(num[6], "First", "7");  
//			 dataset.addValue(num[7], "First", "8");  
//			 dataset.addValue(num[8], "First", "9");
//			 dataset.addValue(num[9], "First", "10");
//			 dataset.addValue(average, "Second", "1");  
//			 dataset.addValue(average, "Second", "2");  
//			 dataset.addValue(average, "Second", "3");  
//			 dataset.addValue(average, "Second", "4");  
//			 dataset.addValue(average, "Second", "5");  
//			 dataset.addValue(average, "Second", "6");  
//			 dataset.addValue(average, "Second", "7");  
//			 dataset.addValue(average, "Second", "8");  
//			 dataset.addValue(average, "Second", "9");
//			 dataset.addValue(average, "Second", "10");
//			 
//		 }
		 return dataset;
	 }

		//球员折线的参数 
	 public  CategoryDataset getLineDatasetP(String season,String player,String linename){
		 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
//		 if(season.equals("regular")){
		 double []num=new double[13];
		 switch (linename) {
		 case "得分":
			 num[0]=(int)(Math.random()*4)+50;
			 num[1]=(int)(Math.random()*4)+60;
			 num[2]=(int)(Math.random()*4)+50;
			 num[3]=(int)(Math.random()*4)+57;
			 num[4]=(int)(Math.random()*4)+61;
			 num[5]=(int)(Math.random()*4)+60;
			 num[6]=(int)(Math.random()*4)+56;
			 break;
		 case "篮板":
			 num[0]=(int)(Math.random()*4)+35;
			 num[1]=(int)(Math.random()*4)+40;
			 num[2]=(int)(Math.random()*4)+34;
			 num[3]=(int)(Math.random()*4)+36;
			 num[4]=(int)(Math.random()*4)+30;
			 num[5]=(int)(Math.random()*4)+32;
			 num[6]=(int)(Math.random()*4)+29;
			 break;
		 case "助攻":
			 num[0]=(int)(Math.random()*4)+21;
			 num[1]=(int)(Math.random()*4)+25;
			 num[2]=(int)(Math.random()*4)+25;
			 num[3]=(int)(Math.random()*4)+20;
			 num[4]=(int)(Math.random()*4)+19;
			 num[5]=(int)(Math.random()*4)+18;
			 num[6]=(int)(Math.random()*4)+24;
			 break;
		 case "抢断":
			 num[0]=(int)(Math.random()*4)+18;
			 num[1]=(int)(Math.random()*4)+17;
			 num[2]=(int)(Math.random()*4)+19;
			 num[3]=(int)(Math.random()*4)+24;
			 num[4]=(int)(Math.random()*4)+20;
			 num[5]=(int)(Math.random()*4)+17;
			 num[6]=(int)(Math.random()*4)+14;
			 break;
			 
		 case "盖帽":
			 num[0]=(int)(Math.random()*4)+3;
			 num[1]=(int)(Math.random()*4)+5;
			 num[2]=(int)(Math.random()*4)+6;
			 num[3]=(int)(Math.random()*4)+9;
			 num[4]=(int)(Math.random()*4)+7;
			 num[5]=(int)(Math.random()*4)+4;
			 num[6]=(int)(Math.random()*4)+5;
			 break;
			 
		 default:
			 break;
		 }
		 
			 
			 dataset.addValue(num[0], "First", "2009");  
			 dataset.addValue(num[1], "First", "2010");  
			 dataset.addValue(num[2], "First", "2011");
			 dataset.addValue(num[3], "First", "2012");  
			 dataset.addValue(num[4], "First", "2013");  
			 dataset.addValue(num[5], "First", "2014");  
			 dataset.addValue(num[6], "First", "2015");  
//		 } 
//		 
//		 else{
//			 double []num=new double[10];
//			 double average = 0;
//			 switch (linename) {
//			 case "得分":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)pmlist.get(i).score;
//				 }
//				 average=(double)pp.score;
//				 break;
//			 case "篮板":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)pmlist.get(i).rebound;
//				 }
//				 average=(double)pp.rebound;
//				 break;
//			 case "助攻":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)pmlist.get(i).secondaryAttack;
//				 }
//				 average=(double)pp.secondaryAttack;
//				 break;
//				 
//			 case "抢断":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)pmlist.get(i).steal;
//				 }
//				 average=(double)pp.steal;
//				 break;
//				 
//			 case "盖帽":
//				 for(int i=0;i<10;i++){
//					 num[i]=(double)pmlist.get(i).blockShot;
//				 }
//				 average=(double)pp.blockShot;
//				 break;
//				 
//			 default:
//				 break;
//			 }
//			 
//			 dataset.addValue(num[0], "First", "1");  
//			 dataset.addValue(num[1], "First", "2");  
//			 dataset.addValue(num[2], "First", "3");  
//			 dataset.addValue(num[3], "First", "4");  
//			 dataset.addValue(num[4], "First", "5");  
//			 dataset.addValue(num[5], "First", "6");  
//			 dataset.addValue(num[6], "First", "7");  
//			 dataset.addValue(num[7], "First", "8");  
//			 dataset.addValue(num[8], "First", "9");
//			 dataset.addValue(num[9], "First", "10");
//			 dataset.addValue(average, "Second", "1");  
//			 dataset.addValue(average, "Second", "2");  
//			 dataset.addValue(average, "Second", "3");  
//			 dataset.addValue(average, "Second", "4");  
//			 dataset.addValue(average, "Second", "5");  
//			 dataset.addValue(average, "Second", "6");  
//			 dataset.addValue(average, "Second", "7");  
//			 dataset.addValue(average, "Second", "8");  
//			 dataset.addValue(average, "Second", "9");
//			 dataset.addValue(average, "Second", "10");
//			 
//		 }
		 return dataset;
	 }
	 
//雷达图	 
	 public CategoryDataset createRadarDataset(){
		 
		 String s = "First";
		  String s1 = "Second";
		  String s2 = "Third";
		  String s3 = "得分";
		  String s4 = "篮板";
		  String s5 = "助攻";
		  String s6 = "抢断";
		  String s7 = "盖帽";
		  
		  pp.score=(int) ((Math.random()*7)+8);
		  pp.rebound=(int) ((Math.random()*7)+1);
		  pp.secondaryAttack=(int) ((Math.random()*7));;
		  pp.steal=(int) ((Math.random()*7));;
		  pp.blockShot=(int) ((Math.random()*5));;
		  
		  DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		  defaultcategorydataset.addValue(pp.score, s, s3);
		  defaultcategorydataset.addValue(pp.rebound, s, s4);
		  defaultcategorydataset.addValue(pp.secondaryAttack, s, s5);
		  defaultcategorydataset.addValue(pp.steal, s, s6);
		  defaultcategorydataset.addValue(pp.blockShot, s, s7);
//		  defaultcategorydataset.addValue(5D, s1, s3);
//		  defaultcategorydataset.addValue(7D, s1, s4);
//		  defaultcategorydataset.addValue(6D, s1, s5);
//		  defaultcategorydataset.addValue(8D, s1, s6);
//		  defaultcategorydataset.addValue(4D, s1, s7);
//		  defaultcategorydataset.addValue(4D, s2, s3);
//		  defaultcategorydataset.addValue(3D, s2, s4);
//		  defaultcategorydataset.addValue(2D, s2, s5);
//		  defaultcategorydataset.addValue(3D, s2, s6);
//		  defaultcategorydataset.addValue(6D, s2, s7);
		  return defaultcategorydataset;
	 }
	
}
