package PO;
import java.io.Serializable;

public class PlayerTechMPO implements Serializable,Cloneable{
	
	/**
	 *每锟斤拷锟斤拷锟斤拷锟斤拷员锟斤拷锟斤拷统锟斤拷 
	 */
	private static final long serialVersionUID = 1L;
	
	public String name;                         //锟斤拷锟斤拷
	public String team;                         //锟斤拷锟斤拷
	public String season;
	public String division;                     //鍒嗗尯
	public String date;
	public String position;                     //位锟斤拷
	public int time;                         //锟节筹拷时锟斤拷
	public int shotIn;                       //投锟斤拷锟斤拷锟斤拷锟斤拷
	public int shot;                         //投锟斤拷锟斤拷锟斤拷锟斤拷
	public int threeShotIn;                  //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	public int threeShot;                    //锟斤拷锟街筹拷锟斤拷锟斤拷
	public int penaltyShotIn;                //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	public int penaltyShot;                  //锟斤拷锟斤拷锟斤拷锟斤拷锟�
	public int offensiveRebound;             //前锟斤拷锟斤拷锟斤拷锟斤拷
	public int defensiveRebound;             //锟斤拷锟斤拷锟斤拷锟斤拷
	public int rebound;                      //锟斤拷锟斤拷锟斤拷锟斤拷
	public int secondaryAttack;              //锟斤拷锟斤拷锟斤拷
	public int steal;                        //锟斤拷锟斤拷锟斤拷
	public int blockShot;                    //锟斤拷帽锟斤拷
	public int fault;                        //失锟斤拷锟斤拷
	public int foul;                         //锟斤拷锟斤拷锟斤拷
	public int score;                        //锟斤拷锟剿得凤拷
	public int ifFirstLineUp;                 //锟角凤拷锟饺凤拷
	public int ifParticipate;                 //锟角凤拷锟斤拷锟�

	//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
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
	public int teamFault;                          //全锟斤拷失锟斤拷锟斤拷锟�    
	public int ifDouble;

	public Object clone() {  
        PlayerTechMPO mpo = null;  
        try{  
            mpo = (PlayerTechMPO)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return mpo;  
    }  
}
