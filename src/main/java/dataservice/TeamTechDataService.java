package dataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import PO.TeamTechPO;

public interface TeamTechDataService extends Remote{
	public ArrayList<TeamTechPO> all();
	
	public ArrayList<TeamTechPO> list(String season);
	
	 public ArrayList<TeamTechPO> Descend (String DataType,String season);
	 
	 public ArrayList<TeamTechPO> Ascend(String DataType,String season);
	 
	 public ArrayList<TeamTechPO> findSeasonHotTeam(String DataType,String season);
	
}
