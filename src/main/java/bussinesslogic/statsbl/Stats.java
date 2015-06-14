package bussinesslogic.statsbl;

import java.util.ArrayList;

import data.StatsData;
import dataservice.StatsDataService;
import bussinesslogic.playerbl.PlayerTech;
import bussinesslogic.playerbl.StatsInfo;
import PO.MatchPO;
import PO.PlayerStatsPO;
import PO.PlayerTechMPO;
import PO.TeamStatsPO;

public class Stats {
	
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
		TeamStatsPO intervalHome=new TeamStatsPO();
		TeamStatsPO intervalGuest=new TeamStatsPO();
//主队计算
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeScore;
		}
		intervalHome.score[0]=calculateInterval(stats,a)[0];
		intervalHome.score[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).homeTeamDeffensiveRebound+mlist.get(i).homeTeamOffensiveRebound);
		}
		intervalHome.rebound[0]=calculateInterval(stats,a)[0];
		intervalHome.rebound[1]=calculateInterval(stats,a)[1];

		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeTeamSecondaryAttack;
		}
		intervalHome.assist[0]=calculateInterval(stats,a)[0];
		intervalHome.assist[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeTeamSteal;
		}
		intervalHome.steal[0]=calculateInterval(stats,a)[0];
		intervalHome.steal[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeTeamBlockShot;
		}
		intervalHome.block[0]=calculateInterval(stats,a)[0];
		intervalHome.block[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeFault;
		}
		intervalHome.turnover[0]=calculateInterval(stats,a)[0];
		intervalHome.turnover[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeTeamFoul;
		}
		intervalHome.foul[0]=calculateInterval(stats,a)[0];
		intervalHome.foul[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homePenaltyShot;
		}
		intervalHome.penaltyshot[0]=calculateInterval(stats,a)[0];
		intervalHome.penaltyshot[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).homeThreeShotIn*3;
		}
		intervalHome.threeshot[0]=calculateInterval(stats,a)[0];
		intervalHome.threeshot[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).homeShotIn/mlist.get(i).homeShot);
		}
		intervalHome.fg[0]=calculateInterval(stats,a)[0];
		intervalHome.fg[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)mlist.get(i).guestScore;
		}
		intervalHome.oppscore[0]=calculateInterval(stats,a)[0];
		intervalHome.oppscore[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).guestTeamDeffensiveRebound+mlist.get(i).guestTeamOffensiveRebound);
		}
		intervalHome.opprebound[0]=calculateInterval(stats,a)[0];
		intervalHome.opprebound[1]=calculateInterval(stats,a)[1];

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
			stats[i]=(double)mlist.get(i).homeScore;
		}
		intervalGuest.oppscore[0]=calculateInterval(stats,a)[0];
		intervalGuest.oppscore[1]=calculateInterval(stats,a)[1];
		
		for(int i=0;i<20;i++){
			stats[i]=(double)(mlist.get(i).homeTeamDeffensiveRebound+mlist.get(i).homeTeamOffensiveRebound);
		}
		intervalGuest.opprebound[0]=calculateInterval(stats,a)[0];
		intervalGuest.opprebound[1]=calculateInterval(stats,a)[1];
		
		if(team.equals(mlist.get(0).homeTeam))
			return intervalHome;
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
	public int singleAverageEstimate(double[] str, double average){
		double[] interval=new double[2];
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
		
	}
//秩和检验
}
