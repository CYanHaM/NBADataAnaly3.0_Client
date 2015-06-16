package bussinesslogic.PlayerInfo;

import java.util.ArrayList;

import PO.PlayerPO;
import VO.PlayerVO;
import blservice.playerinfoblservice.PlayerInfoService;
import bussinesslogic.Transfer.playerinfotrans.PO2VO;
import data.info.PlayerInfo;
import dataservice.playerinfodataservice.PlayerInfoDataService;

public class PlayerInfoBL implements PlayerInfoService{

	PlayerInfoDataService pids = new PlayerInfo();
	PO2VO p2v;
	@Override
	public ArrayList<PlayerVO> showAllPlayerInfo(int retire) {
		ArrayList<PlayerPO> polist = pids.findAll(retire);
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
	public ArrayList<PlayerVO> findPlayerByLetter(char letter,int retire) {
		ArrayList<PlayerPO> ppo = new ArrayList<PlayerPO>();
		ppo = pids.findByLetter(letter,retire);
		ArrayList<PlayerVO> pvo = new ArrayList<PlayerVO>();
		pvo = p2v.list2vo(ppo);
		return pvo;
	}

	@Override
	public ArrayList<PlayerVO> findByTeam(String team,int retire) {
		ArrayList<PlayerPO> ppo = new ArrayList<PlayerPO>();
		ppo = pids.findByTeam(team,retire);
		ArrayList<PlayerVO> pvo = new ArrayList<PlayerVO>();
		pvo = p2v.list2vo(ppo);
		return pvo;
	}

	@Override
	public void PlayerInfoIni() {
		// TODO Auto-generated method stub
	}

}
