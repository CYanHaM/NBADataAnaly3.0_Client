package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.PlayerTechMPO;

public class littletest {
	public static void main(String args[]){
		PlayerTech pt = new PlayerTech();
		ArrayList<String> result = pt.getTeamSeasonList("ATL");
		System.out.println(result.get(1));
	}
}
