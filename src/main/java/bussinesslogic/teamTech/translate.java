package bussinesslogic.teamTech;

import TypeEnum.TeamTechEnum;

public class translate {
	public String translate(TeamTechEnum en){
		switch(en){
			case name :return "team";
			case shotInNum:return "FGM";
			case shotNum:return "FGA";
			case threeShotInNum:return "TPM";
			case threeShotNum:return "TPA";
			case penaltyShotInNum:return "FTM";
			case penaltyShotNum:return "FTA";
			case offensiveRebound:return "OffenOWN";
			case defensiveRebound:return "DefenOWN";
			case rebound:return "TOTOWN";
			case secondaryAttack:return "ASS_OWN";
			case steal:return "STE_OWN";
			case blockShot:return "BLO_OWN";
			case fault:return "TUR_OWN";
			case foul:return "TUR_TECH";
			case score:return "OWN_Poi";
			case shotInRate:return "OWN_FG";
			case threeShotInRate:return "OWN_3P";
			case penaltyShotInRate:return "FT";
			case offensiveEfficiency:return "off_EFF";
			case defensiveEfficiency:return "def_eff";
			
			case shotInNumave:return"FGM";
			case threeShotInNumave:return"TPM";
			case shotNumave:return"FGA";
			case threeShotNumave:return"TPA";
			case penaltyShotInNumave:return"FTM";
			case penaltyShotNumave:return "FTA";
			case offensiveReboundave:return "OffenOWN";
			case defensiveReboundave:return "DefenOWN";
			case reboundave:return "TOTOWN";
			case secondaryAttackave:return"ASS_OWN";
			case stealave:return"STE_OWN";
			case blockShotave:return "BLO_OWN";
			case faultave:return"TUR_OWN";
			case foulave:return"TUR_TECH";
			case scoreave:return"OWN_Poi";

			
			
			case winningNum:return "Win_NUM";
			case winningRate:return "Win_Rate";
			case reboundEfficiency:return "reb_eff";
			case stealEfficiency:return "ste_eff";
			case secondaryAttackEfficiency:return "sec_eff";
			case offensiveRound:return "round";
			case offensiveRoundave:return"round";
			default: return null;
		}
	}
}
