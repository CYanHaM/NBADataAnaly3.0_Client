package bussinesslogic.teamTech;

import TypeEnum.TeamTechEnum;

public class translate {
	public String translate(TeamTechEnum en){
		switch(en){
		case season:return"season";
			case name :return "name";
			case shotInNum:return "shotInNum";
			case shotNum:return "shotNum";
			case threeShotInNum:return "threeShotInNum";
			case threeShotNum:return "threeShotNum";
			case penaltyShotInNum:return "penaltyShotInNum";
			case penaltyShotNum:return "penaltyShotNum";
			case offensiveRebound:return "offensiveRebound";
			case defensiveRebound:return "defensiveRebound";
			case rebound:return "rebound";
			case secondaryAttack:return "secondaryAttack";
			case steal:return "steal";
			case blockShot:return "blockShot";
			case fault:return "fault";
			case foul:return "foul";
			case score:return "score";
			case shotInRate:return "shotInRate";
			case threeShotInRate:return "threeShotInRate";
			case penaltyShotInRate:return "penaltyShotInRate";
			case offensiveEfficiency:return "offensiveEfficiency";
			case defensiveEfficiency:return "defensiveEfficiency";
			
			case shotInNumave:return"shotInNumave";
			case threeShotInNumave:return"threeShotInNumave";
			case shotNumave:return"shotNumave";
			case threeShotNumave:return"threeShotNumave";
			case penaltyShotInNumave:return"penaltyShotInNumave";
			case penaltyShotNumave:return "penaltyShotNumave";
			case offensiveReboundave:return "offensiveReboundave";
			case defensiveReboundave:return "defensiveReboundave";
			case reboundave:return "reboundave";
			case secondaryAttackave:return"secondaryAttackave";
			case stealave:return"stealave";
			case blockShotave:return "blockShotave";
			case faultave:return"faultave";
			case foulave:return"foulave";
			case scoreave:return"scoreave";

			
			
			case winningNum:return "winningNum";
			case winningRate:return "winningRate";
			case reboundEfficiency:return "reboundEfficiency";
			case stealEfficiency:return "stealEfficiency";
			case secondaryAttackEfficiency:return "secondaryAttackEfficiency";
			case offensiveRound:return "offensiveRound";
			case offensiveRoundave:return"offensiveRoundave";
			default: return null;
		}
	}
}
