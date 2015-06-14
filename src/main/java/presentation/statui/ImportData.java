package presentation.statui;

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
	TeamStatsPO tsp=new TeamStatsPO();

	 
	public ImportData(String team,String season,double a){
		statsinfo=new PlayerTech();
		statsbl=new Stats();
		tp=statsinfo.getTeamTech(team, season, 1);
		tr=statsinfo.getTeamRank(team, season, 1);
		ata=statsinfo.getAllTeamAverage(season, 1);
		tsp=statsbl.getTeamtechInterval(team, a, season);
	}
	
	public Object[][] getall(){
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
	
	public Object[][] getoff(){
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
	
	public Object[][] getdeff(){
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
	
}
