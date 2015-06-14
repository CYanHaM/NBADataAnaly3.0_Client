package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.TeamTechPO;
import TypeEnum.TeamTechEnum;
import VO.TeamTechVO;

public interface TeamTechDataService extends Remote{
	public ArrayList<TeamTechPO> list()throws RemoteException;
	
	 public ArrayList<TeamTechPO> Descend (String DataType);
	 
	 public ArrayList<TeamTechPO> Ascend(String DataType);
	 
	 public ArrayList<TeamTechPO> findSeasonHotTeam(String DataType);
	
	public void WriteIn();
}
