package dataservice.playerinfodataservice;

import java.util.ArrayList;

import PO.PlayerPO;

public interface PlayerInfoDataService {
	//acive==0返回现役和退役所有球员,active==1,返回现役,active==2,返回退役
	public ArrayList<PlayerPO> findAll(int active);
	
	public PlayerPO findOne(String name,int active);
	
	public ArrayList<PlayerPO> findByTeam(String team,int active);
	
	public ArrayList<PlayerPO> findByLetter(char letter,int active);

}
