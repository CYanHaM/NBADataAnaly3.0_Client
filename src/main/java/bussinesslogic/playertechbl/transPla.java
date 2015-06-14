package bussinesslogic.playertechbl;

public class transPla {
	public String translate(String con){
		switch(con){
			case"name":return "name";
			case"team":return"team";
			case"gamenum":return"GP";
			case"reboundave":return"REB";
			case"rebound":return"RPG";
			case"secondaryattackave":return "APG";
			case"secondaryattack":return"AST";
			case"timeave":return "MPG";
			case"time":return "MIN";
			case"shotinrate":return"FG%";
			case"threeshotinrate":return"3P%";
			case"penaltyshotinrate":return"FT%";
			case"offensivenumave":return"ORPG";
			case"offensivenum":return"OFF";
			case"defensivenumave":return"DRPG";
			case"defensivenum":return"DEF";
			case"blockshotave":return"BLKPG";
			case"blockshot":return"BLK";
			case"faultave":return"TOPG";
			case"fault":return"TO";
			case"foulave":return"PF";
			case"foul":return"PFPG";
			case"score":return"PTS";
			case"steal":return"STL";
			case"stealave":return"STPG";
			case"trueshotinrate":return"TS";
			case"reboundrate":return"REBR";
			case"offensivereboundrate":return"ORR";
			case"defensivereboundrate":return"DRR";
			case"secondaryattackrate":return"AST";
			case"faultrate":return"TO";
			case"usagerate":return"USG";
			
			case"scoreave":return"PTS";
			

			case"double":return "doubles";
			case"efficiency":return"efficiency";
			case"blockshotrate":return"blockshotrate";
			case"stealrate":return"stealrate";
			case"shootingefficiency":return"shootingefficiency";
			case"gmscefficiency":return"GMSC";
			case"startingnum":return"start";
			default: System.out.println("errrrrrrrrrrr");return null;
		}
	}
}
