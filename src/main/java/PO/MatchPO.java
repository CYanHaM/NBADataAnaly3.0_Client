package PO;
import java.io.Serializable;
import java.util.ArrayList;


public class MatchPO implements Serializable{
	
	/**
	 * 每锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public static final long serialVersionUID = 1L;
	public int ifRegular;                               //鏄惁鏄父瑙勮禌
	public int ifEnd;                                   //鏄惁缁撴潫
	public String season;                                 //锟斤拷锟斤拷
	public String date;                                   //锟斤拷锟斤拷
	public String homeTeam;                               //锟斤拷锟斤拷锟斤拷锟斤拷
	public String guestTeam;                              //锟酵筹拷锟斤拷锟斤拷
	public String score;                                  //锟饺凤拷
	public String score1;                                 //锟斤拷一锟节比凤拷
	public String score2;                                 //锟节讹拷锟节比凤拷
	public String score3;                                 //锟斤拷锟斤拷锟节比凤拷
	public String score4;                                 //锟斤拷锟侥节比凤拷
	public String scoreExtra;                             //锟斤拷时锟斤拷锟饺凤拷
	public ArrayList<PlayerTechMPO> playerStatistic;      //锟斤拷员锟斤拷锟斤拷统锟斤拷
	public String scoringChampion;                            //锟矫凤拷锟斤拷
	public String reboundChampion;                          //锟斤拷锟斤拷锟斤拷
	public String assistChampion;                      //锟斤拷锟斤拷锟斤拷 
	public int ifHomeTeamWin;                         //锟斤拷锟斤拷锟角凤拷胜锟斤拷
	public int ifGuestTeamWin;                        //锟酵讹拷锟角凤拷胜锟斤拷
	public int homeTeamDeffensiveRebound;              //锟斤拷锟接凤拷锟斤拷锟斤拷锟斤拷
	public int guestTeamDeffensiveRebound;              //锟酵队凤拷锟斤拷锟斤拷锟斤拷
	public int homeTeamOffensiveRebound;              //锟斤拷锟接斤拷锟斤拷锟斤拷锟斤拷
	public int guestTeamOffensiveRebound;              //锟酵队斤拷锟斤拷锟斤拷锟斤拷
	public double homeTeamOffensiveRound;              //锟斤拷锟接斤拷锟斤拷锟截猴拷
	public double guestTeamOffensiveRound;              //锟酵队斤拷锟斤拷锟截猴拷
	public int homeTeamFoul;                     //涓婚槦鐘
	public int guestTeamFoul;                     //瀹㈤槦鐘
	public int homeTeamSteal;                     //涓婚槦鎶㈡柇
	public int guestTeamSteal;                     //瀹㈤槦鎶㈡柇
	public int homeTeamSecondaryAttack;                     //涓婚槦鍔╂敾
	public int guestTeamSecondaryAttack;;                     //瀹㈤槦鍔╂敾
	public int homeTeamBlockShot;                     //涓婚槦鐩栧附
	public int guestTeamBlockShot;                     //瀹㈤槦鐩栧附
	public int homeScore;               //锟斤拷锟接得凤拷
	public int guestScore;               //锟酵队得凤拷
	public int homeAllTime;              //锟斤拷锟斤拷全员锟较筹拷时锟斤拷
	public int guestAllTime;             //锟酵讹拷全员锟较筹拷时锟斤拷
	public int homeShotIn;              //锟斤拷锟斤拷锟杰斤拷锟斤拷锟斤拷
	public int guestShotIn;              //锟酵讹拷锟杰斤拷锟斤拷锟斤拷
	public int homeShot;             //锟斤拷锟接筹拷锟街达拷锟斤拷
	public int guestShot;            //锟酵队筹拷锟街达拷锟斤拷
	public int homeTwoShot;             //锟斤拷锟斤拷锟斤拷锟街筹拷锟街达拷锟斤拷
	public int guestTwoShot;            //锟酵讹拷锟斤拷锟街筹拷锟街达拷锟斤拷
	public int homeTwoShotIn;             //锟斤拷锟斤拷锟斤拷锟街斤拷锟斤拷锟斤拷
	public int guestTwoShotIn;            //锟酵讹拷锟斤拷锟街斤拷锟斤拷锟斤拷
	public int homeThreeShot;             //锟斤拷锟斤拷锟斤拷锟街筹拷锟街达拷锟斤拷
	public int guestThreeShot;            //锟酵讹拷锟斤拷锟街筹拷锟街达拷锟斤拷
	public int homeThreeShotIn;             //锟斤拷锟斤拷锟斤拷锟街斤拷锟斤拷锟斤拷
	public int guestThreeShotIn;            //锟酵讹拷锟斤拷锟街斤拷锟斤拷锟斤拷
	public int homePenaltyShot;         //锟斤拷锟接凤拷锟斤拷锟斤拷锟�
	public int guestPenaltyShot;        //锟酵队凤拷锟斤拷锟斤拷锟�
	public int homePenaltyShotIn;         //锟斤拷锟接凤拷锟斤拷锟斤拷锟斤拷锟�
	public int guestPenaltyShotIn;        //锟酵队凤拷锟斤拷锟斤拷锟斤拷锟�
	public int homeFault;                //锟斤拷锟斤拷失锟斤拷锟斤拷锟�
	public int guestFault;               //锟酵讹拷失锟斤拷锟斤拷锟�

}
