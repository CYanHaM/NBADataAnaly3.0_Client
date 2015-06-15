package PO;
import java.io.Serializable;


public class PlayerTechPO implements Serializable{
	/**
	 * 锟斤拷锟斤拷锟斤拷员锟斤拷锟斤拷锟斤拷
	 */
	public static final long serialVersionUID = 1L;
	public String name;                            //锟斤拷员锟斤拷锟斤拷
	public String season;                          //锟斤拷锟斤拷
	public String team;                            //锟斤拷锟斤拷锟斤拷锟�
	public int ifRegular;                          //鏄惁鏄父瑙勮禌
	
	//锟斤拷锟捷筹拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	public String position;                     //浣嶇疆
    public String division;                     //鍒嗗尯
	public int gameNum;                        
	public int startingNum;                     //锟饺凤拷锟斤拷锟斤拷
	public int rebound;                       
	public int secondaryAttack;               
	public int time;                           
	public int offensiveNum;                    
	public int defensiveNum;                  
	public int steal;                           
	public int blockShot;                     
	public int fault;                         
	public int foul;                           
	public int score;                         
	public int shotIn;                     
	public int shot;                         
	public int threeShotIn;                  
	public int threeShot;                   
	public int penaltyShotIn;              
	public int penaltyShot;                  
	
	//锟斤拷锟捷癸拷式锟斤拷锟斤拷
	public double shotInRate;                      
	public double threeShotInRate;              
	public double penaltyShotInRate;              
	public double GmScEfficiency;                   
	public double trueShotInRate;                
	public double shootingEfficiency;               
	public double reboundRate;                   
	public double offensiveReboundRate;           
	public double defensiveReboundRate;          
	public double secondaryAttackRate;
	public double faultRate;                   
	public double usageRate;                    
	
	public int teamAllTime;                  //全锟斤拷锟较筹拷时锟斤拷
	public int teamOffensiveRebound;                  //全锟接斤拷锟斤拷锟斤拷锟斤拷
	public int teamDefensiveRebound;                //全锟接凤拷锟斤拷锟斤拷锟斤拷
	public int opponentOffensiveRebound;                  //锟斤拷锟街斤拷锟斤拷锟斤拷锟斤拷
	public int opponentDefensiveRebound;                //锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷
	public int teamShotIn;                             //全锟接斤拷锟斤拷锟斤拷
	public double opponentOffensiveNum;                     //锟斤拷锟街斤拷锟斤拷锟斤拷锟斤拷
	public int opponentTwoShot;                     //锟斤拷锟街斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷执锟斤拷锟�
	public int teamShot;                          //全锟接筹拷锟街达拷锟斤拷
	public int teamPenaltyShot;                   //全锟接凤拷锟斤拷锟斤拷锟�
	public int teamFault;                          
	public int ifDouble;
	//锟斤拷锟斤拷为锟斤拷锟斤拷锟斤拷
		public double scoreImproving;
		public double stealImproving;
		public double blockShotImproving;
		public double secondaryAttackImproving;
		public double reboundImproving;
	
	public boolean equals(PlayerTechPO ptpo){
		if(!this.name.equals(ptpo.name)){
			System.out.println("name");
			return false;
		}
		if(!this.season.equals(ptpo.season)){
			System.out.println("season");
			return false;
		}
		if(!this.team.equals(ptpo.team)){
			System.out.println("team");
			return false;
		}
		if(this.gameNum!=ptpo.gameNum){
			System.out.println("gameNum");
			return false;
		}
		if(this.startingNum!=ptpo.startingNum){
			System.out.println("startingNum");
			return false;
		}
		if(this.shotInRate!=ptpo.shotInRate){
			System.out.println("shotInRate");
			return false;
		}
		if(this.threeShotInRate!=ptpo.threeShotInRate){
			System.out.println("threeShotInRate");
			return false;
		}
		if(this.penaltyShotInRate!=ptpo.penaltyShotInRate){
			System.out.println("penaltyShotInRate");
			return false;
		}
		if(this.GmScEfficiency!=ptpo.GmScEfficiency){
			System.out.println("GmScEfficiency");
			return false;
		}
		if(this.trueShotInRate!=ptpo.trueShotInRate){
			System.out.println("trueShotInRate");
			return false;
		}
		if(this.shootingEfficiency!=ptpo.shootingEfficiency){
			System.out.println("shootingEfficiency");
			return false;
		}
		if(this.reboundRate!=ptpo.reboundRate){
			System.out.println("reboundRate");
			return false;
		}
		if(this.offensiveReboundRate!=ptpo.offensiveReboundRate){
			System.out.println("offensiveReboundRate");
			return false;
		}
		if(this.defensiveReboundRate!=ptpo.defensiveReboundRate){
			System.out.println("defensiveReboundRate");
			return false;
		}
		if(this.secondaryAttackRate!=ptpo.secondaryAttackRate){
			System.out.println("secondaryAttackRate");
			return false;
		}
		if(this.faultRate!=ptpo.faultRate){
			System.out.println("faultRate");
			return false;
		}
		if(this.usageRate!=ptpo.usageRate){
			System.out.println("usageRate");
			return false;
		}
		if(this.offensiveNum!=ptpo.offensiveNum){
			System.out.println("offensiveNum");
			return false;
		}
		if(this.defensiveNum!=ptpo.defensiveNum){
			System.out.println("defensiveNum");
			return false;
		}
		if(this.steal!=ptpo.steal){
			System.out.println("steal");
			return false;
		}
		if(this.blockShot!=ptpo.blockShot){
			System.out.println("blockShot");
			return false;
		}
		if(this.fault!=ptpo.fault){
			System.out.println("fault");
			return false;
		}
		if(this.foul!=ptpo.foul){
			System.out.println("foul");
			return false;
		}
		if(this.score!=ptpo.score){
			System.out.println("score");
			return false;
		}
		if(this.rebound!=ptpo.rebound){
			System.out.println("rebound");
			return false;
		}
		if(this.secondaryAttack!=ptpo.secondaryAttack){
			System.out.println("secondaryAttack");
			return false;
		}
		if(this.time!=ptpo.time){
			System.out.println("time");
			return false;
		}
		return true;
	}
}