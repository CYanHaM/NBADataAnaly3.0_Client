package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerTechMPO;
import PO.TeamTechPO;

public class PlayerTech implements StatsInfo{

	@Override
	public TeamTechPO getTeamTech(String teamname, String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MatchPO> getRecentMatch(String team,String season) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<MatchPO> getMatchForYear(String team, String season,
			int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,
			String season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerTechMPO> getPlayerForYear(String name,
			String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

}
