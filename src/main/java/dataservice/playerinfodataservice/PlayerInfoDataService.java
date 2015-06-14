package dataservice.playerinfodataservice;

import java.util.ArrayList;

import PO.PlayerPO;

public interface PlayerInfoDataService {
	
	public ArrayList<PlayerPO> findAll();
	
	public PlayerPO findOne(String name);
	
	public ArrayList<PlayerPO> findByTeam(String team);
	
	public ArrayList<PlayerPO> findByLetter(char letter);

}
