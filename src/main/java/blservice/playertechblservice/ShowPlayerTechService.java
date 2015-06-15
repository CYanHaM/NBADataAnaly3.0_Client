package blservice.playertechblservice;

import java.util.ArrayList;

import VO.PlayerTechVO;

public interface ShowPlayerTechService {
	
	//查看某一球员关键数据
	public PlayerTechVO showKeyData (String name,String team );
	
	//刷新赛季球员数据列表
	public void refresh ();

	public void PlayerTechIni();
	
	public ArrayList<PlayerTechVO> ascend(String type,String team );
	
	public ArrayList<PlayerTechVO> descend(String type,String team );

	ArrayList<PlayerTechVO> showSeasonPlayerData(String team);
}
