package PO;
import java.io.Serializable;


public class TeamTechPO implements Serializable {
	
	/**
	 * �������������
	 */
	public static final long serialVersionUID = 1L;
	public int ifReagular;                         //是否是常规赛
	public int index ;                             //���
	public String name;                            //�������
	public String season;                          //����
	public int gameNum;                         //��������
	public int shotInNum;                       //Ͷ��������
	public int shotNum;                         //Ͷ��������
	public int threeShotInNum;                  //����������
	public int threeShotNum;                    //���ֳ�����
	public int penaltyShotInNum;                //����������
	public int penaltyShotNum;                  //���������
	public int offensiveRebound;                //ǰ��������
	public int defensiveRebound;                //��������
	public int rebound;                         //��������
	public int secondaryAttack;                 //������
	public int steal;                           //������
	public int blockShot;                       //��ñ��
	public int fault;                           //ʧ����
	public int foul;                            //������
	public int score;                           //�����÷�
	public double shotInRate;                      //Ͷ��������
	public double threeShotInRate;                 //����������
	public double penaltyShotInRate;               //����������
	public double winningRate;                     //ʤ��
	public int winningNum;                         //ʤ����
	public double offensiveRound;                  //�����غ�
	public double offensiveEfficiency;               //����Ч��
	public double defensiveEfficiency;               //����Ч��
	public double reboundEfficiency;                 //����Ч��
	public double stealEfficiency;                   //����Ч��
	public double secondaryAttackEfficiency;         //����Ч��
	public int opponentDefensiveRebound;          //�Է���������
	public int opponentOffensiveRebound;          //�Է���������
	public double opponentOffensiveRound;        //�Է������غ�
	public int opponentScore;        //�Է��÷�


	public boolean equals(TeamTechPO ttpo){
		if(!this.name.equals(ttpo.name)){
			System.out.println("name");
			return false;
		}
	    if(!this.season.equals(ttpo.season)){
	    	System.out.println("season");
	    	return false;
	    }
	    if(this.gameNum!=ttpo.gameNum){
	    	System.out.println("gameNum");
	    	return false;
	    }
	    if(this.shotInRate!=ttpo.shotInRate){
	    	System.out.println("shotInRate");
	    	return false;
	    }
	    if(this.threeShotInRate!=ttpo.threeShotInRate){
	    	System.out.println("threeShotInRate");
	    	return false;
	    }
	    if(this.penaltyShotInRate!=ttpo.penaltyShotInRate){
	    	System.out.println("penaltyShotInRate");
	    	return false;
	    }
	    if(this.winningRate!=ttpo.winningRate){
	    	System.out.println("winningRate");
	    	return false;
	    }
	    if(this.offensiveEfficiency!=ttpo.offensiveEfficiency){
	    	System.out.println("offensiveEfficiency");
	    	return false;
	    }
	    if(this.defensiveEfficiency!=ttpo.defensiveEfficiency){
	    	System.out.println("defensiveEfficiency");
	    	return false;
	    }
	    if(this.reboundEfficiency!=ttpo.reboundEfficiency){
	    	System.out.println("reboundEfficiency");
	    	return false;
	    }
	    if(this.stealEfficiency!=ttpo.stealEfficiency){
	    	System.out.println("stealEfficiency");
	    	return false;
	    }
	    if(this.secondaryAttackEfficiency!=ttpo.secondaryAttackEfficiency){
	    	System.out.println("secondaryAttackEfficiency");
	    	return false;
	    }
	    if(this.winningNum!=ttpo.winningNum){
	    	System.out.println("winningNum");
	    	return false;
	    }
	    if(this.shotInNum!=ttpo.shotInNum){
	    	System.out.println("shotInNum");
	    	return false;
	    }
	    if(this.shotNum!=ttpo.shotNum){
	    	System.out.println("shotNum");
	    	return false;
	    }
	    if(this.threeShotInNum!=ttpo.threeShotInNum){
	    	System.out.println("threeShotInNum");
	    	return false;
	    }
	    if(this.threeShotNum!=ttpo.threeShotNum){
	    	System.out.println("threeShotNum");
	    	return false;
	    }
	    if(this.penaltyShotInNum!=ttpo.penaltyShotInNum){
	    	System.out.println("penaltyShotInNum");
	    	return false;
	    }
	    if(this.penaltyShotNum!=ttpo.penaltyShotNum){
	    	System.out.println("penaltyShotNum");
	    	return false;
	    }
	    if(this.offensiveRebound!=ttpo.offensiveRebound){
	    	System.out.println("offensiveRebound");
	    	return false;
	    }
	    if(this.defensiveRebound!=ttpo.defensiveRebound){
	    	System.out.println("defensiveRebound");
	    	return false;
	    }
	    if(this.rebound!=ttpo.rebound){
	    	System.out.println("rebound");
	    	return false;
	    }
	    if(this.secondaryAttack!=ttpo.secondaryAttack){
	    	System.out.println("TeamTech");
	    	System.out.println("secondaryAttack");
	    	return false;
	    }
	    if(this.steal!=ttpo.steal){
	    	System.out.println("steal");
	    	return false;
	    }
	    if(this.blockShot!=ttpo.blockShot){
	    	System.out.println("blockShot");
	    	return false;
	    }
	    if(this.fault!=ttpo.fault){
	    	System.out.println("fault");
	    	return false;
	    }
	    if(this.foul!=ttpo.foul){
	    	System.out.println("foul");
	    	return false;
	    }
	    if(this.score!=ttpo.score){
	    	System.out.println("score");
	    	return false;
	    }
	    if(this.offensiveRound!=ttpo.offensiveRound){
	    	System.out.println("offensiveRound");
	    	return false;
	    }
	    return true;
	}
	
}
