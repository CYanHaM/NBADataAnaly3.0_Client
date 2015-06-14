package dataservice.playerinfodataservice;

import java.util.ArrayList;

import PO.PlayerPO;

public interface PlayerInfoDataService {
	//acive==0�������ۺ�����������Ա,active==1,��������,active==2,��������
	public ArrayList<PlayerPO> findAll(int active);
	
	public PlayerPO findOne(String name,int active);
	
	public ArrayList<PlayerPO> findByTeam(String team,int active);
	
	public ArrayList<PlayerPO> findByLetter(char letter,int active);

}
