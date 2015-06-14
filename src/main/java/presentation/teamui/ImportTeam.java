package presentation.teamui;

import java.util.ArrayList;

import TypeEnum.TeamTechEnum;
import VO.PlayerVO;
import VO.TeamTechVO;
import VO.TeamVO;
import blservice.playerinfoblservice.PlayerInfoService;
import blservice.teamblservice.TeamBLservice;
import blservice.teamtechblservice.TeamTechBLService;
import bussinesslogic.TeamBL.Team;
import bussinesslogic.TeamTech.TeamTech;
import bussinesslogic.playerinfobl.PlayerInfo;

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

	public ImportTeam(){
		TTbs = new TeamTech();
		Tbs = new Team();
		pis=new PlayerInfoBL();
	}

	public ArrayList<TeamTechVO> getTeamTechAscend(TeamTechEnum DataType){
		TTbs = new TeamTech();
		return TTbs.Ascend(DataType);
	}

	public ArrayList<TeamTechVO> getTeamTechDescend(TeamTechEnum DataType){
		TTbs = new TeamTech();
		return TTbs.Descend(DataType);
	}
	
	public TeamVO getTeamVO(TeamVO tvo){
		return Tbs.Show(tvo);
	}
	
	public PlayerVO Show(PlayerVO vo){
		return pis.showPlayerInfo(vo.name);
	}
	
	public ArrayList<PlayerVO> findByTeam(String teamname){
		return pis.findByTeam(teamname);
	}
}
