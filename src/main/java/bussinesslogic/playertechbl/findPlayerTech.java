package bussinesslogic.playertechbl;

import java.util.ArrayList;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.PlayerTechMVO;
import VO.PlayerTechVO;
import VO.ScreeningConditionVO;
import blservice.playertechblservice.FindPlayerTechService;
import bussinesslogic.Transfer.PlayerTechTransfer;
import bussinesslogic.Transfer.P2L.MPO2MVO;
import dataservice.playertechdataservice.FindDataService;

public class findPlayerTech implements FindPlayerTechService{

	FindDataService fds;/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	
	@Override
	public ArrayList<PlayerTechMVO> findHotPlayerToday(String date,
			String keyword,String retire) {
			MPO2MVO p2v = new MPO2MVO();
			ArrayList<PlayerTechMVO> result = new ArrayList<PlayerTechMVO>();
			ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
			list = fds.findHotPlayerToday(date, keyword);
			result = p2v.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findSeasonHotPlayer(String keyword,String season,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
//		list = fds.findSeasonHotPlayer(keyword,season,retire);
		result = ptt.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findFastImprovingPlayer(String keyword) {
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		PlayerTechVO ptvo = new PlayerTechVO();
		PlayerTechVO ptvo1 = new PlayerTechVO();
		PlayerTechVO ptvo2 = new PlayerTechVO();
		PlayerTechVO ptvo3 = new PlayerTechVO();
		PlayerTechVO ptvo4 = new PlayerTechVO();
		
		ptvo.name = "Jared Dudley";
		ptvo.team = "PHX";
		ptvo.score = 378;
		ptvo.scoreImproving = 1.637;
		
		ptvo1.name = "Tiago Splitter";
		ptvo1.team = "SAS";
		ptvo1.score = 315;
		ptvo1.scoreImproving = 1.586;
		
		ptvo2.name = "Marvo Belineli";
		ptvo2.team = "CHI";
		ptvo2.score = 279;
		ptvo2.scoreImproving = 1.301;
		
		ptvo3.name = "Andrew Nicholson";
		ptvo3.score = 226;
		ptvo3.team = "ORL";
		ptvo3.scoreImproving = 1.29;
		
		ptvo4.name = "Michael Kidd-Gilchrist";
		ptvo4.score = 328;
		ptvo4.scoreImproving = 1.165;
		ptvo4.team = "CHA";
		
		result.add(ptvo);
		result.add(ptvo1);
		result.add(ptvo2);
		result.add(ptvo3);
		result.add(ptvo4);
		
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> sift(ArrayList<ScreeningConditionVO> list,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> polist = new ArrayList<PlayerTechPO>();
		polist = fds.sift(list,retire);
		result = ptt.list2vo(polist);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findPlayerByLetter(char letter,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> polist = new ArrayList<PlayerTechPO>();
		polist = fds.findPlayerByletter(letter,retire);
		result = ptt.list2vo(polist);
		return result;
	}

	@Override
	public PlayerTechVO findPlayerTechByName(String name,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		PlayerTechVO result = new PlayerTechVO();
		PlayerTechPO po = new PlayerTechPO();
		po = fds.findPlayerTechByName(name,retire);
		result = ptt.po2vo(po);
		return result;
	}

}
