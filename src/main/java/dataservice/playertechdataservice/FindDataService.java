package dataservice.playertechdataservice;

import java.util.ArrayList;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.ScreeningConditionVO;

public interface FindDataService {
	
	public ArrayList<PlayerTechMPO> findHotPlayerToday(String date, String keyword);
	
	public ArrayList<PlayerTechPO> findSeasonHotPlayer(String keyword);
	
	public ArrayList<PlayerTechPO> sift(ArrayList<ScreeningConditionVO> list);
	
	public ArrayList<PlayerTechPO> findPlayerByletter(char letter);
	
	public ArrayList<PlayerTechPO> findPlayerByLetter(char letter);
	//杩涙鏈�蹇悆鍛�
	public ArrayList<PlayerTechPO> findFastImprovingPlayer();
}
