package bussinesslogic.statsbl;

import java.util.ArrayList;

import bussinesslogic.playerbl.PlayerTech;
import bussinesslogic.playerbl.StatsInfo;
import PO.MatchPO;
import PO.TeamTechPO;
import TypeEnum.TeamEnum;

public class Stats {
	
	//球队统计
	public double[] calculateInterval(double[] str){
		return str;
		
	}

	public ArrayList<TeamTechPO> getTeamtechInterval(String team){
		StatsInfo si=new PlayerTech();
		ArrayList<MatchPO>mlist=new ArrayList<MatchPO>();
		mlist=si.getRecentMatch(team);
		
		double[] dataHome=new double[20];
		double[] resultHome=new double[20];
		double[] dataGuest=new double[20];
		double[] resultGuest=new double[20];
		TeamTechPO lowIntervalHome=new TeamTechPO();
		TeamTechPO hignIntervalHome=new TeamTechPO();
		TeamTechPO lowIntervalGuest=new TeamTechPO();
		TeamTechPO hignIntervalGuest=new TeamTechPO();

		for(int i=0;i<20;i++){
			dataHome[i]=(double)mlist.get(i).homeScore;
		}
		lowIntervalHome.score=calculateInterval(dataHome)[0];
	}
}
