package blservice.playerinfoblservice;

import java.util.ArrayList;

import VO.PlayerVO;

public interface PlayerInfoService {
	
	public ArrayList<PlayerVO> showAllPlayerInfo (int retire);
	
	public PlayerVO showPlayerInfo (String name);
	
	public ArrayList<PlayerVO> findPlayerByLetter(char letter,int retire);   

	public ArrayList<PlayerVO> findByTeam(String team,int retire);
	
	public void PlayerInfoIni();
}
