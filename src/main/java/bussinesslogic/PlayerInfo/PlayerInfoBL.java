package bussinesslogic.PlayerInfo;

import java.util.ArrayList;
import java.util.Iterator;

import PO.PlayerPO;
import VO.PlayerTechVO;
import VO.PlayerVO;
import blservice.playerinfoblservice.PlayerInfoService;
import bussinesslogic.Transfer.playerinfotrans.PO2VO;
import bussinesslogic.playertechbl.ShowPlayerTech;
import data.info.PlayerInfo;
import dataservice.playerinfodataservice.PlayerInfoDataService;

public class PlayerInfoBL implements PlayerInfoService{

	PlayerInfoDataService pids = new PlayerInfo();
	PO2VO p2v;
	@Override
	public ArrayList<PlayerVO> showAllPlayerInfo() {
		ArrayList<PlayerPO> polist = pids.findAll();
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		list = p2v.list2vo(polist);
		return list;
	}

	@Override
	public PlayerVO showPlayerInfo(String name) {
		PlayerPO ppo = new PlayerPO();
		ppo = pids.findOne(name);
		PlayerVO pvo = new PlayerVO();
		pvo = p2v.po2vo(ppo);
		return pvo;
	}

	@Override
	public ArrayList<PlayerVO> findPlayerByLetter(char letter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerVO> findByTeam(String team) {
	}

	@Override
	public void PlayerInfoIni() {
		// TODO Auto-generated method stub
		
	}

}
