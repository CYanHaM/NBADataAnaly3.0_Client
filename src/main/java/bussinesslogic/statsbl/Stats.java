package bussinesslogic.statsbl;

import java.util.ArrayList;

import data.StatsData;
import dataservice.StatsDataService;
import blservice.statsblservice.StatsBLService;
import bussinesslogic.playerbl.PlayerTech;
import bussinesslogic.playerbl.StatsInfo;
import PO.MatchPO;
import PO.PlayerStatsPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamStatsPO;
import PO.TeamTechPO;

public class Stats implements StatsBLService{
	
//置信区间	
	public double[] calculateInterval(double[] str,double a){
		StatsDataService sds=new StatsData();
		double[] interval=new double[2];
		double sum=0;
		for(int i=0;i<20;i++){
			sum=sum+str[i];
		}
		double average=sum/20;
		double variance=0;
		for(int i=0;i<20;i++){
			variance=variance+(str[i]-average)*(str[i]-average);
		}
		variance=variance/19;
		interval[0]=average-Math.sqrt(variance/20)*sds.calculateTDistribution(19, a);
		interval[1]=average+Math.sqrt(variance/20)*sds.calculateTDistribution(19, a);
		return interval;
		
	}

//球队统计（赛季分析）	
//获得球队置信区间
	public TeamStatsPO getTeamtechInterval(String team,double a,String season){
		StatsInfo si=new PlayerTech();
		ArrayList<MatchPO>mlist=new ArrayList<MatchPO>();
		mlist=si.getRecentMatch(team,season);
		
		double[] stats=new double[20];
//		TeamStatsPO intervalHome=new TeamStatsPO();
		TeamStatsPO intervalGuest=new TeamStatsPO();
//主队计算
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeScore;
//		}
//		intervalHome.score[0]=calculateInterval(stats,a)[0];
//		intervalHome.score[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)(mlist.get(i).homeTeamDeffensiveRebound+mlist.get(i).homeTeamOffensiveRebound);
//		}
//		intervalHome.rebound[0]=calculateInterval(stats,a)[0];
//		intervalHome.rebound[1]=calculateInterval(stats,a)[1];
//
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeTeamSecondaryAttack;
//		}
//		intervalHome.assist[0]=calculateInterval(stats,a)[0];
//		intervalHome.assist[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeTeamSteal;
//		}
//		intervalHome.steal[0]=calculateInterval(stats,a)[0];
//		intervalHome.steal[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeTeamBlockShot;
//		}
//		intervalHome.block[0]=calculateInterval(stats,a)[0];
//		intervalHome.block[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeFault;
//		}
//		intervalHome.turnover[0]=calculateInterval(stats,a)[0];
//		intervalHome.turnover[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeTeamFoul;
//		}
//		intervalHome.foul[0]=calculateInterval(stats,a)[0];
//		intervalHome.foul[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homePenaltyShot;
//		}
//		intervalHome.penaltyshot[0]=calculateInterval(stats,a)[0];
//		intervalHome.penaltyshot[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).homeThreeShotIn*3;
//		}
//		intervalHome.threeshot[0]=calculateInterval(stats,a)[0];
//		intervalHome.threeshot[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)(mlist.get(i).homeShotIn/mlist.get(i).homeShot);
//		}
//		intervalHome.fg[0]=calculateInterval(stats,a)[0];
//		intervalHome.fg[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)mlist.get(i).guestScore;
//		}
//		intervalHome.oppscore[0]=calculateInterval(stats,a)[0];
//		intervalHome.oppscore[1]=calculateInterval(stats,a)[1];
//		
//		for(int i=0;i<20;i++){
//			stats[i]=(double)(mlist.get(i).guestTeamDeffensiveRebound+mlist.get(i).guestTeamOffensiveRebound);
//		}
//		intervalHome.opprebound[0]=calculateInterval(stats,a)[0];
//		intervalHome.opprebound[1]=calculateInterval(stats,a)[1];

//客队计算
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestScore;
		}
		intervalGuest.score[0]=calculateInterval(stats,a)[0];
		intervalGuest.score[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).guestTeamDeffensiveRebound+mlist.get(i).guestTeamOffensiveRebound);
		}
		intervalGuest.rebound[0]=calculateInterval(stats,a)[0];
		intervalGuest.rebound[1]=calculateInterval(stats,a)[1];

		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestTeamSecondaryAttack;
		}
		intervalGuest.assist[0]=calculateInterval(stats,a)[0];
		intervalGuest.assist[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestTeamSteal;
		}
		intervalGuest.steal[0]=calculateInterval(stats,a)[0];
		intervalGuest.steal[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestTeamBlockShot;
		}
		intervalGuest.block[0]=calculateInterval(stats,a)[0];
		intervalGuest.block[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestFault;
		}
		intervalGuest.turnover[0]=calculateInterval(stats,a)[0];
		intervalGuest.turnover[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestTeamFoul;
		}
		intervalGuest.foul[0]=calculateInterval(stats,a)[0];
		intervalGuest.foul[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestPenaltyShot;
		}
		intervalGuest.penaltyshot[0]=calculateInterval(stats,a)[0];
		intervalGuest.penaltyshot[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestThreeShotIn*3;
		}
		intervalGuest.threeshot[0]=calculateInterval(stats,a)[0];
		intervalGuest.threeshot[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).guestShotIn/mlist.get(i).guestShot);
		}
		intervalGuest.fg[0]=calculateInterval(stats,a)[0];
		intervalGuest.fg[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestScore+6;
		}
		intervalGuest.oppscore[0]=calculateInterval(stats,a)[0];
		intervalGuest.oppscore[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).guestTeamDeffensiveRebound+mlist.get(i).guestTeamOffensiveRebound+5);
		}
		intervalGuest.opprebound[0]=calculateInterval(stats,a)[0];
		intervalGuest.opprebound[1]=calculateInterval(stats,a)[1];
		
		if(team.equals(mlist.get(0).homeTeam))
			return intervalGuest;
		else
			return intervalGuest;
	}
	
	
//球员统计
//获得球员置信区间
	public PlayerStatsPO getPlayerTechInterval(String player,double a,String season){
		
		StatsInfo si=new PlayerTech();
		ArrayList<PlayerTechMPO>mlist=new ArrayList<PlayerTechMPO>();
		mlist=si.getRecentPlayerM(player,season);
		
		double[] stats=new double[20];
		PlayerStatsPO intervalPlayer=new PlayerStatsPO();
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).score;
		}
		intervalPlayer.score[0]=calculateInterval(stats,a)[0];
		intervalPlayer.score[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).rebound);
		}
		intervalPlayer.rebound[0]=calculateInterval(stats,a)[0];
		intervalPlayer.rebound[1]=calculateInterval(stats,a)[1];

		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).secondaryAttack;
		}
		intervalPlayer.assist[0]=calculateInterval(stats,a)[0];
		intervalPlayer.assist[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).steal;
		}
		intervalPlayer.steal[0]=calculateInterval(stats,a)[0];
		intervalPlayer.steal[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).blockShot;
		}
		intervalPlayer.block[0]=calculateInterval(stats,a)[0];
		intervalPlayer.block[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).fault;
		}
		intervalPlayer.turnover[0]=calculateInterval(stats,a)[0];
		intervalPlayer.turnover[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).foul;
		}
		intervalPlayer.foul[0]=calculateInterval(stats,a)[0];
		intervalPlayer.foul[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).penaltyShot;
		}
		intervalPlayer.penaltyshot[0]=calculateInterval(stats,a)[0];
		intervalPlayer.penaltyshot[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).threeShotIn*3;
		}
		intervalPlayer.threeshot[0]=calculateInterval(stats,a)[0];
		intervalPlayer.threeshot[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).shotIn/mlist.get(i).shot);
		}
		intervalPlayer.fg[0]=calculateInterval(stats,a)[0];
		intervalPlayer.fg[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).time;
		}
		intervalPlayer.time[0]=calculateInterval(stats,a)[0];
		intervalPlayer.time[1]=calculateInterval(stats,a)[1];
		
		return intervalPlayer;
		
	}

//单总体均值检验
	public int singleAverageEstimate(double[] str, double average,double a){
		StatsDataService sds=new StatsData();
		int res=0;
		double sum=0;
		for(int i=0;i<str.length;i++){
			sum=sum+str[i];
		}
		double ave=sum/str.length;
		double variance=0;
		for(int i=0;i<str.length;i++){
			variance=variance+(str[i]-average)*(str[i]-average);
		}
		variance=variance/(str.length-1);
		double z=(ave-average)/(Math.sqrt(variance/str.length));
		double p=0;
		if(z>0)
			p=sds.calculateNormalDistribution(z);
		else
			p=1-sds.calculateNormalDistribution(z*(-1));
		
		if(p<a)
			res=1;
		else
			res=0;
		
		return res;

		
	}
	
//球队单总体均值
	public TeamTechPO getTeamChangePlayoffs(String team,String season,double a){
		StatsInfo si=new PlayerTech();
		TeamTechPO ttp=new TeamTechPO();
		TeamTechPO res=new TeamTechPO();
		ttp=si.getTeamTech(team, season, 1);
//		ttp.name="CHA";
//		ttp.score=98;
//		ttp.offensiveRebound=23;
//		ttp.defensiveRebound=30;
//		ttp.secondaryAttack=20;
//		ttp.steal=10;
//		ttp.blockShot=10;
//		ttp.fault=14;
//		ttp.penaltyShotNum=14;
//		ttp.threeShotInNum=10;
//		ttp.shotInNum=70;
//		ttp.shotNum=90;
		ArrayList<MatchPO> mlist=new ArrayList<MatchPO>();
		mlist=si.getRecentMatch(team, season);
//		mlist=creatematch();

		double[] stats=new double[20];
		for(int i=0;i<20;i++){
			if(mlist.get(i).homeTeam.equals(team)){
				stats[i]=(double)(mlist.get(i).homeScore);
			}else {
				stats[i]=(double)(mlist.get(i).guestScore);}
		}
		res.score=singleAverageEstimate(stats, ttp.score, a);
		
		for(int i=0;i<20;i++){
			if(mlist.get(i).homeTeam.equals(team)){
				stats[i]=(double)(mlist.get(i).homeTeamOffensiveRebound+mlist.get(i).homeTeamDeffensiveRebound);
			}else {
				stats[i]=(double)(mlist.get(i).guestTeamOffensiveRebound+mlist.get(i).guestTeamDeffensiveRebound);}
		}
		res.rebound=singleAverageEstimate(stats, ttp.rebound, a);
		
		for(int i=0;i<20;i++){
			if(mlist.get(i).homeTeam.equals(team)){
				stats[i]=(double)(mlist.get(i).homeTeamSecondaryAttack);
			}else {
				stats[i]=(double)(mlist.get(i).guestTeamSecondaryAttack);}
		}
		res.secondaryAttack=singleAverageEstimate(stats, ttp.secondaryAttack, a);
		
		for(int i=0;i<20;i++){
			if(mlist.get(i).homeTeam.equals(team)){
				stats[i]=(double)(mlist.get(i).homeTeamSteal);
			}else {
				stats[i]=(double)(mlist.get(i).guestTeamSteal);}
		}
		res.steal=singleAverageEstimate(stats, ttp.steal, a);
		
		for(int i=0;i<20;i++){
			if(mlist.get(i).homeTeam.equals(team)){
				stats[i]=(double)(mlist.get(i).homeTeamBlockShot);
			}else {
				stats[i]=(double)(mlist.get(i).guestTeamBlockShot);}
		}
		res.blockShot=singleAverageEstimate(stats, ttp.blockShot, a);
		
		return res;

	}
	
	
	//球员单总体均值
		public PlayerTechPO getPlayerChangePlayoffs(String player,String season,double a){
			StatsInfo si=new PlayerTech();
			PlayerTechPO ttp=new PlayerTechPO();
			PlayerTechPO res=new PlayerTechPO();
			ttp=si.getPlayerTech(player, season, 1);
			ArrayList<PlayerTechMPO> mlist=new ArrayList<PlayerTechMPO>();
			mlist=si.getRecentPlayerM(player, season);

			double[] stats=new double[20];
			for(int i=0;i<20;i++){
					stats[i]=(double)(mlist.get(i).score);
			}
			res.score=singleAverageEstimate(stats, ttp.score, a);
			
			for(int i=0;i<20;i++){
				stats[i]=(double)(mlist.get(i).rebound);
			}
			res.rebound=singleAverageEstimate(stats, ttp.rebound, a);
			
			for(int i=0;i<20;i++){
				stats[i]=(double)(mlist.get(i).secondaryAttack);
			}
			res.secondaryAttack=singleAverageEstimate(stats, ttp.secondaryAttack, a);
			
			for(int i=0;i<20;i++){
				stats[i]=(double)(mlist.get(i).steal);
			}
			res.steal=singleAverageEstimate(stats, ttp.steal, a);
			
			for(int i=0;i<20;i++){
				stats[i]=(double)(mlist.get(i).blockShot);
			}
			res.blockShot=singleAverageEstimate(stats, ttp.blockShot, a);

			
			return res;

		}
		
//秩和检验
		
		public int sumEstimate(double[] a,double[] b){
			int[] aorder=new int[a.length];
			int[] border=new int[b.length];
			int index=0;
			for(int i=0;i<a.length;i++){
				for(int j=0;j<a.length;j++){
					if(a[i]>a[j])
						index++;
				}
				for(int j=0;j<b.length;j++){
					if(a[i]>b[j])
						index++;
				}
				aorder[i]=index;
				index=0;
				
			}
			for(int i=0;i<b.length;i++){
				for(int j=0;j<a.length;j++){
					if(b[i]>a[j])
						index++;
				}
				for(int j=0;j<b.length;j++){
					if(b[i]>b[j])
						index++;
				}
				border[i]=index;
				index=0;
				
			}
			int sum=0;
			if(a.length<b.length){
				for(int i=0;i<a.length;i++){
					sum=sum+aorder[i];
				}
			}else{
				for(int i=0;i<b.length;i++){
					sum=sum+border[i];
				}
			}
			
			double u=Math.min(a.length, b.length)*(a.length+b.length+1)/2;
			double s=a.length*b.length*(a.length+b.length+1)/12;
			double uu=(sum-u)/Math.sqrt(s);
			if(Math.abs(uu)<1.96)
				return 0;
			else
				return 1;
		}
		
		public TeamTechPO getTeamChangeYear(String team,String seasona,String seasonb){
			StatsInfo si=new PlayerTech();
			TeamTechPO res=new TeamTechPO();

			ArrayList<MatchPO> mlista=new ArrayList<MatchPO>();
			ArrayList<MatchPO> mlistb=new ArrayList<MatchPO>();

			mlista=si.getRecentMatch(team, seasona);
			mlistb=si.getRecentMatch(team, seasonb);

			double[] statsa=new double[20];
			double[] statsb=new double[20];
			for(int i=0;i<20;i++){
				if(mlista.get(i).homeTeam.equals(team)){
					statsa[i]=(double)(mlista.get(i).homeScore);
				}else {
					statsa[i]=(double)(mlista.get(i).guestScore);}
				if(mlistb.get(i).homeTeam.equals(team)){
					statsb[i]=(double)(mlistb.get(i).homeScore);
				}else {
					statsb[i]=(double)(mlistb.get(i).guestScore);}
			}
			res.score=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				if(mlista.get(i).homeTeam.equals(team)){
					statsa[i]=(double)(mlista.get(i).homeTeamOffensiveRebound+mlista.get(i).homeTeamDeffensiveRebound);
				}else {
					statsa[i]=(double)(mlista.get(i).guestTeamOffensiveRebound+mlista.get(i).guestTeamDeffensiveRebound);
				}	
				if(mlistb.get(i).homeTeam.equals(team)){
					statsb[i]=(double)(mlistb.get(i).homeTeamOffensiveRebound+mlista.get(i).homeTeamDeffensiveRebound);
				}else {
					statsb[i]=(double)(mlistb.get(i).guestTeamOffensiveRebound+mlista.get(i).guestTeamDeffensiveRebound);
				}
			}
			res.rebound=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				if(mlista.get(i).homeTeam.equals(team)){
					statsa[i]=(double)(mlista.get(i).homeTeamSecondaryAttack);
				}else {
					statsa[i]=(double)(mlista.get(i).guestTeamSecondaryAttack);}
				if(mlistb.get(i).homeTeam.equals(team)){
					statsb[i]=(double)(mlistb.get(i).homeTeamSecondaryAttack);
				}else {
					statsb[i]=(double)(mlistb.get(i).guestTeamSecondaryAttack);}
			}
			res.secondaryAttack=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				if(mlista.get(i).homeTeam.equals(team)){
					statsa[i]=(double)(mlista.get(i).homeTeamSteal);
				}else {
					statsa[i]=(double)(mlista.get(i).guestTeamSteal);}
				if(mlistb.get(i).homeTeam.equals(team)){
					statsb[i]=(double)(mlistb.get(i).homeTeamSteal);
				}else {
					statsb[i]=(double)(mlistb.get(i).guestTeamSteal);}
			}
			res.steal=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				if(mlista.get(i).homeTeam.equals(team)){
					statsa[i]=(double)(mlista.get(i).homeTeamBlockShot);
				}else {
					statsa[i]=(double)(mlista.get(i).guestTeamBlockShot);}
				if(mlistb.get(i).homeTeam.equals(team)){
					statsb[i]=(double)(mlistb.get(i).homeTeamBlockShot);
				}else {
					statsb[i]=(double)(mlistb.get(i).guestTeamBlockShot);}
			}
			res.blockShot=sumEstimate(statsa, statsb);
			
			return res;

		}
		
		
		//球员秩和分析
		public PlayerTechPO getPlayerChangeYear(String player,String seasona,String seasonb){
			StatsInfo si=new PlayerTech();
			PlayerTechPO res=new PlayerTechPO();

			ArrayList<PlayerTechMPO> mlista=new ArrayList<PlayerTechMPO>();
			ArrayList<PlayerTechMPO> mlistb=new ArrayList<PlayerTechMPO>();

			mlista=si.getRecentPlayerM(player, seasona);
			mlistb=si.getRecentPlayerM(player, seasonb);

			double[] statsa=new double[20];
			double[] statsb=new double[20];
			
			for(int i=0;i<20;i++){
				statsa[i]=(double)(mlista.get(i).score);
				statsb[i]=(double)(mlistb.get(i).score);
			}
			res.score=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				statsa[i]=(double)(mlista.get(i).rebound);
				statsb[i]=(double)(mlistb.get(i).rebound);
			}
			res.rebound=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				statsa[i]=(double)(mlista.get(i).secondaryAttack);
				statsb[i]=(double)(mlistb.get(i).secondaryAttack);
			}
			res.secondaryAttack=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				statsa[i]=(double)(mlista.get(i).steal);
				statsb[i]=(double)(mlistb.get(i).steal);
			}
			res.steal=sumEstimate(statsa, statsb);
			
			for(int i=0;i<20;i++){
				statsa[i]=(double)(mlista.get(i).blockShot);
				statsb[i]=(double)(mlistb.get(i).blockShot);
			}
			res.blockShot=sumEstimate(statsa, statsb);
			return res;

		}
		
		
		public ArrayList<MatchPO> creatematch(){
			ArrayList<MatchPO> testpo=new ArrayList<MatchPO>();
			MatchPO po1=new MatchPO();
			po1.homeTeam="CHA";
			po1.guestTeam="HOU";
			po1.homeScore=98;
			po1.guestScore=99;
			po1.homeTeamOffensiveRebound=23;
			po1.guestTeamOffensiveRebound=30;
			po1.homeTeamDeffensiveRebound=39;
			po1.guestTeamDeffensiveRebound=24;
			po1.homeTeamSecondaryAttack=20;
			po1.guestTeamSecondaryAttack=29;
			po1.homeTeamSteal=10;
			po1.guestTeamSteal=12;
			po1.homeTeamBlockShot=10;
			po1.guestTeamBlockShot=13;
			po1.homeFault=14;
			po1.guestFault=13;
			po1.homePenaltyShot=14;
			po1.guestPenaltyShot=15;
			po1.homeThreeShotIn=10;
			po1.guestThreeShotIn=14;
			po1.homeShotIn=70;
			po1.guestShotIn=80;
			po1.homeShot=90;
			po1.guestShot=100;
			
			testpo.add(po1);

			for(int i=0;i<10;i++){
				MatchPO newpo=new MatchPO();
				newpo.homeTeam="CHA";
				newpo.guestTeam="LAC";
				newpo.homeScore=80+i;
				newpo.guestScore=90+i;
				newpo.homeShot=5;
				newpo.guestShot=8;
				testpo.add(newpo);
			}
			
			for(int i=0;i<9;i++){
				MatchPO newpo=new MatchPO();
				newpo.homeTeam="LAL";
				newpo.guestTeam="CHA";
				newpo.homeScore=80+i;
				newpo.guestScore=90+i;
				newpo.homeShot=5;
				newpo.guestShot=8;
				testpo.add(newpo);
			}
			return testpo;
		}

		public static void main(String[] args) {
			TeamTechPO po=new Stats().getTeamChangePlayoffs("CHA", "dsad",0.05 );
			System.out.println(po.score+" "+po.score+"　"+po.rebound+" "+po.rebound+"%%%%%%%");
		}

}

