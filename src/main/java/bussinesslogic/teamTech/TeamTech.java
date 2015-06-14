package bussinesslogic.teamTech;

import java.util.ArrayList;

import PO.TeamTechPO;
import TypeEnum.SortEnum;
import TypeEnum.TeamTechEnum;
import VO.TeamTechVO;
import blservice.teamtechblservice.TeamTechBLService;
import bussinesslogic.Transfer.L2V.TeamTechL2V;
import bussinesslogic.Transfer.P2L.TeamTechP2L;
import dataservice.TeamTechDataService;

public class TeamTech implements TeamTechBLService{
	
	TeamTechL2V l2v;
	TeamTechP2L p2l;
	TeamTechDataService ttds;/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

	@Override
	public ArrayList<TeamTechVO> Ascend(TeamTechEnum DataType) {
		translate trans = new translate();
		ArrayList<TeamTechVO> result = new ArrayList<TeamTechVO>();
		ArrayList<TeamTechPO> polist = ttds.Ascend(trans.translate(DataType));
		for(int i =0;i<polist.size();i++){
			p2l = new TeamTechP2L();
			l2v = new TeamTechL2V();
			TeamTechVO ttvo = new TeamTechVO();
			ttvo = l2v.l2v(p2l.p2l(polist.get(i)));
			result.add(ttvo);
		}
		return result;
	}

	@Override
	public ArrayList<TeamTechVO> Descend(TeamTechEnum DataType) {
		translate trans = new translate();
		ArrayList<TeamTechVO> result = new ArrayList<TeamTechVO>();
		ArrayList<TeamTechPO> polist = ttds.Descend(trans.translate(DataType));
		for(int i =0;i<polist.size();i++){
			p2l = new TeamTechP2L();
			l2v = new TeamTechL2V();
			TeamTechVO ttvo = new TeamTechVO();
			ttvo = l2v.l2v(p2l.p2l(polist.get(i)));
			result.add(ttvo);
		}
		return result;
	}

	@Override
	public ArrayList<TeamTechVO> refresh(SortEnum sort, TeamTechEnum DataType) {
		translate trans = new translate();
		ArrayList<TeamTechVO> result = new ArrayList<TeamTechVO>();
		ArrayList<TeamTechPO> polist;
		if(sort.equals(SortEnum.descend)){
			polist = ttds.Descend(trans.translate(DataType));
		}else{
			polist = ttds.Ascend(trans.translate(DataType));
		}
		for(int i =0;i<polist.size();i++){
			p2l = new TeamTechP2L();
			l2v = new TeamTechL2V();
			TeamTechVO ttvo = new TeamTechVO();
			ttvo = l2v.l2v(p2l.p2l(polist.get(i)));
			result.add(ttvo);
		}
		return result;
	}

	@Override
	public ArrayList<TeamTechVO> findSeasonHotTeam(TeamTechEnum DataType) {
		translate trans = new translate();
		ArrayList<TeamTechVO> result = new ArrayList<TeamTechVO>();
		ArrayList<TeamTechPO> polist = ttds.findSeasonHotTeam(trans.translate(DataType));
		for(int i =0;i<polist.size();i++){
			p2l = new TeamTechP2L();
			l2v = new TeamTechL2V();
			TeamTechVO ttvo = new TeamTechVO();
			ttvo = l2v.l2v(p2l.p2l(polist.get(i)));
			result.add(ttvo);
		}
		return result;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
