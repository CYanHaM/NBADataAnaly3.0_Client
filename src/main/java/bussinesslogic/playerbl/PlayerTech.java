package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
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

	@Override
	public PlayerTechPO getPlayerTech(String player, String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamTechPO getTeamRank(String teamname, String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamTechPO getAllTeamAverage(String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getPlayerRank(String player, String season,
			int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getAllPlayerAverage(String season, int ifRegular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamTechPO getDivTeamAverage(String season, int ifRegular,
			String division) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerTechPO getDivPlayerAverage(String season, int ifRegular,
			String division) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTeamSeasonList(String team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayerSeasonList(String player) {
		// TODO Auto-generated method stub
		return null;
	}

}
