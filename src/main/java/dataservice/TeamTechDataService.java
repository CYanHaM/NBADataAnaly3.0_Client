package dataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import PO.TeamTechPO;

public interface TeamTechDataService extends Remote{
	public ArrayList<TeamTechPO> list(String season);
	
	 public ArrayList<TeamTechPO> Descend (String DataType);
	 
	 public ArrayList<TeamTechPO> Ascend(String DataType);
	 
	 public ArrayList<TeamTechPO> findSeasonHotTeam(String DataType,String season);
	
	public void WriteIn();
}
