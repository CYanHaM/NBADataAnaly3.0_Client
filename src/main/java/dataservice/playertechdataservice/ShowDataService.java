package dataservice.playertechdataservice;

import java.util.ArrayList;

import PO.PlayerTechPO;

public interface ShowDataService {
	
		public ArrayList<PlayerTechPO> showSeasonPlayerData (String season);
		
		public PlayerTechPO showKeyData (String name,String team );
		
		public void refresh();
		
		public ArrayList<PlayerTechPO> ascend(String type,String season);
		
		public ArrayList<PlayerTechPO> descend(String type,String season);
}
