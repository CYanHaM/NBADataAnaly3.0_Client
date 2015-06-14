package dataservice.playertechdataservice;

import java.util.ArrayList;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.PlayerTechVO;
import VO.ScreeningConditionVO;

public interface FindDataService {
	
	public ArrayList<PlayerTechMPO> findHotPlayerToday(String date, String keyword);
	
	public ArrayList<PlayerTechPO> findSeasonHotPlayer(String keyword);
	
	public ArrayList<PlayerTechPO> sift(ArrayList<ScreeningConditionVO> list);
	
	public ArrayList<PlayerTechPO> findPlayerByletter(char letter);

	public ArrayList<PlayerTechPO> findFastImprovingPlayer(String keyword);
	
	public PlayerTechPO findPlayerTechByName(String name);
}
