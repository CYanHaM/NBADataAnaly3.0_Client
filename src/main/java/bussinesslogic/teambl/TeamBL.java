package bussinesslogic.teambl;

import PO.TeamPO;
import VO.TeamVO;
import blservice.teamblservice.TeamBLservice;
import bussinesslogic.Transfer.L2V.TeamL2V;
import bussinesslogic.Transfer.P2L.TeamP2L;
import dataservice.TeamDataService;

public class TeamBL implements TeamBLservice{
	TeamDataService tds;
	@Override
	public TeamVO Show(TeamVO tvo) {
		// TODO Auto-generated method stub
		TeamPO tpo = new TeamPO();
		TeamP2L p2l = new TeamP2L();
		TeamL2V l2v = new TeamL2V();
		tpo = tds.find(tvo.abbreviation);
		return l2v.l2v(p2l.p2l(tpo));
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
