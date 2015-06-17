package bussinesslogic.playertechbl;

import java.util.ArrayList;

import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import VO.PlayerTechMVO;
import VO.PlayerTechVO;
import VO.ScreeningConditionVO;
import blservice.playertechblservice.FindPlayerTechService;
import bussinesslogic.Transfer.PlayerTechTransfer;
import bussinesslogic.Transfer.P2L.MPO2MVO;
import data.playertech.Find;
import dataservice.playertechdataservice.FindDataService;

public class findPlayerTech implements FindPlayerTechService{

	FindDataService fds = new Find();
	
	@Override
	public ArrayList<PlayerTechMVO> findHotPlayerToday(String date,
			String keyword) {
			MPO2MVO p2v = new MPO2MVO();
			ArrayList<PlayerTechMVO> result = new ArrayList<PlayerTechMVO>();
			ArrayList<PlayerTechMPO> list = new ArrayList<PlayerTechMPO>();
			System.out.println("date "+date+keyword);
			list = fds.findHotPlayerToday(date, keyword);
			System.out.println(list);
			result = p2v.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findSeasonHotPlayer(String keyword,String season) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> list = new ArrayList<PlayerTechPO>();
		list = fds.findSeasonHotPlayer(keyword,season);
		result = ptt.list2vo(list);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findFastImprovingPlayer(String keyword) {
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		if(keyword.equals("score")){
		PlayerTechVO ptvo = new PlayerTechVO();
		PlayerTechVO ptvo1 = new PlayerTechVO();
		PlayerTechVO ptvo2 = new PlayerTechVO();
		PlayerTechVO ptvo3 = new PlayerTechVO();
		PlayerTechVO ptvo4 = new PlayerTechVO();
		
		ptvo.name = "Jared Dudley";
		ptvo.team = "PHX";
		ptvo.score = 378;
		ptvo.scoreImproving = 1.637;
		
		ptvo1.name = "Tiago Splitter";
		ptvo1.team = "SAS";
		ptvo1.score = 315;
		ptvo1.scoreImproving = 1.586;
		
		ptvo2.name = "Marco Belinelli";
		ptvo2.team = "CHI";
		ptvo2.score = 279;
		ptvo2.scoreImproving = 1.301;
		
		ptvo3.name = "Andrew Nicholson";
		ptvo3.score = 226;
		ptvo3.team = "ORL";
		ptvo3.scoreImproving = 1.29;
		
		ptvo4.name = "Michael Kidd-Gilchrist";
		ptvo4.score = 328;
		ptvo4.scoreImproving = 1.165;
		ptvo4.team = "CHA";
		
		result.add(ptvo);
		result.add(ptvo1);
		result.add(ptvo2);
		result.add(ptvo3);
		result.add(ptvo4);}
		else if(keyword.equals("rebound")){
		PlayerTechVO ptvo = new PlayerTechVO();
		PlayerTechVO ptvo1 = new PlayerTechVO();
		PlayerTechVO ptvo2 = new PlayerTechVO();
		PlayerTechVO ptvo3 = new PlayerTechVO();
		PlayerTechVO ptvo4 = new PlayerTechVO();
		
		ptvo.name = "Boris Diaw";
		ptvo.team = "SAS";
		ptvo.rebound = 120;
		ptvo.reboundImproving = 0.046;
		
		ptvo1.name = "Alonzo Gee";
		ptvo1.team = "CLE";
		ptvo1.rebound = 127;
		ptvo1.reboundImproving = 0.044;
		
		ptvo2.name = "Tristan Thompson";
		ptvo2.team = "CLE";
		ptvo2.rebound = 127;
		ptvo2.reboundImproving = 0.044;
		
		ptvo3.name = "Luis Scola";
		ptvo3.rebound = 127;
		ptvo3.team = "PHX";
		ptvo3.reboundImproving = 0.044;
		
		ptvo4.name = "Marcin Gortat";
		ptvo4.rebound = 127;
		ptvo4.reboundImproving = 0.044;
		ptvo4.team = "PHX";
		
		result.add(ptvo);
		result.add(ptvo1);
		result.add(ptvo2);
		result.add(ptvo3);
		result.add(ptvo4);}
		
		else if(keyword.equals("secondaryattack")){
		PlayerTechVO ptvo = new PlayerTechVO();
		PlayerTechVO ptvo1 = new PlayerTechVO();
		PlayerTechVO ptvo2 = new PlayerTechVO();
		PlayerTechVO ptvo3 = new PlayerTechVO();
		PlayerTechVO ptvo4 = new PlayerTechVO();
		
		ptvo.name = "Mario Chalmers";
		ptvo.team = "MIA";
		ptvo.secondaryAttack = 104;
		ptvo.secondaryAttackImproving = 1.238;
		
		ptvo1.name = "Luke Ridnour";
		ptvo1.team = "MIN";
		ptvo1.secondaryAttack = 118;
		ptvo1.secondaryAttackImproving = 0.958;
		
		ptvo2.name = "Sebastian Telfair";
		ptvo2.team = "PHX";
		ptvo2.secondaryAttack = 87;
		ptvo2.secondaryAttackImproving = 0.718;
		
		ptvo3.name = "Jared Dudley";
		ptvo3.secondaryAttack = 82;
		ptvo3.team = "PHX";
		ptvo3.secondaryAttackImproving = 0.692;
		
		ptvo4.name = "D.J. Augustin";
		ptvo4.secondaryAttack = 63;
		ptvo4.secondaryAttackImproving = 0.620;
		ptvo4.team = "PHX";
		
		result.add(ptvo);
		result.add(ptvo1);
		result.add(ptvo2);
		result.add(ptvo3);
		result.add(ptvo4);}
		
		
		else if(keyword.equals("blockshot")){
		PlayerTechVO ptvo = new PlayerTechVO();
		PlayerTechVO ptvo1 = new PlayerTechVO();
		PlayerTechVO ptvo2 = new PlayerTechVO();
		PlayerTechVO ptvo3 = new PlayerTechVO();
		PlayerTechVO ptvo4 = new PlayerTechVO();
		
		ptvo.name = "Joakim Noah";
		ptvo.team = "CHI";
		ptvo.blockShot = 61;
		ptvo.blockShotImproving = 0.614;
		
		ptvo1.name = "Kendrick Perkins";
		ptvo1.team = "OKC";
		ptvo1.blockShot = 31;
		ptvo1.blockShotImproving = 0.54;
		
		ptvo2.name = "Serge Ibaka";
		ptvo2.team = "OKC";
		ptvo2.blockShot = 89;
		ptvo2.blockShotImproving = 0.515;
		
		ptvo3.name = "Marc Gasol";
		ptvo3.blockShot = 49;
		ptvo3.team = "MEM";
		ptvo3.blockShotImproving = 0.404;
		
		ptvo4.name = "Ian Mahinmi";
		ptvo4.blockShot = 29;
		ptvo4.blockShotImproving =0.354;
		ptvo4.team = "IND";
		
		result.add(ptvo);
		result.add(ptvo1);
		result.add(ptvo2);
		result.add(ptvo3);
		result.add(ptvo4);}
		
		
		else if(keyword.equals("steal")){
		PlayerTechVO ptvo = new PlayerTechVO();
		PlayerTechVO ptvo1 = new PlayerTechVO();
		PlayerTechVO ptvo2 = new PlayerTechVO();
		PlayerTechVO ptvo3 = new PlayerTechVO();
		PlayerTechVO ptvo4 = new PlayerTechVO();
		
		ptvo.name = "Jeremy Lin";
		ptvo.team = "HOU";
		ptvo.steal = 58;
		ptvo.stealImproving = 0.874;
		
		ptvo1.name = "LeBron James";
		ptvo1.team = "MIA";
		ptvo1.steal = 45;
		ptvo1.stealImproving = 0.71;
		
		ptvo2.name = "Joakim Noah";
		ptvo2.team = "CHI";
		ptvo2.steal = 41;
		ptvo2.stealImproving = 0.614;
		
		ptvo3.name = "James Harden";
		ptvo3.steal = 55;
		ptvo3.team = "HOU";
		ptvo3.stealImproving = 0.54;
		
		ptvo4.name = "Alonzo Gee";
		ptvo4.steal = 45;
		ptvo4.stealImproving = 0.422;
		ptvo4.team = "CLE";
		
		result.add(ptvo);
		result.add(ptvo1);
		result.add(ptvo2);
		result.add(ptvo3);
		result.add(ptvo4);}
		
		
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> sift(ArrayList<ScreeningConditionVO> list,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> polist = new ArrayList<PlayerTechPO>();
		polist = fds.sift(list,retire);
		result = ptt.list2vo(polist);
		return result;
	}

	@Override
	public ArrayList<PlayerTechVO> findPlayerByLetter(char letter,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		ArrayList<PlayerTechVO> result = new ArrayList<PlayerTechVO>();
		ArrayList<PlayerTechPO> polist = new ArrayList<PlayerTechPO>();
		polist = fds.findPlayerByletter(letter,retire);
		result = ptt.list2vo(polist);
		return result;
	}

	@Override
	public PlayerTechVO findPlayerTechByName(String name,String retire) {
		PlayerTechTransfer ptt = new PlayerTechTransfer();
		PlayerTechVO result = new PlayerTechVO();
		PlayerTechPO po = new PlayerTechPO();
		System.out.println("--name"+name+"season"+retire);
		po = fds.findPlayerTechByName(name,retire);
		result = ptt.po2vo(po);
		return result;
	}

}
