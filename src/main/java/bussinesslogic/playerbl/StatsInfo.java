package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamTechPO;

public interface StatsInfo {
	//常规赛
	public TeamTechPO getTeamTech(String teamname,String season,int ifRegular);
	//常规赛
	public PlayerTechPO getPlayerTech(String player,String season,int ifRegular);
	//最后20场比赛
	public ArrayList<MatchPO> getRecentMatch(String team,String season);
	//球员最后20场
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,String season);
	//球队的所有常规赛
	public ArrayList<TeamTechPO>getMatchForYear(String team);
	//常规赛 罚球 失误 上场时间 命中率 犯规 三分
	public ArrayList<PlayerTechPO>getPlayerForYear(String name);
	//常规赛 得分篮板助攻抢断盖帽犯规失误投篮命中率三分命中数罚球出手数排名
	public TeamTechPO getTeamRank(String teamname,String season,int ifRegular);
	//常规赛 上面属性的30均值
	public TeamTechPO getAllTeamAverage(String season,int ifRegular);
	//常规赛 球员属性排名 得分篮板助攻抢断盖帽犯规失误投篮命中率三分命中数罚球出手数上场时间
	public PlayerTechPO getPlayerRank(String player,String season,int ifRegular);
	//均值
	public PlayerTechPO getAllPlayerAverage(String season,int ifRegular);
	//WE小写
	public TeamTechPO getDivTeamAverage(String season,int ifRegular,String division);
	public PlayerTechPO getDivPlayerAverage(String season,int ifRegular,String division);
	//参加过的常规赛和季后赛
	public ArrayList<String> getTeamSeasonList(String team);
	public ArrayList<String> getPlayerSeasonList(String player);



}
