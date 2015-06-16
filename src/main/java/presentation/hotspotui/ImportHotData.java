package presentation.hotspotui;

import java.util.ArrayList;

import TypeEnum.TeamTechEnum;
import VO.PlayerTechMVO;
import VO.PlayerTechVO;
import VO.TeamTechVO;
import blservice.playertechblservice.FindPlayerTechService;
import blservice.teamtechblservice.TeamTechBLService;
import bussinesslogic.playertechbl.findPlayerTech;
import bussinesslogic.teamTech.TeamTech;

public class ImportHotData {
	public FindPlayerTechService fts;
	public TeamTechBLService tts;
	
	public ImportHotData() {
		
	}
	
	public ArrayList<PlayerTechMVO> findHotPlayerToday(String date, String keyword){
		fts=new findPlayerTech();
		return fts.findHotPlayerToday(date, keyword);
	}
	
	//TODO I need to figure out what the retire value mean....
	public ArrayList<PlayerTechVO> findSeasonHotPlayer(String keyword,String season){
		fts=new findPlayerTech();
		return fts.findSeasonHotPlayer(keyword,season);
	}
	
	public ArrayList<PlayerTechVO> findFastImprovingPlayer(String keyword){
		fts=new findPlayerTech();
		return fts.findFastImprovingPlayer(keyword);
	}
	
	public ArrayList<TeamTechVO> findSeasonHotTeam(TeamTechEnum DataType,String season){
		tts=new TeamTech();
		return tts.findSeasonHotTeam(DataType,season);
	}
	
}
