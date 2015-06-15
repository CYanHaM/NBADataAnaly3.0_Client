package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerStatsPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamStatsPO;
import PO.TeamTechPO;

public interface StatsInfo {
	
	public TeamTechPO getTeamTech(String teamname,String season,int ifRegular);
	public PlayerTechPO getPlayerTech(String player,String season,int ifRegular);
	public ArrayList<MatchPO> getRecentMatch(String team,String season);
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,String season);
	public ArrayList<MatchPO>getMatchForYear(String team,String season,int ifRegular);
	public ArrayList<PlayerTechMPO>getPlayerForYear(String name,String season,int ifRegular);
	public TeamTechPO getTeamRank(String teamname,String season,int ifRegular);
	public TeamTechPO getAllTeamAverage(String season,int ifRegular);
	public PlayerTechPO getPlayerRank(String player,String season,int ifRegular);
	public PlayerTechPO getAllPlayerAverage(String season,int ifRegular);
	public TeamTechPO getDivTeamAverage(String season,int ifRegular,String division);
	public PlayerTechPO getDivPlayerAverage(String season,int ifRegular,String division);
	public String[] getTeamSeasonList(String team);
	public String[] getPlayerSeasonList(String player);



}
