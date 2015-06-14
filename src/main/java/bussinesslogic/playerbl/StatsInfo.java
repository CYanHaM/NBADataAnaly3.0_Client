package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.TeamTechPO;

public interface StatsInfo {
	
	public TeamTechPO getTeamTech(String teamname,String season,int ifRegular);
	public ArrayList<MatchPO> getRecentMatch(String team);
	

}
