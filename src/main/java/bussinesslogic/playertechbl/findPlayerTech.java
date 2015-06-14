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
			String keyword) {
			MPO2MVO p2v = new MPO2MVO();
			ArrayList<PlayerTechMVO> result = new ArrayList<PlayerTechMVO>();
			ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
			list = fds.findHotPlayerToday(date, keyword);
			result = p2v.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findSeasonHotPlayer(String keyword) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
		list = fds.findSeasonHotPlayer(keyword);
		result = ptt.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findFastImprovingPlayer(String keyword) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
		list = fds.findFastImprovingPlayer(keyword);
		result = ptt.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> sift(ArrayList<ScreeningConditionVO> list) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> polist = new ArrayList<PlayerTechPO>();
		polist = fds.sift(list);
		result = ptt.list2vo(polist);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findPlayerByLetter(char letter) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> polist = new ArrayList<PlayerTechPO>();
		polist = fds.findPlayerByletter(letter);
		result = ptt.list2vo(polist);
		return result;
	}

	@Override
	public PlayerTechVO findPlayerTechByName(String name) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		PlayerTechVO result = new PlayerTechVO();
		PlayerTechPO po = new PlayerTechPO();
		po = fds.findPlayerTechByName(name);
		result = ptt.po2vo(po);
		return result;
	}

}
