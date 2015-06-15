package blservice.statsblservice;

import PO.PlayerStatsPO;
import PO.PlayerTechPO;
import PO.TeamStatsPO;
import PO.TeamTechPO;

public interface StatsBLService {

	public TeamStatsPO getTeamtechInterval(String team,double a,String season);
	public PlayerStatsPO getPlayerTechInterval(String player,double a,String season);
	public TeamTechPO getTeamChangePlayoffs(String team,String season,double a);
	public PlayerTechPO getPlayerChangePlayoffs(String player,String season,double a);
	public TeamTechPO getTeamChangeYear(String team,String seasona,String seasonb);
	public PlayerTechPO getPlayerChangeYear(String player,String seasona,String seasonb);

}
