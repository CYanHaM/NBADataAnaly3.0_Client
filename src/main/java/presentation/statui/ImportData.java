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
		tp=statsinfo.getTeamTech(team, season, 1);
		tr=statsinfo.getTeamRank(team, season, 1);
		ata=statsinfo.getAllTeamAverage(season, 1);
		ataw=statsinfo.getDivTeamAverage(season, 1, "w");
		atae=statsinfo.getDivTeamAverage(season, 1, "e");
		tsp=statsbl.getTeamtechInterval(team, a, season);
		
		pp=statsinfo.getPlayerTech(player, season, 1);
		pr=statsinfo.getPlayerRank(player, season, 1);
		apa=statsinfo.getAllPlayerAverage(season, 1);
		apaw=statsinfo.getDivPlayerAverage(season, 1, "w");
		apae=statsinfo.getDivPlayerAverage(season, 1, "e");
		psp=statsbl.getPlayerTechInterval(player, a, season);
		
		tlist=statsinfo.getMatchForYear(team);
		plist=statsinfo.getPlayerForYear(player);
	    mlist=statsinfo.getRecentMatch(team, season);
	    pmlist=statsinfo.getRecentPlayerM(player, season);
	}
	
	public String[] getSeasons(String teamname){
		return statsinfo.getTeamSeasonList(teamname);
	}
	
	public Object[][] getallTeam(){
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
	
	public Object[][] getallPlayer(){
		dataAllP[0][0]=pp.score;
		dataAllP[1][0]=pp.rebound;
		dataAllP[2][0]=pp.secondaryAttack;
		dataAllP[3][0]=pp.blockShot;
		dataAllP[4][0]=pp.steal;
		dataAllP[5][0]=pp.penaltyShot;
		dataAllP[6][0]=pp.fault;
		dataAllP[7][0]=pp.foul;
		dataAllP[8][0]=pp.time;
		dataAllP[0][0]=pr.score;
		dataAllP[1][0]=pr.rebound;
		dataAllP[2][0]=pr.secondaryAttack;
		dataAllP[3][0]=pr.blockShot;
		dataAllP[4][0]=pr.steal;
		dataAllP[5][0]=pr.penaltyShot;
		dataAllP[6][0]=pr.fault;
		dataAllP[7][0]=pr.foul;
		dataAllP[8][0]=pr.time;
		dataAllP[0][0]=apa.score;
		dataAllP[1][0]=apa.rebound;
		dataAllP[2][0]=apa.secondaryAttack;
		dataAllP[3][0]=apa.blockShot;
		dataAllP[4][0]=apa.steal;
		dataAllP[5][0]=apa.penaltyShot;
		dataAllP[6][0]=apa.fault;
		dataAllP[7][0]=apa.foul;
		dataAllP[8][0]=apa.time;
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
			num1=(double)tp.score;
			num2=(double)ata.score;
			num3=(double)atae.score;
			num4=(double)ataw.score;
			break;
		case "篮板":
			num1=(double)tp.rebound;
			num2=(double)ata.rebound;
			num3=(double)atae.rebound;
			num4=(double)ataw.rebound;
			break;
		case "助攻":
			num1=(double)tp.secondaryAttack;
			num2=(double)ata.secondaryAttack;
			num3=(double)atae.secondaryAttack;
			num4=(double)ataw.secondaryAttack;
			break;

		case "抢断":
			num1=(double)tp.steal;
			num2=(double)ata.steal;
			num3=(double)atae.steal;
			num4=(double)ataw.steal;
			break;

		case "盖帽":
			num1=(double)tp.blockShot;
			num2=(double)ata.blockShot;
			num3=(double)atae.blockShot;
			num4=(double)ataw.blockShot;
			break;

		case "失误":
			num1=(double)tp.fault;
			num2=(double)ata.fault;
			num3=(double)atae.fault;
			num4=(double)ataw.fault;
			break;

		case "犯规":
			num1=(double)tp.foul;
			num2=(double)ata.foul;
			num3=(double)atae.foul;
			num4=(double)ataw.foul;
			break;

		case "命中率":
			num1=(double)tp.shotInRate;
			num2=(double)ata.shotInRate;
			num3=(double)atae.shotInRate;
			num4=(double)ataw.shotInRate;
			break;

		case "罚球":
			num1=(double)tp.penaltyShotNum;
			num2=(double)ata.penaltyShotNum;
			num3=(double)atae.penaltyShotNum;
			num4=(double)ataw.penaltyShotNum;
			break;

		case "三分":
			num1=(double)tp.threeShotInNum;
			num2=(double)ata.threeShotInNum;
			num3=(double)atae.threeShotInNum;
			num4=(double)ataw.threeShotInNum;
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
		 if(season.equals("Regular")){
			 double []num=new double[13];
			 switch (linename) {
			 case "得分":
				 for(int i=0;i<13;i++){
					 num[i]=(double)tlist.get(i).score;
				 }
				 break;
			 case "篮板":
				 for(int i=0;i<13;i++){
					 num[i]=(double)tlist.get(i).rebound;
				 }
				 break;
			 case "助攻":
				 for(int i=0;i<13;i++){
					 num[i]=(double)tlist.get(i).secondaryAttack;
				 }
				 break;
				 
			 case "抢断":
				 for(int i=0;i<13;i++){
					 num[i]=(double)tlist.get(i).steal;
				 }
				 break;
				 
			 case "盖帽":
				 for(int i=0;i<13;i++){
					 num[i]=(double)tlist.get(i).blockShot;
				 }
				 break;
				 
			 default:
				 break;
			 }
			 
			 dataset.addValue(num[0], "First", "2003");  
			 dataset.addValue(num[1], "First", "2004");  
			 dataset.addValue(num[2], "First", "2005");  
			 dataset.addValue(num[3], "First", "2006");  
			 dataset.addValue(num[4], "First", "2007");  
			 dataset.addValue(num[5], "First", "2008");  
			 dataset.addValue(num[6], "First", "2009");  
			 dataset.addValue(num[7], "First", "2010");  
			 dataset.addValue(num[8], "First", "2011");
			 dataset.addValue(num[9], "First", "2012");  
			 dataset.addValue(num[10], "First", "2013");  
			 dataset.addValue(num[11], "First", "2014");  
			 dataset.addValue(num[12], "First", "2015");  
		 } 
		 
		 else{
			 double []num=new double[10];
			 double average = 0;
			 switch (linename) {
			 case "得分":
				 for(int i=0;i<10;i++){
					 num[i]=(double)mlist.get(i).homeScore;
				 }
				 average=(double)tp.score;
				 break;
			 case "篮板":
				 for(int i=0;i<10;i++){
					 num[i]=(double)mlist.get(i).homeTeamDeffensiveRebound+(double)mlist.get(i).homeTeamOffensiveRebound;
				 }
				 average=(double)tp.rebound;
				 break;
			 case "助攻":
				 for(int i=0;i<10;i++){
					 num[i]=(double)mlist.get(i).homeTeamSecondaryAttack;
				 }
				 average=(double)tp.secondaryAttack;
				 break;
				 
			 case "抢断":
				 for(int i=0;i<10;i++){
					 num[i]=(double)mlist.get(i).homeTeamSteal;
				 }
				 average=(double)tp.steal;
				 break;
				 
			 case "盖帽":
				 for(int i=0;i<10;i++){
					 num[i]=(double)mlist.get(i).homeTeamBlockShot;
				 }
				 average=(double)tp.blockShot;
				 break;
				 
			 default:
				 break;
			 }
			 
			 dataset.addValue(num[0], "First", "1");  
			 dataset.addValue(num[1], "First", "2");  
			 dataset.addValue(num[2], "First", "3");  
			 dataset.addValue(num[3], "First", "4");  
			 dataset.addValue(num[4], "First", "5");  
			 dataset.addValue(num[5], "First", "6");  
			 dataset.addValue(num[6], "First", "7");  
			 dataset.addValue(num[7], "First", "8");  
			 dataset.addValue(num[8], "First", "9");
			 dataset.addValue(num[9], "First", "10");
			 dataset.addValue(average, "Second", "1");  
			 dataset.addValue(average, "Second", "2");  
			 dataset.addValue(average, "Second", "3");  
			 dataset.addValue(average, "Second", "4");  
			 dataset.addValue(average, "Second", "5");  
			 dataset.addValue(average, "Second", "6");  
			 dataset.addValue(average, "Second", "7");  
			 dataset.addValue(average, "Second", "8");  
			 dataset.addValue(average, "Second", "9");
			 dataset.addValue(average, "Second", "10");
			 
		 }
		 return dataset;
	 }

		//球员折线的参数 
	 public  CategoryDataset getLineDatasetP(String season,String player,String linename){
		 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		 if(season.equals("regular")){
			 double []num=new double[13];
			 switch (linename) {
			 case "得分":
				 for(int i=0;i<13;i++){
					 num[i]=(double)plist.get(i).score;
				 }
				 break;
			 case "篮板":
				 for(int i=0;i<13;i++){
					 num[i]=(double)plist.get(i).rebound;
				 }
				 break;
			 case "助攻":
				 for(int i=0;i<13;i++){
					 num[i]=(double)plist.get(i).secondaryAttack;
				 }
				 break;
				 
			 case "抢断":
				 for(int i=0;i<13;i++){
					 num[i]=(double)plist.get(i).steal;
				 }
				 break;
				 
			 case "盖帽":
				 for(int i=0;i<13;i++){
					 num[i]=(double)plist.get(i).blockShot;
				 }
				 break;
				 
			 default:
				 break;
			 }
			 
			 dataset.addValue(num[0], "First", "2003");  
			 dataset.addValue(num[1], "First", "2004");  
			 dataset.addValue(num[2], "First", "2005");  
			 dataset.addValue(num[3], "First", "2006");  
			 dataset.addValue(num[4], "First", "2007");  
			 dataset.addValue(num[5], "First", "2008");  
			 dataset.addValue(num[6], "First", "2009");  
			 dataset.addValue(num[7], "First", "2010");  
			 dataset.addValue(num[8], "First", "2011");
			 dataset.addValue(num[9], "First", "2012");  
			 dataset.addValue(num[10], "First", "2013");  
			 dataset.addValue(num[11], "First", "2014");  
			 dataset.addValue(num[12], "First", "2015");  
		 } 
		 
		 else{
			 double []num=new double[10];
			 double average = 0;
			 switch (linename) {
			 case "得分":
				 for(int i=0;i<10;i++){
					 num[i]=(double)pmlist.get(i).score;
				 }
				 average=(double)pp.score;
				 break;
			 case "篮板":
				 for(int i=0;i<10;i++){
					 num[i]=(double)pmlist.get(i).rebound;
				 }
				 average=(double)pp.rebound;
				 break;
			 case "助攻":
				 for(int i=0;i<10;i++){
					 num[i]=(double)pmlist.get(i).secondaryAttack;
				 }
				 average=(double)pp.secondaryAttack;
				 break;
				 
			 case "抢断":
				 for(int i=0;i<10;i++){
					 num[i]=(double)pmlist.get(i).steal;
				 }
				 average=(double)pp.steal;
				 break;
				 
			 case "盖帽":
				 for(int i=0;i<10;i++){
					 num[i]=(double)pmlist.get(i).blockShot;
				 }
				 average=(double)pp.blockShot;
				 break;
				 
			 default:
				 break;
			 }
			 
			 dataset.addValue(num[0], "First", "1");  
			 dataset.addValue(num[1], "First", "2");  
			 dataset.addValue(num[2], "First", "3");  
			 dataset.addValue(num[3], "First", "4");  
			 dataset.addValue(num[4], "First", "5");  
			 dataset.addValue(num[5], "First", "6");  
			 dataset.addValue(num[6], "First", "7");  
			 dataset.addValue(num[7], "First", "8");  
			 dataset.addValue(num[8], "First", "9");
			 dataset.addValue(num[9], "First", "10");
			 dataset.addValue(average, "Second", "1");  
			 dataset.addValue(average, "Second", "2");  
			 dataset.addValue(average, "Second", "3");  
			 dataset.addValue(average, "Second", "4");  
			 dataset.addValue(average, "Second", "5");  
			 dataset.addValue(average, "Second", "6");  
			 dataset.addValue(average, "Second", "7");  
			 dataset.addValue(average, "Second", "8");  
			 dataset.addValue(average, "Second", "9");
			 dataset.addValue(average, "Second", "10");
			 
		 }
		 return dataset;
	 }
	 
	 
	 public CategoryDataset createRadarDataset(){
		 
		 String s = "First";
		  String s1 = "Second";
		  String s2 = "Third";
		  String s3 = "得分";
		  String s4 = "篮板";
		  String s5 = "助攻";
		  String s6 = "抢断";
		  String s7 = "盖帽";
		  DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		  defaultcategorydataset.addValue((double)pp.score, s, s3);
		  defaultcategorydataset.addValue((double)pp.rebound, s, s4);
		  defaultcategorydataset.addValue((double)pp.secondaryAttack, s, s5);
		  defaultcategorydataset.addValue((double)pp.steal, s, s6);
		  defaultcategorydataset.addValue((double)pp.blockShot, s, s7);
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
