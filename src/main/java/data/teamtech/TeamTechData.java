package data.teamtech;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.TeamTechPO;
import dataservice.TeamTechDataService;

public class TeamTechData implements TeamTechDataService {

	@Override
	public ArrayList<TeamTechPO> list(String season){
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> Descend(String DataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> Ascend(String DataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamTechPO> findSeasonHotTeam(String DataType,
			String season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void WriteIn() {
		// TODO Auto-generated method stub
		
	}
	

}
