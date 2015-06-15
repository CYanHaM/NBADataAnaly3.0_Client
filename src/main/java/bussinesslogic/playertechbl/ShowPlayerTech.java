package bussinesslogic.playertechbl;

import java.util.ArrayList;

import PO.PlayerTechPO;
import VO.PlayerTechVO;
import blservice.playertechblservice.ShowPlayerTechService;
import bussinesslogic.Transfer.PlayerTechTransfer;
import dataservice.playertechdataservice.ShowDataService;

public class ShowPlayerTech  implements ShowPlayerTechService {

	ShowDataService sd ;/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	@Override
	public ArrayList<PlayerTechVO> showSeasonPlayerData() {
		ArrayList<PlayerTechPO> ptpo = new ArrayList<PlayerTechPO>();
		ptpo = sd.showSeasonPlayerData();
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> ptvo = ptt.list2vo(ptpo);
		return ptvo;
	}
	@Override
	public PlayerTechVO showKeyData(String name, String team) {
		PlayerTechPO ptpo = new PlayerTechPO();
		ptpo = sd.showKeyData(name, team);
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		PlayerTechVO ptvo = ptt.po2vo(ptpo);
		return ptvo;
	}
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void PlayerTechIni() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<PlayerTechVO> ascend(String type) {
		ShowPlayerTech sp = new ShowPlayerTech();
	}
	
	@Override
	public ArrayList<PlayerTechVO> descend(String type) {
		transPla tp = new transPla();
		String con = tp.translate(type);
		ArrayList<PlayerTechPO>polist = sd.descend(con);
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO>volist = ptt.list2vo(polist);
		return volist;
	}
	
}