package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dataservice.MatchDataService;
import PO.MatchPO;
import PO.PlayerTechMPO;

public class MatchData implements MatchDataService{
	
	public String showPresentTime(){
		String date=null;
		try {
			String encoding = "GBK";
			File file = new File("MatchData");
			File filelist[]=file.listFiles();
			ArrayList<String>info=new ArrayList<String>();
			InputStreamReader read = new InputStreamReader(new FileInputStream(filelist[filelist.length-1].getAbsolutePath()),encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String line = null;
			while((line = bufferedReader.readLine())!=null) {
				info.add(line);
			}
			read.close();
			String[][]data=new String [info.size()][];
			for(int j=0;j<info.size();j++){
				data[j]=info.get(j).split(";");
			}
			String filename[]=filelist[filelist.length-1].getName().split("_");
			if(Integer.valueOf(filename[1].split("-")[0])<=7)
				date="20"+filename[0].split("-")[1]+"-"+filename[1];
			else
				date="20"+filename[0].split("-")[0]+"-"+filename[1];
		}catch(Exception e){
			System.out.println("读入操作出错");
			e.printStackTrace();
		}
		return date;
	}
	
	public MatchPO completeMatch(MatchPO po){
		
		//录入胜负情况
		if(po.homeScore>po.guestScore){
			po.ifHomeTeamWin=1;
			po.ifGuestTeamWin=0;
		}
		else if(po.homeScore<po.guestScore){
			po.ifHomeTeamWin=0;
			po.ifGuestTeamWin=1;
		}else if(po.homeScore==po.guestScore){
			po.ifHomeTeamWin=0;
			po.ifGuestTeamWin=0;
		}
		
		for(PlayerTechMPO ptmp:po.playerStatistic){		
			if(ptmp.team.equals("DAL")||ptmp.team.equals("DEN")||ptmp.team.equals("GSW")||ptmp.team.equals("HOU")||ptmp.team.equals("LAC")||ptmp.team.equals("LAL")||ptmp.team.equals("MEM")||ptmp.team.equals("MIN")||ptmp.team.equals("NOP")||ptmp.team.equals("OKC")||ptmp.team.equals("PHX")||ptmp.team.equals("POR")||ptmp.team.equals("SAC")||ptmp.team.equals("SAS")||ptmp.team.equals("UTA")||ptmp.team.equalsIgnoreCase("UTAH")||ptmp.team.equals("SEA")||ptmp.team.equals("NO")||ptmp.team.equals("GS"))
				ptmp.division="W";
			else
				ptmp.division="E";
			
			if(ptmp.time>0)
				ptmp.ifParticipate=1;
			else
				ptmp.ifParticipate=0;
			if((ptmp.score>=10&&ptmp.rebound>=10)||(ptmp.score>=10&&ptmp.secondaryAttack>=10)||(ptmp.score>=10&&ptmp.steal>=10)||(ptmp.score>=10&&ptmp.blockShot>=10)||(ptmp.rebound>=10&&ptmp.secondaryAttack>=10)||(ptmp.rebound>=10&&ptmp.steal>=10)||(ptmp.rebound>=10&&ptmp.blockShot>=10)||(ptmp.secondaryAttack>=10&&ptmp.steal>=10)||(ptmp.secondaryAttack>=10&&ptmp.blockShot>=10)||(ptmp.steal>=10&&ptmp.blockShot>=10))
				ptmp.ifDouble=1;
			else
				ptmp.ifDouble=0;
			
		}
		
		//计算主客队进攻防守篮板数
		int homeDeRebound=0;
		int guestDeRebound=0;
		int homeOfRebound=0;
		int guestOfRebound=0;
		int homeShot=0;
		int guestShot=0;
		int homeShotin=0;
		int guestShotin=0;
		int homethreeshot=0;
		int guestthreeshot=0;
		int homethreeshotIn=0;
		int guestthreeshotIn=0;
		int homePShot=0;
		int guestPShot=0;
		int homePShotIn=0;
		int guestPShotIn=0;
		int homeFault=0;
		int guestFault=0;
		int homeTime=0;
		int guestTime=0;
		int homeTeamFoul=0;                     //主队犯规
		int guestTeamFoul=0;                     //客队犯规
		int homeTeamSteal=0;                     //主队抢断
		int guestTeamSteal=0;                     //客队抢断
		int homeTeamSecondaryAttack=0;                     //主队助攻
		int guestTeamSecondaryAttack=0;                     //客队助攻
		int homeTeamBlockShot=0;                     //主队盖帽
		int guestTeamBlockShot=0;                     //客队盖帽
		
		TeamTechAssist tta = new TeamTechAssist();
		
		
		ArrayList<PlayerTechMPO>homelist=new ArrayList<PlayerTechMPO>();
		ArrayList<PlayerTechMPO>guestlist=new ArrayList<PlayerTechMPO>();
		System.out.println(po.homeTeam);
		for(PlayerTechMPO ptmp:po.playerStatistic){
			String name = tta.abbr(ptmp.team);
			System.out.println(ptmp.team);
			if(name.equals(po.homeTeam))
				homelist.add(ptmp);
			if(name.equals(po.guestTeam))
				guestlist.add(ptmp);
		}
		System.out.println(guestlist.size());
		System.out.println(homelist.size());
		for(int j=0;j<guestlist.size();j++){
			
			guestDeRebound=guestDeRebound+guestlist.get(j).defensiveRebound;
			guestOfRebound=guestOfRebound+guestlist.get(j).offensiveRebound;
			guestShot=guestShot+guestlist.get(j).shot;
			guestShotin=guestShotin+guestlist.get(j).shotIn;
			guestthreeshot=guestthreeshot+guestlist.get(j).threeShot;
			guestthreeshotIn=guestthreeshotIn+guestlist.get(j).threeShotIn;
			guestPShot=guestPShot+guestlist.get(j).penaltyShot;
			guestPShotIn=guestPShotIn+guestlist.get(j).penaltyShotIn;
			guestFault=guestFault+guestlist.get(j).fault;
			guestTime=guestTime+guestlist.get(j).time;
			guestTeamFoul=guestTeamFoul+guestlist.get(j).foul;
			guestTeamSteal=guestTeamSteal+guestlist.get(j).steal;
			guestTeamSecondaryAttack=guestTeamSecondaryAttack+guestlist.get(j).secondaryAttack;
			guestTeamBlockShot=guestTeamBlockShot+guestlist.get(j).blockShot;
			
			
			
		}
		
		for(int j=0;j<homelist.size();j++){
			homeDeRebound=homeDeRebound+homelist.get(j).defensiveRebound;
			homeOfRebound=homeOfRebound+homelist.get(j).offensiveRebound;
			homeShot=homeShot+homelist.get(j).shot;
			homeShotin=homeShotin+homelist.get(j).shotIn;
			homethreeshot=homethreeshot+homelist.get(j).threeShot;
			homethreeshotIn=homethreeshotIn+homelist.get(j).threeShotIn;
			homePShot=homePShot+homelist.get(j).penaltyShot;
			homePShotIn=homePShotIn+homelist.get(j).penaltyShotIn;
			homeFault=homeFault+homelist.get(j).fault;
			homeTime=homeTime+homelist.get(j).time;
			homeTeamFoul=homeTeamFoul+homelist.get(j).foul;
			homeTeamSteal=homeTeamSteal+homelist.get(j).steal;
			homeTeamSecondaryAttack=homeTeamSecondaryAttack+homelist.get(j).secondaryAttack;
			homeTeamBlockShot=homeTeamBlockShot+homelist.get(j).blockShot;
		}
		po.homeShot=homeShot;
		po.guestShot=guestShot;
		po.homeShotIn=homeShotin;
		po.guestShotIn=guestShotin;
		po.homeTwoShot=homeShot-homethreeshot;
		po.guestTwoShot=guestShot-guestthreeshot;
		po.homeTwoShotIn=homeShotin-homethreeshotIn;
		po.guestTwoShotIn=guestShotin-guestthreeshotIn;
		po.homeThreeShot=homethreeshot;
		po.guestThreeShot=guestthreeshot;
		po.homeThreeShotIn=homethreeshotIn;
		po.guestThreeShotIn=guestthreeshotIn;
		po.homePenaltyShot=homePShot;
		po.guestPenaltyShot=guestPShot;
		po.homePenaltyShotIn=homePShotIn;
		po.guestPenaltyShotIn=guestPShotIn;
		po.homeFault=homeFault;
		po.guestFault=guestFault;
		po.guestTeamDeffensiveRebound=guestDeRebound;
		po.homeTeamDeffensiveRebound=homeDeRebound;
		po.guestTeamOffensiveRebound=guestOfRebound;
		po.homeTeamOffensiveRebound=homeOfRebound;
		po.homeAllTime=homeTime;
		po.guestAllTime=guestTime;
		po.homeTeamFoul=homeTeamFoul;
		po.guestTeamFoul=guestTeamFoul;
		po.homeTeamSteal=homeTeamSteal;
		po.guestTeamSteal=guestTeamSteal;
		po.homeTeamSecondaryAttack=homeTeamSecondaryAttack;
		po.guestTeamSecondaryAttack=guestTeamSecondaryAttack;
		po.homeTeamBlockShot=homeTeamBlockShot;
		po.guestTeamBlockShot=guestTeamBlockShot;
		//计算主客队进攻回合
		po.homeTeamOffensiveRound=(double)homeShot+0.4*(double)homePShot-1.07*((double)homeOfRebound/((double)homeOfRebound+(double)guestDeRebound)*((double)homeShot-(double)homeShotin))+1.07*(double)homeFault;
		po.guestTeamOffensiveRound=(double)guestShot+0.4*(double)guestPShot-1.07*((double)guestOfRebound/((double)guestOfRebound+(double)homeDeRebound)*((double)guestShot-(double)guestShotin))+1.07*(double)guestFault;
		int scoreKing=0;
		int reboundKing=0;
		int assistKing=0;
		int mostScore=po.playerStatistic.get(0).score;
		int mostRebound=po.playerStatistic.get(0).rebound;
		int mostSecondaryAttack=po.playerStatistic.get(0).secondaryAttack;
		
		for(int k=0;k<po.playerStatistic.size()-1;k++){
			if(po.playerStatistic.get(k+1).score>mostScore){
				mostScore=po.playerStatistic.get(k+1).score;
				scoreKing=k+1;
			}
			if(po.playerStatistic.get(k+1).rebound>mostRebound){
				mostRebound=po.playerStatistic.get(k+1).rebound;
				reboundKing=k+1;
			}
			if(po.playerStatistic.get(k+1).secondaryAttack>mostSecondaryAttack){
				mostSecondaryAttack=po.playerStatistic.get(k+1).secondaryAttack;
				assistKing=k+1;
			}
		}
		po.scoringChampion=po.playerStatistic.get(scoreKing).name+"_"+Integer.toString(po.playerStatistic.get(scoreKing).score)+"_"+po.playerStatistic.get(scoreKing).team;
		po.assistChampion=po.playerStatistic.get(assistKing).name+"_"+Integer.toString(po.playerStatistic.get(assistKing).secondaryAttack)+"_"+po.playerStatistic.get(assistKing).team;;
		po.reboundChampion=po.playerStatistic.get(reboundKing).name+"_"+Integer.toString(po.playerStatistic.get(reboundKing).rebound)+"_"+po.playerStatistic.get(reboundKing).team;
		
		return po;
		
		
		
		
		
	}
	
}

