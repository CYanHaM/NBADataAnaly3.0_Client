package bussinesslogic.teamTech;

import TypeEnum.TeamTechEnum;

public class translate {
	public String translate(TeamTechEnum en){
		switch(en){
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
			
			case shotInNumave:return"shotInNum";
			case threeShotInNumave:return"threeShotInNum";
			case shotNumave:return"shotNum";
			case threeShotNumave:return"threeShotNum";
			case penaltyShotInNumave:return"penaltyShotInNum";
			case penaltyShotNumave:return "penaltyShotNum";
			case offensiveReboundave:return "offensiveRebound";
			case defensiveReboundave:return "defensiveRebound";
			case reboundave:return "rebound";
			case secondaryAttackave:return"ASS_OWN";
			case stealave:return"steal";
			case blockShotave:return "blockShot";
			case faultave:return"fault";
			case foulave:return"foul";
			case scoreave:return"score";

			
			
			case winningNum:return "winningNum";
			case winningRate:return "winningRate";
			case reboundEfficiency:return "reboundEfficiency";
			case stealEfficiency:return "stealEfficiency";
			case secondaryAttackEfficiency:return "secondaryAttackEfficiency";
			case offensiveRound:return "offensiveRound";
			case offensiveRoundave:return"offensiveRound";
			default: return null;
		}
	}
}
