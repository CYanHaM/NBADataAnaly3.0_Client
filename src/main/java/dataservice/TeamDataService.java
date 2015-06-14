package dataservice;


import PO.TeamPO;

public interface TeamDataService {
	
	public TeamPO find(String name);
	
	public void WriteIn();
}
