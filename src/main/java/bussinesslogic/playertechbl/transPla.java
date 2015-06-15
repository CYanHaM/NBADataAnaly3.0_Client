package bussinesslogic.playertechbl;

public class transPla {
	public String translate(String con){
		switch(con){
			case"name":return "name";
			case"team":return"team";
			case"gamenum":return"gameNum";
			case"reboundave":return"reboundave";
			case"rebound":return"rebound";
			case"secondaryattackave":return "secondaryattackave";
			case"secondaryattack":return"assist";
			case"timeave":return "timeave";
			case"time":return "time";
			case"shotinrate":return"shotInRate";
			case"threeshotinrate":return"threeShotInRate";
			case"penaltyshotinrate":return"penaltyShotInRate";
			case"offensivenumave":return"offensivenumave";
			case"offensivenum":return"offensiveNum";
			case"defensivenumave":return"defensivenumave";
			case"defensivenum":return"defensiveNum";
			case"blockshotave":return"blockshotave";
			case"blockshot":return"block";
			case"faultave":return"faultave";
			case"fault":return"fault";
			case"foulave":return"foulave";
			case"foul":return"foul";
			case"score":return"score";
			case"steal":return"steal";
			case"stealave":return"stealave";
			case"trueshotinrate":return"trueShotInRate";
			case"reboundrate":return"reboundRate";
			case"offensivereboundrate":return"offReboundrate";
			case"defensivereboundrate":return"defReboundrate";
			case"secondaryattackrate":return"assistRate";
			case"faultrate":return"faultRate";
			case"usagerate":return"usageRate";
			
			case"scoreave":return"scoreave";
			

			case"double":return "double";
			case"stealrate":return"stealRate";
			case"shootingefficiency":return"shootingEfficiency";
			case"gmscefficiency":return"GmSc";
			case"startingnum":return"startingnum";
			default: System.out.println("errrrrrrrrrrr");return null;
		}
	}
}
