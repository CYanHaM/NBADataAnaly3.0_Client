package presentation.teamui;

import java.util.ArrayList;

import PO.TeamTechPO;
import TypeEnum.TeamTechEnum;
import VO.PlayerVO;
import VO.TeamTechVO;
import VO.TeamVO;
import blservice.playerinfoblservice.PlayerInfoService;
import blservice.teamblservice.TeamBLservice;
import blservice.teamtechblservice.TeamTechBLService;
import bussinesslogic.PlayerInfo.PlayerInfoBL;
import bussinesslogic.teamTech.TeamTech;
import bussinesslogic.teambl.TeamBL;
import data.teamtech.TeamTechData;
import dataservice.TeamTechDataService;

public class ImportTeam {
/**
 * team panel import data
 * @author blisscry
 * @date 2015年4月29日21:07:23
 * @version 1.2
 */
	TeamTechBLService TTbs;
	TeamBLservice Tbs;
	PlayerInfoService pis;
	TeamTechDataService teamtech;

	public ImportTeam(){
		TTbs = new TeamTech();
		Tbs = new TeamBL();
		pis=new PlayerInfoBL();
		teamtech=new TeamTechData();
	}

	public ArrayList<String> getTeamSeasonList(){
		ArrayList<String> seasonlist=new ArrayList<String>();
		ArrayList<TeamTechPO> teamtechlist=teamtech.all();
		for(int i=0;i<teamtechlist.size();i++){
			seasonlist.add(teamtechlist.get(i).season);
		}
		return seasonlist;
	}
	
	public ArrayList<TeamTechVO> getTeamTechAscend(TeamTechEnum DataType,String season){
		TTbs = new TeamTech();
		return TTbs.Ascend(DataType,season);
	}

	public ArrayList<TeamTechVO> getTeamTechDescend(TeamTechEnum DataType,String season){
		TTbs = new TeamTech();
		return TTbs.Descend(DataType,season);
	}
	
	public TeamVO getTeamVO(TeamVO tvo){
		return Tbs.Show(tvo);
	}
	
	public PlayerVO Show(PlayerVO vo){
		return pis.showPlayerInfo(vo.name,1);
	}
	
	public ArrayList<PlayerVO> findByTeam(String teamname){
		return pis.findByTeam(teamname,1);
	}
}
