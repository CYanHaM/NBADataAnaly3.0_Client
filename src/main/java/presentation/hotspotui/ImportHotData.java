package presentation.hotspotui;

import java.util.ArrayList;

import TypeEnum.TeamTechEnum;
import VO.PlayerTechMVO;
import VO.PlayerTechVO;
import VO.TeamTechVO;
import blservice.playertechblservice.FindPlayerTechService;
import blservice.teamtechblservice.TeamTechBLService;
import bussinesslogic.TeamTech.TeamTech;
import bussinesslogic.playertechbl.FindPlayerTech;

public class ImportHotData {
	public FindPlayerTechService fts;
	public TeamTechBLService tts;
	
	public ImportHotData() {
	}
	
	public ArrayList<PlayerTechMVO> findHotPlayerToday(String date, String keyword){
		fts=new FindPlayerTech();
		return fts.findHotPlayerToday(date, keyword);
	}
	
	public ArrayList<PlayerTechVO> findSeasonHotPlayer(String keyword){
		fts=new FindPlayerTech();
		return fts.findSeasonHotPlayer(keyword);
	}
	
	public ArrayList<PlayerTechVO> findFastImprovingPlayer(String keyword){
		fts=new FindPlayerTech();
		return fts.findFastImprovingPlayer(keyword);
	}
	
	public ArrayList<TeamTechVO> findSeasonHotTeam(TeamTechEnum DataType){
		tts=new TeamTech();
		return tts.findSeasonHotTeam(DataType);
	}
	
}
