package presentation.playerui;

import java.util.ArrayList;

import VO.PlayerTechVO;
import VO.PlayerVO;
import VO.ScreeningConditionVO;
import VO.TeamVO;
import blservice.playerinfoblservice.PlayerInfoService;
import blservice.playertechblservice.FindPlayerTechService;
import blservice.playertechblservice.ShowPlayerTechService;
import bussinesslogic.playerinfobl.PlayerInfo;
import bussinesslogic.playertechbl.FindPlayerTech;
import bussinesslogic.playertechbl.ShowPlayerTech;

public class ImportPlayer {
	/**
	 * 界面层通过BL层的接口导入并处理数据
	 * @author blisscry
	 * @date 2015年3月31日01:35:32
	 * @version 1.0
	 */
	//定义层间传输接口
	PlayerInfoService pis;
	FindPlayerTechService fpt;
	ShowPlayerTechService spt;

	public ImportPlayer(){
		pis = new PlayerInfoBL();
		fpt = new FindPlayerTech();
		spt = new ShowPlayerTech();
	}

	public ArrayList<PlayerTechVO> getPlayerTechAscend(String DataType){
		return spt.ascend(DataType);
	}

	public ArrayList<PlayerTechVO> getPlayerTechDescend(String DataType){
		return spt.descend(DataType);
	}
	
	public ArrayList<PlayerTechVO> sift(ArrayList<ScreeningConditionVO> scvo){
		return fpt.sift(scvo);
	}
	
	public PlayerTechVO findPlayerTechByName(String name){
		return fpt.findPlayerTechByName(name);
	}
	
	public ArrayList<PlayerTechVO> findPlayerByLetter(char letter){
		return fpt.findPlayerByLetter(letter);
	}

	public ArrayList<PlayerVO> findByTeam(String tvo){
		return pis.findByTeam(tvo);
	}
	
	public PlayerVO showPlayerInfo (String name){
		return pis.showPlayerInfo(name);
	}

}
