package dataservice.playertechdataservice;

import java.util.ArrayList;

import PO.PlayerTechPO;
import VO.PlayerTechVO;

public interface ShowDataService {
	
		public ArrayList<PlayerTechPO> showSeasonPlayerData (String season);
		
		public PlayerTechPO showKeyData (String name,String team );
		
		public void refresh();
		
		public ArrayList<PlayerTechVO> ascend(String type);
		
		public ArrayList<PlayerTechVO> descend(String type);
}
