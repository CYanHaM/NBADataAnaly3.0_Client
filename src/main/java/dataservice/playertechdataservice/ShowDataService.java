package dataservice.playertechdataservice;

import java.util.ArrayList;

import PO.PlayerTechPO;
import VO.PlayerTechVO;

public interface ShowDataService {
	
	   //鏉╂柨娲栫挧娑橆劀閻炲啫鎲抽弫鐗堝祦
		public ArrayList<PlayerTechPO> showSeasonPlayerData (String season);
		
		//閺屻儳婀呴弻鎰閻炲啫鎲抽崗鎶芥暛閺佺増宓�
		public PlayerTechPO showKeyData (String name,String team );

		public ArrayList<PlayerTechPO> ascend(String type);
		
		public ArrayList<PlayerTechPO> descend(String type);
		
		public void refresh();
}
