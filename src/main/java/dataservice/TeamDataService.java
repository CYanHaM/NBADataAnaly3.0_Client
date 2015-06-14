package dataservice;


import PO.TeamPO;

public interface TeamDataService {
	public TeamPO find(TeamPO tpo);
	public void WriteIn();
}
