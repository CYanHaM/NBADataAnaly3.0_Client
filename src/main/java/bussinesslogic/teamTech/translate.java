package bussinesslogic.TeamTech;

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
			
			
			case winningNum:return "Win_NUM";
			case winningRate:return "Win_Rate";
			case reboundEfficiency:return "reb_eff";
			case stealEfficiency:return "ste_eff";
			case secondaryAttackEfficiency:return "sec_eff";
			default: return null;
		}
	}
}
